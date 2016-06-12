/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbwebserviceclient;

import com.ublibrary.webservce.Books;

/**
 *
 * @author tcheutchoua at Jun 7, 2016
 */
public class EjbWebServiceClient {
        public static void  main (String[] args){
            for(Books book:getBooks()){
                System.out.println(book.getName());
            }
        }

    private static java.util.List<com.ublibrary.webservce.Books> getBooks() {
        com.ublibrary.webservce.LibraryService service = new com.ublibrary.webservce.LibraryService();
        com.ublibrary.webservce.LibraryPersistentBeanWS port = service.getLibraryPersistentBeanWSPort();
        return port.getBooks();
    }
}
