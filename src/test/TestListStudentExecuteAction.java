package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TestListStudentDao;
import bean.School;
import bean.TestListStudent;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<String> errors = new ArrayList<>();

            // パラメータを取得
            String studentNo = request.getParameter("studentNo");

            // 必須項目のチェック
            if (studentNo == null || studentNo.isEmpty()) {
                errors.add("学生番号を入力してください");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("test_regist.jsp").forward(request, response);
                return;
            }

            // 学校情報を取得
            School school = (School) request.getSession().getAttribute("school");
            if (school == null) {
                throw new IllegalStateException("School object is not found in session");
            }

            // DAOを使用して条件に合ったテストデータを取得
            TestListStudentDao testListStudentDao = new TestListStudentDao();
            List<TestListStudent> testList = testListStudentDao.filter(studentNo, school);

            // リクエストにセット
            request.setAttribute("testList", testList);

            // 遷移先JSPページを指定
            request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
        } catch (Exception e) {
            // 例外処理
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
