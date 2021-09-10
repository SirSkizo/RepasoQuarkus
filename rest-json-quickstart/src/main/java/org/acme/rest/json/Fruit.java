package org.acme.rest.json;

import javax.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Entity
public class Fruit extends PanacheEntity {

    public String name;
    public String description;

    public Fruit() {
    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // return name as uppercase in the model
    public String getName(){
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name){
        this.name = name.toLowerCase();
    }

    public static Fruit findByName(String name){
        return find("name", name).firstResult();
    }

}