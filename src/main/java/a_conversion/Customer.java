package a_conversion;

import java.util.Objects;

public class Customer {
    private String name;
    private static int customerCount;

    public Customer(String name){
        this.name = name;
        customerCount++;
    }

    public Customer(){
        this.name = "Joe Bloggs";
        customerCount++;
    }

    public static int getCustomerCount(){
        return Customer.customerCount;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Customer{name=\"" + name + "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return Objects.equals(name.toLowerCase(),
                customer.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name.toLowerCase());
    }
}
