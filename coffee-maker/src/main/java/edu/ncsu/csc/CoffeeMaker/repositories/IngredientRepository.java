package edu.ncsu.csc.CoffeeMaker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.CoffeeMaker.models.Ingredient;

/**
 * Tells Spring we want to store a collection of Ingredient objects, each
 * identified by a Long primary key
 *
 * @author cfbunao
 *
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    /**
     * finds an ingredient object based off the name provided
     *
     * @param name
     *            name of ingredient to find
     *
     * @return Ingredient object or null if the object does not exist
     *
     */
    Ingredient findByName ( String name );
}
