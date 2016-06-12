/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ublibrary.depInjMsgBean;

import com.ublibrary.entityBook.Books;
import com.ublibrary.libraryPersistentBean.LibraryPersistentBeanRemote;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author tcheutchoua
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "BookQueue")
})
public class DepInjMessageBean implements MessageListener {

    @Resource()
    private MessageDrivenContext mdctx;

    LibraryPersistentBeanRemote libraryBean;

    //dependency injection on method
    @EJB(beanName = "com.ublibrary.libraryPersistentBean.LibraryPersistentBean")
    public void setLibraryPersistentBean(
            LibraryPersistentBeanRemote libraryBean) {
        this.libraryBean = libraryBean;
    }

    public DepInjMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = null;
        try {

            objectMessage = (ObjectMessage) message;
            System.out.println(objectMessage.toString());
            Books book = (Books) objectMessage.getObject();
            libraryBean.addBook(book);
        } catch (JMSException ex) {
            System.out.println("Did not insert with message bean");
            mdctx.setRollbackOnly();
        }
    }

}
