
function getStudents() {
    fetch('http://localhost:8080/students')
        .then(response => response.json())
        .then(data => {
            const studentList = document.getElementById('student-list');
            studentList.innerHTML = '';
            data.forEach(student => {
                const li = document.createElement('li');
                li.textContent = `ID: ${student.id}, Name: ${student.name}, Grade: ${student.grade}`;
                studentList.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching students:', error));
}

function getCourses() {
    const courses = ['Mathematics', 'Science', 'History', 'Computer Science'];
    const courseList = document.getElementById('course-list');
    courseList.innerHTML = '';

    courses.forEach(course => {
        const li = document.createElement('li');
        li.textContent = course;
        courseList.appendChild(li);
    });
}

function addStudent() {
    const studentName = document.getElementById('student-name').value;
    const studentGrade = document.getElementById('student-grade').value;

    if (studentName === '' || studentGrade === '') {
        alert('Please fill in both fields.');
        return;
    }

    const studentList = document.getElementById('student-list');
    const li = document.createElement('li');
    li.innerHTML = `${studentName} <span class="grade">${studentGrade}</span>`;
    studentList.appendChild(li);

    document.getElementById('student-name').value = '';
    document.getElementById('student-grade').value = '';
}

function addTeacher() {
    const teacherId = document.getElementById('teacher-id').value;
    const teacherName = document.getElementById('teacher-name').value;
    const subjectTaught = document.getElementById('subject-taught').value;
    const teacherExperience = document.getElementById('teacher-experience').value;

    if (teacherId === '' || teacherName === '' || subjectTaught === '' || teacherExperience === '') {
        alert('Please fill in all fields.');
        return;
    }

    const teacherList = document.getElementById('teacher-list');
    const li = document.createElement('li');
    li.innerHTML = `ID: ${teacherId} - ${teacherName}, <span class="subject">${subjectTaught}</span> 
                    <span class="experience">${teacherExperience} years</span>`;
    teacherList.appendChild(li);

    document.getElementById('teacher-id').value = '';
    document.getElementById('teacher-name').value = '';
    document.getElementById('subject-taught').value = '';
    document.getElementById('teacher-experience').value = '';
}