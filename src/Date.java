/**
 * The Date class represents a specific date, including day, month, and year.
 * It provides methods for creating, manipulating, comparing, and formatting
 * dates. It handles leap years, date validation, and basic calculations.
 *
 * @author Daniel Shimon
 * @version (Maman 12 )
 */

public class Date {

    // Instance variables
    private int _day;
    private int _month;
    private int _year;

    // Constants
    private static final int DEFAULT_DAY = 1;   // Default day
    private static final int DEFAULT_MONTH = 1;  // Default month
    private static final int DEFAULT_YEAR = 2000;  // Default year
    private static final int MIN_DAY = 1;      // Minimum possible day in a month
    private static final int MAX_DAY = 31;      // Maximum possible day in a month
    private static final int FIRST_MONTH = 1;  // Numerical representation of the first month
    private static final int LAST_MONTH = 12;  // Numerical representation of the last month
    private static final int DAYS_IN_MONTH = 30; // number of days in most months
    private static final int DAYS_IN_FEBRUARY = 28;  // Number of days in February for a regular year
    private static final int DAYS_IN_LEAP_FEBRUARY = 29;  // Number of days in February for a leap year

    // Constants for month numbers
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;


    //Constructor
    /**
     * Creates a new Date object with the specified day, month, and year.
     *
     * @param day   the day in the month(1-31)
     * @param month the month in the year(1-12)
     * @param year  the year ( 4 digits)
     */
    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            _day = day;
            _month = month;
            _year = year;
        } else {
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }
    }

    // Copy constructor
    /**
     * Copy Constructor
     *
     * @param other the Date to be copied
     */
    public Date(Date other) {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    // Checks if the given date is valid.
    private boolean isValidDate(int day, int month, int year) {
        // Check day range
        if (day < MIN_DAY || day > MAX_DAY) {
            return false;
        }
        // Check month range
        if (month < FIRST_MONTH || month > LAST_MONTH) {
            return false;
        }
        // Year must be positive
        if (year <= 1) {
            return false;
        }
        // Check for appropriate day count for February in leap years
        if (month == FEBRUARY) {
            return day <= DAYS_IN_FEBRUARY || (day == DAYS_IN_LEAP_FEBRUARY && isLeapYear(year));
        }
        // Check for 30-day months
        if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            return day <= DAYS_IN_MONTH;
        }
        return true;
    }

    //Determines whether the given year is a leap year.
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate(int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    //get methods

    /**
     * Gets the day of the month.
     *
     * @return the day
     */
    public int getDay() {
        return _day;
    }


    /**
     * Gets the month of the year.
     *
     * @return the month
     */
    public int getMonth() {
        return _month;
    }


    /**
     * Gets the year.
     *
     * @return the year
     */
    public int getYear() {
        return _year;
    }


    // set methods

    /**
     * Sets the day of the month if the resulting date is valid.
     *
     * @param dayToSet the new day
     */
    public void setDay(int dayToSet) {
        if (isValidDate(dayToSet, _month, _year))
            _day = dayToSet;
    }


    /**
     * Sets the day of the month if the resulting date is valid.
     *
     * @param monthToSet the new month
     */
    public void setMonth(int monthToSet) {
        if (isValidDate(_day, monthToSet, _year))
            _month = monthToSet;
    }


    /**
     * Sets the day of the month if the resulting date is valid.
     *
     * @param yearToSet the new year
     */
    public void setYear(int yearToSet) {
        if (isValidDate(_day, _month, yearToSet))
            _year = yearToSet;
    }


    // Comparison methods
    /**
     * Checks if this Date is equal to another Date.
     *
     * @param other the other Date to compare to
     * @return true if the dates are equal, false otherwise
     */
    public boolean equals(Date other) {
        return ((_day == other._day) && (_month == other._month) && (_year == other._year));
    }


    /**
     * Determines if this Date is before another Date.
     *
     * @param other the other Date to compare to
     * @return true if this Date is before the other Date, false otherwise
     */
    public boolean before(Date other) {
        return this.getYear() < other.getYear() || (this.getYear() == other.getYear() && (this.getMonth() < other.getMonth() || (this.getMonth() == other.getMonth() && this.getDay() < other.getDay())));
            }



    /**
     * Determines if this Date is after another Date.
     *
     * @param other the other Date to compare to
     * @return true if this Date is after the other Date, false otherwise
     */
    public boolean after(Date other) {
        return other.before(this);
    }


    /**
     * Calculates the difference in days between this Date and another Date.
     *
     * @param other the other Date to compare to
     * @return the difference in days
     */
    public int difference(Date other) {
        int difference;
        int thisDateNum = calculateDate(this.getDay(), this.getMonth(), this.getYear());
        int otherDateNum = calculateDate(other.getDay(), other.getMonth(), other.getYear());
        if (thisDateNum > otherDateNum)
            difference = thisDateNum - otherDateNum;
        else
            difference = otherDateNum - thisDateNum;
        return difference;
    }



    /**
     * Creates a new Date object with the specified number of years added to the current date.
     *
     * @param num the number of years to add
     * @return a new Date object with the added years (the original Date remains unchanged)
     */
    public Date addYearsToDate(int num) {
        int newYear = this._year + num;
        int adjustedDay = this._day;

        // Handle February 28th/29th cases directly using if statements
        if (this._month == FEBRUARY && this._day == DAYS_IN_FEBRUARY) {
            if (isLeapYear(newYear)) {
                adjustedDay = DAYS_IN_LEAP_FEBRUARY;
            } else
                adjustedDay = DAYS_IN_FEBRUARY;
        } else if (this._month == FEBRUARY && this._day == DAYS_IN_LEAP_FEBRUARY && !isLeapYear(newYear)) {
            adjustedDay = DAYS_IN_FEBRUARY;
        }
        return new Date(adjustedDay, this._month, newYear);
    }

    /**
     * Returns a string representation of the Date in the format DD/MM/YYYY.
     *
     * @return the string representation of the Date
     */
    public String toString() {
        String dayString = String.valueOf(_day);
        String monthString = String.valueOf(_month);
        if (_day < 10)
            dayString = "0" + dayString;
        if (_month < 10)
            monthString = "0" + monthString;
        return dayString + "/" + monthString + "/" + _year;
    }
}
