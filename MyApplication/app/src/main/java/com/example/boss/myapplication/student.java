package com.example.boss.myapplication;

import java.io.Serializable;

/**
 * Created by BOSS on 2016/10/31.
 */
public class student implements Serializable {
    private String name;
    private String id;
    private String stu_class;
    private String grade1;
    private String grade2;
    private String grade3;
    private String grade4;
    private String grade5;
    private String totle;
    public student(){}
    public student(String name,String id,String stu_class,String grade1,String grade2,String grade3,String grade4,String grade5,String totle)
    {
        this.name=name;
        this.id=id;
        this.stu_class=stu_class;
        this.grade1=grade1;
        this.grade2=grade2;
        this.grade3=grade3;
        this.grade4=grade4;
        this.grade5=grade5;
        this.totle=totle;
    }
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public String getStu_class()
    {
        return stu_class;
    }
    public String getGrade1()
    {
        return grade1;
    }
    public String getGrade3()
    {
        return grade3;
    }
    public String getGrade4()
    {
        return grade4;
    }
    public String getGrade5()
    {
        return grade5;
    }
    public String getGrade2()
    {
        return grade2;
    }
    public String getTotle()
    {
        return totle;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public void setStu_class(String stu_class)
    {
        this.stu_class=stu_class;
    }
    public void setGrade1(String grade1)
    {
        this.grade1=grade1;
    }
    public void setGrade2(String grade2)
    {
        this.grade2=grade2;
    }
    public void setGrade3(String grade3)
    {
        this.grade3=grade3;
    }
    public void setGrade4(String grade4)
    {
        this.grade4=grade4;
    }
    public void setGrade5(String grade5)
    {
        this.grade5=grade5;
    }
    public void setTotle(String totle)
    {
        this.totle=totle;
    }
}
