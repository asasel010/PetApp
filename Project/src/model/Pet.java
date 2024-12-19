package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a pet with various attributes such as name, age, species, and more.
 * Implements {@link Serializable} for object serialization.
 *
 * @author Guillermo Mesquida Megias
 * @version 1.0
 */
public class Pet implements Serializable {

    /**
     * Serial version UID for serialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String species;
    private String gender;
    private String color;
    private float price;
    private String details;
    private String comments;
    private boolean isForSale;

    /**
     * Constructs a new Pet object with the specified attributes.
     *
     * @param name      the name of the pet
     * @param age       the age of the pet
     * @param species   the species of the pet
     * @param gender    the gender of the pet
     * @param color     the color of the pet
     * @param price     the price of the pet
     * @param details   additional details about the pet
     * @param comments  comments about the pet
     * @param isForSale whether the pet is for sale
     */
    public Pet(String name, int age, String species, String gender, String color, float price, String details, String comments, boolean isForSale) {
        setName(name);
        setAge(age);
        setSpecies(species);
        setGender(gender);
        setColor(color);
        setPrice(price);
        setDetails(details);
        setComments(comments);
        setForSale(isForSale);
    }

    /**
     * Gets the name of the pet.
     *
     * @return the pet's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet.
     *
     * @param name the pet's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the pet.
     *
     * @return the pet's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the pet.
     *
     * @param age the pet's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the species of the pet.
     *
     * @return the pet's species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the species of the pet.
     *
     * @param species the pet's species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Gets the gender of the pet.
     *
     * @return the pet's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the pet.
     *
     * @param gender the pet's gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the color of the pet.
     *
     * @return the pet's color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color of the pet.
     *
     * @param color the pet's color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the price of the pet.
     *
     * @return the pet's price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price of the pet.
     *
     * @param price the pet's price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets additional details about the pet.
     *
     * @return details about the pet
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets additional details about the pet.
     *
     * @param details details about the pet
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets comments about the pet.
     *
     * @return comments about the pet
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets comments about the pet.
     *
     * @param comment comments about the pet
     */
    public void setComments(String comment) {
        this.comments = comment;
    }

    /**
     * Checks if the pet is for sale.
     *
     * @return true if the pet is for sale, false otherwise
     */
    public boolean getIsForSale() {
        return isForSale;
    }

    /**
     * Sets whether the pet is for sale.
     *
     * @param isForSale true if the pet is for sale, false otherwise
     */
    public void setForSale(boolean isForSale) {
        this.isForSale = isForSale;
    }

    /**
     * Gets the serial version UID.
     *
     * @return the serial version UID
     */
    public static long getID() {
        return serialVersionUID;
    }

    /**
     * Compares this pet to another object for equality, including the price.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pet otherPet = (Pet) obj;
        return this.name.equals(otherPet.getName()) &&
            this.age == otherPet.getAge() &&
            this.species.equals(otherPet.getSpecies()) &&
            this.gender.equals(otherPet.getGender()) &&
            this.color.equals(otherPet.getColor()) &&
            this.price == otherPet.getPrice() &&
            this.details.equals(otherPet.getDetails()) &&
            this.comments.equals(otherPet.getComments()) &&
            this.isForSale == otherPet.isForSale;
    }

    /**
     * Compares this pet to another object for equality, excluding the price.
     *
     * @param obj the object to compare
     * @return true if the objects are equal (excluding price), false otherwise
     */
    public boolean equalsWithoutPrice(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pet otherPet = (Pet) obj;
        return this.name.equals(otherPet.getName()) &&
            this.age == otherPet.getAge() &&
            this.species.equals(otherPet.getSpecies()) &&
            this.gender.equals(otherPet.getGender()) &&
            this.color.equals(otherPet.getColor()) &&
            this.details.equals(otherPet.getDetails()) &&
            this.comments.equals(otherPet.getComments());
    }

    /**
     * Returns a string representation of the pet.
     *
     * @return a string containing the pet's attributes
     */
    public String toString() {
        return getName() + "; " + getAge() + "; " + getSpecies() + "; " + getGender() + "; " + getColor() + "; " + getPrice() + "; " + getDetails() + "; " + getComments() + "; " + getIsForSale();
    }
}
