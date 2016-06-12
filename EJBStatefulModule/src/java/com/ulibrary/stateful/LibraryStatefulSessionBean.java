/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ulibrary.stateful;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author tcheutchoua
 */
@Stateful
public class LibraryStatefulSessionBean implements LibraryStatefulSessionBeanRemote {

    List<String> bookShelf ;

    public LibraryStatefulSessionBean() {
        bookShelf = new ArrayList<String>();
    }
    

    @Override
    public void addBook(String bookName) {
        bookShelf.add(bookName);
    }

    @Override
    public List getBooks() {
        return bookShelf;
    }

   
}
