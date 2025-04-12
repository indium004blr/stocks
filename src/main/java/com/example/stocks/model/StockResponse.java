package com.example.stocks.model;

import com.example.stocks.entity.Stocks;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder

public class StockResponse {
    private String id;
    private String name;
    private BigDecimal price;
    private Integer totalQuantity;
    private String blockId;

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public StockResponse(String id, String name, BigDecimal price, Integer totalQuantity, String blockId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.blockId=blockId;
    }

    public static StockResponse build(Stocks stocks){
        return new StockResponse(stocks.getId(), stocks.getName(), stocks.getPrice(), stocks.getTotalQuantity(), stocks.getBlockId());

    }
}
