package edu.ncsu.csc.CoffeeMaker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.services.IngredientService;

/**
 * API controller for Ingredients
 *
 * @author clorejol
 *
 */
@SuppressWarnings ( { "rawtypes", "unchecked" } )
@RestController
public class APIIngredientController extends APIController {

    /**
     * RecipeService object, to be autowired in by Spring to allow for
     * manipulating the Recipe model
     */
    @Autowired
    private IngredientService ingredientService;

    /**
     * REST API method to provide GET access to all ingredients in the system
     *
     * @return JSON representation of all ingredients
     */
    @GetMapping ( BASE_PATH + "/ingredients" )
    public List<Ingredient> getAllIngredients () {
        return ingredientService.findAll();
    }

    /**
     * Get a single ingredient
     *
     * @param name
     *            name of the ingredient
     * @return ResponseEntity HttpStatus successful or not
     */
    @GetMapping ( BASE_PATH + "/ingredients/{name}" )
    public ResponseEntity getIngredient ( @PathVariable ( "name" ) final String name ) {
        // variable passed in by the user will be part of the url

        // calls methods in the persistent class to get the Ingredient
        final Ingredient ingr = ingredientService.findByName( name );

        if ( null == ingr ) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity( ingr, HttpStatus.OK );
    }

    /**
     * Endpoint for creating an ingredient
     *
     * @param ingredient
     *            to created
     * @return ResponseEntity HttpStatus successful or notResponseEntity
     *         HttpStatus successful or not
     */
    @PostMapping ( BASE_PATH + "/ingredients" )
    public ResponseEntity createIngredient ( @RequestBody final Ingredient ingredient ) {
        if ( null != ingredientService.findByName( ingredient.getName() ) ) {
            return new ResponseEntity( HttpStatus.CONFLICT );
        }
        try {
            ingredientService.save( ingredient );
            return new ResponseEntity( HttpStatus.CREATED );
        }
        catch ( final Exception e ) {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Endpoint for updating in ingredient
     *
     * @param name
     *            of ingredient to be updated
     * @param editIng
     *            ingredient with new parameters
     * @return ResponseEntity HttpStatus successful or not
     */
    @PutMapping ( BASE_PATH + "/ingredients/{name}" )
    public ResponseEntity updateIngredient ( @PathVariable ( "name" ) final String name,
            @RequestBody final Ingredient editIng ) {
        final Ingredient ing = ingredientService.findByName( name );
        if ( null == ing ) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }
        if ( null == editIng ) {
            return new ResponseEntity( HttpStatus.NO_CONTENT );
        }

        ing.setAmount( editIng.getAmount() );
        ing.setName( editIng.getName() );
        try {
            ingredientService.delete( ing );
            ingredientService.save( editIng );
        }
        catch ( final Exception e ) {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }

        return new ResponseEntity( HttpStatus.OK );
    }

    /**
     * Endpoint for deleting an ingredient
     *
     * @param name
     *            of ingredient to be deleted
     * @return ResponseEntity HttpStatus successful or not
     */
    @DeleteMapping ( BASE_PATH + "/ingredients/{name}" )
    public ResponseEntity deleteIngredient ( @PathVariable final String name ) {
        final Ingredient ing = ingredientService.findByName( name );
        if ( null == ing ) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }
        try {
            ingredientService.delete( ing );
        }
        catch ( final Exception e ) {
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }
        return new ResponseEntity( HttpStatus.OK );
    }
}
