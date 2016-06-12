/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientforStatefulComponent;


import com.ulibrary.stateful.LibraryStatefulSessionBean;
import com.ulibrary.stateful.LibraryStatefulSessionBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author tcheutchoua
 */
public class ClientForStatefulComponent {

    BufferedReader brConsoleReader = null ;
    Properties props;
    InitialContext ctx ;
    
    String   lookupString = " java:global/EJBStatefulModule/LibraryStatefulSessionBean!com.ulibrary.stateful.LibraryStatefulSessionBeanRemote" ;

    
    {
        // Choose whether you will use glass fish or jboss configurations based on the configurations below
        glassfishConfigurations();
        //jbossConfigurations();
        
        brConsoleReader = 
                new BufferedReader(new InputStreamReader(System.in));
    }
    public static void main(String[] args) {
        ClientForStatefulComponent clientApp = new ClientForStatefulComponent();
        clientApp.testStatelessEjb();
    }
    
    
    private void showGUI(){
        System.out.println("*****************************");
        System.out.println("Welcome to Book Store");
        System.out.println("*****************************");
        System.out.print("Options \n1. Add Book \n2. Exit \nEnter Choice: ");
    }
    
    private void testStatelessEjb(){
        try {
            int choice = 1;
            LibraryStatefulSessionBeanRemote libraryBean =
                    (LibraryStatefulSessionBeanRemote) ctx.lookup("java:global/EJBStatefulModule/LibraryStatefulSessionBean!com.ulibrary.stateful.LibraryStatefulSessionBeanRemote");
            //(LibrarySessionBeanRemote) ctx.lookup("ejb:/"+modulename+"/"+beanName+"!"+fullyQualifiedRemote);
            while(choice != 2){
                String bookName ;
                showGUI();
                String strChoice = brConsoleReader.readLine();
                choice = Integer.parseInt(strChoice);
                if (choice == 1){
                    System.out.print("Enter book name: ");
                    bookName = brConsoleReader.readLine();
                    libraryBean.addBook(bookName);
                } else if ( choice == 2){
                    break ;
                }
            }
            
            List<String> booksList = libraryBean.getBooks();
            System.out.println("Book(s) entered so far: " + booksList.size());
            for(int i = 0 ; i < booksList.size(); ++i){
                System.out.println((i+1) + "."+ booksList.get(i));
            }
            
            LibraryStatefulSessionBean libraryBean1 = 
                    (LibraryStatefulSessionBean) ctx.lookup("java:global/EJBStatefulModule/LibraryStatefulSessionBean!com.ulibrary.stateful.LibraryStatefulSessionBeanRemote");
            
            List<String> bookList1 = libraryBean1.getBooks();
            System.out.println(
                    "***Using second lookup to get library stateless object***");
            System.out.println(
                    "Book(s) entered so far : " + bookList1.size());
            for (int i = 0; i < bookList1.size(); i++) {
                System.out.println((i+1) + ". " + bookList1.get(i));
            }
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if(brConsoleReader != null)
                    brConsoleReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
    
    public void glassfishConfigurations(){
         try {
            // with glassh fish, you don't need to initialize the Initial context jndi properties.
            ctx = new InitialContext();
        } catch (NamingException ex) {
           // Logger.getLogger(ClientForStatefulComponent.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
         
    }
}
