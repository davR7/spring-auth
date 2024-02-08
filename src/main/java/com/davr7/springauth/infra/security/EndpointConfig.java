package com.davr7.springauth.infra.security;

public class EndpointConfig {
	
	public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
		"/auth/signin",
		"/auth/signup"
	};
}
