package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Commands {
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
        Commands commands = (Commands) o;
        return id == commands.id &&
                Objects.equals(name, commands.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
