import java.util.ArrayList;
import java.util.List;

public class Faculty extends Client {
    private List<String> courses;

    public Faculty(int id, String email, String password, String type) {
        super(id, email, password, type);
        this.courses = new ArrayList<>();
    }
    
    public void addCourse(String course, String edition) {
    	String courseWithEdition = course + " (Edition: " + edition + ")";
        this.courses.add(courseWithEdition);
        System.out.println("Faculty " + getEmail() + " added course: " + course + " (Edition: " + edition + ")");
    }

    public List<String> getCourses() {
        return courses;
    }

    public void removeCourse(String course) {
        courses.remove(course);
    }
}