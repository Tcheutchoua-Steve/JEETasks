/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ublibrary.webservce;

import com.ublibrary.webserviceBook.Books;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author tcheutchoua
 */
@Stateless
@WebService(serviceName = "LibraryService")
public class LibraryPersistentBeanWS implements LibraryPersistentBeanWSRemote {

    public LibraryPersistentBeanWS() {
    }
    @PersistenceContext(unitName = "EjbWebservicePU")
    private EntityManager entityManager;

    public void addBook(Books book) {
        entityManager.persist(book);
    }

    @WebMethod(operationName = "getBooks")
    public List<Books> getBooks() {
        TypedQuery<Books> book = entityManager.createQuery("SELECT b FROM Books b", Books.class);
        return book.getResultList();
    }
}
