package edu.ncsu.csc.CoffeeMaker.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import edu.ncsu.csc.CoffeeMaker.TestConfig;
import edu.ncsu.csc.CoffeeMaker.models.Ingredient;
import edu.ncsu.csc.CoffeeMaker.models.Inventory;
import edu.ncsu.csc.CoffeeMaker.models.Recipe;
import edu.ncsu.csc.CoffeeMaker.services.InventoryService;

@ExtendWith ( SpringExtension.class )
@EnableAutoConfiguration
@SpringBootTest ( classes = TestConfig.class )
public class InventoryTest {

    @Autowired
    private InventoryService inventoryService;

    @BeforeEach
    public void setup () {
        final Inventory ivt = inventoryService.getInventory();

        final Ingredient ingCoffee = new Ingredient( "Coffee", 500 );
        final Ingredient ingMilk = new Ingredient( "Milk", 500 );
        final Ingredient ingSugar = new Ingredient( "Sugar", 500 );
        final Ingredient ingChocolate = new Ingredient( "Chocolate", 500 );
        final List<Ingredient> list = new ArrayList<Ingredient>();
        list.add( ingCoffee );
        list.add( ingMilk );
        list.add( ingSugar );
        list.add( ingChocolate );
        ivt.addIngredients( list );
        inventoryService.save( ivt );
    }

    @Test
    @Transactional
    public void testConsumeInventory () {
        final Inventory i = inventoryService.getInventory();

        final Recipe recipe = new Recipe();
        recipe.setName( "Delicious Not-Coffee" );
        final Ingredient ingCoffee = new Ingredient( "Coffee", 1 );
        final Ingredient ingMilk = new Ingredient( "Milk", 20 );
        final Ingredient ingSugar = new Ingredient( "Sugar", 5 );
        final Ingredient ingChocholate = new Ingredient( "Chocolate", 10 );
        recipe.addIngredient( ingCoffee );
        recipe.addIngredient( ingMilk );
        recipe.addIngredient( ingSugar );
        recipe.addIngredient( ingChocholate );

        recipe.setPrice( 5 );

        i.useIngredients( recipe );

        /*
         * Make sure that all of the inventory fields are now properly updated
         * (indexes correspond to correct ingredient)
         */
        Assertions.assertEquals( 499, (int) i.getIngredients().get( 0 ).getAmount() );
        Assertions.assertEquals( 480, (int) i.getIngredients().get( 1 ).getAmount() );
        Assertions.assertEquals( 495, (int) i.getIngredients().get( 2 ).getAmount() );
        Assertions.assertEquals( 490, (int) i.getIngredients().get( 3 ).getAmount() );

    }

    @Test
    @Transactional
    public void testAddInventory1 () {
        Inventory ivt = inventoryService.getInventory();


        final Ingredient ingCoffee = new Ingredient( "Coffee", 5 );
        final Ingredient ingMilk = new Ingredient( "Milk", 3 );
        final Ingredient ingSugar = new Ingredient( "Sugar", 7 );
        final Ingredient ingChocolate = new Ingredient( "Chocolate", 2 );
        final List<Ingredient> list = new ArrayList<Ingredient>();
        list.add( ingCoffee );
        list.add( ingMilk );
        list.add( ingSugar );
        list.add( ingChocolate );
        ivt.addIngredients( list );

        /* Save and retrieve again to update with DB */
        inventoryService.save( ivt );

        ivt = inventoryService.getInventory();

        Assertions.assertEquals( 505, (int) ivt.getIngredients().get( 0 ).getAmount(),
                "Adding to the inventory should result in correctly-updated values for coffee" );
        Assertions.assertEquals( 503, (int) ivt.getIngredients().get( 1 ).getAmount(),
                "Adding to the inventory should result in correctly-updated values for milk" );
        Assertions.assertEquals( 507, (int) ivt.getIngredients().get( 2 ).getAmount(),
                "Adding to the inventory should result in correctly-updated values sugar" );
        Assertions.assertEquals( 502, (int) ivt.getIngredients().get( 3 ).getAmount(),
                "Adding to the inventory should result in correctly-updated values chocolate" );

    }

    @Test
    @Transactional
    public void testAddInventory2 () {
        final Inventory ivt = inventoryService.getInventory();

        try {
            final Ingredient ingCoffee = new Ingredient( "Coffee", -5 );
            final Ingredient ingMilk = new Ingredient( "Milk", 3 );
            final Ingredient ingSugar = new Ingredient( "Sugar", 7 );
            final Ingredient ingChocolate = new Ingredient( "Chocolate", 2 );
            final List<Ingredient> list = new ArrayList<Ingredient>();
            list.add( ingCoffee );
            list.add( ingMilk );
            list.add( ingSugar );
            list.add( ingChocolate );
            ivt.addIngredients( list );
        }
        catch ( final IllegalArgumentException iae ) {
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 0 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for coffee should result in no changes -- coffee" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 1 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for coffee should result in no changes -- milk" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 2 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for coffee should result in no changes -- sugar" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 3 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for coffee should result in no changes -- chocolate" );
        }
    }

    @Test
    @Transactional
    public void testAddInventory3 () {
        final Inventory ivt = inventoryService.getInventory();

        try {
            final Ingredient ingCoffee = new Ingredient( "Coffee", 5 );
            final Ingredient ingMilk = new Ingredient( "Milk", -3 );
            final Ingredient ingSugar = new Ingredient( "Sugar", 7 );
            final Ingredient ingChocolate = new Ingredient( "Chocolate", 2 );
            final List<Ingredient> list = new ArrayList<Ingredient>();
            list.add( ingCoffee );
            list.add( ingMilk );
            list.add( ingSugar );
            list.add( ingChocolate );
            ivt.addIngredients( list );
        }
        catch ( final IllegalArgumentException iae ) {
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 0 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for milk should result in no changes -- coffee" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 1 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for milk should result in no changes -- milk" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 2 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for milk should result in no changes -- sugar" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 3 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for milk should result in no changes -- chocolate" );

        }

    }

    @Test
    @Transactional
    public void testAddInventory4 () {
        final Inventory ivt = inventoryService.getInventory();

        try {
            final Ingredient ingCoffee = new Ingredient( "Coffee", 5 );
            final Ingredient ingMilk = new Ingredient( "Milk", 3 );
            final Ingredient ingSugar = new Ingredient( "Sugar", -7 );
            final Ingredient ingChocolate = new Ingredient( "Chocolate", 2 );
            final List<Ingredient> list = new ArrayList<Ingredient>();
            list.add( ingCoffee );
            list.add( ingMilk );
            list.add( ingSugar );
            list.add( ingChocolate );
            ivt.addIngredients( list );
        }
        catch ( final IllegalArgumentException iae ) {
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 0 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for sugar should result in no changes -- coffee" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 1 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for sugar should result in no changes -- milk" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 2 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for sugar should result in no changes -- sugar" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 3 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for sugar should result in no changes -- chocolate" );

        }

    }

    @Test
    @Transactional
    public void testAddInventory5 () {
        final Inventory ivt = inventoryService.getInventory();

        try {
            final Ingredient ingCoffee = new Ingredient( "Coffee", 5 );
            final Ingredient ingMilk = new Ingredient( "Milk", 3 );
            final Ingredient ingSugar = new Ingredient( "Sugar", 7 );
            final Ingredient ingChocolate = new Ingredient( "Chocolate", -2 );
            final List<Ingredient> list = new ArrayList<Ingredient>();
            list.add( ingCoffee );
            list.add( ingMilk );
            list.add( ingSugar );
            list.add( ingChocolate );
            ivt.addIngredients( list );
        }
        catch ( final IllegalArgumentException iae ) {
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 0 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for chocolate should result in no changes -- coffee" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 1 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for chocolate should result in no changes -- milk" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 2 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for chocolate should result in no changes -- sugar" );
            Assertions.assertEquals( 500, (int) ivt.getIngredients().get( 3 ).getAmount(),
                    "Trying to update the Inventory with an invalid value for chocolate should result in no changes -- chocolate" );

        }

    }

    @Test
    @Transactional
    public void testCheckInventory () {

        final Inventory ivt = inventoryService.getInventory();

        // Create recipe invalid
        final Recipe recipe = new Recipe();
        recipe.setName( "Delicious Not-Coffee" );
        final Ingredient ingCoffee = new Ingredient( "Coffee", 1000 );
        final Ingredient ingMilk = new Ingredient( "Milk", 2000 );
        final Ingredient ingSugar = new Ingredient( "Sugar", 5000 );
        final Ingredient ingChocholate = new Ingredient( "Chocolate", 1000 );
        recipe.addIngredient( ingCoffee );
        recipe.addIngredient( ingMilk );
        recipe.addIngredient( ingSugar );
        recipe.addIngredient( ingChocholate );

        recipe.setPrice( 5 );

        // Create recipe valid
        final Recipe recipe2 = new Recipe();
        recipe2.setName( "Delicious Not-Coffee2" );
        final Ingredient ingCoffee3 = new Ingredient( "Coffee", 3 );
        final Ingredient ingMilk3 = new Ingredient( "Milk", 3 );
        final Ingredient ingSugar3 = new Ingredient( "Sugar", 3 );
        final Ingredient ingChocholate3 = new Ingredient( "Chocolate", 3 );
        recipe2.addIngredient( ingCoffee3 );
        recipe2.addIngredient( ingMilk3 );
        recipe2.addIngredient( ingSugar3 );
        recipe2.addIngredient( ingChocholate3 );

        recipe2.setPrice( 5 );

        // Should return false
        assertFalse( ivt.enoughIngredients( recipe ) );

        // Should return true
        assertTrue( ivt.enoughIngredients( recipe2 ) );

    }

}
