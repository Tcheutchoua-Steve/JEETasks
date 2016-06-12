/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dengun.callbacks.beans;

import com.ublibrary.callbacks.entity.Books;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 *
 * @author tcheutchoua
 */
public class BooksCallBackListerner {

    @PrePersist
    public void prePersist(Books book) {
        System.out.println("BookCallbackListener.prePersist:"
                + "Book to be created with book id: " + book.getId());
    }

    @PostPersist
    public void postPersist(Object book) {
        System.out.println("BookCallbackListener.postPersist::"
                + "Book created with book id: " + ((Books) book).getId());
    }

    @PreRemove
    public void preRemove(Books book) {
        System.out.println("BookCallbackListener.preRemove:"
                + " About to delete Book: " + book.getId());
    }

    @PostRemove
    public void postRemove(Books book) {
        System.out.println("BookCallbackListener.postRemove::"
                + " Deleted Book: " + book.getId());
    }

    @PreUpdate
    public void preUpdate(Books book) {
        System.out.println("BookCallbackListener.preUpdate::"
                + " About to update Book: " + book.getId());
    }

    @PostUpdate
    public void postUpdate(Books book) {
        System.out.println("BookCallbackListener.postUpdate::"
                + " Updated Book: " + book.getId());
    }

    @PostLoad
    public void postLoad(Books book) {
        System.out.println("BookCallbackListener.postLoad::"
                + " Loaded Book: " + book.getId());
    }
}
