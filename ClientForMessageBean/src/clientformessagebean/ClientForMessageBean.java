/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientformessagebean;

import com.ublibrary.entityBook.Books;
import com.ublibrary.libraryPersistentBean.LibraryPersistentBeanRemote;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;

/**
 *
 * @author tcheutchoua at May 22, 2016
 */
public class ClientForMessageBean {

     BufferedReader brConsoleReader = null ;
     Properties props ;
    InitialContext ctx ;
    
    {
        try {
            props = new Properties();
           props.put(Context.PROVIDER_URL, "mq://localhost:7676");
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        brConsoleReader = 
                new BufferedReader(new InputStreamReader(System.in));
    }
    
    
    public static void main(String[] args) {
       ClientForMessageBean ejbTester = new ClientForMessageBean();
       ejbTester.testMessageBean();
    }
    
     private void showGUI(){
        System.out.println("*****************************");
        System.out.println("Welcome to Book Store");
        System.out.println("*****************************");
        System.out.println("Options \n1. Add Book \n2. Exit \nEnter Choice");
    }
     
     private void testMessageBean(){
         try {
             int choice  =  1 ;
             
             Queue queue = (Queue) ctx.lookup("BookQueue"); 
             QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("BookQueueConnectionFactory");
             //QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("jms/BookQueue");
             
             QueueConnection connection = factory.createQueueConnection();
             QueueSession session =
                     connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
             
             QueueSender sender = session.createSender(queue);
             
             
             while (choice != 2){
                 String bookName ;
                 showGUI();
                 String strChoice = brConsoleReader.readLine();
                 choice = Integer.parseInt(strChoice);
                 
                 if(choice == 1){
                     System.out.println("Enter book name: ");
                     bookName = brConsoleReader.readLine();
                     Books book = new Books();
                     book.setName(bookName);
                     ObjectMessage objectMessage =
                             session.createObjectMessage(book);
                     
                     sender.send(objectMessage);
                 } else if( choice == 2){
                     break ;
                 }
             }
             
             LibraryPersistentBeanRemote libraryBean =
                    (LibraryPersistentBeanRemote) ctx.lookup(LibraryPersistentBeanRemote.class.getName());
             
             List<Books> bookList = libraryBean.getBooks();
             
             System.out.println("Book(s) entered so far: " + bookList.size());
             int i = 0 ;
             for(Books book : bookList){
                 System.out.println((i+1)+". " + book.getName());
             }
             session.close();
             ctx.close();
         } catch (Exception e) {
             System.out.println(e.getMessage());
             e.printStackTrace();
         } finally {
             try {
                 
                 if(brConsoleReader != null)
                    brConsoleReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
         }
     }
}
