package com.example.stocks.service;

import com.example.stocks.entity.Stocks;
import com.example.stocks.kafkaConfig.Producer;
import com.example.stocks.repo.StocksRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@Slf4j
public class StocksService {

    private final StocksRepo stocksRepo;
    private final Producer producer;

    StocksService(StocksRepo stocksRepo, Producer producer) {
        this.stocksRepo=stocksRepo;
        this.producer=producer;
    }

    public List<Stocks> getAllStocks(){
        return stocksRepo.findAll();
    }

    public Stocks getById(String id){
        return stocksRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Stock not found"));
    }

    public void updateStockPrices() {
        List<Stocks> stocks=stocksRepo.findAll();
        stocks.forEach(s->s.setPrice(BigDecimal.valueOf(Math.random()*100)));
        stocks.forEach(s->s.setBlockId(getSaltString()));
        producer.sendMessage("NotificationService", stocks.toString());
        stocksRepo.saveAll(stocks);
    }

    public void updateStocks(String id, Integer quantity, String type) {
        Stocks stocks=stocksRepo.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Stock not found"));

        if(type.equals("credit")){
            stocks.setTotalQuantity(stocks.getTotalQuantity() + quantity);
        } else if(type.equals("debit")) {
            if(quantity>stocks.getTotalQuantity()){
                throw new IllegalArgumentException("Quantity is greater than available stock");
            }
            stocks.setTotalQuantity(stocks.getTotalQuantity() - quantity);
        } else {
            throw new IllegalArgumentException("Unrecognized type");

        }
        stocksRepo.save(stocks);
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 7) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
