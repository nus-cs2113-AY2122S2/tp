package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class TravelPackage {
    public static DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String EXAMPLENAME = "Switzerland - Conquer Summits";
    public static final int EXAMPLEID = 999;
    public static final String EXAMPLESTART = "13/09/2022";
    public static final String EXAMPLEEND = "22/10/2022";
    public static final String EXAMPLEHOTEL = "EXAMPLE HOTEL";
    public static final double EXAMPLEPRICE = 99.99;
    public static final String EXAMPLECOUNTRY = "Switzerland";
    public static final int EXAMPLEMAX = 10;

    private static final DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String name;
    private final int id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String hotel;
    private final double price;
    private final String country;
    private final int maxParticipants;
    private int numParticipants;
    private Reservations reservationList;

    public TravelPackage(String name, int id, LocalDate startDate, LocalDate endDate,
                         String hotel, double price, String country, int maxParticipants) {
        this.name = name;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotel = hotel;
        this.price = price;
        this.country = country;
        this.maxParticipants = maxParticipants;
        this.numParticipants = 0;
        this.reservationList = new Reservations();
    }

    public TravelPackage(String name, int id, LocalDate startDate, LocalDate endDate,
            String hotel, double price, String country, int maxParticipants, int numParticipants) {
        this.name = name;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotel = hotel;
        this.price = price;
        this.country = country;
        this.maxParticipants = maxParticipants;
        this.numParticipants = numParticipants;
        this.reservationList = new Reservations();
    }

    public boolean isFull() {
        return this.numParticipants >= maxParticipants;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStartDate() {
        return this.startDate.format(printFormat);
    }

    public LocalDate getLocalStartDate() {
        return this.startDate;
    }

    public LocalDate getLocalEndDate() {
        return this.endDate;
    }

    public String getEndDate() {
        return this.endDate.format(printFormat);
    }

    public String getHotel() {
        return this.hotel;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCountry() {
        return this.country;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public int getNumParticipants() {
        return this.numParticipants;
    }

    public Reservations getReservationList() {
        return this.reservationList;
    }

    public void addParticipants(int addParticipants) {
        this.numParticipants = this.numParticipants + addParticipants;
    }

    public void removeParticipants(int removeParticipants) {
        this.numParticipants = this.numParticipants - removeParticipants;
    }

    public void setReservationList(Reservations r) {
        this.reservationList = r;
    }

    public String toString() {
        return "Here are the details for "
                + this.name + ", Travel Package ID of "
                + this.getID() + "\nCountry: "
                + this.country + "\nPrice: "
                + this.price + "\nHotel: "
                + this.hotel + "\nVacancies Filled: "
                + this.numParticipants + "/" + this.maxParticipants + "\nTravel Period: "
                + this.startDate + " to " + this.endDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        TravelPackage cur = (TravelPackage) obj;

        if (cur.getID() != this.getID()) {
            return false;
        }

        if (!cur.getName().equals(this.getName())) {
            return false;
        }

        if (!cur.getStartDate().equals(this.getStartDate())) {
            return false;
        }

        if (!cur.getEndDate().equals(this.getEndDate())) {
            return false;
        }

        if (!cur.getHotel().equals(this.getHotel())) {
            return false;
        }

        if (cur.getPrice() != this.getPrice()) {
            return false;
        }

        if (!cur.getCountry().equals(this.getCountry())) {
            return false;
        }

        return cur.getMaxParticipants() == this.getMaxParticipants();
    }

    public String saveTravelPackage() {
        String str = toSave() + "$";
        for (int i = 0; i < reservationList.getReservationSize(); i++) {
            str += reservationList.getReservation(i).toSave() + "%";
        }
        return str;
    }

    public String toSave() {
        return name + " | " +
                id + " | " +
                startDate.format(Parser.PARSE_FORMAT) + " | " +
                endDate.format(Parser.PARSE_FORMAT) + " | " +
                hotel + " | " +
                price + " | " +
                country + " | " +
                maxParticipants + " | " +
                numParticipants;
    }

}
