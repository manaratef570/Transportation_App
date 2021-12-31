package transport_application;
/**
 *
 * @author Manar Atef
 */

public class Ride {
    
    private final String Source ;
    private final String Destination ;
    private final int Price;
    private discount Discount ;
    
    public Ride(String Source, String Destination , int Price , discount Discount) {
        this.Source = Source;
        this.Destination = Destination;
        this.Price = Price;
        this.Discount=Discount;
    }

    public int getPrice() {
        
        return Discount.update_Price(Price);
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }
}

