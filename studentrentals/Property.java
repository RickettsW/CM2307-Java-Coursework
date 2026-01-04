package studentrentals;

public class Property {

    private String address;
    private String postCode;
    private String description;
    private String area;
    private Homeowner homeowner;

    public Property(String address, String postCode, String description, String area, Homeowner homeowner){

        this.address = address;
        this.postCode = postCode;
        this.description = description;
        this.area = area;
        this.homeowner = homeowner;
    }

    // getters
    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getDescription() {
        return description;
    }

    public String getArea() {
        return area;
    }

    public Homeowner getHomeowner() {
        return homeowner;
    }

    
}




/*future method
calcAverageStarRating()
 */
