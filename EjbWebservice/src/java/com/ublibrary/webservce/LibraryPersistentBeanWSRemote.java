/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ublibrary.webservce;

import com.ublibrary.webserviceBook.Books;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author tcheutchoua
 */
@Remote
public interface LibraryPersistentBeanWSRemote {

    void addBook(Books bookName);

    List<Books> getBooks();
}
