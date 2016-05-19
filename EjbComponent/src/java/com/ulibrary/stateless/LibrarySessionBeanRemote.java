/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ulibrary.stateless;

//import javax.ejb.Local;

import java.util.List;
import javax.ejb.Remote;


/**
 *
 * @author tcheutchoua
 */
@Remote
public interface LibrarySessionBeanRemote {
    
    void addBook(String bookName);
    
    List getBooks();
}
