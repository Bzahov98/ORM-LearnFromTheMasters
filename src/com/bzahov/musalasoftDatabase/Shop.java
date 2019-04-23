package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Shops")
public class Shop implements Serializable {
    private long ID;
    private String location;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return ID;
    }
    public void setId(long id) {
        this.ID = id;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return ID == shop.ID &&
                Objects.equals(getLocation(), shop.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, getLocation());
    }
}
