package com.shop.soap_server.model.wrapperXML;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.soap_server.model.PurchaseInfo;
import com.shop.soap_server.model.User;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "PurchasesUsers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchasesUsersWrapper {

    @XmlElementWrapper(name = "Users")
    @XmlElement(name = "User")
    private List<User> userList;
    @XmlElement
    private PurchaseInfo purchaseInfo;
    @XmlElementWrapper(name = "Purchases")
    @XmlElement(name = "Purchase")
    private List<PurchaseInfo> purchases;


    public PurchasesUsersWrapper(){}

    public PurchasesUsersWrapper(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;
    }

    public PurchaseInfo getPurchaseInfo() {
        return purchaseInfo;
    }

    public PurchasesUsersWrapper(List<PurchaseInfo> purchases) {
        this.purchases = purchases;
    }

    public PurchasesUsersWrapper(List<User> userList, List<PurchaseInfo> purchases) {
        this.userList = userList;
        this.purchases = purchases;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<PurchaseInfo> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseInfo> purchases) {
        this.purchases = purchases;
    }

}