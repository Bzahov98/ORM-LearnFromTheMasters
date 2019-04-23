package com.bzahov.musalasoftDatabase;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderDetailsPK implements Serializable {
    private int orderId;
    private int productsId;

    @Column(name = "OrderID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "ProductsID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProductsId() {
        return productsId;
    }

    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsPK that = (OrderDetailsPK) o;
        return orderId == that.orderId &&
                productsId == that.productsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productsId);
    }
}
