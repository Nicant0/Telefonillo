import java.util.ArrayList;

class TelefonoMovil {
    private String myNumber;
    private ArrayList<Contacto> myContacts;

    public TelefonoMovil(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contacto contact) {
        if (findContact(contact) == -1) {
            myContacts.add(contact);
            return true;
        } else
        return false;
    }

    public boolean updateContact(Contacto oldContact, Contacto newContact) {
        int posicion = findContact(oldContact);
        if (posicion != -1 && findContact(newContact) == -1) {
            myContacts.set(posicion, newContact);
            return true;
        }
        return false;
    }

    public boolean removeContact(Contacto contact) {
        int posicion = findContact(contact);
        if (posicion != -1) {
            myContacts.remove(posicion);
            return true;
        }
        return false;
    }

    private int findContact(Contacto contact) {
        return myContacts.indexOf(contact);
    }

    private int findContact(String name) {
        for (int i = 0; i < myContacts.size(); i++) {
            Contacto contact = myContacts.get(i);
            if (contact.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contacto queryContact(String name) {
        int posicion = findContact(name);
        if (posicion != -1) {
            return myContacts.get(posicion);
        }
        return null;
    }

    public void printContacts() {
        System.out.println("Lista de contactos:");
        for (int i = 0; i < myContacts.size(); i++) {
            Contacto contact = myContacts.get(i);
            System.out.println((i + 1) + ". " + contact.getName() + " -> " + contact.getPhoneNumber());
        }
    }
}
