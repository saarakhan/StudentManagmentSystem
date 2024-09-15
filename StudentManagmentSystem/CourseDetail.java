package StudentManagmentSystem;

public class CourseDetail {
    // fields
    private int courseId;
    private String courseName;
    private String courseInstructor;
    private String courseDuration;

    // constructor
    public CourseDetail(int courseId, String courseName, String courseInstructor, String courseDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseDuration = courseDuration;
    }

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    // display
    public void DisplayDetails() {
        System.out.println("Course Id is  : " + courseId);
        System.out.println("Course Name is : " + courseName);
        System.out.println("Instructor of course is : " + courseInstructor);
        System.out.println("Duration in Weeks : " + courseDuration);
    }

    // update
    public void updateCourse(int courseId, String courseName, String courseInstructor, String courseDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseDuration = courseDuration;
        DisplayDetails();
    }

    // check if a particular instructor is teacting this corse
    public Boolean isInstructor(String instructor) {
        return this.courseInstructor.equalsIgnoreCase(instructor);
    }

}
