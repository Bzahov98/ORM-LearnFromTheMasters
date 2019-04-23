package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Currencies {
    private int id;
    private String iso;
    private BigDecimal rate;

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
    @Column(name = "ISO")
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Basic
    @Column(name = "Rate")
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currencies that = (Currencies) o;
        return id == that.id &&
                Objects.equals(iso, that.iso) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iso, rate);
    }
}
