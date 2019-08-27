package com.models;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User {

    @XmlElement
    private String name;

    public User() {}  // This is required for JAXB stuff
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
