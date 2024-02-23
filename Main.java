import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TelefonoMovil telefono = new TelefonoMovil("626703410");

        int opcion = 0;
        boolean exit = false;
        do {
            try {
                System.out.println("Menú:");
                System.out.println("0. Salir");
                System.out.println("1. Imprimir contactos");
                System.out.println("2. Agregar un nuevo contacto");
                System.out.println("3. Actualizar un contacto existente");
                System.out.println("4. Eliminar un contacto");
                System.out.println("5. Buscar contacto por nombre");
                System.out.println("6. Mostrar menú");

                System.out.print("Selecciona una opción: ");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 0:
                        exit = true;
                        System.out.println("Saliendo...");
                        break;
                    case 1:
                        telefono.printContacts();
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre del nuevo contacto: ");
                        String newName = sc.next();
                        System.out.print("Ingrese el número de teléfono del nuevo contacto: ");
                        String newPhoneNumber = sc.next();
                        while (newPhoneNumber.length() != 9) {
                            System.out.println("El número debe tener 9 dígitos. Introducelo de nuevo.");
                            newPhoneNumber = sc.next();
                        }
                        Contacto newContact = Contacto.createContact(newName, newPhoneNumber);
                        if (telefono.addNewContact(newContact)) {
                            System.out.println("Contacto agregado correctamente.");
                        } else {
                            System.out.println("Ya existe un contacto con ese nombre.");
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre del contacto a actualizar: ");
                        String oldName = sc.next();
                        Contacto existingContact = telefono.queryContact(oldName);
                        if (existingContact != null) {
                            System.out.print("Ingrese el nuevo nombre del contacto: ");
                            String newNameUpdate = sc.next();
                            System.out.print("Ingrese el nuevo número de teléfono del contacto: ");
                            String newPhoneNumberUpdate = sc.next();
                            Contacto newContactUpdate = Contacto.createContact(newNameUpdate, newPhoneNumberUpdate);
                            if (telefono.updateContact(existingContact, newContactUpdate)) {
                                System.out.println("Contacto actualizado correctamente.");
                            } else {
                                System.out.println("No se pudo actualizar el contacto. Ya existe un contacto con el mismo nombre.");
                            }
                        } else {
                            System.out.println("El contacto no existe.");
                        }
                        break;
                    case 4:
                        System.out.print("Introduce el nombre del contacto a eliminar: ");
                        String contactToRemove = sc.next();
                        Contacto contact = telefono.queryContact(contactToRemove);
                        if (contact != null) {
                            if (telefono.removeContact(contact)) {
                                System.out.println("Contacto eliminado correctamente.");
                            } else {
                                System.out.println("No se pudo eliminar el contacto.");
                            }
                        } else {
                            System.out.println("El contacto no existe.");
                        }
                        break;
                    case 5:
                        System.out.print("Introduce el nombre del contacto a buscar: ");
                        String contactToFind = sc.next();
                        Contacto foundContact = telefono.queryContact(contactToFind);
                        if (foundContact != null) {
                            System.out.println("Contacto encontrado: " + foundContact.getName() + " -> " + foundContact.getPhoneNumber());
                        } else {
                            System.out.println("El contacto no existe.");
                        }
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Introduce una opción válida.");
                sc.next();
            }
        } while (!exit);
    }
}