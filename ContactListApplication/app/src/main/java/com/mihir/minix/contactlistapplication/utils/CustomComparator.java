package com.mihir.minix.contactlistapplication.utils;

import com.mihir.minix.contactlistapplication.model.Contact;

import java.util.Comparator;

/**
 * Created by minix on 1/14/2018.
 */

public class CustomComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact contact, Contact t1) {
        //return 0;
        return contact.getName().compareTo(String.valueOf(t1.getId()));
    }

    @Override
    public Comparator<Contact> reversed() {
        return null;
    }
}
