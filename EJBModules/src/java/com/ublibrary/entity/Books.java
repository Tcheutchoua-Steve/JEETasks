/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ublibrary.entity;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author tcheutchoua at May 25, 2016
 */
@Entity
@Table(name = "books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    private Publisher publisher;

    public Books() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name",
                column = @Column(name = "PUBLISHER")),
        @AttributeOverride(name = "address",
                column = @Column(name = "PUBLISHER_ADDRESS"))
    })
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher pub) {
        this.publisher = pub;
    }

}
