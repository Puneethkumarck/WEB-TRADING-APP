package com.cassandrawebtrader.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author puneethkumar
 *
 */
public class Username {

	@NotEmpty
	@Size(min = 1, max = 20)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Username other = (Username) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Username [username=" + username + "]";
	}

	public Username(String username) {
		super();
		this.username = username;
	}

	public Username() {
		super();
		// TODO Auto-generated constructor stub
	}

}
