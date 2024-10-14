package com.prueba.resfullApiInventario.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    /*
    SECRET_KEY, es una clave que sirve para firmar y verificar que los tokens que estamos generando
    (que el jwt se auténtico)
     */
    private static final String SECRET_KEY = "xihca36yzw5eevtg88efd9fjt6pq6vevezjuh2tk360xuc7v5j1rvmdzx9223r0etgu59kvkay5xp6r3uz3ijacq10pm4kgnfwcwac29t1fnqzbny7c2jiadbafzrxkkik9wtxq91yg0xevww2hfrcpa896ywrtmyrj600nrpe6h0y747qdvqrhr2q619tkzfqeutiupeb98rk3cptqgj8cv9h96t222n9jt8vb78bm33ppwdku8xqhhcxc47nnyi5rx6gjepbjzgh7ktytxggar46f5md3w4y8yc3mkm07q";

    public String generateToken(UserDetails userDetails){
        /*Este método entrega un token al cliente para poder permitirle que acceda a lo recursosde
        nuestra aplicación
         */
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis())) //Fecha creación del token

                /*
                Fecha en que se genera el token. Si nos envian el mismo token despues de esas 24 hrs,
                retornaremos un 403
                 */
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *24 ))

                //Colocamos el algoritmo de firma para obtener decodificado la secret key
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserName(String token) {
        /*
        El método getSubject, sirve para obtener el nombre del usuario del token que se esta enviando
         */
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        //Convertimos nuestra clave secreta en base 64
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        //Creamos y retornamos una nueva firma HMAC
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserName(token);

        /*
        Verifica que sea igual al usuario que estamos extrayendo del token y que no haya expirado
        el token
         */
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}
