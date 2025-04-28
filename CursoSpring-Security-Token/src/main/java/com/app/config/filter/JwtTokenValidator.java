package com.app.config.filter;

import com.app.util.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

// IMPLEMENTAR EL OncePerRequestFilter 
public class JwtTokenValidator extends OncePerRequestFilter { 

	// INSTANCIAMOS O INJECTAMOS NUESTRO JWTUtils
	// AL NO PODER INJECTAR CON @Autowired LO HACEMOS POR CONSTRUCTOR, DAR CLICK DERECHO GENERAR METODO
    private JwtUtils jwtUtils; 					

    // CLICK DERECHO -> Source -> Generator Constructor 
	public JwtTokenValidator(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	// SE IMPLEMENTA EL METODO QUE TRAE EN JwtTokenValidator
    @Override					  // NO DEBE SERA NULL
    protected void doFilterInternal(@NonNull HttpServletRequest request, //SIEMPRE EJECUTA EL FILTRO OncePerRequestFilter POR CADA REQUEST
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION); // SINO ENVIO NINGUN TOKEN VA A filterChain Y POR DEFECTO RECHAZAMOS

        if (jwtToken != null) { // SI EL TOKEN VIENE ENTONCES VALIDA:
            jwtToken = jwtToken.substring(7); // EL 7 ES EL INDICE Y EXTRAE EL STRING O CARACTER APARTIR DEL INDEX 7

            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken); // SI EL TOKEN ES VALIDO CONCEDO EL ACCESO:

            String username = jwtUtils.extractUsername(decodedJWT); // DEVUELVE EL username Y OBTIENE LAS AUTIRIZACIONES: stringAuthorities
            String stringAuthorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString(); // DEVUELVE COMO UN STRING

            // DEFINIR COLLECTOR PARA AUTORIZACION O PERMISOS SEPARADOS POR COMA: READ, DELETE, WRITE, ETC
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

            // ACCESO
            SecurityContext context = SecurityContextHolder.getContext(); // createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities); // DECLARO AUTENTICATOR(USER, PASS, PERMISOS
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        } // SINO VIENE EL TOKEN CONTINUA CON EL SIGUIENTE FILTRO:
        filterChain.doFilter(request, response);
    }
    
}
