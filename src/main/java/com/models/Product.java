package com.models;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product")
public class Product {

    @XmlElement
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private double price;
    @XmlElement
    private int category;

    public Product() {}  // This is required for JAXB stuff

    public Product(String name, String description, double price, int category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }
}
