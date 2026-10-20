package edu.ncsu.csc.CoffeeMaker.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.repositories.IngredientRepository;

/**
 * IngredientService class which handles CRUD operations
 *
 * @author clorejol
 *
 */
@Component
@Transactional
public class IngredientService extends Service<Ingredient, Long> {

    /** Ingredient repository to be called */
    @Autowired
    private IngredientRepository ingRepo;

    /**
     * Finds the name of an ingredient
     *
     * @param name
     *            of ingredient to be found
     * @return Ingredient that was found
     */
    public Ingredient findByName ( final String name ) {
        return ingRepo.findByName( name );

    }

    /**
     * Returns autowired instance of ingRepo
     *
     * @return ingRepo autowired instance of ingRepo
     */
    @Override
    protected JpaRepository<Ingredient, Long> getRepository () {
        return ingRepo;
    }

}
