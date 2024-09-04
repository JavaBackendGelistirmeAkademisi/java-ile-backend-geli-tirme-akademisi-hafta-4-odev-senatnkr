import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;

    public Store() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public  void removeProduct(int productId){
        products.removeIf(product -> product.getProductId() == productId); //*
        System.out.println("Ürün Silindi. ");
    }

    public void updateProduct(int productId, int newStock) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                product.setStock(newStock);
                System.out.println("Ürün güncellendi.");
                return;
            }
        }
        System.out.println("Ürün bulunamadı.");
    }

    public void listProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void saveProductsToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }

    public void loadProductsFromFile(String fileName) throws IOException {
        products.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Product.fromString(line);
                products.add(product);
            }
        }
    }
    public boolean checkAndProcessOrder(int productId, int quantity) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                if (product.getStock() >= quantity) {
                    product.setStock(product.getStock() - quantity);
                    System.out.println("Sipariş onaylandı. Kalan stok: " + product.getStock());
                    return true;
                } else {
                    System.out.println("Yetersiz stok. Mevcut stok: " + product.getStock());
                    return false;
                }
            }
        }
        System.out.println("Ürün bulunamadı.");
        return false;
    }
}
