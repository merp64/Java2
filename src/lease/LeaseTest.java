package lease;

import java.util.Scanner;

/**
 * LeaseTest Class
 * 
 * This class creates a Lease object, gathers input from the user,
 * and calls the getLease() method of the Lease object to generate
 * and display a formatted lease to the console.
 * 
 * Copyright 2025 Howard Community College
 * 
 * @author Mario Pallares
 * @version 1.0
 */
public class LeaseTest {
    public static void main(String[] args) {
        Lease lease = new Lease();
        Scanner scanner = new Scanner(System.in);

        // Get user input
        System.out.print("Enter your name: ");
        lease.setTenantName(scanner.nextLine().trim());

        System.out.print("Enter the size of the apartment (int square feet): ");
        lease.setSizeInSquareFeet(scanner.nextInt());

        System.out.print("Enter the number of bedrooms: ");
        lease.setNumberOfBedrooms(scanner.nextInt());
        
        System.out.print("Enter number of bathrooms (e.g., 1, 1.5, 2): ");
        lease.setNumberOfBathrooms(scanner.nextDouble());
        scanner.nextLine();

        // Generate and display lease
        System.out.println();
        System.out.println(lease.getLease());

        scanner.close();
    } //end main
} // end class LeaseTest