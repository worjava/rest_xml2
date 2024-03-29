package com.shop.soap_server.model.dto.reports;

public class TopBuyerDto {
    private String name;
    private String lastName;
    private int salesCount;

    public TopBuyerDto(String name, String lastName, int salesCount) {
        this.name = name;
        this.lastName = lastName;
        this.salesCount = salesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }
}
