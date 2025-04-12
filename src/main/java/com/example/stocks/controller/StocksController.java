package com.example.stocks.controller;

import com.example.stocks.entity.Stocks;
import com.example.stocks.model.QuantityRequest;
import com.example.stocks.model.StockResponse;
import com.example.stocks.service.StocksService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableScheduling
public class StocksController {

    private final StocksService stocksService;

    StocksController(StocksService stocksService){
        this.stocksService=stocksService;
    }

    @GetMapping("stocks")
    public List<StockResponse> getAllStocks(){
        List<Stocks> stocks=stocksService.getAllStocks();
        return stocks.stream().map(StockResponse::build).toList();
    }

    @GetMapping("stocks/{id}")
    public StockResponse getById(@PathVariable String id){
        Stocks stocks=stocksService.getById(id);
        return StockResponse.build(stocks);
    }

    @Scheduled(fixedRate = 60000)
    @GetMapping(value="/update")
    public void update() {
        stocksService.updateStockPrices();
    }

    @PostMapping("/stocks/update-quantity")
    public String updateQuantity(@RequestBody QuantityRequest quantityRequest){
        stocksService.updateStocks(quantityRequest.getStockId(), quantityRequest.getQuantity(), quantityRequest.getType());
        return "Success";
    }
}
