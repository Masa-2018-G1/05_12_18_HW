package com.sheygam.masa_2018_g1_05_12_18_hw.data.model;

import java.util.List;

public class ContactListDto {
    private List<ContactDto> contacts;

    public ContactListDto() {
    }

    public ContactListDto(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "ContactListDto{" +
                "contacts=" + contacts +
                '}';
    }
}
