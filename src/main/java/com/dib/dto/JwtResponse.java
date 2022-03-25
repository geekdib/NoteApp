package com.dib.dto;

import java.io.Serializable;

import lombok.ToString;

/**
 * @author Dibyaprakash
 * 
 * For parsing the JwtResponse
 */
@ToString
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}