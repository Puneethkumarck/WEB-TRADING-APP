package com.cassandrawebtrader.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrawebtrader.domain.SignalPerDay;

/**
 * @author puneethkumar
 * 
 * Repository to query signalperday 
 *
 */
public interface SignalPerDayRepository extends CassandraRepository<SignalPerDay> {

	@Query("select * from signalperday where signal_day = ?0 order by symbol")
	public List<SignalPerDay> findByDate(String day);

}
