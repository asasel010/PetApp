package model;
// Importing necessary package for serialization
import java.io.Serial;
import java.io.Serializable;
/**
 * Customer class implements serializable to allow it to be serialized
 * It contains fields for customer details like name, surname, email and phone number
 * @author Fadumo Ahmed Farah
 * @version 1.0
 */
public class Customer implements Serializable
{
    // The serialVersionUID is used for version control of serialized objects
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private String email;
    private String phoneNo;
    /**
     * Constructor to initialize the customer object with given values
     * for name, surname, email and phone number
     */
    public Customer(String name, String surname, String email, String phoneNo)
    {
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.phoneNo=phoneNo;
    }
    /**
     *Getter method for the 'name' field
     * @return the customer's name
     */
    public String getName(){
        return name;
    }
    /**
     * setter method for the 'name' field
     * @param name the new value to set for the customer's name
     */
    public void setName(String name) {
        this.name=name;
    }
    /**
     *Getter method for the 'surname' field
     * @return the customer's surname
     */
    public String getSurname() {
        return surname;
    }
    /**
     * setter method for the 'surname' field
     * @param surname the new value to set for the customer's surname
     */

    public void setSurname(String surname){
        this.surname=surname;
    }
    /**
     *Getter method for the 'Email' field
     * @return the customer's 'Email'
     */

    public String getEmail() {
        return this.email;
    }
    /**
     * setter method for the 'Email' field
     * @param email the new value to set for the customer's Email
     */


    public void setEmail(String email) {
        this.email = email;
    }
    /**
     *Getter method for the 'PhoneNo' field
     * @return the customer's 'PhoneNo'
     */


    public String getPhoneNo() {
        return this.phoneNo;
    }
    /**
     * setter method for the 'PhoneNO' field
     * @param phoneNo the new value to set for the customer's PhoneNo
     */

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    /**
     * Static method to return the serialVersionUID
     * @return the serialVersionUID
     */
    public static long getID() {
        return serialVersionUID;
    }
    /**
     * Override of the toString() method to return a formatted string
     * with all customer details(name surname email and phone number)
     * @return a string representation of the customer object
     */

    public String toString() {
        return "Name: " + this.name + ", Surname: " + this.surname +", Email: " + this.email +", PhoneNo: " + this.phoneNo;
    }
    /**
     *override of the equals() method to compare two Customer objects for equality
     * based on their fields (name surname email and phone number)
     * @param obj the object to compare
     * @return true if the objects are equal otherwise false
     */

    public boolean equals(Object obj){
        // If the object is null or not of the same class, return false
        if (obj == null || getClass() !=obj.getClass()){
            return false;
        }
        // Typecast the object to a customer the fields for equality
        Customer other = (Customer)obj;
        return this.name.equals(other.name) && this.surname.equals(other.surname) && this.email.equals(other.email) && this.phoneNo.equals(other.phoneNo);
    }
}
