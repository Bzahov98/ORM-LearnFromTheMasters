package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vendors {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendors vendors = (Vendors) o;
        return id == vendors.id &&
                Objects.equals(name, vendors.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
