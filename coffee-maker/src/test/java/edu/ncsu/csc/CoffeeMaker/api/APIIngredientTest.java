package edu.ncsu.csc.CoffeeMaker.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.ncsu.csc.CoffeeMaker.common.TestUtils;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.services.IngredientService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith ( SpringExtension.class )
public class APIIngredientTest {

    /**
     * MockMvc uses Spring's testing framework to handle requests to the REST
     * API
     */
    private MockMvc               mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private IngredientService     service;

    /**
     * Sets up the tests.
     */
    @BeforeEach
    public void setup () {
        mvc = MockMvcBuilders.webAppContextSetup( context ).build();

        service.deleteAll();
    }

    /**
     * testing creating an ingredient
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void createIngredient () throws Exception {
        service.deleteAll();
        final Ingredient ing = new Ingredient( "Milk", 2 );
        mvc.perform( post( "/api/v1/ingredients" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( ing ) ) ).andExpect( status().isCreated() );

    }

    /**
     * testing creating an ingredient
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void createIngredientInvalid () throws Exception {

        service.deleteAll();
        final Ingredient ing = new Ingredient( "Milk", 2 );

        mvc.perform( post( "/api/v1/ingredients" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( ing ) ) ).andExpect( status().isCreated() );

        mvc.perform( post( "/api/v1/ingredients" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( ing ) ) ).andExpect( status().isConflict() );
    }

    /**
     * testing deleting ingredient from controller
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void deleteIngredient () throws Exception {
        final Ingredient ing = new Ingredient( "Milk", 20 );
        service.save( ing );

        mvc.perform( delete( "/api/v1/ingredients/Milk" ) ).andExpect( status().isOk() );

    }

    /**
     * Testing getting all ingredients from Controller
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void getIngredient () throws Exception {

        final Ingredient ing = new Ingredient( "Milk", 2 );
        service.save( ing );
        mvc.perform( get( "/api/v1/ingredients/Milk" ) ).andExpect( status().isOk() );

    }

    /**
     * Testing editing ingredient
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void editIngredient () throws Exception {
        final Ingredient ing = new Ingredient( "Milk", 20 );
        service.save( ing );

        final Ingredient ing2 = new Ingredient( "Coffee", 20 );

        mvc.perform( put( "/api/v1/ingredients/Milk" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( ing ) ) ).andExpect( status().isOk() );

        mvc.perform( put( "/api/v1/ingredients/Coffee" ).contentType( MediaType.APPLICATION_JSON )
                .content( TestUtils.asJsonString( ing2 ) ) ).andExpect( status().isNotFound() );

    }

}
