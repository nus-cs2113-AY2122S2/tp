package arcs.data.flightbooking;

import java.util.ArrayList;

public class FlightBookingManager {
    private ArrayList<FlightBooking> flightBookings;

    public FlightBookingManager() {
        flightBookings = new ArrayList<>();
    }

    public FlightBookingManager(ArrayList<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public ArrayList<FlightBooking> getAllFlightBookings() {
        return flightBookings;
    }

    public boolean hasDuplicateIc(String ic) {
        for (FlightBooking booking: flightBookings) {
            if (ic.equals(booking.getIc())) {
                return true;
            }
        }
        return false;
    }
}
