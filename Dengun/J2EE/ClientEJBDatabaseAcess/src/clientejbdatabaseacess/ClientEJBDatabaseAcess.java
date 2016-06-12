/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientejbdatabaseacess;

import com.ejb.database.entity.Book;
import com.ejb.database.library.beans.LibraryPersistenceBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Tcheutchoua Steve
 */
public class ClientEJBDatabaseAcess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
          ClientEJBDatabaseAcess testDatabaseAcess= new ClientEJBDatabaseAcess();
            testDatabaseAcess.testEjbDatabaseAcess();
    }
    
    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        try {
            ctx = new InitialContext();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    public void testEjbDatabaseAcess() {
        try {
            int choice = 1;
            LibraryPersistenceBeanRemote libraryBean = (LibraryPersistenceBeanRemote) ctx.lookup(LibraryPersistenceBeanRemote.class.getName());

            //java:global/StatefulEJbModule/LibraryStatefulSessionBean!com.ubLibrary.stateful.LibraryStatefulSessionBeanRemote
            while (choice != 2) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");

                    bookName = brConsoleReader.readLine();
                    //create a new book entity and persist to the database
                    Book newBook = new Book();

                    newBook.setName(bookName);

                    libraryBean.addBook(newBook);
                } else if (choice == 2) {
                    break;
                }
            }
            List<Book> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());

            int i = 0;
            for (Book book : booksList) {
                System.out.println((i + 1) + ". " + book.getName());

                i++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (brConsoleReader != null) {
                    brConsoleReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
