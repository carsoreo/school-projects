package edu.ncsu.csc.CoffeeMaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.ncsu.csc.CoffeeMaker.models.Recipe;
import edu.ncsu.csc.CoffeeMaker.services.RecipeService;

@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )

public class TestDatabaseInteraction {

    @Autowired
    private RecipeService recipeService;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        recipeService.deleteAll();
    }

    /**
     * Tests the RecipeService class
     */
    @Test
    @Transactional
    public void testRecipes () {
        final Recipe r = new Recipe();

        r.setName( "Mocha" );
        r.setPrice( 350 );

        recipeService.save( r );

        final List<Recipe> dbRecipes = recipeService.findAll();

        assertEquals( 1, dbRecipes.size() );

        final Recipe dbRecipe = dbRecipes.get( 0 );

        assertEquals( r.getName(), dbRecipe.getName() );
        assertEquals( r.getPrice(), dbRecipe.getPrice() );

        assertTrue( r.equals( recipeService.findByName( r.getName() ) ) );

        dbRecipe.setPrice( 15 );
        recipeService.save( dbRecipe );

        // Extra test cases
        assertEquals( (Integer) 15, dbRecipe.getPrice() );

        assertEquals( 1, recipeService.count() );

        assertEquals( 15, (int) recipeService.findAll().get( 0 ).getPrice() );
        // assertEquals( 12, (int) recipeService.findAll().get( 0 ).getSugar()
        // );
    }

    /**
     * Tests the RecipeService class
     */
    @Test
    @Transactional
    public void testDeleteRecipeNitin_1 () {
        final Recipe r = new Recipe();
        r.setName( "Taro Milk Tea" );
        recipeService.save( r );
        final List<Recipe> dbRecipes = recipeService.findAll();
        // making sure the recipe exists in the RecipeService object
        assertTrue( recipeService.existsById( r.getId() ) );
        assertEquals( r, recipeService.findById( r.getId() ) );

        // Making sure list of recipes contains newly added recipe
        assertTrue( dbRecipes.contains( r ) );

        recipeService.deleteAll();
        assertEquals( 0, recipeService.count() );
        // Adding recipe back into RecipeService object

    }

    @Test
    @Transactional
    public void testDeleteRecipeNitin_2 () {
        final Recipe r = new Recipe();
        r.setName( "Taro Milk Tea" );
        r.setPrice( 500 );

        recipeService.save( r );
        assertEquals( 1, recipeService.count() );
        final List<Recipe> listOfRecipes = recipeService.findAll();
        final int index = listOfRecipes.indexOf( r );
        assertTrue( listOfRecipes.contains( r ) );
        listOfRecipes.remove( index );
        assertFalse( listOfRecipes.contains( r ) );

    }

    /**
     * Tests the RecipeService class
     */
    @Test
    @Transactional
    public void testUpdateAndCheckClaudine_1 () {
        final Recipe r = new Recipe();
        r.setName( "Taro Milk Tea" );
        r.setPrice( 500 );
        recipeService.save( r );

        assertTrue( recipeService.existsById( r.getId() ) );
        assertEquals( r, recipeService.findById( r.getId() ) );
        assertEquals( 1, recipeService.count() );

        final Recipe r2 = new Recipe();
        r2.setName( "Taro Milk Tea" );
        r2.setPrice( 10 );
        recipeService.save( r2 );

        final List<Recipe> dbRecipes = recipeService.findAll();
        // making sure the recipe exists in the RecipeService object
        assertTrue( recipeService.existsById( r2.getId() ) );
        assertEquals( r, recipeService.findById( r2.getId() ) );

        // Making sure list of recipes contains newly added recipe
        assertTrue( dbRecipes.contains( r ) );
        assertTrue( dbRecipes.contains( r2 ) );
        assertEquals( 2, recipeService.count() );

        // update recipe
        r.updateRecipe( r2 );
        // test checkRecipe()
        assertFalse( r2.checkRecipe() );
        assertFalse( r.checkRecipe() );

    }

    @Test
    @Transactional
    public void testDelete1of2Claudine_2 () {
        final Recipe r = new Recipe();
        r.setName( "Taro Milk Tea" );
        r.setPrice( 500 );

        recipeService.save( r );
        assertEquals( 1, recipeService.count() );
        assertFalse( r.checkRecipe() );

        final Recipe r2 = new Recipe();
        r2.setName( "New Milk Tea" );
        r2.setPrice( 10 );

        recipeService.save( r2 );
        assertEquals( 2, recipeService.count() );
        assertFalse( r2.checkRecipe() );

        final List<Recipe> listOfRecipes = recipeService.findAll();
        final int index = listOfRecipes.indexOf( r );
        final int index2 = listOfRecipes.indexOf( r2 );

        assertTrue( listOfRecipes.contains( r ) );
        assertTrue( listOfRecipes.contains( r2 ) );

        listOfRecipes.remove( index2 );
        listOfRecipes.remove( index );
        assertFalse( listOfRecipes.contains( r ) );
        assertFalse( listOfRecipes.contains( r2 ) );

    }

}
