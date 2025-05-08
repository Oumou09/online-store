package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                  displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        // This method should read a CSV file with product information and
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    String productId = parts[0];
                    String productDescription = parts[1];
                    double productPrice = Double.parseDouble(parts[2]);
                    Product newProduct = new Product(productId, productDescription, productPrice);
                    inventory.add(newProduct);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // populate the inventory ArrayList with com.pluralsight.Product objects. Each line
        // of the CSV file contains product information in the following format:
        //
        // id,name,price
        //
        // where id is a unique string identifier, name is the product name,
        // price is a double value representing the price of the product
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        // This method should display a list of products from the inventory,
        while (true) {
            System.out.println("1. Add product to cart");
            System.out.println("2. Go back");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            if (input.equals("2")) {
                break;
            } else if (input.equals("1")) {
                System.out.print("Enter product ID to add to cart: ");
                String id = scanner.nextLine();

                Product found = findProductById(id, inventory);
                if (found != null) {
                    cart.add(found);
                    System.out.println("Added to cart: " + found);
                } else {
                    System.out.println("Product not found.");
                }
            } else {
                System.out.println("Invalid choice.");
            }

            System.out.println("3. Checkout");
            System.out.println("4. Exit");
        }
    }


    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount){
            // This method should display the items in the cart ArrayList, along
        totalAmount = 0;
        System.out.println("===== Your Cart =====");
        for (Product product : cart) {
            System.out.println(product);
            totalAmount += product.getProductPrice();
        }
        System.out.printf("Total Amount: $%.2f\n", totalAmount);

        System.out.print("Enter the product ID to remove from cart (or press Enter to skip): ");
        String idToRemove = scanner.nextLine().trim();

        if (!idToRemove.isEmpty()) {
            Product toRemove = null;
            for (Product product : cart) {
                if (product.getProductId().equalsIgnoreCase(idToRemove)) {
                    toRemove = product;
                    break;
                }
            }
            if (toRemove != null) {
                cart.remove(toRemove);
                System.out.println("Removed: " + toRemove);
            } else {
                System.out.println("Product ID not found in cart.");
            }
        }
    }

//        for (Product product : cart){
//            System.out.printf(String.valueOf(product));
//            if (product.getProductId()){
//                System.out.printf(String.valueOf((product)));

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        Scanner scanner = new Scanner(System.in);
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("===== Checkout Summary =====");
        totalAmount = 0.0;
        for (Product product : cart) {
            System.out.println(product);
            totalAmount += product.getProductPrice();
        }

        System.out.printf("Total: $%.2f\n", totalAmount);

        System.out.print("Do you want to proceed with the purchase? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            System.out.print("Enter amount paid: $");
            try {
                double amountPaid = Double.parseDouble(scanner.nextLine());

                if (amountPaid >= totalAmount) {
                    double change = amountPaid - totalAmount;
                    System.out.printf("Purchase confirmed. Your change: $%.2f\n", change);
                    cart.clear();
                    System.out.println("Thank you for your purchase!");
                } else {
                    System.out.println("Insufficient amount. Transaction canceled.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered. Transaction canceled.");
            }
        } else {
            System.out.println("Purchase canceled.");
        }
    }
        // This method should calculate the total cost of all items in the cart,
        // and display a summary of the purchase to the user. The method should
        // prompt the user to confirm the purchase, and calculate change and clear the cart
        // if they confirm.

   public static Product findProductById(String id, ArrayList<Product> inventory) {
       for (Product product : inventory) {
           if (product.getProductId().equalsIgnoreCase(id)) {
               return product;
           }
       }
       return null;
   }


}


