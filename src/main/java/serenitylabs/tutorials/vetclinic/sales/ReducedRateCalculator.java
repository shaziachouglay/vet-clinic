package serenitylabs.tutorials.vetclinic.sales;

import serenitylabs.tutorials.vetclinic.sales.model.LineItem;

public class ReducedRateCalculator implements TaxRateCalculator {
    @Override
    public TaxRate rateFor(LineItem item) {
        if (item.getQuantity() > 5){
            return new TaxRate(0.135,"Reduced");
        }
        return new TaxRate(0.09,"Reduced");
    }
}
