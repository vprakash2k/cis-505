public class Customer {
    // Private fields
    private String name;
    private String address;
    private String city;
    private String zip;

    // No-argument constructor (default customer)
    public Customer() {
        this.name = "Default Name";
        this.address = "Default Address";
        this.city = "Default City";
        this.zip = "00000";
    }

    // Argument constructor
    public Customer(String name, String address, String city, String zip) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.zip = zip;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    // toString method
    @Override
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nCity: " + city + "\nZip: " + zip;
    }
}
