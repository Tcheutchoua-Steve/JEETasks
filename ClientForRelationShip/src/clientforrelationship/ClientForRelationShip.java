/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientforrelationship;

import com.ublibrary.relationB.LibraryPersistentBeanRemote;
import com.ulibrary.relationships.Author;
import com.ulibrary.relationships.Book2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author tcheutchoua at Jun 2, 2016
 */
public class ClientForRelationShip {

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
        ClientForRelationShip ejbTester = new ClientForRelationShip();
        ejbTester.testRelationShipObject();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testRelationShipObject() {
        try {
            int choice = 1;
            LibraryPersistentBeanRemote libraryBean
                    = (LibraryPersistentBeanRemote) ctx.lookup("java:global/EntityRelationShipModule/LibraryPersistentBean!com.ublibrary.relationB.LibraryPersistentBeanRemote");
            while (choice != 2) {
                String bookName;
                String authorName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    System.out.print("Enter author name: ");
                    authorName = brConsoleReader.readLine();
                    Book2 book = new Book2();
                    book.setName(bookName);
                    Author author = new Author();
                    author.setName(authorName);
                    Set<Author> authors = new HashSet<Author>();
                    authors.add(author);
                    book.setAuthors(authors);
                    libraryBean.addBook(book);
                } else if (choice == 2) {
                    break;
                }
            }
            List<Book2> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            int i = 0;
            for (Book2 book : booksList) {
                System.out.println((i + 1) + ". " + book.getName());
                System.out.print("Author: ");
                Author[] authors = (Author[]) book.getAuthors().toArray();
                for (int j = 0; j < authors.length; j++) {
                    System.out.println(authors[j]);
                }
                i++;
            }
            LibraryPersistentBeanRemote libraryBean1
                    = (LibraryPersistentBeanRemote) ctx.lookup("java:global/EntityRelationShipModule/LibraryPersistentBean!com.ublibrary.relationB.LibraryPersistentBeanRemote");
            List<Book2> booksList1 = libraryBean1.getBooks();
            System.out.println(
                    "***Using second lookup to get library stateless object***");
            System.out.println("Book(s) entered so far: " + booksList1.size());
            for (Book2 book : booksList) {
                System.out.println((i + 1) + ". " +  book.getName());
                System.out.println((i + 1) + ". " +  book.getAuthors());
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
