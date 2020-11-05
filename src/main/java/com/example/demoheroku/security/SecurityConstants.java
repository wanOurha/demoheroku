package com.example.demoheroku.security;

public interface SecurityConstants {
	String SECRET_KEY = "seCret1";// ket to encode
	String TOKEN_PREFIX = "Bearer "; // Auth type
	String HEADER_AUTHORIZATION = "Authorization"; // Auth Header
	String CLAIMS_ROLE = "role";
	long EXPIRATION_TIME = (5 * 60 * 1000);// 1 min
}
