
/**
 * This class represents a person with a name, ID, and date of birth.
 * It provides methods to create, access, and modify person information,
 * as well as compare Person objects based on their date of birth.
 *
 * @author Daniel Shimon
 * @version (Maman 12 )
 */
public class Person {


    // Instance variables
    private String _name;
    private String _id;
    private Date _dateOfBirth;


    // Constants
    private static final String DEFAULT_ID = "000000000";  // Default ID
    private static final String DEFAULT_NAME = "Someone";  // Default ID
    private static final Date  INVALID_DATE= new Date(1, 1 ,2000); //
    private static final int VALID_ID_LENGTH = 9;

    /**
     * Creates a new Person object with the specified name, date of birth, and ID.
     *
     * @param name  the name of the person
     * @param day   the day of birth (1-31)
     * @param month the month of birth (1-12)
     * @param year  the year of birth
     * @param id    the person's ID
     */
    //Constructor
    public Person(String name, int day, int month, int year, String id) {
        if (name.equals(""))
            _name = DEFAULT_NAME;
        else
            _name = name;
        _dateOfBirth = new Date(day, month, year);
        if (id.length() == VALID_ID_LENGTH)
            _id = id;
        else
            _id = DEFAULT_ID;
    }

    // Copy constructor

    /**
     * Creates a new Person object as a copy of another Person object.
     *
     * @param other the Person object to copy
     */
    public Person(Person other) {
        _name = other._name;
        _dateOfBirth = new Date(other._dateOfBirth);
        _id = other._id;
    }

    //get methods

    /**
     * Returns the name of the person.
     *
     * @return the person's name
     */
    public String getName() {
        return _name;
    }

    /**
     * Returns the person's ID.
     *
     * @return the person's ID
     */
    public String getId() {
        return _id;
    }


    /**
     * Returns the person's date of birth.
     *
     * @return the person's date of birth
     */
    public Date getDateOfBirth() {
        return  new Date(_dateOfBirth);
    }

    //set methods

    /**
     * Sets the name of the person.
     *
     * @param nameToSet the new name to set
     */
    public void setName(String nameToSet) {
        if (!nameToSet.equals(""))
            _name = nameToSet;
    }

    /**
     * Sets the person's ID.
     *
     * @param idToSet the new ID to set
     */
    public void setId(String idToSet) {
        if (idToSet.length() == VALID_ID_LENGTH)
            _id = idToSet;
    }


    /**
     * Sets the date of birth for this Person object.
     *
     * @param setDateOfBirth the new date of birth to set
     */
    public void setDateOfBirth(Date setDateOfBirth) {
        _dateOfBirth = setDateOfBirth;
        }


    public boolean equals(Person other) {
        return (_name.equals(other._name) &&
                (_id.equals(other._id))) &&
                (_dateOfBirth.equals(other._dateOfBirth));
    }

    /**
     * Compares this Person object to another Person object based on their date of birth.
     *
     * @param other the other Person object to compare with
     * @return a negative integer, zero, or a positive integer as this person is younger than,
     *         equal to, or older than the other person, respectively
     */
    public int compareTo(Person other) {
        if (getDateOfBirth().before(other._dateOfBirth)) {
            return 1; // This person is older
        } else if (getDateOfBirth().after(other._dateOfBirth)) {
            return -1; // This person is younger
        } else {
            return 0; // Same date of birth
        }
    }



        /**
         * Returns a string representation of the Person object, including name, ID, and date of birth.
         *
         * @return a string representation of the Person object
         */
        public String toString () {
            return "Name: " + this.getName() + "\nID: " + this.getId() + "\nDate of birth: " + this.getDateOfBirth();
        }


}