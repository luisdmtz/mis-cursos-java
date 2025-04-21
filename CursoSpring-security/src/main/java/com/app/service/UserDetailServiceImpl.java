package com.app.service;

import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // INSTANCIAR EL REPPOSITORY
    
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
}
