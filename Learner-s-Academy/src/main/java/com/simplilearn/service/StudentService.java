package com.simplilearn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.simplilearn.model.Student;

public class StudentService {

    private DataSource dataSource;

    public StudentService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/LearnerAcademyDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new student
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (student_name, class_id) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getClassId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a student by ID
    public Student getStudent(int id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("student_id"), rs.getString("student_name"), rs.getInt("class_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to update an existing student
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET student_name = ?, class_id = ? WHERE student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getClassId());
            stmt.setInt(3, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student by ID
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("student_id"), rs.getString("student_name"), rs.getInt("class_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
