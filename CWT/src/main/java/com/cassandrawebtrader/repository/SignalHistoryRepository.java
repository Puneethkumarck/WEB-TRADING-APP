package com.cassandrawebtrader.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.cassandrawebtrader.domain.SignalHistory;

/**
 * @author puneethkumar
 * 
 * Repository to query signalHistory
 *
 */
public interface SignalHistoryRepository extends CassandraRepository<SignalHistory> {

	@Query("select * from signalhistory where symbol = ?0 order by signal_time desc")
	public List<SignalHistory> findBySymbol(String symbol);

}
