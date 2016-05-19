/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientforstatefulejbmodule;

import com.ubLibrary.stateful.LibraryStatefulSessionBeanRemote;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author tcheutchoua
 */
public class StatefulEjbTester {

    BufferedReader brConsoleReader = null;
    Properties props;
    InitialContext ctx;

    {
        props = new Properties();
        props = new Properties();
        
        props.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        //props.put(Context.PROVIDER_URL,System.getProperty(Context.PROVIDER_URL, "remote://localhost:4848"));
        props.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state",
                    "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        try {
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader
                = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) {
        StatefulEjbTester ejbTester = new StatefulEjbTester();
        ejbTester.testStatelessEjb();
    }

        private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testStatelessEjb() {
        try {
            int choice = 1;
            LibraryStatefulSessionBeanRemote libraryBean
                    = (LibraryStatefulSessionBeanRemote )ctx.lookup("java:global/StatefulEJbModule/LibraryStatefulSessionBean!com.ubLibrary.stateful.LibraryStatefulSessionBeanRemote");
            while (choice != 2) {
                String bookName;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1) {
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    libraryBean.addBook(bookName);
                } else if (choice == 2) {
                    break;
                }
            }
            List<String> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            for(int i = 0 ; i < booksList.size(); ++i){
                System.out.println((i+1) + "."+ booksList.get(i));
            }
            LibraryStatefulSessionBeanRemote libraryBean1
                    = (LibraryStatefulSessionBeanRemote) ctx.lookup("java:global/StatefulEJbModule/LibraryStatefulSessionBean!com.ubLibrary.stateful.LibraryStatefulSessionBeanRemote");

            List<String> booksList1 = libraryBean1.getBooks();
            System.out.println(
                    "***Using second lookup to get library stateful object***");
            System.out.println(
                    "Book(s) entered so far: " + booksList1.size());
            for (int i = 0; i < booksList1.size(); ++i) {
                System.out.println((i + 1) + ". " + booksList1.get(i));
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