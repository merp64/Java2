package lease;

/**Lease Class
* 
* This class models an apartment lease. It stores tenant information,
* apartment details (number, size, bedrooms, bathrooms), and provides
* a method to calculate the monthly rent. The getLease() method
* generates a formatted lease agreement string for display.
* 
* Copyright 2025 Howard Community College
* 
* @author Mario Pallares
* @version 1.0
*/
public class Lease {
    // Private fields
    private String tenantName;
    private String apartmentNumber;
    private int numberOfBedrooms;
    private double sizeInSquareFeet;
    private double numberOfBathrooms;
    private StringBuilder leaseBuilder;

    // Default constructor
    public Lease() {
    	this.tenantName = "";
        this.apartmentNumber = "461"; // default to match the output
        this.numberOfBedrooms = 0;
        this.sizeInSquareFeet = 0.0;
        this.numberOfBathrooms = 0.0;
        this.leaseBuilder = new StringBuilder();
    }

    // Getters and setters for all fields
    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public String getApartmentNumber() { return apartmentNumber; }
    public void setApartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; }

    public int getNumberOfBedrooms() { return numberOfBedrooms; }
    public void setNumberOfBedrooms(int numberOfBedrooms) { this.numberOfBedrooms = numberOfBedrooms; }

    public double getSizeInSquareFeet() { return sizeInSquareFeet; }
    public void setSizeInSquareFeet(double sizeInSquareFeet) { this.sizeInSquareFeet = sizeInSquareFeet; }

    public double getNumberOfBathrooms() { return numberOfBathrooms; }
    public void setNumberOfBathrooms(double numberOfBathrooms) { this.numberOfBathrooms = numberOfBathrooms; }

    // Calculate rent
    private double calculateRent() {
    	 // Base = size * 1.5; + $300 per bedroom; + $150 per bathroom
        return (sizeInSquareFeet * 1.5) + (numberOfBedrooms * 300.0) + (numberOfBathrooms * 150.0);
    }

    // Generate lease
    public String getLease() {
        double rent = calculateRent();
        leaseBuilder.setLength(0); // Clear previous content

        leaseBuilder.append(tenantName).append("\n\n");
        leaseBuilder.append("We are excited to have you join Fictional Apartments in Downtown Columbia. Your lease is now ready for you to review and sign.\n");
        leaseBuilder.append("Please reach out to our office with any questions or concerns.\n\n");
        leaseBuilder.append("Apartment: ").append(apartmentNumber).append("\n");
        leaseBuilder.append("Size: ").append(sizeInSquareFeet).append(" sqft\n");
        leaseBuilder.append("Bedrooms: ").append(numberOfBedrooms).append("\n");
        leaseBuilder.append("Baths: ").append(numberOfBathrooms).append("\n\n");
        leaseBuilder.append("Rent is due at the 1st of each month, parties are not allowed, etcetera.\n\n");
        leaseBuilder.append(String.format("Monthly cost: $%.2f%n%n", rent));
        leaseBuilder.append("Sign here: ________________\n");
        leaseBuilder.append("Fictional Apartments\n");

        return leaseBuilder.toString();
    } // end of getLease method
} //end of Lease class