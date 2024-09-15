package StudentManagmentSystem;

import java.util.*;

public class StudentDetails {
    private int Stu_Id;
    private ArrayList<Integer> courseIds = new ArrayList<>();
    private String StudentName;
    private String StudentGrade;

    // constructor
    private static int counter = 0;

    public StudentDetails(String StudentName, String StudentGrade) {
        counter++;
        this.Stu_Id = counter;
        // this.courseIds = courseIds;
        this.StudentName = StudentName;
        this.StudentGrade = StudentGrade;
    }

    // Getters
    public int getStudentId() {
        return Stu_Id;
    }

    public String StudentName() {
        return StudentName;
    }

    public String StudentGrade() {
        return StudentGrade;
    }

    public ArrayList<Integer> CoursesId() {
        return courseIds;
    }

    public void PurchasedCourseBySt() {
        System.out.println("Purchases courses Id are :");
        for (Integer Id : courseIds) {
            System.out.println(Id);
        }
    }

    // display
    public void DisplayDetails() {
        System.out.println("Course Id is  : " + Stu_Id);
        System.out.println("Student Name is : " + StudentName);
        System.out.println("Grade of Student is : " + StudentGrade);
        PurchasedCourseBySt();
        // System.out.println("Ids of courses are : " + courseIds);
    }

    // purchased course
    public void PurchasedCourse(ArrayList<CourseDetail> courses) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the course you want to purchase : ");
        String CourseName = sc.nextLine();

        int Id = SearchCourse(CourseName, courses);
        if (Id != -1) {
            courseIds.add(Id);
            System.out.println(courseIds);
        }
    }

    public int SearchCourse(String CourseName, ArrayList<CourseDetail> courses) {
        for (CourseDetail course : courses) {
            if (course.getCourseName().equalsIgnoreCase(CourseName)) {
                return course.getCourseId();
            }
        }
        return -1;
    }

}
