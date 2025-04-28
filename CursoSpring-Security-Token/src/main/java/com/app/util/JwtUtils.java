package com.app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    // METODO CREA TOKEN
    public String createToken(Authentication authentication) { // CREAR UNA CLAVE PARA GENERAR EL TOKEN
    	
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey); // DEFINIR EL ARGORITO DE ENCRIPTACION ES EL: HMAC256

        String username = authentication.getPrincipal().toString(); /// OBTENEMOS EL USERNAME
        String authorities = authentication.getAuthorities() // OBTIENE TODAS LAS AUTORIZACIONES O PERMISOS: READ, WRITE, CREATE, DELETE
                .stream()
                .map(GrantedAuthority::getAuthority) // ES LO MISMO: grantedAuthority -> getAuthority // LO DEVUELVE CON UN STRING
                .collect(Collectors.joining(",")); // EL VCollectors.joining VA TOMAR CADA UNO DE LOS PERMISOS POR COMA

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator) //USUARIO QUE GENERA EL TOKEN
                .withSubject(username) // SUJETO QUE SE LE GENERO EL TOKEN ES EL USERNAME
                .withClaim("authorities", authorities) // GENERAR CLEAMS ES EL PAYLOAD 
                .withIssuedAt(new Date()) // FECHA EN LA QUE SE GENERA EL TOKEN
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) // MOMENTO QUE EXPIRA EL TOKEN EN MILISEGUNDOS // COLOCAR DE MILI A MINUTOS // EN 30 MINUTOS
                .withJWTId(UUID.randomUUID().toString()) // ASIGNAR UN ID AL TOKEN ES TIPO randomUUID PARA QUE CADA TOKEN TENGA UN ID DISTINTO
                .withNotBefore(new Date(System.currentTimeMillis())) // DEFINIR EN QUE MOMENTO SEA VALIDO EL TOKEN
                .sign(algorithm); // COLOCAR LA FIRMA O KEY
        return jwtToken;
    }

    // VALIDA EL TOKEN
    public DecodedJWT validateToken(String token) { // DEVUELVE EL JWT DECODIFICADO //RECIBIMOS TOKEN
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey); // NECESITAMOS EL QUE GENERA EL TOKEN JUNTO CON EL QUE DECODIFICA EL TOKEN EL ALGORITMO AGORITO

            JWTVerifier verifier = JWT.require(algorithm) // VERIFICADOR DEL TOKEN
                    .withIssuer(this.userGenerator)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token); // SI SALE CORRECTO DEVUELVE EL TOKEN O DE CONTGRARIO EXCEPCION
            return decodedJWT; // DEVIUELVE TOKEN DECODIFICADO
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalid, not Authorized");
        }
    }

    // METODO PARA EXTRAER EL USUARIO QUE VIENE DENTRO DEL TOKEN
    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString(); // NECESITO EL TOKEN CODIFICADO PARA ECTRAER EL USUARIO
    }

    // EXTRAER EL USERNAME DEL CLEAM - ES EL CIERPO DEL PAYLOAD - SON LOS DATOS DEL USUARIO
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName); 
    }
    // METODO QUE EXTRAE TODOS LOS CLAIM
    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT){
    	return decodedJWT.getClaims();
    }
    
}
