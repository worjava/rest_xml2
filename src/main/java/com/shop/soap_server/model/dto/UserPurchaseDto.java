package com.shop.soap_server.model.dto;

import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.User;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "UserPurchase")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserPurchaseDto {

    @XmlElement(name = "User")
    private UserDto user;

    @XmlElement(name = "PurchaseInfo")
    private PurchaseInfoDto purchaseInfo;

    @XmlElement(name = "users")
    private List<User> users;

    @XmlElement(name = "purchases")
    private List<PurchaseInfo> purchases;


    public UserPurchaseDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PurchaseInfoDto getPurchaseInfo() {
        return purchaseInfo;
    }

    public void setPurchaseInfo(PurchaseInfoDto purchaseInfo) {
        this.purchaseInfo = purchaseInfo;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<PurchaseInfo> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseInfo> purchases) {
        this.purchases = purchases;
    }
}