package com.app.service;

import com.app.controller.dto.AuthCreateUserRequest;
import com.app.controller.dto.AuthLoginRequest;
import com.app.controller.dto.AuthResponse;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.RoleRepository;
import com.app.persistence.repository.UserRepository;
import com.app.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
    @Autowired //<- INJECTA AUTOMANTICAMENTE UNA CLASE AQUI
    private JwtUtils jwtUtils; // INSTANCIAR JWT 
    
    @Autowired
    private PasswordEncoder passwordEncoder; // INSTANCIAR PasswordEncoder
    
    @Autowired
    private UserRepository userRepository; // INSTANCIAR USER REPPOSITORY
    
    @Autowired
    private RoleRepository roleRepository; // INSTANCIAR ROLE REPOSITORY
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // UTILIZAR API STREAMS PARA LISTAR, FILTRAR, MAPEAR, RECORRER Y PROCESAR LAS COLLECIONES
        //AGREGAR LOS R0LE A LA <LIST>
        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        // RECORRER CADA UNO DE LOS ROLES POR CADA PERMISOS
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream()) // TOMA LOS ROLES Y AGREGA PERMISOS PARA AUTORIZAR
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName()))); // SE LE PASA EL NOMBRE DEL PERMISO Y LISTO

        // RETORNAR // BUSQUE LOS USUARIOS EN LA BD, TOME PERMISOS Y ROLES, CONVIERTA A OBJETOS Y DEVOLVEMOS EL USUARIO
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }
    
    // METODO DEFINIR EL TOKEN DE ACCESO
    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) { // DEFINIR EL TOKEN DE ACCESO
    	// recupero el ususario y password
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password); // SI LAS CREDENCIALES SON CORRECTAS
        SecurityContextHolder.getContext().setAuthentication(authentication); // COLOCAR EL OBJETO AUTENTICADO

        String accessToken = jwtUtils.createToken(authentication); // CREA TOKEN
        
        AuthResponse authResponse = new AuthResponse(username, "User loged succesfully", accessToken, true); // DEVUELVE MENSAJE
        return authResponse;
    }
    
    // METODO PARA AUTENTICAR // SE ENCARGA QUE LAS CREDENCIALES SEAN CORRECTOS
    public Authentication authenticate(String username, String password) { // AUTENTICAR CON LAS DOS PARAMETORS
        UserDetails userDetails = this.loadUserByUsername(username); // BUSCA EL USUARIO EN LA BD SI EXISTE

        if (userDetails == null) { // SINO EXISTE EL USUARIO TIRA ERROR:
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) { // VALIDAR SI LA CONTRASEÑA NO ES CORRECTA QUE ENVIO Y LA QUE TENGO EN BD
            throw new BadCredentialsException("Incorrect Password"); // ERROR
        }
        // SI LA CONTRASEÑA ES CORRECTA DEVOLVEMOS EL TOKEN
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities()); // DEVUELVE LOS DATOS DEL TOKEN: USER, PASS,
        //return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
    
    // METODO PARA REGISTRAR USUARIO
    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {
    	// CUANDO CREAMOS USUARIO MANDO, USER, PASS, ROL TIENE QUE COINCIDIR CON LA BD
        String username = createRoleRequest.username(); // OBTIENE EL USUARIO
        String password = createRoleRequest.password(); // OBTIENE CONTRASEÑA
        List<String> rolesRequest = createRoleRequest.roleRequest().roleListName(); // OBTENER LA LISTA DE ROLES

        // VALIDAR QUE LOS ROLES QUE ESTAMOS ENVIANDO EN REQUEST SEAN LOS MISMOS ROLES QUE TENEMOS EN LA TABLA
        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest).stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty()) { // VALIDAR SI EL RESULT ES VACIO NO PODRA CREAR USUARIO
            throw new IllegalArgumentException("The roles specified does not exist.");
        }
        // SI EXISTE CONSTRUYE EL USUARIO
        UserEntity userEntity = UserEntity.builder()
        		.username(username)
        		.password(passwordEncoder.encode(password)) // PASS ENCRIPTRLO
        		.roles(roleEntityList).isEnabled(true).accountNoLocked(true).accountNoExpired(true).credentialNoExpired(true).build(); // VALIDAR ACTIVO,CUENTA, CREDENCIALES, NO ESTEN EXPIRADAS

        UserEntity userSaved = userRepository.save(userEntity); // DEFINIR GUARDAR

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>(); // LISTA PERMISOS
     
        // AUTORIZACIONES POR ROLES A LA LISTA
        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())))); // SIEMPRE QUE SEA LOS ROLES COLOCAR GUION BAJO_
        // AUTORIZACIONES POR PERMISOS A LA LISTA
        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));
      
        // DEFINIR ACCESO AL USUARIO
        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities); // DEFINIR AUTENTICACION

        // GENERAR EL TOKEN Y MANDAMOS OBJETO authentication
        String accessToken = jwtUtils.createToken(authentication);

        // ENVIAR UN RESPONSE AL CREAR USUARIO
        AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true); // ENVIAMOS RESPUESTA
        return authResponse;
    }

    
}
