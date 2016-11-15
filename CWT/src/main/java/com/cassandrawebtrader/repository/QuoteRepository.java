package com.cassandrawebtrader.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrawebtrader.domain.Quote;

/**
 * @author puneethkumar
 * 
 * Repository to query stockquote
 *
 */
public interface QuoteRepository extends CassandraRepository<Quote> {

	@Query("select * from stockquote where symbol = ?0 order by price_time desc limit 1")
	public Quote findLastBySymbol(String symbol);

	@Query("select * from stockquote where symbol = ?0 order by price_time desc limit ?1")
	public List<Quote> findLastNBySymbol(String symbol, Integer count);

}
