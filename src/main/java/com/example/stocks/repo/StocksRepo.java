package com.example.stocks.repo;

import com.example.stocks.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StocksRepo extends JpaRepository<Stocks, String> {

//    String GET_ALL_STOCKS="SELECT * from Stocks";
//
//    @Query(GET_ALL_STOCKS)
//    List<Stocks> getAllStocks();

}
