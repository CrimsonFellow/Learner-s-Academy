package com.simplilearn.model;

public class Assignment {
    private String className;
    private String subjectName;

    public Assignment(String className, String subjectName) {
        this.className = className;
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
