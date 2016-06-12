/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb.database.library.beans;

import com.ejb.database.entity.Book;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

//import javax.persistence.Query;
/**
 *
 * @author Tcheutchoua Steve
 */
@Stateless
public class LibraryPersistenceBean implements LibraryPersistenceBeanRemote {

    public LibraryPersistenceBean() {

    }

    @PersistenceContext(unitName="EJBDatabaseAcessPU")
    private EntityManager entityManager ; 
    @Override
    public void addBook(Book book) {
         entityManager.persist(book);
    }

    @Override
    public List<Book> getBooks() {
        TypedQuery<Book> book = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return book.getResultList();
    }
}

    // Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

