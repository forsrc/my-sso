package org.forsrc.sso.entity;


import java.io.Serializable;

public class AuthorityPk implements Serializable {


	private static final long serialVersionUID = 7733797098284693690L;

	private String username;

    private String authority;
    
    public AuthorityPk() {
	}

	public AuthorityPk(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityPk{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
