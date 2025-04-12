package com.example.stocks.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuantityRequest {
    String stockId;
    Integer quantity;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
