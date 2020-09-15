package Course;

public class Course {
    private String name;
    private String Teacher_name;
    private String room;
    public Course(){}
    public Course(String name,String teacher_name,String room)
    {
            this.name=name;
            this.Teacher_name=teacher_name;
            this.room=room;
    }
   public String GetNname()
    {
        return this.name;
    }
    public String Get_T_Name()
    {
        return this.Teacher_name;
    }
    public String GetRoom()
    {
        return this.room;
    }

}
