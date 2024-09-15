package StudentManagmentSystem;

public class TeacherDetail {
    private int teacherId;
    private String teacherName;
    private String subjectTaught;
    private int experienceInYears;

    public TeacherDetail(int teacherId, String teacherName, String subjectTaught, int experienceInYears) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subjectTaught = subjectTaught;
        this.experienceInYears = experienceInYears;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSubjectTaught() {
        return subjectTaught;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void DisplayDetails() {
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Teacher Name: " + teacherName);
        System.out.println("Subject Taught: " + subjectTaught);
        System.out.println("Years of Experience: " + experienceInYears);
    }

    public void updateTeacherDetails(int teacherId, String teacherName, String subjectTaught, int experienceInYears) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.subjectTaught = subjectTaught;
        this.experienceInYears = experienceInYears;
        DisplayDetails();
    }

    //  if a teacher teaches a particular subject
    public Boolean teachesSubject(String subject) {
        return this.subjectTaught.equalsIgnoreCase(subject);
    }

    //  if a teacher has more than a specific number of years of experience
    public Boolean hasExperience(int years) {
        return this.experienceInYears >= years;
    }
}
