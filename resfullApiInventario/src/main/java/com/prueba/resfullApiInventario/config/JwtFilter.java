package com.prueba.resfullApiInventario.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        /*
        Este método recibre tres parámetros:
                > request: Es lo que el cliente nos envia en la petición http. Contiene varios elementos
                internos que nosotros vamos a necesitar manipular para llevar a cabo el filtrado.
                > response: Es lo que nosotros le vamos a entregar al cliente una vez procesada su
                solicitud.
                > filterChain: Es un elemento que nos permitirá continuar con el proceso de la solicitud
                una vez que hayamos filtrado.
         */

        //Header de autorización que nosotros vamos a necesitar
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail; //Lo usamos como el usuario para poder atenticarse dentro de nuestro sistema

        //Válida si nuestro authHeader es nulo o si nuestro authHeader no empieza con "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            /*
            Verificamos que el cliente nos este mandando un header de autorización (jwt). Si es nulo,
            retorna (termina) y devuelve un estatus 403, ya que no esta enviado las credenciales
            de acceso al sistema.

            Si el cliente me envia unas credenciales, pero estas credenciales no empiezan con Bearer
            (porque el tipo de autenticación que estoy utilizando en el token es Bearer), de igual forma
            regresare un estatus 403 y no te voy a permitir autenticarte.
             */

            filterChain.doFilter(request, response);
            return;
        }

        /*
        Si paso el anterior filtro, recupera el jwt que se encuentra a partir de la posición 7, ya que
        "Bearer dwqe3e2..."
        */
        jwt = authHeader.substring(7);
        userEmail = jwtService.getUserName(jwt); //Extraemos en mail del jwt

        /*
        Si el usuario es diferente de nulo y el contexto de seguridad también es nulo, sigue con
        el proceso de autenticación.

        SecurityContextHolder.getContext().getAuthentication() == null, significa que, en esta petición
        no estés autenticado, porque seria perdida de tiempo, ya que si ya estás autenticado, continuar con
        el proceso de autenticación nuevamente.
         */
        if (userEmail !=  null && SecurityContextHolder.getContext().getAuthentication() == null){

            /*
            this.userDetailsService.loadUserByUsername(userEmail), lo que hace es ir la la bd y verificar
            que mediante el mail, este usuario realmente exista dentro del sistema
             */
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            /*
            Valida si el token es auténtico para continuar con el proceso, de lo contrario retonamos
            un estatus 403.
             */
            if(jwtService.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities() //Los permisos que el empleado tiene
                );

                //Creamos los detalles de nuestra petición, los cuales están dentro del request
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                /*
                Actualizamos el security context holder.

                Obtenemos el contexto y le colocamos la autenticación que es el authenticationToken.
                authenticationToken, este objeto va cargado con el token y con los detalles del usuario
                y con los permisos que el usuario ya tiene.
                 */
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
