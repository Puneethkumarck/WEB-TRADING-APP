package com.cassandrawebtrader.dto;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cassandrawebtrader.domain.Member;

/**
 * @author puneethkumar
 *
 */
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5071381211044889237L;
	
	private Member member;
	
	public UserDetailsImpl(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(1);
		authorities.add(new SimpleGrantedAuthority(member.getRole()));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
