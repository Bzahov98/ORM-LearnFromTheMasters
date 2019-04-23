package com.bzahov.musalasoftDatabase;

import org.hibernate.Session;

import java.math.BigDecimal;

public class ProductsFactory {
    public static Products createProducts(String productName, BigDecimal discount,
                                          BigDecimal singlePrice, BigDecimal vat,
                                          String vendorName)throws ExceptionInInitializerError{

        final Session session = SessionHolder.getINSTANCE();
        session.beginTransaction();
        Vendors vendors = new Vendors();
        vendors.setName(vendorName);
        session.save(vendors);

        Products product = new Products();
        product.setVendorId(vendors.getId());
        product.setDiscount(discount);
        product.setName(productName);
        product.setSinglePrice(singlePrice);
        product.setVat(vat);
        session.save(product);
        session.getTransaction().commit();
        session.close();
        return product;
    }
}
