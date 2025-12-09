package com.example.order.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents an item included in an order.")
public class OrderItem {
    @Schema(description = "Unique identifier of the order item", example = "1")
    private Integer id;

    @Schema(description = "Identifier of the product", example = "200")
    private Integer productId;

    @Schema(description = "Name of the product", example = "Wireless Mouse")
    private String productName;

    @Schema(description = "Price paid for the product", example = "29.99")
    private Double pricePaid;

    @Schema(description = "Quantity of the product ordered", example = "2")
    private Integer quantity;

    public OrderItem() {

    }
    
    public OrderItem(Integer id, Integer productId, String productName, Double pricePaid, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.pricePaid = pricePaid;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    

}
