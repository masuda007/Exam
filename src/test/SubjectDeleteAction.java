package test;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.School;
import tool.Action;

public class SubjectDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cd = request.getParameter("cd"); // 科目コードを取得

        // セッションから学校情報を取得
        HttpSession session = request.getSession();
        School school = (School) session.getAttribute("school");

        // 科目情報を取得
        SubjectDao subjectDao = new SubjectDao();
        bean.Subject subject = subjectDao.get(cd, school);

        // 科目情報をリクエストスコープにセット
        request.setAttribute("subject", subject);

        // 科目削除ページにフォワード
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
    }
}