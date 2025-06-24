package com.edu.educational.repository;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.edu.educational.model.*;
import com.edu.educational.repository.impl.CourseRepositoryInterface;

public class FileCourseRepository implements CourseRepositoryInterface {

    private final String FILE_PATH;

    public FileCourseRepository()  throws CourseWasNotFoundException {
        try {
            URL location = FileCourseRepository.class.getProtectionDomain().getCodeSource().getLocation();
            String basePath = new File(location.toURI()).getPath();
            this.FILE_PATH = basePath + "/resources/course.txt";
        } catch (Exception e) {
            throw new CourseWasNotFoundException("Can't find path for CourseRepository.", e);
        }
    }

    public void saveCourse(Course course) throws CourseWasNotFoundException {
        List<Course> existingCourses = loadCoursesFromFile();

        for (Course exCourse : existingCourses) {
            if (exCourse.getName().equalsIgnoreCase(course.getName())) {
                throw new IllegalArgumentException("Course with name '" + course.getName() + "' already exists");
            }
        }

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)))) {
            pw.println("Course;" + course.getName());

            for (Person p : course.getParticipants()) {
                Student s = (Student) p;
                pw.println("Student;" + s.getName() + ";" + s.getEmail() + ";" + s.getGroup() + ";" + s.getAverageGrade());
            }

            for (Person p : course.getStaff()) {
                if (p.getClass() == Administrator.class) {
                    Administrator a = (Administrator) p;
                    pw.println("Administrator;" + a.getName() + ";" + a.getEmail() + ";" + a.getDepartment());
                } else if (p.getClass() == Teacher.class) {
                    Teacher t = (Teacher) p;
                    pw.println("Teacher;" + t.getName() + ";" + t.getEmail() + ";" + t.getSubject());
                }
            }

            pw.println("---");
        } catch (IOException e) {
            throw new CourseWasNotFoundException("Error while saving course to the file.", e);
        }
    }

    public List<Course> getAllCourses() throws CourseWasNotFoundException {
        return loadCoursesFromFile();
    }

    public List<Course> loadCoursesFromFile() throws CourseWasNotFoundException {
        List<Course> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Course course = null;
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.equals("---") || line.isBlank()) continue;

                String[] parts = line.split(";");
                switch (parts[0]) {
                    case "Course" -> {
                        if (course != null) result.add(course);
                        course = new Course(parts[1]);
                    }
                    case "Teacher" -> {
                        if (course != null) {
                            course.addParticipant(new Teacher(parts[1], parts[2], parts[3]));
                        }
                    }
                    case "Administrator" -> {
                        if (course != null) {
                            course.addParticipant(new Administrator(parts[1], parts[2], parts[3]));
                        }
                    }
                    case "Student" -> {
                        if (course != null) {
                            course.addParticipant(new Student(parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
                        }
                    }
                }
            }
            if (course != null) result.add(course);

        } catch (IOException e) {
            throw new CourseWasNotFoundException("Error while loading courses.", e);
        }

        return result;
    }

    public void obfuscateStudentInCourse(String courseName, String studentEmail) {
        List<String> lines = new ArrayList<>();
        boolean insideCourse = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Course;")) {
                    insideCourse = line.equalsIgnoreCase("Course;" + courseName);
                    lines.add(line);
                } else if (insideCourse && line.startsWith("Student;")) {
                    String[] parts = line.split(";");
                    if (parts.length >= 5 && parts[2].equalsIgnoreCase(studentEmail)) {
                        lines.add("Student;***;***@***;" + parts[3] + ";" + parts[4]);
                        continue;
                    }
                    lines.add(line);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while obfuscating data.");
            e.printStackTrace();
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String l : lines) {
                writer.println(l);
            }
        } catch (IOException e) {
            System.err.println("Error while writing data after obfuscation process");
            e.printStackTrace();
        }
    }
}