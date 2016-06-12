/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb.database.library.beans;

//import javax.ejb.Local;

import com.ejb.database.entity.Book;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Tcheutchoua Steve
 */
@Remote
public interface LibraryPersistenceBeanRemote {
    void addBook(Book bookName);
    
    List<Book> getBooks();
}
