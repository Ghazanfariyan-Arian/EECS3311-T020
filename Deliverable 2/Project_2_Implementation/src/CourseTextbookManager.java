import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CourseTextbookManager {
    // Map to store textbooks for each course, with editions
    private Map<String, String> coursesWithEditions;

    public CourseTextbookManager() {
        this.coursesWithEditions = new HashMap<>();
        // Add some example courses with editions
        coursesWithEditions.put("Mathematics1001", "Edition 1");
        coursesWithEditions.put("Biology3090", "Edition 2");
        coursesWithEditions.put("Literature2042", "Edition 3");
    }

 // Method to assign course to student or faculty
    public String assignCourse(Client client) {
        if (coursesWithEditions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        List<String> courses = new ArrayList<>(coursesWithEditions.keySet());
        int randomIndex = random.nextInt(courses.size());
        String course = courses.get(randomIndex);
        String edition = coursesWithEditions.get(course);

        // For demonstration purposes, assume that the course and edition are now assigned to the client
        if (client instanceof Student) {
            Student student = (Student) client;
            student.addCourse(course, edition);
        } else if (client instanceof Faculty) {
            Faculty faculty = (Faculty) client;
            faculty.addCourse(course, edition);
        }

        return course;
    }
    // Method to get the courses with editions
    public Map<String, String> getCoursesWithEditions() {
        return coursesWithEditions;
    }
 // Method to get the textbook for a given course
    public String getTextbookForCourse(String course) {
        return coursesWithEditions.get(course);
    }

    // Method to notify client about new edition of textbook
    public void notifyClientNewEdition(String course, String newEdition) {
    	
        System.out.println("New edition of textbook for course " + course + ": " + newEdition);
    }
}