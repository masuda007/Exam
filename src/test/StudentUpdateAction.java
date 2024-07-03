package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClassNumDao;
import DAO.StudentDao;
import bean.Student;
import bean.Teacher;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String no = request.getParameter("no");
        if (no == null || no.isEmpty()) {
            return;
        }

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);

        if (student == null) {
            return;
        }

        request.setAttribute("student", student);

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(teacher.getSchool());

        request.setAttribute("class_num_set", classNumSet);

        request.getRequestDispatcher("student_update.jsp").forward(request, response);
    }
}
