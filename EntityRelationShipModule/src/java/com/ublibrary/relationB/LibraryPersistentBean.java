/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ublibrary.relationB;

import com.ulibrary.relationships.Book2;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author tcheutchoua
 */
@Stateless
public class LibraryPersistentBean implements LibraryPersistentBeanRemote {

    @PersistenceContext(unitName="EntityRelationShipModulePU")
    private EntityManager entityManager ; 
    @Override
    public void addBook(Book2 bookName) {
               entityManager.persist(bookName);

    }

    @Override
    public List<Book2> getBooks() {
        TypedQuery<Book2> book = entityManager.createQuery("SELECT b FROM Book2 b", Book2.class);
        return book.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
