package bean;

<<<<<<< HEAD
public class Test 
=======
import java.io.Serializable;

public class Test implements Serializable{

    private Student student;
    private String classNum;
    private String subject;
    private School school;
    private int no;
    private int point;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }


    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }


    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }


    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }


    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }


    public int getPoint(){
    	return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }

}
>>>>>>> branch 'master' of https://github.com/masuda007/Exam.git
