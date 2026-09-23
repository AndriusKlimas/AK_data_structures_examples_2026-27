package a_conversion;

public class CreatingACustomer {
    static void main(String[] args) {
        Customer c = new Customer();
        Customer c2 = new Customer("Helen");

        System.out.println("c: " + c.getName());
        System.out.println("c2: " + c2.getName());

        if(c.equals(c2)){
            System.out.println("These are the same customer!");
        }else{
            System.out.println("Huzzah, we have two customers!");
        }

        if(!c.equals(c2)){
            System.out.println("You are so different.");
        }
    }
}
