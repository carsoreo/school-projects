package edu.ncsu.csc.CoffeeMaker.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Inventory for the coffee maker. Inventory is tied to the database using
 * Hibernate libraries. See InventoryRepository and InventoryService for the
 * other two pieces used for database support.
 *
 * @author Kai Presler-Marshall
 */
@Entity
public class Inventory extends DomainObject {

    /** id for inventory entry */
    @Id
    @GeneratedValue
    private Long             id;

    /** list of ingredients for inventory */
    @OneToMany ( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Ingredient> ingList;

    /**
     * Empty constructor for Hibernate
     */
    public Inventory () {
        // Intentionally empty so that Hibernate can instantiate
        // Inventory object.
    }

    /**
     * Use this to create inventory with specified amts.
     *
     * @param ingredientList
     *            list of ingredients
     */
    public Inventory ( final List<Ingredient> ingredientList ) {
        this.ingList = ingredientList;
    }

    /**
     * Gets list of ingredients
     *
     * @return List of ingredients
     */
    public List<Ingredient> getIngredients () {
        return this.ingList;
    }

    /**
     * Returns the ID of the entry in the DB
     *
     * @return long
     */
    @Override
    public Long getId () {
        return id;
    }

    /**
     * Set the ID of the Inventory (Used by Hibernate)
     *
     * @param id
     *            the ID
     */
    public void setId ( final Long id ) {
        this.id = id;
    }

    /**
     * Returns true if there are enough ingredients to make the beverage.
     *
     * @param r
     *            recipe to check if there are enough ingredients
     * @return true if enough ingredients to make the beverage
     */
    public boolean enoughIngredients ( final Recipe r ) {
        final List<Ingredient> consumeList = r.getIngredients();
        final boolean isEnough = true;
        for ( int i = 0; i < consumeList.size(); i++ ) {
            for ( int j = 0; j < ingList.size(); j++ ) {
                if ( consumeList.get( i ).getName().equals( ingList.get( j ).getName() ) ) {
                    if ( consumeList.get( i ).getAmount() > ingList.get( j ).getAmount() ) {
                        return false;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        return isEnough;
    }

    /**
     * Returns amount of ingredient unless it is not found or param is invalid
     *
     * @param ing
     *            ingredient to be checked
     * @return -1 if not found or invalid, and int number if found
     */
    public int checkIngredient ( final Ingredient ing ) {
        if ( ing == null ) {
            return -1;
        }
        for ( int i = 0; i < ingList.size(); i++ ) {
            if ( ing.getName().equals( ingList.get( i ).getName() ) ) {
                return ingList.get( i ).getAmount();
            }
        }
        return -1;
    }

    /**
     * Removes the ingredients used to make the specified recipe. Assumes that
     * the user has checked that there are enough ingredients to make
     *
     * @param r
     *            recipe to make
     * @return true if recipe is made.
     */
    public boolean useIngredients ( final Recipe r ) {
        final List<Ingredient> consumeList = r.getIngredients();
        if ( enoughIngredients( r ) ) {
            for ( int i = 0; i < consumeList.size(); i++ ) {
                for ( int j = 0; j < ingList.size(); j++ ) {
                    if ( ingList.get( j ).getName().equals( consumeList.get( i ).getName() ) ) {
                        ingList.get( j ).setAmount( ingList.get( j ).getAmount() - consumeList.get( i ).getAmount() );
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds ingredients to the inventory
     *
     * @param list
     *            Ingredient list to be added
     *
     * @return true or false if ingredient add was succesful
     */
    public boolean addIngredients ( final List<Ingredient> list ) {
        // Check if number is invalid
        if ( list == null ) {
            return false;
        }
        if ( list.size() == 0 ) {
            return true;
        }
        for ( int i = 0; i < list.size(); i++ ) {
            final Ingredient ing = list.get( i );
            if ( ing.getAmount() < 0 ) {
                throw new IllegalArgumentException( "Amount cannot be negative" );
            }
        }
        // Find ingredient with same name, if found, add new amount
        for ( int i = 0; i < list.size(); i++ ) {
            boolean found = false;
            for ( int j = 0; j < ingList.size(); j++ ) {
                if ( ingList.get( j ).getName().equals( list.get( i ).getName() ) ) {
                    found = true;
                    ingList.get( j ).setAmount( ingList.get( j ).getAmount() + list.get( i ).getAmount() );
                    break;
                }
            }
            if ( !found ) {
                ingList.add( list.get( i ) );
            }
        }
        // If ingredient with same name is not found, add it to the list and
        // return true

        return true;
    }

    /**
     * Sets ingredients List
     *
     * @param list
     *            List of Ingredients to be set
     */
    public void setIngredients ( final List<Ingredient> list ) {
        this.ingList = list;
    }

    /**
     * Returns a string describing the current contents of the inventory.
     *
     * @return String
     */
    @Override
    public String toString () {
        final StringBuffer buf = new StringBuffer();
        for ( int i = 0; i < ingList.size(); i++ ) {
            buf.append( ingList.get( i ).getName() + ": " );
            buf.append( ingList.get( i ).getAmount() + "\n" );
        }
        return buf.toString();
    }

}
