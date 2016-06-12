/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejb.query.library.beans;


import com.ejb.query.library.entity.Books;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.persistence.Query;

/**
 *
 * @author Tcheutchoua Steve
 */
@Stateless
public class LibraryPersistenceBean implements LibraryPersistenceBeanRemote {

    public LibraryPersistenceBean(){
    
    
    }
    
    @PersistenceContext(unitName = "EJBQueryLanguagePU")
    private EntityManager entityManager;
    
    @Override
    public void addBook(Books bookName) {
        
        entityManager.persist(bookName);
    }

    @Override
    public List<Books> getBooks() {
        
        entityManager.flush();
        
          String ejbSql= "From Books b where b.name like ?1";
          
          //ceate the sql quey now
          Query query= entityManager.createQuery(ejbSql);
          
          //substitue parameter now 
           query.setParameter(1, "%Test%");
        //Create an ejbsql expression
        
        
       return  query.getResultList();
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
