package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The KennelBooking class represents a booking for a pet in the kennel.
 * It includes customer object, pet object, start of reservation, the end of reservation, and pricing.
 *
 * @author Damian Michal Choina
 * @author Grzegorz Franciszek Frankowicz
 * @version 1.0
 */
public class KennelBooking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Customer customer;
    private Pet pet;
    private LocalDate dateOfReservation;
    private LocalDate endDate;
    private float pricePerDay;
    private float finalPrice;

    /**
     * Constructs a new KennelBooking object with the specified details.
     *
     * @param customer   the customer making the booking
     * @param pet        the pet being booked
     * @param startDate  the start date of the booking
     * @param endDate    the end date of the booking
     * @param pricePerDay the price per day of the booking
     * @param finalPrice the final price for the entire booking duration
     */
    public KennelBooking(Customer customer, Pet pet, LocalDate startDate, LocalDate endDate, float pricePerDay, float finalPrice) {
        setCustomer(customer);
        setPet(pet);
        setDateOfReservation(startDate);
        setEndDate(endDate);
        setPricePerDay(pricePerDay);
        setFinalPrice(finalPrice);
    }

    /**
     * Gets the price per day of the booking.
     *
     * @return the price per day
     */
    public float getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Sets the price per day of the booking.
     *
     * @param pricePerDay the price per day to set
     */
    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Gets the customer making the booking.
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Sets the customer making the booking.
     *
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the pet being booked.
     *
     * @return the pet
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * Sets the pet being booked.
     *
     * @param pet the pet to set
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Gets the start date of the reservation.
     *
     * @return the start date
     */
    public LocalDate getDateOfReservation() {
        return this.dateOfReservation;
    }

    /**
     * Sets the start date of the reservation.
     *
     * @param dateOfReservation the start date to set
     */
    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    /**
     * Gets the end date of the reservation.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the end date of the reservation.
     *
     * @param endDate the end date to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the final price for the entire booking duration.
     *
     * @return the final price
     */
    public float getFinalPrice() {
        return finalPrice;
    }

    /**
     * Sets the final price for the entire booking duration.
     *
     * @param finalPrice the final price to set
     */
    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * Gets the customer's name.
     *
     * @return the customer's name
     */
    public String getNameC() {
        return customer.getName();
    }

    /**
     * Gets the customer's surname.
     *
     * @return the customer's surname
     */
    public String getSurnameC() {
        return customer.getSurname();
    }

    /**
     * Gets the customer's email.
     *
     * @return the customer's email
     */
    public String getEmail() {
        return customer.getEmail();
    }

    /**
     * Gets the customer's phone number.
     *
     * @return the customer's phone number
     */
    public String getPhoneNo() {
        return customer.getPhoneNo();
    }

    /**
     * Gets the pet's name.
     *
     * @return the pet's name
     */
    public String getPetName() {
        return pet.getName();
    }

    /**
     * Gets the pet's species.
     *
     * @return the pet's species
     */
    public String getSpecies() {
        return pet.getSpecies();
    }

    /**
     * Gets comments about the pet.
     *
     * @return the pet's comments
     */
    public String getComments() {
        return pet.getComments();
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
     * Returns a string representation of the kennel booking.
     *
     * @return a string containing the booking details
     */
    @Override
    public String toString() {
        return pet.getName() + "; " + pet.getSpecies() + "; " + pet.getComments() + "; " + customer.getName() + "; " + customer.getSurname() + "; " + customer.getEmail() + "; " + customer.getPhoneNo() + "; " + getDateOfReservation() + "; " + getEndDate() + "; " + getPricePerDay() + "; " + getFinalPrice();
    }

    /**
     * Checks if this booking is equal to another object, including price details.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KennelBooking that = (KennelBooking) o;
        return Float.compare(pricePerDay, that.pricePerDay) == 0 && Float.compare(finalPrice, that.finalPrice) == 0 && Objects.equals(customer, that.customer) && Objects.equals(pet, that.pet) && Objects.equals(dateOfReservation, that.dateOfReservation) && Objects.equals(endDate, that.endDate);
    }

    /**
     * Checks if this booking is equal to another object, excluding price details.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    public boolean equalsWithoutPrice(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KennelBooking that = (KennelBooking) o;
        return Objects.equals(customer, that.customer) && Objects.equals(pet, that.pet) && Objects.equals(dateOfReservation, that.dateOfReservation) && Objects.equals(endDate, that.endDate);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(customer, pet, dateOfReservation, endDate, pricePerDay, finalPrice);
    }
}
