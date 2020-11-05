package com.example.demoheroku.security;

import static com.example.demoheroku.security.SecurityConstants.CLAIMS_ROLE;
import static com.example.demoheroku.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.demoheroku.security.SecurityConstants.SECRET_KEY;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demoheroku.controller.request.UserRequest;
import com.example.demoheroku.exception.WrongUserOrPasswordException;
import com.example.demoheroku.model.User;
import com.example.demoheroku.service.UserServiceImplement;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// run to implement
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	public UserServiceImplement userServiceImplement;
	public long userID;
	public User users;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		// check POST "/auth/login" to do Authentication
		this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// requestbody mapping of POST("/auth/login)" are authen before access with this
			// method
			UserRequest userRequest = new ObjectMapper().readValue(request.getInputStream(), UserRequest.class);
//			System.out.println("response is: " + userServiceImplement.findUserByUsername(userRequest.getUsername()));
//			System.out.println("response is: " + response.toString());
//			System.out.println(userServiceImplement.findUserByUsername(userRequest.getUsername()));
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),
					userRequest.getPassword(), new ArrayList<>()));

		} catch (IOException ex) {
			throw new WrongUserOrPasswordException(ex);
		}

	}

	// when auth success
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		if (authResult.getPrincipal() != null) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult
					.getPrincipal(); // cast authen
			// jwt username, role
			System.out.println(authResult.getPrincipal());
			String username = user.getUsername();
			String userID = substringBefore(username, " ");
			// sent claims
			// claims is ...
			if (username != null && username.length() > 0) {
				Claims claims = Jwts.claims().setSubject(username).setIssuer("ad").setAudience("ad");
				List<String> roles = new ArrayList<>();
				user.getAuthorities().stream().forEach(authority -> {
					roles.add(authority.getAuthority());
				});
				claims.put(CLAIMS_ROLE, roles);
				claims.put("value", "value1");

				response.setContentType("appliction/json");
				response.setCharacterEncoding("UTF-8");

				Map<String, Object> responseJSON = new HashMap<>();
				responseJSON.put("token", createToken(claims));
				responseJSON.put("role", roles.get(0));
				responseJSON.put("user_id", userID);

				OutputStream out = response.getOutputStream();
				ObjectMapper mapper = new ObjectMapper();
				mapper.writerWithDefaultPrettyPrinter().writeValue(out, responseJSON);

				out.flush();
			}

		}

		super.successfulAuthentication(request, response, chain, authResult);
	}

	// keyV1 is key. you will change it to change token
	private String createToken(Claims claims) {
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public static String substringBefore(final String str, final String separator) {
		if (str.isEmpty() || separator == null) {
			return str;
		}
		if (separator.isEmpty()) {
			return str;
		}
		final int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}
}
