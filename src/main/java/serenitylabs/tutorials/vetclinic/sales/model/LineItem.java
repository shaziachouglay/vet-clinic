package serenitylabs.tutorials.vetclinic.sales.model;

import java.util.Objects;

public class LineItem {
    @Override
    public String toString() {
        return "LineItem{" +
                "unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Double.compare(lineItem.unitPrice, unitPrice) == 0 &&
                quantity == lineItem.quantity &&
                Objects.equals(name, lineItem.name) &&
                category == lineItem.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, quantity, name, category);
    }

    public ProductCategory getCategory() {
        return category;
    }

    private final double unitPrice;
    private final int quantity;
    private final String name;
    private final ProductCategory category;

    public LineItem(double unitPrice, int quantity, String name, ProductCategory category) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.name = name;
        this.category = category;
    }

    public static ItemCalled forASaleOf(int quantity) {
        return new LineItemBuilder(quantity);
    }

    public double getTotal() {
        return quantity * unitPrice;
    }

    public interface ItemCalled{InCategory itemCalled(String name);}
    public interface InCategory{LineItemBuilder inCategory(ProductCategory category);}

    public static class LineItemBuilder implements ItemCalled, InCategory {
        private final int quantity;
        private String name;
        private ProductCategory category;

        public LineItemBuilder(int quantity) {
            this.quantity = quantity;
        }

        public InCategory itemCalled(String name) {
            this.name = name;
            return this;
        }

        public LineItemBuilder inCategory(ProductCategory category) {
            this.category = category;
            return this;
        }

        public LineItem withAUnitPriceOf(double unitPrice) {
            return new LineItem(unitPrice,quantity,name,category);
        }
    }
}
