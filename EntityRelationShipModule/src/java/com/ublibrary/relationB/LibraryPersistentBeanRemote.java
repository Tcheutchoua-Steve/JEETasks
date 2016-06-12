/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ublibrary.relationB;

import com.ulibrary.relationships.Book2;
import java.util.List;
import javax.ejb.Remote;


/**
 *
 * @author tcheutchoua
 */
@Remote
public interface LibraryPersistentBeanRemote {
             void addBook(Book2 bookName);
   
        List<Book2> getBooks();
}
