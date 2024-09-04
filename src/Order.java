public class Order {
    private int orderId;
    private int productId;
    private int quantity;

    public Order(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return orderId + "," + productId + "," + quantity;
    }

    public static Order fromString(String orderString) {
        String[] parts = orderString.split(",");
        int orderId = Integer.parseInt(parts[0]);
        int productId = Integer.parseInt(parts[1]);
        int quantity = Integer.parseInt(parts[2]);
        return new Order(orderId, productId, quantity);
    }
}
