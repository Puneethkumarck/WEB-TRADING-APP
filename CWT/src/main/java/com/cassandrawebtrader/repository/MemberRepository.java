package com.cassandrawebtrader.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrawebtrader.domain.Member;

/**
 * @author puneethkumar
 * 
 * Repository to query member
 *
 */
public interface MemberRepository extends CassandraRepository<Member> {

	@Query("select * from member where username = ?0")
	public Member findByUsername(String username);

	@Query("select * from member where active = 'Y'")
	public List<Member> findByActive();
}
