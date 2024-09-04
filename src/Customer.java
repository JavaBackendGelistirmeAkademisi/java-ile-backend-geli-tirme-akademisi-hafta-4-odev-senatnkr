import java.io.*;
import java.util.*;
public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;

    public Customer(int customerId, String customerName, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }


    public String toString() {
        return customerId + "," + customerName + "," + customerAddress;
    }

    public static void saveCustomersToFile(String filename, List<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (Customer customer : customers) {
                bw.write(customer.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Müşteri dosyası yazma hatası: " + e.getMessage());
        }
    }

    public static List<Customer> loadCustomersFromFile(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    Customer customer = new Customer(Integer.parseInt(details[0]), details[1], details[2]);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.err.println("Müşteri dosyası okuma hatası: " + e.getMessage());
        }
        return customers;
    }
}
