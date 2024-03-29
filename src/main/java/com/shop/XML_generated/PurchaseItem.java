
package com.shop.XML_generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PurchaseItem.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PurchaseItem"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="TELEVISION"/&gt;
 *     &lt;enumeration value="SMARTPHONE"/&gt;
 *     &lt;enumeration value="JUICER"/&gt;
 *     &lt;enumeration value="HEADPHONES"/&gt;
 *     &lt;enumeration value="KEYBOARD"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PurchaseItem")
@XmlEnum
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
