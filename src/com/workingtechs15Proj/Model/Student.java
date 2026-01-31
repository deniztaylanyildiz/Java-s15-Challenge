package com.workingtechs15Proj.Model;

import com.workingtechs15Proj.Model.abs.MemberRecord;


public class Student extends MemberRecord {
    public Student(String id, String name) { super(id, name); }
    @Override public String getMemberType() { return "Öğrenci"; }
    @Override public int getMaxBookLimit() { return 5; }
}