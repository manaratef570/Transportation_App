package transport_application;
/**
 *
 * @author Manar Atef
 */
public class Ride {
    
    private final String Source ;
    private final String Destination ;
    private final int Price;
     
    public Ride(String Source, String Destination , int Price) {
        this.Source = Source;
        this.Destination = Destination;
        this.Price = Price;
    }

    public int getPrice() {
        return Price;
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }
}

