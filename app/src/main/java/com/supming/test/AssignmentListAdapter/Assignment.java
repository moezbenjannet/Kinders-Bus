package com.supming.test.AssignmentListAdapter;



public class Assignment {

    private String classe;
    private String teachername;
    private String teachernum;
    private String children;

   public Assignment(String classe,String teachername, String teachernum,String children) {

       this.classe = classe;
       this.teachername = teachername;
       this.teachernum = teachernum ;
       this.children = children ;
    }
    public Assignment(){}

    public String getclasse() {return classe;}
    public void setclasse(String classe){this.classe=classe;}

    public String getteachername() {return teachername;}
    public void setteachername(String teachername){this.teachername=teachername;}

    public String getteachernum() {return teachernum;}
    public void setteachernum(String teachernum){this.teachernum=teachernum;}

    public String getchildren() {return children;}
    public void setchildren(String children){this.children=children;}

}
