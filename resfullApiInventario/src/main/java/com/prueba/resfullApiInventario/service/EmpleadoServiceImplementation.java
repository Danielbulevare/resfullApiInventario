package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.config.JwtService;
import com.prueba.resfullApiInventario.controller.models.AuthResponse;
import com.prueba.resfullApiInventario.controller.models.AuthenticationRequest;
import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.error.EmailAlreadyExistsException;
import com.prueba.resfullApiInventario.error.EmployeeNotFoundException;
import com.prueba.resfullApiInventario.projection.classbased.EmployeeDataDTO;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.EmployeeDataClosedView;
import com.prueba.resfullApiInventario.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImplementation implements EmpleadoService{
    //Inject the repository
    @Autowired
    EmpleadoRepository empleadoRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<EmployeeDataClosedView> findBy() {
        return empleadoRepository.findBy();
    }

    @Override
    public Empleado saveEmployee(Empleado empleado) throws EmailAlreadyExistsException {

        try {
            //Encripta la contraseña
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
            return empleadoRepository.save(empleado);
        }catch (DataIntegrityViolationException e){
            throw new EmailAlreadyExistsException("El correo electrónico ya está registrado.");
        }
    }

    @Override
    public Empleado updateEmployee(Long id, Empleado empleado) {
        Empleado empleadoDb = empleadoRepository.findById(id).get();

        if (Objects.nonNull(empleado.getMail()) && !"".equalsIgnoreCase(empleado.getMail())){
            empleadoDb.setMail(empleado.getMail());
        }

        if (Objects.nonNull(empleado.getName()) && !"".equalsIgnoreCase(empleado.getName())){
            empleadoDb.setName(empleado.getName());
        }

        if (Objects.nonNull(empleado.getPassword()) && !"".equalsIgnoreCase(empleado.getPassword())){
            //Encripta la contraseña
            empleadoDb.setPassword(passwordEncoder.encode(empleado.getPassword()));
        }

        if (Objects.nonNull(empleado.getStatus())){
            empleadoDb.setStatus(empleado.getStatus());
        }

        if(Objects.nonNull(empleado.getRole())){
            empleadoDb.setRole(empleado.getRole());
        }

        return empleadoRepository.save(empleadoDb);
    }

    @Override
    public void deleteEmployee(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Optional<EmployeeDataClosedView> findEmployeeByNameWithJPQL(String name) {
        return empleadoRepository.findEmployeeByNameWithJPQL(name);
    }

    @Override
    public Optional<EmployeeDataClosedView> findByName(String name) {
        return empleadoRepository.findByName(name);
    }

    @Override
    public Optional<EmployeeDataClosedView> findByNameIgnoreCase(String name) {
        return empleadoRepository.findByNameIgnoreCase(name);
    }

    @Override
    public EmployeeDataDTO findEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        //Verifica si devuelve un valor nulo
        if (!empleado.isPresent()){
            throw new EmployeeNotFoundException("El empleado no existe");
        }

        EmployeeDataDTO employeeDataDTO = new EmployeeDataDTO(empleado.get().getId(),
                empleado.get().getName(),
                empleado.get().getStatus().getId(),
                empleado.get().getStatus().getStatus(),
                empleado.get().getRole().getId(),
                empleado.get().getRole().getRole());

        return employeeDataDTO;
    }

    //Application of methods
    @Override
    public Optional<EmployeeDataClosedView> findByMailAndPassword(String mail, String password) throws EmployeeNotFoundException {
        Optional<EmployeeDataClosedView> empleado = empleadoRepository.findByMailAndPassword(mail, password);

        if(!empleado.isPresent()){
            throw new EmployeeNotFoundException("El empleado no existe.");
        }

        return empleado;
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        /*
        Si se autentica, quiere decir que el jwt fue correcto
         */
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),request.getPassword()
                )
        );

        /*
        Volvemos a buscar el mail en la BD.
        Tomamos ese mail directamente de la BD haber si es que loe encuentro para construir el token
        para poderle darle permisos al cliente que acaba de enviar la petición.
        No es para nada seguro tomar mail del request para poder construir el token
         */
        Empleado employee = empleadoRepository.findEmployeeByMail(request.getMail()).orElseThrow();

        //Se construye el jwt para darle acceso a nuestra aplicación
        var jwtToken = jwtService.generateToken(employee);

        return AuthResponse.builder().token(jwtToken).build();
    }
}
