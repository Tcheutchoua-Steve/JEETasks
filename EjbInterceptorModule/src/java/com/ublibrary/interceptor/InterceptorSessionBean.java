/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ublibrary.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author tcheutchoua
 */
@Interceptors({BusinessInterceptor.class})
@Stateless
public class InterceptorSessionBean implements InterceptorSessionBeanRemote {

    List<String> bookShelf;
    
    public InterceptorSessionBean(){
        bookShelf = new ArrayList<>();
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
