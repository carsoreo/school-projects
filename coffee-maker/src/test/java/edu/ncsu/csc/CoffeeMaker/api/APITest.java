package edu.ncsu.csc.CoffeeMaker.api;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.context.WebApplicationContext;

import edu.ncsu.csc.CoffeeMaker.common.TestUtils;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.models.Inventory;
import edu.ncsu.csc.CoffeeMaker.models.Recipe;
import edu.ncsu.csc.CoffeeMaker.services.InventoryService;
import edu.ncsu.csc.CoffeeMaker.services.RecipeService;

@RunWith ( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
public class APITest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RecipeService         service;

    @Autowired
    private InventoryService      iService;

    /**
     * Sets up the tests.
     */
    @Before
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();
    }

    @Test
    @Transactional
    @DeleteMapping ( "resourcePath" )
    public void ensureRecipe () throws Exception {
        String recipe = mvc.perform( get( "/api/v1/recipes" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();

        /* Figure out if the recipe we want is present */
        if ( !recipe.contains( "Mocha" ) ) {
            final Recipe r = new Recipe();
            final Ingredient ingCoffee = new Ingredient( "Coffee", 5 );
            final Ingredient ingMilk = new Ingredient( "Milk", 3 );
            final Ingredient ingSugar = new Ingredient( "Sugar", 4 );
            final Ingredient ingChocholate = new Ingredient( "Chocolate", 8 );
            r.addIngredient( ingCoffee );
            r.addIngredient( ingMilk );
            r.addIngredient( ingSugar );
            r.addIngredient( ingChocholate );
            r.setPrice( 10 );
            r.setName( "Mocha" );

            mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                    .content( TestUtils.asJsonString( r ) ) ).andExpect( status().isOk() );
        }

        recipe = mvc.perform( get( "/api/v1/recipes" ) ).andDo( print() ).andExpect( status().isOk() ).andReturn()
                .getResponse().getContentAsString();

        assertTrue( recipe.contains( "Mocha" ) );

        final Ingredient ingCoffee = new Ingredient( "Coffee", 50 );
        final Ingredient ingMilk = new Ingredient( "Milk", 50 );
        final Ingredient ingSugar = new Ingredient( "Sugar", 50 );
        final Ingredient ingChocolate = new Ingredient( "Chocolate", 50 );
        final List<Ingredient> list = new ArrayList<Ingredient>();
        list.add( ingCoffee );
        list.add( ingMilk );
        list.add( ingSugar );
        list.add( ingChocolate );

        final Inventory inv = new Inventory( list );

        mvc.perform( put( "/api/v1/inventory" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( inv ) ) ).andExpect( status().isOk() );

        mvc.perform( post( String.format( "/api/v1/makecoffee/%s", "Mocha" ) ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( 100 ) ) ).andExpect( status().isOk() ).andDo( print() );

    }

    @Test
    @Transactional
    @DeleteMapping ( "resourcePath" )
    public void deleteRecipeNitin () throws Exception {
        final String recipe = mvc.perform( get( "/api/v1/recipes" ) ).andDo( print() ).andExpect( status().isOk() )
                .andReturn().getResponse().getContentAsString();
        Recipe r = null;
        if ( !recipe.contains( "Mocha" ) ) {
            r = new Recipe();
            final Ingredient ingCoffee = new Ingredient( "Coffee", 5 );
            final Ingredient ingMilk = new Ingredient( "Milk", 3 );
            final Ingredient ingSugar = new Ingredient( "Sugar", 4 );
            final Ingredient ingChocholate = new Ingredient( "Chocolate", 8 );
            r.addIngredient( ingCoffee );
            r.addIngredient( ingMilk );
            r.addIngredient( ingSugar );
            r.addIngredient( ingChocholate );
            r.setPrice( 10 );
            r.setName( "Mocha" );

            mvc.perform( post( "/api/v1/recipes" ).contentType( MediaType.APPLICATION_JSON )
                    .content( TestUtils.asJsonString( r ) ) ).andExpect( status().isOk() );
        }
        mvc.perform( delete( String.format( "/api/v1/recipes/Mocha" ) ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r ) ) ).andExpect( status().isOk() );

        mvc.perform( delete( String.format( "/api/v1/recipes/Mocha" ) ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( r ) ) ).andExpect( status().isNotFound() );

    }

    @Test
    @Transactional
    @DeleteMapping ( "resourcePath" )
    public void editInventoryClaudine () throws Exception {
        service.deleteAll();
        iService.deleteAll();

        final Inventory ivt = iService.getInventory();

        mvc.perform( get( String.format( "/api/v1/inventory" ) ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( ivt ) ) ).andExpect( status().isOk() );

        final Ingredient ingCoffee = new Ingredient( "Coffee", 150 );
        final Ingredient ingMilk = new Ingredient( "Milk", 150 );
        final Ingredient ingSugar = new Ingredient( "Sugar", 150 );
        final Ingredient ingChocolate = new Ingredient( "Chocolate", 150 );
        final List<Ingredient> list = new ArrayList<Ingredient>();
        list.add( ingCoffee );
        list.add( ingMilk );
        list.add( ingSugar );
        list.add( ingChocolate );

        ivt.setIngredients( list );
        iService.save( ivt );

        Assertions.assertEquals( 150, (int) ivt.getIngredients().get( 0 ).getAmount() );
        Assertions.assertEquals( 150, (int) ivt.getIngredients().get( 1 ).getAmount() );
        Assertions.assertEquals( 150, (int) ivt.getIngredients().get( 2 ).getAmount() );
        Assertions.assertEquals( 150, (int) ivt.getIngredients().get( 3 ).getAmount() );

        final Ingredient ingCoffee2 = new Ingredient( "Coffee", 15 );
        final Ingredient ingMilk2 = new Ingredient( "Milk", 15 );
        final Ingredient ingSugar2 = new Ingredient( "Sugar", 15 );
        final Ingredient ingChocolate2 = new Ingredient( "Chocolate", 15 );
        final List<Ingredient> list2 = new ArrayList<Ingredient>();
        list.add( ingCoffee2 );
        list.add( ingMilk2 );
        list.add( ingSugar2 );
        list.add( ingChocolate2 );

        ivt.setIngredients( list2 );
        iService.save( ivt );

    }

}
