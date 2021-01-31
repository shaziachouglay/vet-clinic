package serenitylabs.tutorials.vetclinic.sales.model;

import java.util.Objects;

public class SalesTax {
    public double getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTax salesTax = (SalesTax) o;
        return Double.compare(salesTax.rate, rate) == 0 &&
                Double.compare(salesTax.amount, amount) == 0 &&
                Objects.equals(name, salesTax.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, name, amount);
    }

    private final double rate;
    private final String name;

    @Override
    public String toString() {
        return "SalesTax{" +
                "rate=" + rate +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    private final double amount;

    public SalesTax(double rate, String name, double amount) {
        this.rate = rate;
        this.name = name;
        this.amount = amount;
    }

    public static WithName atRateOf(double rate) {
        return new TaxRateBuilder(rate);
    }

    public interface WithName{TaxRateBuilder withName(String name);}

    public static class TaxRateBuilder implements WithName {
        private double rate;
        private String name;
        private double amount;

        public TaxRateBuilder(double rate) {
            this.rate = rate;
        }

        public TaxRateBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SalesTax forAnAmountOf(double amount) {
            return new SalesTax(rate,name,amount);
        }
    }
}
