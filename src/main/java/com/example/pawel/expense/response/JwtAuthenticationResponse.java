package com.example.pawel.expense.response;

public class JwtAuthenticationResponse {
	private String accesToken;
	private String tokenType = "Bearer";
	
	public JwtAuthenticationResponse(String accesToken) {
		this.accesToken = accesToken;
		
	}

	public String getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
