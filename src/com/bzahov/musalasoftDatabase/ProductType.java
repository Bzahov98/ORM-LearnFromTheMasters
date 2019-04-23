package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductType {
    private int id;
    private String name;
    private ProductType productTypeByParentId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
        ProductType that = (ProductType) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "ParentID", referencedColumnName = "ID", nullable = false)
    public ProductType getProductTypeByParentId() {
        return productTypeByParentId;
    }

    public void setProductTypeByParentId(ProductType productTypeByParentId) {
        this.productTypeByParentId = productTypeByParentId;
    }
}
