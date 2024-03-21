package com.shop.soap_server.model.dto;

import com.shop.soap_server.model.PurchaseItem;
import com.shop.soap_server.util.LocalDateAdapter;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "PurchaseInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseInfoDto {
    @XmlElement(required = true)
    private String firstName;

    @XmlElement(required = true)
    private String lastName;
    @XmlElement(required = true)
    private int age;

    @XmlElement(required = true)
    private int count;

    @XmlElement(required = true)
    private PurchaseItem purchaseItem;

    @XmlElement(required = true)
    private BigDecimal amount;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate purchaseDate;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PurchaseItem getPurchaseItem() {
        return purchaseItem;
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

}
