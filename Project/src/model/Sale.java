/**
 * Represents a sale of a pet to a customer, including details such as the pet,
 * customer, date of sale, and price.
 * Implements {@link Serializable} for object serialization.
 *
 * @author Tomas Luciano Fiorita
 * @version 1.0
 */
package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {

    /**
     * Serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private Pet pet;
    private Customer customer;
    private LocalDate dateOfSale;
    private float price;

    /**
     * Constructs a new Sale object with the specified attributes.
     *
     * @param pet         the pet being sold
     * @param customer    the customer buying the pet
     * @param dateOfSale  the date of the sale
     * @param price       the price of the sale
     */
    public Sale(Pet pet, Customer customer, LocalDate dateOfSale, float price) {
        setPet(pet);
        setCustomer(customer);
        setDateOfSale(dateOfSale);
        setPrice(price);
    }

    /**
     * Gets the pet involved in the sale.
     *
     * @return the pet
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Sets the pet involved in the sale.
     *
     * @param pet the pet
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Gets the customer involved in the sale.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Sets the customer involved in the sale.
     *
     * @param customer the customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the date of the sale.
     *
     * @return the date of sale
     */
    public LocalDate getDateOfSale() {
        return this.dateOfSale;
    }

    /**
     * Sets the date of the sale.
     *
     * @param dateOfSale the date of sale
     */
    public void setDateOfSale(LocalDate dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    /**
     * Gets the price of the sale.
     *
     * @return the price of the sale
     */
    public float getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the sale.
     *
     * @param price the price of the sale
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the name of the pet involved in the sale.
     * Used for displaying property in table columns.
     *
     * @return the pet's name, or an empty string if the pet is null
     */
    public String getPetName() {
        return pet != null ? pet.getName() : "";
    }

    /**
     * Gets the species of the pet involved in the sale.
     * Used for displaying property in table columns.
     *
     * @return the pet's species, or an empty string if the pet is null
     */
    public String getSpecies() {
        return pet != null ? pet.getSpecies() : "";
    }

    /**
     * Gets the name of the customer involved in the sale.
     * Used for displaying property in table columns.
     *
     * @return the customer's name, or an empty string if the customer is null
     */
    public String getCustomerName() {
        return customer != null ? customer.getName() : "";
    }

    /**
     * Gets the surname of the customer involved in the sale.
     * Used for displaying property in table columns.
     *
     * @return the customer's surname, or an empty string if the customer is null
     */
    public String getCustomerSurname() {
        return customer != null ? customer.getSurname() : "";
    }

    /**
     * Gets the phone number of the customer involved in the sale.
     * Used for displaying property in table columns.
     *
     * @return the customer's phone number, or an empty string if the customer is null
     */
    public String getCustomerPhone() {
        return customer != null ? customer.getPhoneNo() : "";
    }

    /**
     * Gets the email of the customer involved in the sale.
     * Used for displaying property in table columns.
     *
     * @return the customer's email, or an empty string if the customer is null
     */
    public String getCustomerEmail() {
        return customer != null ? customer.getEmail() : "";
    }

    /**
     * Gets the date of the sale.
     * Used for displaying property in table columns.
     *
     * @return the sale date
     */
    public LocalDate getSaleDate() {
        return dateOfSale;
    }

    /**
     * Gets the serial version UID for serialization.
     *
     * @return the serial version UID
     */
    public static long getID() {
        return serialVersionUID;
    }

    /**
     * Returns a string representation of the sale.
     *
     * @return a string containing the sale details
     */
    @Override
    public String toString() {
        return pet.getName() + "; " + pet.getSpecies() + "; " + customer.getName() + "; " + customer.getSurname() + "; " + customer.getPhoneNo() + "; " + customer.getEmail() + "; " + getSaleDate() + "; " + getPrice();
    }

    /**
     * Compares this sale to another object for equality, including the price.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sale otherSale = (Sale) obj;
        return this.pet.equals(otherSale.getPet()) &&
            this.customer.equals(otherSale.getCustomer()) &&
            this.dateOfSale.equals(getSaleDate()) &&
            this.price == otherSale.getPrice();
    }

    /**
     * Compares this sale to another object for equality, excluding the price.
     *
     * @param obj the object to compare
     * @return true if the objects are equal (excluding price), false otherwise
     */
    public boolean equalsWithoutPrice(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sale otherSale = (Sale) obj;
        return this.pet.equals(otherSale.getPet()) &&
            this.customer.equals(otherSale.getCustomer()) &&
            this.dateOfSale.isEqual(otherSale.getDateOfSale());
    }
}
