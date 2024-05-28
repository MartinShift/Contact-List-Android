package com.example.lesson05;

import java.util.ArrayList;
import java.util.List;

public class ContactApi {
    private static List<Contact> contacts = new ArrayList<>();

    public static void init(){
        contacts.add(new Contact(1, R.drawable.android, "Ivan", "Ivanov", "666", "ivanov@gmail.com"));
        contacts.add(new Contact(2, R.drawable.android, "Petr", "Petrov", "777", "petrov@gmail.com"));
        contacts.add(new Contact(3, R.drawable.android, "Stepan", "Stepanov", "888", "stepanov@gmail.com"));

    }

    public static List<Contact> getContacts() {
        return contacts;
    }

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }

    public static void updateContact(Contact updatedContact) {
        for (Contact contact : contacts) {
            if (contact.getId() == updatedContact.getId()) {
                contact.setFirstName(updatedContact.getFirstName());
                contact.setLastName(updatedContact.getLastName());
                contact.setPhone(updatedContact.getPhone());
                contact.setEmail(updatedContact.getEmail());
                break;
            }
        }
    }

    public static int getMaxId()
    {
        return contacts.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId();
    }
}
