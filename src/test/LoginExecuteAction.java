package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TeacherDao;
import bean.Teacher;
import tool.Action;

public class LoginExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);

        if (teacher != null) {
            // ログイン成功時の処理
            session.setAttribute("teacher", teacher);
            response.sendRedirect("menu.jsp"); // menu.jspにリダイレクト
        } else {
            // ログイン失敗時の処理
            String errorMessage = "IDまたはパスワードが間違っています。再度お試しください。";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
