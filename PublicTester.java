import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class PublicTester {

    // Student class
    // Test constructor with valid argument
    @Test
    public void testStudentConstructor() {
        Student student = new Student("Test", "Student", "A12345678");
        assertEquals("Test", student.getFirstName());
        assertEquals("Student", student.getLastName());
        assertEquals("A12345678", student.getPID());
    }

    // Test equals with two equivalent Student objects
    @Test
    public void testEqualsSame() {
        Student student1 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        Student student2 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        assertTrue(student1.equals(student2));
    }

    // Test hashing function
    @Test
    public void testHashValueSame() {
        Student student1 = new Student(new String("Test"), 
            new String("Student"), new String("A12345678"));
        assertEquals(student1.hashCode(), Objects.hash(new String("Test"), 
            new String("Student"), new String("A12345678")));
    }

    // Course class
    // Test constructor with valid argument
    @Test
    public void testCourseConstructor() throws NoSuchFieldException {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        assertEquals("CSE", course.getDepartment());
        assertEquals("12", course.getNumber());
        assertEquals("Data Structure", course.getDescription());
        assertEquals(100, course.getCapacity());
    }

    // Enroll a new non-null student
    @Test
    public void testEnrollNewStudent() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        course.enrolled = new HashSet<>();
        Student stu = new Student("Whales", "Ocean", "A123");
        assertTrue(course.enroll(stu));
        assertTrue(course.enrolled.contains(stu));
        assertEquals(1, course.enrolled.size());
    }

    // Unenroll an existing student
    @Test
    public void testUnenrollExistingStudent() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        Student stu = new Student("Whales", "Ocean", "A123");

        course.enrolled = new HashSet<>();
        course.enrolled.add(stu);

        assertTrue(course.unenroll(stu));
        assertFalse(course.enrolled.contains(stu));
        assertEquals(0, course.enrolled.size());
    }

    // Test isFull with a full roster
    @Test
    public void testIsFull() {
        Course course = new Course("CSE", "12", "Data Structure", 3);
        course.enrolled = new HashSet<>();
        for(int i=0; i<3; i++) {
            course.enrolled.add(new Student("Whales"+i, "Ocean", "A123"));
        }

        assertTrue(course.isFull());
    }

    // Get a descriptive string from a normal course
    @Test
    public void testToString() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        assertEquals("CSE 12 [100]\nData Structure", course.toString());
    }

    // Sanctuary class
    // Test constructor with valid argument
    @Test
    public void testSanctuaryConstructor() {
        Sanctuary sanct = new Sanctuary(500, 50);
        assertEquals(500, sanct.maxAnimals);
        assertEquals(50, sanct.maxSpecies);
        assertEquals(0, sanct.sanctuary.size());
    }

    // Test getNum with valid argument
    @Test
    public void testGetNum(){
        Sanctuary sanct = new Sanctuary(500, 50);
        sanct.sanctuary.put("Giraffe", 20);
        sanct.sanctuary.put("Meerkat", 65);

        assertEquals(20, sanct.getNum("Giraffe"));
        assertEquals(65, sanct.getNum("Meerkat"));
    }

    // Get the total number of animals at the sanctuary
    @Test
    public void TestGetTotalAnimals(){
        Sanctuary sanct1 = new Sanctuary(100, 4);
        sanct1.sanctuary.put("Koala", 55);
        assertEquals(55, sanct1.getTotalAnimals());
    }

    // Get total number of species at a sanctuary
    public void TestGetTotalSpecies(){
        Sanctuary sanct1 = new Sanctuary(1000, 4);
        sanct1.sanctuary.put("Koala", 55);
        sanct1.sanctuary.put("Capybara", 70);
        sanct1.sanctuary.put("Groundhog", 22);
        sanct1.sanctuary.put("Vulture", 3);
        sanct1.sanctuary.put("Zebra", 14);
        assertEquals(5, sanct1.getTotalSpecies());
    }

    // Add a new species of animals to not-full sanctuary
    @Test
    public void testRescue(){
        Sanctuary sanct = new Sanctuary(100, 20);

        assertEquals(0, sanct.rescue("Panda", 3));
        assertTrue(sanct.sanctuary.containsKey("Panda"));
        assertEquals(3, (int)sanct.sanctuary.get("Panda"));

        assertEquals(0, sanct.rescue("Frog", 32));
        assertTrue(sanct.sanctuary.containsKey("Frog"));
        assertEquals(32, (int)sanct.sanctuary.get("Frog"));

        assertEquals(2, (int)sanct.sanctuary.size());
    }

    // Remove animals from a larger group of them at the sanctuary
    @Test
    public void testRelease(){
        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Capybara", 40);
        sanct.sanctuary.put("Horse", 5);

        sanct.release("Capybara", 10);
        assertTrue(sanct.sanctuary.containsKey("Capybara"));
        assertEquals(30, (int)sanct.sanctuary.get("Capybara"));

        assertEquals(true, sanct.sanctuary.containsKey("Horse"));
        assertEquals(5, (int)sanct.sanctuary.get("Horse"));

        assertEquals(2, sanct.sanctuary.size());
    }
}
