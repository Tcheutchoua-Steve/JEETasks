/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejbwebservice;

/**
 *
 * @author tcheutchoua
 */
public class ClientEJBWebService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 

        //run the web service client
        for (com.dengun.ejb.webservice.library.beans.Books book : getBooks()) {
            System.out.println(book.getName());
        }
    }

    private static java.util.List<com.dengun.ejb.webservice.library.beans.Books> getBooks() {
        com.dengun.ejb.webservice.library.beans.LibraryService service = new com.dengun.ejb.webservice.library.beans.LibraryService();
        com.dengun.ejb.webservice.library.beans.LibraryPersistenceBean port = service.getLibraryPersistenceBeanPort();
        return port.getBooks();
    }

}
