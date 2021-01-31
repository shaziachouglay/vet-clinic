package serenitylabs.tutorials.vetclinic.sales.model;

public class LineItem {
    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "quantity=" + quantity +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", unitPrice=" + unitPrice +
                '}';
    }

    private final int quantity;
    private final String name;
    private final ProductCategory category;
    private final double unitPrice;

    public LineItem(int quantity, String name, ProductCategory category, double unitPrice) {
        this.quantity = quantity;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
    }
    public double getTotal(){
        return quantity *unitPrice;
    }

    public static ItemCalled forASaleOf(int quantity) {
        return new LineItemBuilder(quantity);
    }
    public interface ItemCalled{InCategory itemCalled(String name);}
    public interface InCategory{LineItemBuilder inCategory(ProductCategory category);}


    public static class LineItemBuilder implements ItemCalled,InCategory {
        private int quantity;
        private String name;
        private ProductCategory category;

        public LineItemBuilder(int quantity) {
            this.quantity = quantity;
        }

        public LineItemBuilder itemCalled(String itemName) {
            this.name = itemName;
            return this;
        }

        public LineItemBuilder inCategory(ProductCategory cateogory) {
            this.category = cateogory;
            return this;
        }

        public LineItem withAUnitPriceOf(double unitPrice) {
            return new LineItem(quantity,name,category,unitPrice);
        }
    }
}
