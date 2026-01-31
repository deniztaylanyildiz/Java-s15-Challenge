package com.workingtechs15Proj.Model;

import com.workingtechs15Proj.Model.abs.MemberRecord;

public class Faculty extends MemberRecord {
    public Faculty(String id, String name) { super(id, name); }
    @Override public String getMemberType() { return "Akademisyen"; }
    @Override public int getMaxBookLimit() { return 10; }
}