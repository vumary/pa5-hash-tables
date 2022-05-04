
/**
 * Name: Mary Vu
 * Email: m2vu@ucsd.edu
 * Sources used: None
 * 
 * This file contains one class, Course, and is made for registering students
 * into courses. Also, this file imports and uses HashSet from Java's 
 * built in library.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This is my implementation of methods and instance variables that help
 * store the students registered for this particular course in the form of a
 * HashSet, which is the instance variable named enrolled. This class also
 * has instance variables that contain information for the course such as 
 * capacity, department, number, and description.
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * Constructor that initializes the course's information with an initial 
     * enrollment of 0 students
     * 
     * @param department this course falls under this department
     * @param number this is the course number
     * @param description this is the description of the course
     * @param capacity this is the maximum number of students that can be 
     *                 enrolled in this course
     */
    public Course(String department, String number, String description,
            int capacity) {
        if (department == null || number == null || description == null 
        || capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = new HashSet<Student>();
    }

    /**
     * Get the department name
     * 
     * @return the department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Get the course number
     * 
     * @return the course number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Get the description of the course
     * 
     * @return the course description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the capacity of the course
     * 
     * @return the course capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * If there is room in this course and the student is not currently 
     * enrolled, add the student to the course. Return whether student was 
     * enrolled or not
     * 
     * @param student the student in question
     * @return true for a successful enrollment and false otherwise
     */
    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (!this.isFull() && !enrolled.contains(student)) {
            enrolled.add(student);
            return true;
        }
        return false;
    }

    /**
     * If the student is enrolled in the course, remove them from the course. 
     * Return whether student was unenrolled or not
     * 
     * @param student the student in question
     * @return true for a successful unenrollment and false otherwise
     */
    public boolean unenroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        return false;
    }

    /**
     * If the course is canceled, all of the students are dropped from the 
     * course. Remove all the students from the course
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Determine whether the course is at its capacity or not
     * 
     * @return true if the course is at capacity and false otherwise
     */
    public boolean isFull() {
        return this.getEnrolledCount() == this.getCapacity();
    }

    /**
     * Get the current number of enrolled students
     * 
     * @return number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Get the number of students that can still enroll in the course (assuming
     * everyone stays enrolled) 
     * 
     * @return available spots left in course
     */
    public int getAvailableSeats() {
        return capacity - enrolled.size();
    }

    /**
     * Return a shallow copy of all the students enrolled in this course
     * 
     * @return copy of students enrolled
     */
    public HashSet<Student> getStudents() {
        return enrolled;
    }

    /**
     * Turn the collection of enrolled students into an ArrayList collection
     * by iterating through the HashSet using the iterator, and then returning
     * the final ArrayList sorted
     * 
     * @return sorted ArrayList of enrolled students
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> roster = new ArrayList<>();
        Iterator<Student> it = enrolled.iterator();
        while (it.hasNext()) {
            roster.add(it.next());
        }
        Collections.sort(roster);
        return roster;
    }

    /**
     * Return a string representation for a Course object. The format should be 
     * as follows: <department> <number> [<capacity>]\n<description> where 
     * the contents inside the < > refer to the instance variables of the class
     * 
     * @return string representation of a Course object
     */
    public String toString() {
        return department + " " + number + " " + "[" + capacity + "]\n" +
                description;
    }
}
