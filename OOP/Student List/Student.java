/**
 * A Student has a first and last name and a grade,
 * which can be compared with other Students
 * @author Jacqueline Zhu
 * @version 1.0
 */

public class Student implements Comparable<Student> {

    private String firstName;
    private String lastName;
    private double grade;

    /**
     * Initializes first and last name unless they are
     * null or empty strings
     * @param firstName
     * @param lastName
     */
    public Student(String firstName, String lastName) {
        if (firstName == null
            || lastName == null
            || firstName.contains(" ")
            || lastName.contains(" ")
            || firstName == ""
            || lastName == "") {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets Student's grade
     * @param grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Create's a string representation of the student
     * @return str
     */
    public String toString() {
        String str = firstName + " " + lastName + ": " + grade;
        return str;
    }

    /**
     * Compares one student to the other based on
     * if their first and last names are the same
     * @param other (object to be compared to)
     */
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Student)) {
            return false;
        }
        Student that = (Student) other;
        return (this.firstName.equals(that.firstName)
                && this.lastName.equals(that.lastName));
    }

    /**
     * compares grades, first names, and last names of two Students
     * @param other (Student to be compared to)
     * @return positive number if greater than, negative number
     * if less than, and 0 if they are equal
     */
    public int compareTo(Student other) {
        int gradeComparison = (int) (this.grade - other.grade);
        int firstComparison = this.firstName.compareTo(other.firstName);
        int lastComparison = this.lastName.compareTo(other.lastName);
        if (gradeComparison != 0) {
            return gradeComparison;
        }
        if (firstComparison != 0) {
            return firstComparison;
        }
        if (lastComparison != 0) {
            return lastComparison;
        }
        return 0;
    }

    /**
     * creates hashCode using recipe
     * @param result
     */
    public int hashCode() {
        int result = 17;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;

    }
}