package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            // ログインしていない場合の処理
            response.sendRedirect("login.jsp");
            return;
        }

        School school = teacher.getSchool();
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.filter(school);

        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("/test/subject_list.jsp").forward(request, response);
    }
}
