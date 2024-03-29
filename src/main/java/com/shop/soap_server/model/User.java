package com.shop.soap_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Objects;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;
    private Integer age;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @XmlTransient
    private List<PurchaseInfo> purchaseInfo;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<PurchaseInfo> getPurchaseInfo() {
        return purchaseInfo;
    }

    public void setPurchaseInfo(List<PurchaseInfo> purchaseInfo) {
        this.purchaseInfo = purchaseInfo;
    }


    public User(String firstName, String lastName, Integer age, List<PurchaseInfo> purchaseInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.purchaseInfo = purchaseInfo;
    }

    public User(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(age, user.age) && Objects.equals(purchaseInfo, user.purchaseInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, purchaseInfo);
    }
}