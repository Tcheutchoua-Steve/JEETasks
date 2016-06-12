/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejb.webservice.library.beans;


import com.ejb.webservice.entity.Books;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

/**
 *
 * @author tcheutchoua
 */
@Stateless
@WebService(serviceName="LibraryService")
public class LibraryPersistenceBean implements LibraryPersistenceBeanRemote {

    public LibraryPersistenceBean(){
    
    
    }
    
    @PersistenceContext(unitName = "EJBWebServicePU")
    private EntityManager entityManager;
    
    @Override
    public void addBook(Books bookName) {
        
        entityManager.persist(bookName);
    }

    @WebMethod(operationName="getBooks")
    public List<Books> getBooks() {
        
        entityManager.flush();
       return  entityManager.createQuery("SELECT name FROM Books name order by name.id").getResultList();
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
