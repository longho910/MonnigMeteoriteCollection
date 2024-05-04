package edu.tcu.cs.monnigmeteoritecollection.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfiguration {

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    @Value("${api.endpoint.base-url}")
    private String baseUrl;

    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    private final CustomBearerTokenAuthenticationEntryPoint customBearerTokenAuthenticationEntryPoint;

    private final CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler;

    public SecurityConfiguration(CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint, CustomBearerTokenAuthenticationEntryPoint customBearerTokenAuthenticationEntryPoint, CustomBearerTokenAccessDeniedHandler customBearerTokenAccessDeniedHandler) throws NoSuchAlgorithmException {
        this.customBasicAuthenticationEntryPoint = customBasicAuthenticationEntryPoint;  //generate a public/private keypair
        this.customBearerTokenAuthenticationEntryPoint = customBearerTokenAuthenticationEntryPoint;
        this.customBearerTokenAccessDeniedHandler = customBearerTokenAccessDeniedHandler;


        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // The generated key will have a size of 2048 bits.
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                    // users
                .requestMatchers(HttpMethod.GET, this.baseUrl + "/users/**").hasAuthority("ROLE_curator") // Protect the endpoint.
                .requestMatchers(HttpMethod.POST, this.baseUrl + "/users").hasAuthority("ROLE_curator") // Protect the endpoint.
                .requestMatchers(HttpMethod.PUT, this.baseUrl + "/users/**").hasAuthority("ROLE_curator") // Protect the endpoint.
                .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/users/**").hasAuthority("ROLE_curator") // Protect the endpoint.

                // Loan endpoints - PROTECTED
                // .requestMatchers(HttpMethod.GET, this.baseUrl + "/loans/**").permitAll()
                // .requestMatchers(HttpMethod.GET, this.baseUrl + "/loans/**").hasAuthority("ROLE_curator")
                // .requestMatchers(HttpMethod.PUT, this.baseUrl + "/loans/**").hasAuthority("ROLE_curator")
                // .requestMatchers(HttpMethod.POST, this.baseUrl + "/loans/**").hasAuthority("ROLE_curator")
                // .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/loans/**").hasAuthority("ROLE_curator")
                // .requestMatchers(HttpMethod.GET, this.baseUrl + "/loans/archived").hasAuthority("ROLE_curator")


                // Loan endpoints - remove protection
                .requestMatchers(HttpMethod.GET, this.baseUrl + "/loans/**").permitAll()
                .requestMatchers(HttpMethod.PUT, this.baseUrl + "/loans/**").permitAll()
                .requestMatchers(HttpMethod.POST, this.baseUrl + "/loans/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/loans/**").permitAll()

                // Meteorite endpoints
                .requestMatchers(HttpMethod.GET, this.baseUrl + "/meteorites/onloan").hasAuthority("ROLE_curator") // protect onloans endpoint
                .requestMatchers(HttpMethod.GET, this.baseUrl + "/meteorites").permitAll()
                .requestMatchers(HttpMethod.GET, this.baseUrl + "/meteorites/**").permitAll()
                .requestMatchers(HttpMethod.POST, this.baseUrl + "/meteorites/search").permitAll()
                .requestMatchers(HttpMethod.PUT, this.baseUrl + "/meteorites/**").hasAuthority("ROLE_curator")
                .requestMatchers(HttpMethod.POST, this.baseUrl + "/meteorites/**").hasAuthority("ROLE_curator")
                .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/meteorites/**").hasAuthority("ROLE_curator")

                // SampleHistory endpoints
                // .requestMatchers(HttpMethod.GET, this.baseUrl + "/histories/**").permitAll()
                // .requestMatchers(HttpMethod.PUT, this.baseUrl + "/histories/**").hasAuthority("ROLE_curator")
                // .requestMatchers(HttpMethod.POST, this.baseUrl + "/histories/**").hasAuthority("ROLE_curator")
                // .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/histories/**").hasAuthority("ROLE_curator")

                // History endpoints, remove protection
                .requestMatchers(HttpMethod.GET, this.baseUrl + "/histories/**").permitAll()
                .requestMatchers(HttpMethod.PUT, this.baseUrl + "/histories/**").permitAll()
                .requestMatchers(HttpMethod.POST, this.baseUrl + "/histories/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/histories/**").permitAll()

                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll() //makes H2 public

                //disallow anything else(must be authenticated)
                .anyRequest().authenticated() // Always a good idea to put this as last.
            )
            .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) // This is for H2 browser console access.
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults()) //enabiling cors for spring security
            .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.customBasicAuthenticationEntryPoint))
            .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                .jwt(Customizer.withDefaults())
                .authenticationEntryPoint(this.customBearerTokenAuthenticationEntryPoint)
                .accessDeniedHandler(this.customBearerTokenAccessDeniedHandler))
            /* Configures the spring boot application as an OAuth2 Resource Server which authenticates all
                the incoming requests (except the ones excluded above) using JWT authentication.
                */
            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // turn off session
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwkSet = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        /*
        Let’s say that that your authorization server communicates authorities in a custom claim called "authorities".
        In that case, you can configure the claim that JwtAuthenticationConverter should inspect, like so:
         */
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        /*
        You can also configure the authority prefix to be different as well. The default one is "SCOPE_".
        In this project, you need to change it to empty, that is, no prefix!
         */
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter; // use this converter to convert json web token to spring security authentication
    }
}