package serenitylabs.tutorials.vetclinic.sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import serenitylabs.tutorials.vetclinic.sales.model.LineItem;
import serenitylabs.tutorials.vetclinic.sales.model.ProductCategory;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static serenitylabs.tutorials.vetclinic.sales.model.ProductCategory.*;
import static serenitylabs.tutorials.vetclinic.sales.model.ProductCategory.Toys;

@RunWith(Parameterized.class)
public class WhenApplyinhSalesTax {

    @Parameterized.Parameters(name = "{0} x {1} in category {2} costing €{3}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {1, "crisps",        Snacks, 3.00,       0.09, "Reduced",    0.27},
                {50,"crisps",        Snacks, 3.00,       0.135, "Reduced",  20.25},
                {1, "training dogs", Books,  5.00,       0.0, "Zero",         0.0},
                {1, "pills",         Medicine, 5.00,     0.0, "Zero",         0.0},
                {1, "rubber bone",   Toys,    10.00,     0.23, "Standard",   2.30},
        });
    }

    private int quantity;
    private String name;
    private ProductCategory category;
    private double unitPrice;
    private double expectedRate;
    private String expectedRateName;
    private double expectedAmount;

    public WhenApplyinhSalesTax(int quantity, String name, ProductCategory category, double unitPrice, double expectedRate, String expectedRateName, double expectedAmount) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.expectedRate = expectedRate;
        this.expectedRateName = expectedRateName;
        this.expectedAmount = expectedAmount;
    }

    @Test
    public void items_shd_be_charged_at_the_correct_rate(){
        //GIVEN
        LineItem item = LineItem.forASaleOf(quantity)
                .itemCalled(name)
                .inCategory(category)
                .withAUnitPriceOf(unitPrice);
        //WHEN
        SalesTaxService salesTaxService = new SalesTaxService();
        SalesTax calculatedSalesTax = salesTaxService.salesTaxEntryFor(item);

        //THEN
        SalesTax expectedSalesTax = SalesTax.atRateOf(expectedRate).withName(expectedRateName).ForAnAmountOf(expectedAmount);


    }
}
