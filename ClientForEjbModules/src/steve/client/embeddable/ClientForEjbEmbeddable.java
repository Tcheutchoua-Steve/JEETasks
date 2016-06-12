/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steve.client.embeddable;

import com.ublibrary.entity.Books;
import com.ublibrary.entity.Publisher;
import com.ublibrary.stateless.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author tcheutchoua at May 25, 2016
 */
public class ClientForEjbEmbeddable {

    BufferedReader brConsoleReader = null;
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

    public static void main(String[] args) {
        ClientForEjbEmbeddable ejbTester = new ClientForEjbEmbeddable();
        ejbTester.testEmbeddedObjects();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testEmbeddedObjects() {
        try {
            int choice = 1;
            LibraryPersistentBeanRemote libraryBean
                    = (LibraryPersistentBeanRemote) ctx.lookup(LibraryPersistentBeanRemote.class.getName());
            while (choice != 2) {
                String bookName;
                String publisherName;
                String publisherAddress;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    System.out.print("Enter publisher name: ");
                    publisherName = brConsoleReader.readLine();
                    System.out.print("Enter publisher address: ");
                    publisherAddress = brConsoleReader.readLine();
                    Books book = new Books();
                    book.setName(bookName);
                    book.setPublisher(new Publisher(publisherName, publisherAddress));
                    libraryBean.addBook(book);
                } else if (choice == 2) {
                    break;
                }
            }
            List<Books> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            int i = 0;
            for (Books book : booksList) {
                System.out.println((i + 1) + ". " + book.getName());
                System.out.println("Publication: " + book.getPublisher());
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
