/**
 * The Apartment class represents an apartment with its properties and rental information.
 *
 * @author Daniel Shimon
 *  @version (Maman 12 )
 */

public class Apartment {

    // Instance variables
    private int _noOfRooms;
    private double _area;
    private double _price;
    private Person _tenant;
    private Date _rentalStartDate;
    private Date _rentalEndDate;


    // Constants
    private static final int DEFAULT_NO_OF_ROOMS = 3;
    private static final double DEFAULT_AREA = 80;
    private static final double DEFAULT_PRICE = 5000.0;
    private static final int MIN_ROOMS = 1;
    private static final double MIN_AREA = 1;
    private static final double MIN_PRICE = 1;
    private static final int MAX_RENT_DAYS_LEFT = 90;

    //Constructor
    /**
     * Creates a new Apartment object with the specified properties and rental dates.
     *
     * @param noOfRooms the number of rooms in the apartment
     * @param area      the area of the apartment
     * @param price     the rental price of the apartment
     * @param tenant    the tenant currently residing in the apartment
     * @param startDay  the day of the rental start date
     * @param startMonth the month of the rental start date
     * @param startYear  the year of the rental start date
     * @param endDay     the day of the rental end date
     * @param endMonth   the month of the rental end date
     * @param endYear    the year of the rental end date
     */
    public Apartment(int noOfRooms,double area,double price, Person tenant, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
        if (noOfRooms >= MIN_ROOMS)
            _noOfRooms = noOfRooms;
        else
            _noOfRooms = DEFAULT_NO_OF_ROOMS;
        if (area >= MIN_AREA)
            _area = area;
        else
            _area = DEFAULT_AREA;
        if (price >= MIN_PRICE)
            _price = price;
        else
            _price = DEFAULT_PRICE;
        _tenant = new Person(tenant);
        _rentalStartDate = new Date(startDay, startMonth, startYear);
        _rentalEndDate = new Date(endDay, endMonth, endYear);
        if (_rentalEndDate.before(_rentalStartDate) || _rentalEndDate.equals(_rentalStartDate)) {
            _rentalEndDate = _rentalStartDate.addYearsToDate(1);
        }
    }

    //copy constructor
    /**
     * Copy constructor. Creates a new Apartment object that is a deep copy of the given Apartment.
     *
     * @param other the Apartment to be copied
     */
    public Apartment(Apartment other) {
        _noOfRooms = other.getNoOfRooms();
        _area = other.getArea();
        _price = other.getPrice();
        _tenant = new Person(other.getTenant());
        _rentalStartDate = new Date(other.getRentalStartDate());
        _rentalEndDate = new Date(other.getRentalEndDate());
    }



    //getter

    /**
     * Gets the number of rooms in the apartment.
     *
     * @return the number of rooms
     */
    public int getNoOfRooms() {
        return _noOfRooms;
    }


    /**
     * Gets the area of the apartment.
     *
     * @return the area
     */
    public double getArea() {
        return _area;
    }


    /**
     * Gets the rental price of the apartment.
     *
     * @return the price
     */
    public double getPrice() {
        return _price;
    }


    /**
     * Gets the tenant currently residing in the apartment.
     *
     * @return the tenant
     */
    public Person getTenant() {
        return new Person(_tenant);
    }


    /**
     * Gets the rental start date of the apartment.
     *
     * @return the rental start date
     */
    public Date getRentalStartDate() {
        return new Date(_rentalStartDate);
    }


    /**
     * Gets the rental end date of the apartment.
     *
     * @return the rental end date
     */
    public Date getRentalEndDate() {
        return new Date(_rentalEndDate);
    }

    //setters

    /**
     * Sets the number of rooms in the apartment.
     *
     * @param roomsToSet the new number of rooms
     */
    public void setNoOfRooms(int roomsToSet) {
        if (roomsToSet >= MIN_ROOMS)
            _noOfRooms = roomsToSet;
    }


    /**
     * Sets the area of the apartment.
     *
     * @param areaToSet the new area
     */
    public void setArea(int areaToSet) {
        if (areaToSet >= MIN_AREA)
            _area = areaToSet;
    }

    /**
     * Sets the rental price of the apartment.
     *
     * @param priceToSet the new price
     */
    public void setPrice(double priceToSet){
        if (priceToSet >= MIN_PRICE)
            _price = priceToSet;
    }

    /**
     * Sets the tenant currently residing in the apartment.
     *
     * @param tenantToSet the new tenant
     */
    void setTenant(Person tenantToSet){
        _tenant = new Person(tenantToSet);
    }


    /**
     * Sets the rental start date of the apartment.
     *
     * @param startDateToSet the new rental start date
     */
    public  void setRentalStartDate(Date startDateToSet){
    if(_rentalEndDate.after(startDateToSet))
        _rentalStartDate = new Date(startDateToSet);
    }


    /**
     * Sets the rental end date of the apartment.
     *
     * @param endDateToSet the new rental end date
     */
    public void setRentalEndDate(Date endDateToSet){
        if (endDateToSet.after(_rentalStartDate))
            _rentalEndDate = new Date(endDateToSet);
    }


    /**
     * Determines if this apartment is equal to another apartment.
     *
     * @param other the other apartment to compare to
     * @return true if the apartments are equal, false otherwise
     */
    public boolean equals(Apartment other){
        return ((_noOfRooms == other._noOfRooms) &&
                (_area == (other._area)) &&
                (_price == (other._price)) &&
                (_tenant.equals(other._tenant)) &&
                (_rentalStartDate.equals(other._rentalStartDate)) &&
                (_rentalEndDate.equals(other._rentalEndDate)));
    }


    /**
     * Extends the rental period of the apartment by the specified number of years.
     *
     * @param extendYear the number of years to extend the rental period
     */
    public void extendRentalPeriod(int extendYear){
        if(extendYear > 0){
            extendYear = _rentalEndDate.getYear() + extendYear;
            _rentalEndDate.setYear(extendYear);
        }
    }


    /**
     * Gets the number of days remaining in the rental period of the apartment.
     *
     * @param dateToCheck the date to check
     * @return the number of days remaining
     */
    public int daysLeft(Date dateToCheck) {
        if (dateToCheck.after(_rentalEndDate))
            return -1; // Date is after the end of the lease
        return _rentalEndDate.difference(dateToCheck);
    }


    /**
     * Changes the tenant of the apartment.
     *
     * @param startDate the new rental start date
     * @param newPerson the new tenant
     * @param newPrice the new rental price
     * @return true if the change was successful, false otherwise
     */
    public boolean changeTenant(Date startDate, Person newPerson, double newPrice) {
        if (_tenant.getDateOfBirth().before(newPerson.getDateOfBirth()) &&
        _price <= newPrice &&
        daysLeft(startDate) <= MAX_RENT_DAYS_LEFT)
        {
            _tenant = new Person(newPerson);
            _price = newPrice;
            _rentalStartDate = new Date(startDate);
            _rentalEndDate = new Date(startDate.addYearsToDate(1));
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of the apartment.
     *
     * @return the string representation
     */
    public String toString () {
        return "Number of rooms: " + this.getNoOfRooms() +
                "\nArea: " + this.getArea() +
                "\nPrice: " + this.getPrice() + " NIS" +
                "\nTenant name: " + _tenant.getName() +
                "\nRental start date: " + this.getRentalStartDate() +
                "\nRental end date: " + this.getRentalEndDate();


    }
}

