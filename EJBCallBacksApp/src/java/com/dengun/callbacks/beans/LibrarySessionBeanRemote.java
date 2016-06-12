/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dengun.callbacks.beans;

//import javax.ejb.Local;
import com.ublibrary.callbacks.entity.Books;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tcheutchoua
 */
@Remote
public interface LibrarySessionBeanRemote {
    void addBook(Books bookName);
    
    List<Books> getBooks();
}
