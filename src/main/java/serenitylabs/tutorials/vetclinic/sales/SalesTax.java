package serenitylabs.tutorials.vetclinic.sales;

public class SalesTax {
    public static WithName atRateOf(double rate) {
        return new TaxEntryBuilder(rate);
    }
    public interface WithName{TaxEntryBuilder withName(String name);}

    public static class TaxEntryBuilder implements WithName {
        private double rate;
        private String name;

        public TaxEntryBuilder(double rate) {
            this.rate = rate;
        }

        public TaxEntryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SalesTax ForAnAmountOf(double amount) {
            return new SalesTax();
        }
    }
}
