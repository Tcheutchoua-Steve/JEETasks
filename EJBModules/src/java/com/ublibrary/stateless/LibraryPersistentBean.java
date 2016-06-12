/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ublibrary.stateless;

import com.ublibrary.entity.Books;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author tcheutchoua
 */
@Stateless
public class LibraryPersistentBean implements LibraryPersistentBeanRemote {
    
    @PersistenceContext(unitName="EJBModulesPU")
    private EntityManager entityManager ;
    private Query query;

    public LibraryPersistentBean() {

    }
    
    @Override
    public void addBook(Books book) {
       entityManager.persist(book);
    }

    @Override
    public List<Books> getBooks() {
         TypedQuery<Books> book = entityManager.createQuery("SELECT b FROM Books b", Books.class);
        return book.getResultList();
    }


   
}
