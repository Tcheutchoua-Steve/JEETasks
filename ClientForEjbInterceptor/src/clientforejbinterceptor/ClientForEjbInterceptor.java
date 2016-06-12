/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientforejbinterceptor;

import com.ublibrary.interceptor.InterceptorSessionBean;
import com.ublibrary.interceptor.InterceptorSessionBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author tcheutchoua at May 24, 2016
 */
public class ClientForEjbInterceptor {

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
        ClientForEjbInterceptor ejbTester = new ClientForEjbInterceptor();
        ejbTester.testInterceptedEjb();
    }

    private void showGUI() {
        System.out.println("**********************");
        System.out.println("Welcome to Book Store");
        System.out.println("**********************");
        System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
    }

    private void testInterceptedEjb() {
        try {
            int choice = 1;
            InterceptorSessionBeanRemote libraryBean
                    = (InterceptorSessionBeanRemote) ctx.lookup("java:global/EjbInterceptorModule/InterceptorSessionBean!com.ublibrary.interceptor.InterceptorSessionBeanRemote");
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
            for (int i = 0; i < booksList.size(); ++i) {
                System.out.println((i + 1) + ". " + booksList.get(i));
            }
            InterceptorSessionBeanRemote libraryBean1
                    = (InterceptorSessionBeanRemote) ctx.lookup("java:global/EjbInterceptorModule/InterceptorSessionBean!com.ublibrary.interceptor.InterceptorSessionBeanRemote");
            List<String> booksList1 = libraryBean1.getBooks();
            System.out.println(
                    "***Using second lookup to get library stateless object***");
            System.out.println("Book(s) entered so far: " + booksList1.size());
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
