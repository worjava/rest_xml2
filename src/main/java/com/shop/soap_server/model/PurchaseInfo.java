package com.shop.soap_server.model;


import com.shop.soap_server.util.LocalDateAdapter;

import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseInfo  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int count;
    @Column(name = "purchase_item")
    @Enumerated(EnumType.STRING)
    private PurchaseItem purchaseItem;
    private BigDecimal amount;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate purchaseDate;
    public PurchaseItem getPurchaseItem() {
        return purchaseItem;
    }
    public void setPurchaseItem(PurchaseItem purchaseItem) {
        this.purchaseItem = purchaseItem;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public PurchaseInfo(String firstName, String lastName, int age, int count, PurchaseItem purchaseItem, BigDecimal amount, LocalDate purchaseDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.count = count;
        this.purchaseItem = purchaseItem;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
    }

    public PurchaseInfo() {}

    @Override
    public String toString() {
        return "PurchaseInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", count=" + count +
                ", purchaseItem=" + purchaseItem +
                ", amount=" + amount +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseInfo that = (PurchaseInfo) o;
        return age == that.age && count == that.count && Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && purchaseItem == that.purchaseItem && Objects.equals(amount, that.amount) && Objects.equals(purchaseDate, that.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, count, purchaseItem, amount, purchaseDate);
    }

}
