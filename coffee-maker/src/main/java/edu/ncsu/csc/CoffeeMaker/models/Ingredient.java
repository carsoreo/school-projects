package edu.ncsu.csc.CoffeeMaker.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 * Ingredient class with id amount and name
 *
 * @author cfbunao
 *
 */
@Entity // class will be persisted to the database
public class Ingredient extends DomainObject {

    /** Automated ID */
    @Id
    @GeneratedValue
    Long    id;

    /** Amount of ingredient */
    @Min ( 0 )
    Integer amount;

    /** Name of ingredient */
    String  name;

    /**
     * Constructor for Ingredient
     *
     * @param name
     *            name of ingredient
     * @param amount
     *            amount of ingredient
     */
    public Ingredient ( final String name, final int amount ) {
        this.amount = amount;
        this.name = name;
    }

    /**
     * Empty constructor for Hibernate
     */
    public Ingredient () {
        this.name = "";
    }

    /**
     * Getter for ID
     *
     * @return id the ID
     */
    @Override
    public Serializable getId () {
        return this.id;
    }

    /**
     * Set the ID of the Ingredient (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Getter for amount
     *
     * @return the amount
     */
    public Integer getAmount () {
        return amount;
    }

    /**
     * Setter for amount
     *
     * @param amount
     *            the amount to set
     */
    public void setAmount ( final Integer amount ) {
        this.amount = amount;
    }

    /**
     * Setter for the name
     *
     * @param name
     *            to be set
     */
    public void setName ( final String name ) {
        this.name = name;
    }

    /**
     * Getter for the name
     *
     * @return name to be got
     */
    public String getName () {
        return this.name;
    }

    /**
     * Setter for ID
     *
     * @param id
     *            the id to set
     */
    public void setId ( final long id ) {
        this.id = id;
    }

    /**
     * toString statement for Ingredient
     *
     * @return String of all fields
     */
    @Override
    public String toString () {
        return "Ingredient [id=" + id + ", amount=" + amount + ", name=" + name + "]";
    }

}
