package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        if (cd == null || cd.isEmpty() || name == null || name.isEmpty()) {
            // 必要なパラメータがない場合、エラーメッセージを設定し、元のページにリダイレクト
            request.setAttribute("error", "科目が存在しません");
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        School school = teacher.getSchool();
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject(cd, name, school);

        try {
            boolean isUpdated = subjectDao.save(subject);
            if (isUpdated) {
            	response.sendRedirect("subject_update_done.jsp");
            } else {
            	response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "科目情報の更新中にエラーが発生しました。");
        }
    }
}
