package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Products {
    private int id;
    private String name;
    private int availableQuantity;
    private int vendorId;
    private BigDecimal singlePrice;
    private BigDecimal discount;
    private BigDecimal vat;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "AvailableQuantity")
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Basic
    @Column(name = "SinglePrice")
    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    @Basic
    @Column(name = "Discount")
    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "VAT")
    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return id == products.id &&
                availableQuantity == products.availableQuantity &&
                Objects.equals(name, products.name) &&
                Objects.equals(singlePrice, products.singlePrice) &&
                Objects.equals(discount, products.discount) &&
                Objects.equals(vat, products.vat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, availableQuantity, singlePrice, discount, vat);
    }


    @Column(name = "vendorID")
    @Basic
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

}
