/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dengun.callbacks.beans;

import com.ublibrary.callbacks.entity.Books;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

/**
 *
 * @author tcheutchoua
 */
@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {

    public LibrarySessionBean() {

    }

    @PersistenceContext(unitName = "EJBCallBacksAppPU")
    private EntityManager entityManager;

    @Override
    public void addBook(Books bookName) {

        entityManager.persist(bookName);
    }

    @Override
    public List<Books> getBooks() {

        entityManager.flush();
        return entityManager.createQuery("SELECT name FROM Books name order by name.id").getResultList();

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct:: LibraryPersistentBean session bean"
                + " created with entity Manager object: ");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy: LibraryPersistentBean session"
                + " bean is removed ");
    }

 

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
