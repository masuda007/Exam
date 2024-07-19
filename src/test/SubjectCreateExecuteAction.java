package test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ログインしている教師が所属する学校を取得
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        Map<String, String> errors = new HashMap<>();

        // 科目コードの長さチェック
        if (cd == null || cd.length() != 3) {
            errors.put("cd", "科目コードは3文字で入力してください。");
        }

        // 科目名のチェック
        if (name == null || name.isEmpty()) {
            errors.put("name", "科目名を入力してください。");
        }

        // 科目コードの重複チェック
        SubjectDao subjectDao = new SubjectDao();
        Subject existingSubject = null;
        try {
            existingSubject = subjectDao.get(cd, school);
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("db_error", "データベースエラーが発生しました。");
        }

        if (existingSubject != null) {
            errors.put("cd", "科目コードが重複しています。");
        }

        // エラーメッセージがある場合
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
            return;
        }

        // 科目データの作成
        Subject subject = new Subject(cd, name, school);
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        // データの保存（DAOを通して行う）
        try {
            subjectDao.save(subject);
        } catch (Exception e) {
            e.printStackTrace();
            errors.put("db_error", "データベースエラーが発生しました。");
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        // リダイレクトまたはフォワード
        response.sendRedirect("subject_create_done.jsp");
    }
}
