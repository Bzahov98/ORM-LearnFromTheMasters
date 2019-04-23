package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Customers {
    private int id;
    private String name;
    private String email;
    private boolean isCorporate;
    private BigDecimal vipDiscount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "IsCorporate")
    public boolean isCorporate() {
        return isCorporate;
    }

    public void setCorporate(boolean corporate) {
        isCorporate = corporate;
    }

    @Basic
    @Column(name = "VIPDiscount")
    public BigDecimal getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(BigDecimal vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return id == customers.id &&
                isCorporate == customers.isCorporate &&
                Objects.equals(name, customers.name) &&
                Objects.equals(email, customers.email) &&
                Objects.equals(vipDiscount, customers.vipDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, isCorporate, vipDiscount);
    }
}
