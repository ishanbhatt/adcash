package com.models;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class Category {

    @NotNull
    private String name;

    public Category() {}
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
