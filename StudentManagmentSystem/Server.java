package StudentManagmentSystem;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.*;

public class Server {

    private static final List<CourseDetail> courses = new ArrayList<>();
    private static final Map<Integer, Student> students = new HashMap<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static int studentIdCounter = 1; // For generating unique student IDs
    private static int courseIdCounter = 1; // For generating unique course IDs
    private static int teacherIdCounter = 1; // For generating unique teacher IDs

    public static void main(String[] args) throws IOException {
        // Initialize sample courses
        courses.add(new CourseDetail(courseIdCounter++, "Java", "Alice", "4 weeks"));
        courses.add(new CourseDetail(courseIdCounter++, "Operating System", "Bob", "6 weeks"));
        courses.add(new CourseDetail(courseIdCounter++, "Data Science", "Charlie", "8 weeks"));

        // Initialize the server
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/courses", new CourseHandler());
        server.createContext("/students", new StudentHandler());
        server.createContext("/teachers", new TeacherHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started at http://localhost:8080");
    }

    // CourseHandler for managing courses
    static class CourseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            setResponseHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                StringBuilder jsonResponse = new StringBuilder("[");
                for (int i = 0; i < courses.size(); i++) {
                    CourseDetail course = courses.get(i);
                    jsonResponse.append(course.toJson());
                    if (i < courses.size() - 1) {
                        jsonResponse.append(", ");
                    }
                }
                jsonResponse.append("]");
                sendResponse(exchange, 200, jsonResponse.toString());
            } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String query = new String(exchange.getRequestBody().readAllBytes());
                String[] parts = query.split("&");
                String courseName = parts[0].split("=")[1];
                String courseInstructor = parts[1].split("=")[1];
                String courseDuration = parts[2].split("=")[1];

                CourseDetail newCourse = new CourseDetail(courseIdCounter++, courseName, courseInstructor,
                        courseDuration);
                courses.add(newCourse);
                sendResponse(exchange, 201, "Course added successfully!");
            } else if ("DELETE".equalsIgnoreCase(exchange.getRequestMethod())) {
                String query = exchange.getRequestURI().getQuery();
                int courseId = Integer.parseInt(query.split("=")[1]);
                courses.removeIf(course -> course.getCourseId() == courseId);
                sendResponse(exchange, 200, "Course deleted successfully!");
            }
        }
    }

    // StudentHandler for managing students
    static class StudentHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            setResponseHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                StringBuilder responseBuilder = new StringBuilder("[");
                for (Student student : students.values()) {
                    responseBuilder.append(student.toJson()).append(",");
                }
                if (!students.isEmpty()) {
                    responseBuilder.setLength(responseBuilder.length() - 1); // Remove trailing comma
                }
                responseBuilder.append("]");
                sendResponse(exchange, 200, responseBuilder.toString());
            } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String query = new String(exchange.getRequestBody().readAllBytes());
                String studentName = query.split("=")[1];

                Student newStudent = new Student(studentIdCounter++, studentName);
                students.put(newStudent.getStudentId(), newStudent);
                sendResponse(exchange, 201, "Student added successfully!");
            }
        }
    }

    // TeacherHandler for managing teachers
    static class TeacherHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            setResponseHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                StringBuilder jsonResponse = new StringBuilder("[");
                for (int i = 0; i < teachers.size(); i++) {
                    Teacher teacher = teachers.get(i);
                    jsonResponse.append(teacher.toJson());
                    if (i < teachers.size() - 1) {
                        jsonResponse.append(", ");
                    }
                }
                jsonResponse.append("]");
                sendResponse(exchange, 200, jsonResponse.toString());
            } else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String query = new String(exchange.getRequestBody().readAllBytes());
                String[] parts = query.split("&");
                String teacherName = parts[0].split("=")[1];
                String subjectTaught = parts[1].split("=")[1];
                int experience = Integer.parseInt(parts[2].split("=")[1]);

                Teacher newTeacher = new Teacher(teacherIdCounter++, teacherName, subjectTaught, experience);
                teachers.add(newTeacher);
                sendResponse(exchange, 201, "Teacher added successfully!");
            }
        }
    }

    // Utility methods
    private static void setResponseHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    // CourseDetail, Student, and Teacher classes
    static class CourseDetail {
        private final int courseId;
        private final String courseName;
        private final String courseInstructor;
        private final String courseDuration;

        public CourseDetail(int courseId, String courseName, String courseInstructor, String courseDuration) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.courseInstructor = courseInstructor;
            this.courseDuration = courseDuration;
        }

        public int getCourseId() {
            return courseId;
        }

        public String toJson() {
            return String.format(
                    "{\"courseId\":%d,\"courseName\":\"%s\",\"courseInstructor\":\"%s\",\"courseDuration\":\"%s\"}",
                    courseId, courseName, courseInstructor, courseDuration);
        }
    }

    static class Student {
        private final int studentId;
        private final String studentName;

        public Student(int studentId, String studentName) {
            this.studentId = studentId;
            this.studentName = studentName;
        }

        public int getStudentId() {
            return studentId;
        }

        public String toJson() {
            return String.format("{\"studentId\":%d,\"studentName\":\"%s\"}", studentId, studentName);
        }
    }

    static class Teacher {
        private final int teacherId;
        private final String teacherName;
        private final String subjectTaught;
        private final int experience;

        public Teacher(int teacherId, String teacherName, String subjectTaught, int experience) {
            this.teacherId = teacherId;
            this.teacherName = teacherName;
            this.subjectTaught = subjectTaught;
            this.experience = experience;
        }

        public String toJson() {
            return String.format(
                    "{\"teacherId\":%d,\"teacherName\":\"%s\",\"subjectTaught\":\"%s\",\"experience\":%d}",
                    teacherId, teacherName, subjectTaught, experience);
        }
    }
}
