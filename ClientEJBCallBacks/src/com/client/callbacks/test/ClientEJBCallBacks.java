/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client.callbacks.test;

import com.client.callbacks.library.EJBTester;

/**
 *
 * @author tcheutchoua
 */
public class ClientEJBCallBacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         EJBTester callBacksPersistenceBean= new EJBTester();
         
           callBacksPersistenceBean.testEjbPersistenceCallBacks();
    }
    
}
