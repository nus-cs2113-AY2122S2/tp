package arcs.data.flightbooking;


public class FlightBooking {
    private String ic;
    private String name;
    private String phone;
    private String email;
    private String fid;

    public FlightBooking(String ic, String name, String phone, String email, String fid) {
        this.ic = ic;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.fid = fid;
    }

    public String getIc() {
        return ic;
    }


}
