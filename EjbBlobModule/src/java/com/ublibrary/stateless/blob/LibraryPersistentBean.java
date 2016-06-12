/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ublibrary.stateless.blob;



import com.ublibrary.blobEntity.Book;
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

@PersistenceContext(unitName="EjbBlobModulePU")
    private EntityManager entityManager ; 
     private List bookList;
    private Query query;

    public LibraryPersistentBean() {

    }
    
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
