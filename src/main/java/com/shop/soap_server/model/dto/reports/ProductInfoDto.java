package com.shop.soap_server.model.dto.reports;

public class ProductInfoDto {
    private String productName;
    private Integer salesCount;

    // Конструктор
  public ProductInfoDto(){}

    public ProductInfoDto(String productName, Integer salesCount) {
        this.productName = productName;
        this.salesCount = salesCount;
    }

    // Геттеры и сеттеры
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }
}
