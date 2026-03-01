 import java.util.Scanner;
import java.util.ArrayList;

public class InventorySystem {
    
    // ---------- Inner Product Class (simple OOP) ----------
    static class Product {
        // Attributes
        String name;
        int quantity;
        double price;
        
        // Constructor
        Product(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
        
        // Methods
        void display() {
            System.out.println(name + " | Qty: " + quantity + " | Price: " + price + " EGP");
        }
        
        boolean sell(int qty) {
            if (qty <= quantity) {
                quantity -= qty;
                return true;
            }
            return false;
        }
        
        void buy(int qty) {
            quantity += qty;
        }
    }
    // --------------------------------------------------------
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        
        
        System.out.println("================================");
        System.out.println("  SAMPLE INVENTORY SYSTEM ");
        System.out.println("================================");
        
        int choice = 0;
        
        while (choice != 5) {
            
            // Menu
            System.out.println("\n----------------------");
            System.out.println("1. Add product");
            System.out.println("2. Show all products");
            System.out.println("3. Sell product");
            System.out.println("4. Buy product");
            System.out.println("5. Exit");
            System.out.println("----------------------");
            System.out.print("Choose (1-5): ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            // 1. Add product
            if (choice == 1) {
                
                System.out.println("\n--- Add New Product ---");
                
                System.out.print("Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Quantity: ");
                int qty = scanner.nextInt();
                
                System.out.print("Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                
                products.add(new Product(name, qty, price));
                System.out.println(" Added: " + name);
            }
            
            // 2. Show all products
            else if (choice == 2) {
                
                System.out.println("\n--- All Products ---");
                
                if (products.isEmpty()) {
                    System.out.println("No products.");
                } else {
                    for (int i = 0; i < products.size(); i++) {
                        System.out.print((i+1) + ". ");
                        products.get(i).display();
                    }
                }
            }
            
            // 3. Sell product
            else if (choice == 3) {
                
                if (products.isEmpty()) {
                    System.out.println(" No products!");
                    continue;
                }
                
                System.out.println("\n--- Products ---");
                for (int i = 0; i < products.size(); i++) {
                    System.out.print((i+1) + ". ");
                    products.get(i).display();
                }
                
                System.out.print("\nProduct number to sell: ");
                int num = scanner.nextInt() - 1;
                
                if (num < 0 || num >= products.size()) {
                    System.out.println(" Wrong number!");
                    continue;
                }
                
                System.out.print("Quantity to sell: ");
                int sellQty = scanner.nextInt();
                
                Product p = products.get(num);
                
                if (p.sell(sellQty)) {
                    double total = sellQty * p.price;
                    System.out.println(" Sold! Total: " + total + " EGP");
                    System.out.println("   Remaining: " + p.quantity);
                } else {
                    System.out.println(" Not enough! Only " + p.quantity + " left.");
                }
            }
            
            // 4. Buy product
            else if (choice == 4) {
                
                if (products.isEmpty()) {
                    System.out.println(" No products!");
                    continue;
                }
                
                System.out.println("\n--- Products ---");
                for (int i = 0; i < products.size(); i++) {
                    System.out.print((i+1) + ". ");
                    products.get(i).display();
                }
                
                System.out.print("\nProduct number to restock: ");
                int num = scanner.nextInt() - 1;
                
                if (num < 0 || num >= products.size()) {
                    System.out.println(" Wrong number!");
                    continue;
                }
                
                System.out.print("Quantity to add: ");
                int addQty = scanner.nextInt();
                
                Product p = products.get(num);
                p.buy(addQty);
                
                System.out.println(" Added! New quantity: " + p.quantity);
            }
            
            // 5. Exit
            else if (choice == 5) {
                System.out.println("\nGoodbye! ");
            }
            
            else {
                System.out.println(" Wrong choice!");
            }
        }
        
        scanner.close();
    }
}

