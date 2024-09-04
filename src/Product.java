public class Product {
    private int productId;
    private String name;
    private double price;
    private int stock;
    public Product(int productId, String name, double price, int stock){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return productId + "," + name + "," + price + "," + stock;
    }


    public static Product fromString(String productString) {
        String[] parts = productString.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        int stock = Integer.parseInt(parts[3]);
        return new Product(id, name, price, stock);
    }
}
