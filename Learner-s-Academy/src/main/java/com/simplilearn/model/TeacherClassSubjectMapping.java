package com.simplilearn.model;

public class TeacherClassSubjectMapping {
    private int id;
    private int teacherId;
    private int classId;
    private int subjectId;
    private String teacherName;
    private String className;
    private String subjectName;

    public TeacherClassSubjectMapping() {
    }

    public TeacherClassSubjectMapping(int id, int teacherId, int classId, int subjectId) {
        this.id = id;
        this.teacherId = teacherId;
        this.classId = classId;
        this.subjectId = subjectId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
