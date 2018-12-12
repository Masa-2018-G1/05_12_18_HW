package com.sheygam.masa_2018_g1_05_12_18_hw;


import java.util.List;

public class ContactList {
    private List<Contact> contacts;

    public ContactList() {
    }

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
