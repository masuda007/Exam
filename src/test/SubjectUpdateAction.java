package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String cd = request.getParameter("cd");
        if (cd == null || cd.isEmpty()) {
            return;
        }

        School school = teacher.getSchool();
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(cd, school);

        if (subject == null) {
            return;
        }

        request.setAttribute("subject", subject);
        request.setAttribute("cd", cd);

        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
