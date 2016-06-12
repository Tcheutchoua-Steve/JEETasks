/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ublibrary.stateless.blob;




import com.ublibrary.blobEntity.Book;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author tcheutchoua
 */
@Remote
public interface LibraryPersistentBeanRemote {
      void addBook(Book bookName);
   
        List<Book> getBooks();
}
