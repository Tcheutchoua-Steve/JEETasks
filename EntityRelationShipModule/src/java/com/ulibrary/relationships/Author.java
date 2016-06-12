/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ulibrary.relationships;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author tcheutchoua at Jun 2, 2016
 */
@Entity
@Table(name = "author")
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer authorId;
   
    
    private String name;

    public Author() {
    }

    public Author(Integer authorId , String name) {
        this.authorId = authorId;
        this.name = name ;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
return authorId + "," + name;
}
}
