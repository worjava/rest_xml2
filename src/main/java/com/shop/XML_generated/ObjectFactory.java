
package com.shop.XML_generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.shop.XML_generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PurchaseInfo_QNAME = new QName("", "PurchaseInfo");
    private final static QName _UserPurchase_QNAME = new QName("", "UserPurchase");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.shop.XML_generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PurchaseInfo }
     * 
     */
    public PurchaseInfo createPurchaseInfo() {
        return new PurchaseInfo();
    }

    /**
     * Create an instance of {@link UserPurchase }
     * 
     */
    public UserPurchase createUserPurchase() {
        return new UserPurchase();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PurchaseInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PurchaseInfo }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "PurchaseInfo")
    public JAXBElement<PurchaseInfo> createPurchaseInfo(PurchaseInfo value) {
        return new JAXBElement<PurchaseInfo>(_PurchaseInfo_QNAME, PurchaseInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserPurchase }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UserPurchase }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "UserPurchase")
    public JAXBElement<UserPurchase> createUserPurchase(UserPurchase value) {
        return new JAXBElement<UserPurchase>(_UserPurchase_QNAME, UserPurchase.class, null, value);
    }

}
