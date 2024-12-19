/**
 * Utility class for handling file operations related to serialization and deserialization
 * of objects such as Pets, Customers, KennelBookings, and Sales.
 * @author Grzegorz Franciszek Frankowicz
 * @author Damian Michal Choina
 * @version 1.0
 */
package utils;

import model.Customer;
import model.KennelBooking;
import model.Pet;
import model.Sale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    /**
     * Loads a list of Pet objects from a file.
     *
     * @param filename the name of the file to load from
     * @return a list of Pet objects
     * @throws IOException if an I/O error occurs during the operation
     */
    public static List<Pet> loadPetsFromFile(String filename) throws IOException {
        List<Pet> pets = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Pet pet = (Pet) ois.readObject();
                    pets.add(pet);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new IOException("Class not found while loading pets", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return pets;
    }

    /**
     * Saves a list of Pet objects to a file.
     *
     * @param filename the name of the file to save to
     * @param pets the list of Pet objects to save
     * @throws IOException if an I/O error occurs during the operation
     */
    public static void savePetsToFile(String filename, List<Pet> pets) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Pet pet : pets) {
                oos.writeObject(pet);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Loads a list of Customer objects from a file.
     *
     * @param filename the name of the file to load from
     * @return a list of Customer objects
     * @throws IOException if an I/O error occurs during the operation
     */
    public static List<Customer> loadCustomersFromFile(String filename) throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Customer customer = (Customer) ois.readObject();
                    customers.add(customer);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new IOException("Class not found while loading customers", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return customers;
    }

    /**
     * Saves a list of Customer objects to a file.
     *
     * @param filename the name of the file to save to
     * @param customers the list of Customer objects to save
     * @throws IOException if an I/O error occurs during the operation
     */
    public static void saveCustomersToFile(String filename, List<Customer> customers) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Customer customer : customers) {
                oos.writeObject(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Loads a list of KennelBooking objects from a file.
     *
     * @param filename the name of the file to load from
     * @return a list of KennelBooking objects
     * @throws IOException if an I/O error occurs during the operation
     */
    public static List<KennelBooking> loadKennelFromFile(String filename) throws IOException {
        List<KennelBooking> bookings = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    KennelBooking booking = (KennelBooking) ois.readObject();
                    bookings.add(booking);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new IOException("Class not found while loading kennel bookings", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return bookings;
    }

    /**
     * Saves a list of KennelBooking objects to a file.
     *
     * @param filename the name of the file to save to
     * @param bookings the list of KennelBooking objects to save
     * @throws IOException if an I/O error occurs during the operation
     */
    public static void saveBookingsToFile(String filename, List<KennelBooking> bookings) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (KennelBooking booking : bookings) {
                oos.writeObject(booking);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Loads a list of Sale objects from a file.
     *
     * @param filename the name of the file to load from
     * @return a list of Sale objects
     * @throws IOException if an I/O error occurs during the operation
     */
    public static List<Sale> loadSalesFromFile(String filename) throws IOException {
        List<Sale> sales = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Sale sale = (Sale) ois.readObject();
                    sales.add(sale);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new IOException("Class not found while loading sales", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return sales;
    }

    /**
     * Saves a list of Sale objects to a file.
     *
     * @param filename the name of the file to save to
     * @param sales the list of Sale objects to save
     * @throws IOException if an I/O error occurs during the operation
     */
    public static void saveSalesToFile(String filename, List<Sale> sales) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Sale sale : sales) {
                oos.writeObject(sale);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
