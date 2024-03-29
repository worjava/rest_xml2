package com.shop.soap_server.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shop.soap_server.util.LocalDateAdapter;

import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(cascade =  CascadeType.REMOVE)
    private User user;
    private int count;
    @Column(name = "purchase_item")
    @Enumerated(EnumType.STRING)
    private PurchaseItem purchaseItem;
    private BigDecimal amount;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate purchaseDate;

    public PurchaseInfo(User user, int count, PurchaseItem purchaseItem, BigDecimal amount, LocalDate purchaseDate) {
        this.user = user;
        this.count = count;
        this.purchaseItem = purchaseItem;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PurchaseItem getPurchaseItem() {
        return purchaseItem;
    }

    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public PurchaseInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseInfo that = (PurchaseInfo) o;
        return count == that.count && Objects.equals(id, that.id) && Objects.equals(user, that.user) && purchaseItem == that.purchaseItem && Objects.equals(amount, that.amount) && Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, count, purchaseItem, amount, purchaseDate);
    }
}