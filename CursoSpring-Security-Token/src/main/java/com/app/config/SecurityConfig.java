package com.app.config;

import com.app.config.filter.JwtTokenValidator;
import com.app.service.UserDetailServiceImpl;
import com.app.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { // configuracion de seguridad

	@Autowired
	private JwtUtils jwtUtils; // IMPLEMENTAMOS NUESTRA INSTANCIA JwtUtils
	
    @Bean   // 1.- SECURITY FILTER CHAN - FILTROS DE SEGURIDAD
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { // EL HttpSecurity PASA POR MEDIO DE LOS FILTROS
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilitamos la proteccion  
                .httpBasic(Customizer.withDefaults()) // httpBasic - AUTENTICAR POR DEFECTO SOLO X USER Y PASS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // trabajar sesion sin estado, no vamos a guardar una sesion en memoria, depende del tiempo definido de la sesion del token
                .authorizeHttpRequests(http -> {
                    // Configurar los endpoints publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll(); // ACCESO A TODOS PUBLICO
                    
                    // Configurar los endpoints privados - POR->ROL: ADMIN, DEVELOPER
                    http.requestMatchers(HttpMethod.POST, "/method/post").hasAnyRole("ADMIN", "DEVELOPER"); // ACCESO POR ROL EJEMPLO: "ADMIN", "DEVELOPER"   
                    http.requestMatchers(HttpMethod.GET, "/method/get").hasAnyRole("INVITED"); //ACCESO POR ROL: EJEMPLO USER, INVITED
                    http.requestMatchers(HttpMethod.GET, "/method/hello").hasAnyRole("USER"); //ACCESO POR ROL: EJEMPLO USER, INVITED

                    // Configurar los endpoints privados - POR->PERMISOS: REFACTOR, READ, CREATE, UPDATE
                    http.requestMatchers(HttpMethod.PATCH, "/method/patch").hasAuthority("REFACTOR"); // ACCESO POR PERMISOS EJEMPLO: "REFACTOR"
                    http.requestMatchers(HttpMethod.POST, "/method/postper").hasAnyAuthority("CREATE","READ"); //ACCESO POR PERMISOS, EJEMPLO: UPDATE
                    
                    // Configurar el resto de endpoint - NO ESPECIFICADOS, DENEGA ACCESO
                    http.anyRequest().denyAll(); // RECHAZA TODO LO QUE NO ESPECIFIQUE
                    	
                    // http.anyRequest().authenticated(); // ACEPTA AUN QUE NO SE ESPECIFIQUE
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class) //ES Before: XK NECESITAMOS EJECUTAR ESTE FILTRO ANTES QUE TERMINE LA AUTENTICACION
                .build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }

    @Bean  // 2.- AUTENTICATION MANAGER - VALIDA LAS INFORMACION DEL USUARIO
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean  // 3.- AUTENTICATION PROVIDER - PROVEEDOR DE BASE DE DATOS
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // - NECESITA DOS COMPONENTES PASSWORD Y USER PARA VALIDAR AL LLAMADO DE BD
        provider.setPasswordEncoder(passwordEncoder()); // password
        provider.setUserDetailsService(userDetailService); // user
        return provider;
    }

    @Bean  // 4.- DEFINIR EL COMPORTAMIENTO DE PASWORD CODIFICADO
    public PasswordEncoder passwordEncoder(){ 
    	return new BCryptPasswordEncoder();  // ENCRIPTA LAS CONTRASEÑAS
    		//return NoOpPasswordEncoder.getInstance(); // VA RETORNAR YA SEA ENCRIPTADO O NO
    }
    
    
    
    // METODO PARA ENCRIPTAR LA CONTRASEÑA
    public static void main(String[] args) {
    	System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }

    
    
    
    
//    @Bean  // SIMULAMOS UNA BASE DE DATOS
//    public UserDetailsService userDetailsService() {
//    	List<UserDetails> userDetails = new ArrayList<>();
//    	
//    	userDetails.add(User.withUsername("Luis")
//    			.password("123456")
//    			.roles("ADMIN")
//    			.authorities("READ","CREATE")
//    			.build());
//    	
//    	userDetails.add(User.withUsername("Luis")
//    			.password("123456")
//    			.roles("ADMIN")
//    			.authorities("READ","CREATE")
//    			.build());
//    	
//    	return new InMemoryUserDetailsManager(userDetails);
//    }
    
    
}
