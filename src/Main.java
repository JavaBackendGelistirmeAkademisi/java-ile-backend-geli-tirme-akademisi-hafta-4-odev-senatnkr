import java.util.*;
import java.io.*;

public class Main {
        private static final String ProductFile = "products.txt";
        private static final String OrderFile = "orders.txt";
        private static final String CustomerFile = "customer.txt";
        private static List<Customer> customers = new ArrayList<>();
        public static void main(String[] args) {
            Store store = new Store();
            List<Order> orders = new ArrayList<>();
           // List<Customer> customers = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);

            int choice;

            try {
                store.loadProductsFromFile(ProductFile);
            } catch (IOException e) {
                System.out.println("Ürün dosyası yüklenemedi: " + e.getMessage());
            }

            do {
                // Ana menü seçenekleri
                System.out.println("\n--- E Ticaret ---");
                System.out.println("1. Mağaza");
                System.out.println("2. Müşteri");
                System.out.println("3. Çıkış");
                System.out.print("Seçiminizi Yapın: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Yeni satır karakterini temizlemek için kullanılır.

                if (choice == 1) {
                    // Mağaza alt menüsü
                    System.out.println("Mağaza Seçenekleri:");
                    System.out.println("1. Mağaza Bilgilerini Görüntüle");
                    System.out.println("2. Mağazaya Ürün Ekle");
                    System.out.println("3. Mağazadan Ürün Sil");
                    System.out.print("Seçiminizi Yapın: ");
                    int storeChoice = scanner.nextInt();
                    scanner.nextLine(); // Yeni satır karakterini temizlemek için kullanılır.

                    switch (storeChoice) {
                        case 1:
                            System.out.println("Mağaza Bilgileri:");
                            store.listProducts();
                            break;
                        case 2:
                            System.out.print("Ürün ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Satır atlama hatasını önlemek için
                            System.out.print("Ürün Adı: ");
                            String name = scanner.nextLine();
                            System.out.print("Ürün Fiyatı: ");
                            double price = scanner.nextDouble();
                            System.out.print("Stok: ");
                            int stock = scanner.nextInt();
                            store.addProduct(new Product(id, name, price, stock));
                            System.out.println("Ürün eklendi.");
                            break;
                        case 3:
                            System.out.println("Silmek istediğiniz ürün Id: ");
                            int productId = scanner.nextInt();
                            store.removeProduct(productId);
                            break;
                        default:
                            System.out.println("Geçersiz Id! Lütfen tekrar deneyin.");
                            break;
                    }
                } else if (choice == 2) {
                    // Müşteri alt menüsü
                    System.out.println("Müşteri Seçenekleri:");
                    System.out.println("1. Müşteri Kayıt");
                    System.out.println("2. Müşteri Sipariş Ver");
                    System.out.println("3. Müşteri Siparişlerini Görüntüle");
                    System.out.println("4. Müşteri Bilgileri");

                    System.out.print("Seçiminizi Yapın: ");
                    int customerChoice = scanner.nextInt();
                    scanner.nextLine(); // Yeni satır karakterini temizlemek için kullanılır.

                    switch (customerChoice) {
                        case 1:
                            System.out.print("Müşteri ID: ");
                            int customerId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Müşteri Adı: ");
                            String customerName = scanner.nextLine();
                            System.out.print("Müşteri Adresi: ");
                            String customerAddress = scanner.nextLine();
                            customers.add(new Customer(customerId, customerName, customerAddress));
                            Customer.saveCustomersToFile(CustomerFile, customers); // Müşteri bilgilerini dosyaya kaydet
                            System.out.println("Müşteri eklendi.");
                            break;
                        case 2:
                            System.out.print("Sipariş ID: ");
                            int orderId = scanner.nextInt();
                            System.out.print("Ürün ID: ");
                            int orderProductId = scanner.nextInt();
                            System.out.print("Miktar: ");
                            int quantity = scanner.nextInt();
                            if (store.checkAndProcessOrder(orderProductId, quantity)) {
                                orders.add(new Order(orderId, orderProductId, quantity));
                                System.out.println("Sipariş verildi.");
                            } else {
                                System.out.println("Sipariş gerçekleştirilemedi.");
                            }
                            break;
                        case 3:
                            System.out.println("Müşteri Siparişleri:");
                            if (orders.isEmpty()){
                                System.out.println("Sipariş Bulunmamaktadır.");

                            }else{

                                for (Order order : orders) {
                                    System.out.println(order);

                                }

                            }
                            break;
                        case 4:
                            System.out.println("Müşteri Bilgileri:");
                            if (customers.isEmpty()){
                                System.out.println("Müşteri Bulunmamaktadır.");
                            }else {
                                for (Customer customer : customers){
                                    System.out.println(customer);
                                }
                            }
                            break;
                        default:
                            System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                            break;
                    }
                } else if (choice == 3) {
                    System.out.println("Çıkış yapılıyor...");
                } else {
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                }
            } while (choice != 3);

            try {
                store.saveProductsToFile(ProductFile);
                saveOrdersToFile(orders, OrderFile);

            } catch (IOException e) {
                System.out.println("Dosyaya yazma hatası: " + e.getMessage());
            }

            scanner.close();
        }

        public static void saveOrdersToFile(List<Order> orders, String fileName) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Order order : orders) {
                    writer.write(order.toString());
                    writer.newLine();
                }
            }
        }
    }
