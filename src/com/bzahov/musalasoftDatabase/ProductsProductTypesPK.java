package com.bzahov.musalasoftDatabase;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductsProductTypesPK implements Serializable {
    private int productId;
    private int productTypeId;

    @Column(name = "ProductID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "ProductTypeID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        ProductsProductTypesPK that = (ProductsProductTypesPK) o;
        return productId == that.productId &&
                productTypeId == that.productTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productTypeId);
    }
}
