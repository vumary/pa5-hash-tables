/**
 * Name: Mary Vu
 * ID: A17081693
 * Email: m2vu@ucsd.edu
 * Sources used: None
 * 
 * This file contains one class, CustomTester. This is a private tester file
 * for PA 5 and it uses JUnit 4.12 testing.
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains all the private test cases for PA 5 for methods in the
 * Student class, Course class, and Sanctuary class. These private tests are
 * custom made by me and meant to test edge cases that the public tester 
 * missed. 
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {

    // ----------------Student class----------------
    /**
     * Test the equals method with two different Student objects
     */
    @Test
    public void testEquals() {
        Student student1 = new Student("Mary", "Vu", "A17081683");
        Student student2 = new Student("Annie", "Trinh", "A71806138");
        assertFalse(student1.equals(student2));
    }

    /**
     * Test the compareTo method when current object lexicographically comes 
     * before Student o, when current object lexicographically comes 
     * after Student o, and when both have all instance variables equivalent
     */
    @Test
    public void testCompareTo() {
        Student student1 = new Student("Angela", "Jones", "A12345678");
        Student student2 = new Student("Aaron", "Smith", "A12345679");
        Student student3 = new Student("Angela", "Jones", "A12345678");
        
        // current object comes before Student o
        assertEquals(-1, student1.compareTo(student2));

        // current object after before Student o
        assertEquals(1, student2.compareTo(student1));

        // current object and Student o are equivalent
        assertEquals(0, student1.compareTo(student3));
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when Student is null
     */
    @Test
    public void testEnroll() {
        Course CSE11 = new Course("CSE", "11", "Accelerated Programming", 30);
        Student nullStudent = null;
        try {
            CSE11.enroll(nullStudent);
            fail();
        } catch (Exception IllegalArgumentException) {
            // passes
        }
    }

    /**
     * Test the unenroll method on a non-existing Student and null Student
     */
    @Test
    public void testUnenroll() {
        Student student1 = new Student("Mary", "Vu", "A17081683");
        Student student2 = new Student("Annie", "Trinh", "A71806138");
        Student student3 = new Student("Angela", "Jones", "A12345678");
        Student student4 = new Student("Aaron", "Smith", "A12345679");
        Course CSE11 = new Course("CSE", "11", "Accelerated Programming", 30);
        CSE11.enrolled = new HashSet<>();
        CSE11.enrolled.add(student1);
        CSE11.enrolled.add(student2);
        CSE11.enrolled.add(student3);
        
        assertFalse("Unsuccessful because student was not in course", 
        CSE11.unenroll(student4));

        // testing unenroll on null Student
        Student nullStudent = null;
        try {
            CSE11.unenroll(nullStudent);
            fail();
        } catch (Exception IllegalArgumentException) {
            // passes
        }
    }

    /**
     * Test the getRoster method when the course has no Students and when the
     * course has two Students
     */
    @Test
    public void testGetRoster() {
        // testing when course has no students
        Course CSE11 = new Course("CSE", "11", "Accelerated Programming", 30);
        ArrayList<Student> roster = new ArrayList<>();
        assertEquals(roster, CSE11.getRoster());

        // testing when course has two students
        Student student1 = new Student("Mary", "Vu", "A17081683");
        Student student2 = new Student("Annie", "Trinh", "A71806138");
        CSE11.enrolled.add(student1);
        CSE11.enrolled.add(student2);
        // note that Students are expected to be sorted so the ArrayList
        // was added to accordingly 
        roster.add(student2);
        roster.add(student1);
        assertEquals(roster, CSE11.getRoster());
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor with invalid arguments
     */
    @Test
    public void testSanctuaryConstructor() {
        // invalid maxAnimals
        try {
            Sanctuary sanct1 = new Sanctuary(-1, 10);
            fail();
        } catch (Exception IllegalArgumentException) {
            // passes
        }
        
        // invalid maxSpecies 
        try {
            Sanctuary sanct2 = new Sanctuary(100, -5);
            fail();
        } catch (Exception e) {
            // passes
        }
    }

    /**
     * Test the rescue method when adding a new species of animals to a 
     * full sanctuary
     */
    @Test
    public void testRescueTestOne(){
        // testing when full because animals are maxed out
        Sanctuary sanct = new Sanctuary(20, 2);
        sanct.sanctuary.put("Hippo", 20); // filling up sanctuary to make full
        assertEquals(2, sanct.rescue("Hippo", 2));

        // testing when full because species are maxed out
        Sanctuary sanct2 = new Sanctuary(10, 1);
        sanct2.sanctuary.put("Hippo", 5);
        assertEquals(3, sanct2.rescue("Zebra", 3));
        assertFalse(sanct2.sanctuary.containsKey("Zebra"));
    }

    /**
     * Test the rescue method when adding the number of animals exceeds the 
     * maximum limit, but you add as many as permitted
     */
    @Test
    public void testRescueTestTwo(){
        // testing when rescuing number exceeds max animals
        Sanctuary sanct2 = new Sanctuary(20, 2);
        sanct2.sanctuary.put("Hippo", 10);
        sanct2.sanctuary.put("Zebra", 8);
        assertEquals(1, sanct2.rescue("Hippo", 3));
        assertEquals(12, (int) sanct2.sanctuary.get("Hippo"));
        assertEquals(8, (int) sanct2.sanctuary.get("Zebra"));
        assertEquals(20, sanct2.getTotalAnimals());
        assertEquals(2, sanct2.sanctuary.size());

        // adding a new species and also exceeding the max animal
        Sanctuary sanct = new Sanctuary(20, 2);
        sanct.sanctuary.put("Hippo", 10);
        assertEquals(5, sanct.rescue("Zebra", 15));
        assertTrue(sanct.sanctuary.containsKey("Zebra"));
        assertEquals(10, (int) sanct.sanctuary.get("Zebra"));
        assertEquals(10, (int) sanct.sanctuary.get("Hippo"));
        assertEquals(20, sanct.getTotalAnimals());
        assertEquals(2, sanct.sanctuary.size());
    }

    /**
     * Test the release method when the num is greater than the number of
     * animals of that species in the sanctuary
     */
    @Test
    public void testReleaseTestOne(){
        Sanctuary sanct = new Sanctuary(20, 2);
        sanct.sanctuary.put("Hippo", 10);
        try {
            sanct.release("Hippo", 11);
            fail();
        } catch (Exception IllegalArgumentException) {
            // passes
        }
    }

    /**
     * Test the release method when species does not exist in the sanctuary
     */
    @Test
    public void testReleaseTestTwo(){
        Sanctuary sanct = new Sanctuary(20, 2);
        sanct.sanctuary.put("Hippo", 10);
        try {
            sanct.release("Zebra", 5);
            fail();
        } catch (Exception IllegalArgumentException) {
            // passes
        }
    }
}

