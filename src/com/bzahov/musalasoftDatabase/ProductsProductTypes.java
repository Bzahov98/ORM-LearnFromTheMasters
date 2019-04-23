package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Products_ProductTypes", schema = "dbo", catalog = "MusalaSoft")
@IdClass(ProductsProductTypesPK.class)
public class ProductsProductTypes {
    private int productId;
    private int productTypeId;
    //private Products productsByProductId;

    @Id
    @Column(name = "ProductID")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "ProductTypeID")
    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsProductTypes that = (ProductsProductTypes) o;
        return productId == that.productId &&
                productTypeId == that.productTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productTypeId);
    }

    /*
    @ManyToOne(optional = false)
    @JoinColumn(name = "ProductID", referencedColumnName = "ID", nullable = false)
    public Products getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(Products productsByProductId) {
        this.productsByProductId = productsByProductId;
        */
}
