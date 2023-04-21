package com.algaworks.algafood.core.security.authorizationserver;

import java.security.KeyStore;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception{
		
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
				new OAuth2AuthorizationServerConfigurer();
		
		
		
		RequestMatcher endpointsMatcher = authorizationServerConfigurer
				.getEndpointsMatcher();

		authorizationServerConfigurer.authorizationEndpoint(customizer -> customizer.consentPage("/oauth2/consent"));
		
		http
			.securityMatcher(endpointsMatcher)
			.authorizeHttpRequests(authorize ->
				authorize.anyRequest().authenticated()
			)
			.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
			.formLogin(Customizer.withDefaults())
			.exceptionHandling(exceptions -> {
				exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
			})
			.apply(authorizationServerConfigurer);
		 return http.formLogin(customizer -> customizer.loginPage("/login")).build();
	}
	
	@Bean
	public AuthorizationServerSettings providerSettings(AlgaFoodSecurityProperties properties) {
		return AuthorizationServerSettings.builder()
				.issuer(properties.getProviderUrl())
				.build();
	}
	
	@Bean
	public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder, 
			JdbcOperations JdbcOperations) {
		
		return new JdbcRegisteredClientRepository(JdbcOperations);
	}
	
	@Bean
	public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations,
			RegisteredClientRepository registeredClientRepository) {
		
		return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
	}
	
	@Bean
	public JWKSource<SecurityContext> jwkSource(JwtKeyStoreProperties properties) throws Exception{
		char[] keyStorePass = properties.getPassword().toCharArray();
		var keyPairAlias = properties.getKeypairAlias();
		
		var jksLocation = properties.getJksLocation();
		var inputStream = jksLocation.getInputStream();
		var keyStore = KeyStore.getInstance("JKS");
		keyStore.load(inputStream, keyStorePass);
		
		var rsaKey = RSAKey.load(keyStore, keyPairAlias, keyStorePass);
		
		return new ImmutableJWKSet<>(new JWKSet(rsaKey));
		
	}
	
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(UsuarioRepository usuarioRepository){
		return context -> {
			var authentication = context.getPrincipal();
			if(authentication instanceof User) {
				var user = (User) authentication.getPrincipal();
				
				var uruario = usuarioRepository.findByEmail(user.getUsername().toString()).orElseThrow();
				
				Set<String> authorities = new HashSet<>();
				
				for (GrantedAuthority authority : user.getAuthorities()) {
					authorities.add(authority.getAuthority());
				}
				context.getClaims().claim("usuario_id", uruario.getId().toString());
				context.getClaims().claim("authorities", authorities);
			}
		};	
		
	}
	
	@Bean
	public OAuth2AuthorizationConsentService consentService(JdbcOperations jdbcOperations, RegisteredClientRepository clientRepository) {
		
		return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, clientRepository);
	}
	
	@Bean
	public OAuth2AuthorizationQueryService auth2AuthorizationQueryService(
			JdbcOperations jdbcOperations,
			RegisteredClientRepository repository
			) {
		return new JdbcOAuth2AuthorizationQueryService(jdbcOperations, repository);
	}
}
