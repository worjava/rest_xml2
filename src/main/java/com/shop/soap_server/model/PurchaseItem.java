package com.shop.soap_server.model;



public enum PurchaseItem {
    TELEVISION,
    SMARTPHONE,
    JUICER,
    HEADPHONES,
    KEYBOARD;

    public String value() {
        return name();
    }

    public static PurchaseItem fromValue(String v) {
        return valueOf(v);

    }
}