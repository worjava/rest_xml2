package com.shop.soap_server.model;

import com.shop.soap_server.model.PurchaseInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "purchases")
public class Purchases {
    private List<PurchaseInfo> purchaseInfoList;

    public Purchases() {}

    @XmlElement(name = "purchaseInfo")
    public List<PurchaseInfo> getPurchaseInfoList() {
        return purchaseInfoList;
    }

    public void setPurchaseInfoList(List<PurchaseInfo> purchaseInfoList) {
        this.purchaseInfoList = purchaseInfoList;
    }
}
