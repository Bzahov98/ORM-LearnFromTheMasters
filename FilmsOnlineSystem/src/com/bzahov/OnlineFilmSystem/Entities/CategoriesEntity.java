package com.bzahov.OnlineFilmSystem.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "CategoriesEntity")
@Table(name = "categories", schema = "dbo", catalog = "Filmatics")
public class CategoriesEntity {
    private int id;
    private String name;
    private String info;
    private Set<FilmsEntity> allFilmsSet = new HashSet<>();

    public CategoriesEntity() { }

    public CategoriesEntity(String name, String info, Set<FilmsEntity> allFilmsSet) {
        this.name = name;
        this.info = info;
        this.allFilmsSet = allFilmsSet;
    }

    public CategoriesEntity(String name, String info) {
        this.name = name;
        this.info = info;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 255)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesEntity that = (CategoriesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info);
    }

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "allCategoriesSet")
    public Set<FilmsEntity> getAllFilmsSet() {
        return allFilmsSet;
    }

    public void setAllFilmsSet(Set<FilmsEntity> filmsCategoriesById) {
        this.allFilmsSet = filmsCategoriesById;
    }

    @Override
    public String toString() {
        return "CategoriesEntity{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                /*", allFilmsByCategorySet=" + allFilmsByCategorySet.toString() */+
                '}';
    }
}
