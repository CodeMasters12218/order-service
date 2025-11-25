package com.example.order.domain.model;

public class OrderItem {
    private Integer id;
    private Integer productId;
    private String productName;
    private Double pricePaid;
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
