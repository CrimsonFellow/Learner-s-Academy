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

import com.simplilearn.model.SchoolClass; // Change import statement

public class ClassService {

    private DataSource dataSource;

    public ClassService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/LearnerAcademyDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new class
    public void addClass(SchoolClass schoolClass) {
        String sql = "INSERT INTO classes (class_name) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schoolClass.getName()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve a class by ID
    public SchoolClass getClass(int id) { 
        String sql = "SELECT * FROM classes WHERE class_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SchoolClass(rs.getInt("class_id"), rs.getString("class_name")); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    // Method to update an existing class
    public void updateClass(SchoolClass schoolClass) { 
        String sql = "UPDATE classes SET class_name = ? WHERE class_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, schoolClass.getName()); 
            stmt.setInt(2, schoolClass.getId()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a class by ID
    public void deleteClass(int id) {
        String sql = "DELETE FROM classes WHERE class_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // Method to retrieve all classes
    public List<SchoolClass> getAllClasses() { 
        List<SchoolClass> classes = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                classes.add(new SchoolClass(rs.getInt("class_id"), rs.getString("class_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return classes;
    }
}

