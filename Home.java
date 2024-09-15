import StudentManagmentSystem.CourseDetail;
import StudentManagmentSystem.StudentDetails;
import StudentManagmentSystem.TeacherDetail;

import java.util.*;

public class Home {
    public static void main(String[] args) {
        ArrayList<CourseDetail> courses = new ArrayList<>();

        CourseDetail DSA_JAVA = new CourseDetail(1121, "JAVA", "Sonali_K", "2");
        CourseDetail OperatingSystem = new CourseDetail(1221, "OperatingSystem", "KirtiMathur", "3");
        CourseDetail DataScience = new CourseDetail(1321, "DataScience", "Shaligram", "4");

        courses.add(DSA_JAVA);
        courses.add(OperatingSystem);
        courses.add(DataScience);

        // Displaying course
        DSA_JAVA.DisplayDetails();
        System.out.println("---------------------------------------------");
        // UpdateCourse
        // DSA_JAVA.updateCourse(1122, "C++", "Sonali_K", "3");

        System.out.println("---------------------------------------------");

        // checking Instructor
        System.out.println("Is Sonali_K our instructor :" + DSA_JAVA.isInstructor("Sonali"));

        System.out.println("======= Student ======");
        StudentDetails s1 = new StudentDetails("Saara Khan", "VSem");
        s1.PurchasedCourse(courses);
        s1.DisplayDetails();

        StudentDetails s2 = new StudentDetails("Ram Patidar", "VSem");
        s2.PurchasedCourse(courses);
        s2.DisplayDetails();

        s1.CoursesId();
        s2.CoursesId();

        TeacherDetail t[] = new TeacherDetail[3];
        t[0] =new TeacherDetail(1,"ram" , "c++", 1);
        t[1] =new TeacherDetail(2,"saara" , "python", 2);
        t[2] =new TeacherDetail(3,"yashvi" , "python", 3);

        t[0].DisplayDetails();
        t[1].DisplayDetails();
        t[2].DisplayDetails();
    }
}
