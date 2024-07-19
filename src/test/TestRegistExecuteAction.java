package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TestDao;
import bean.School;
import bean.Test;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<String> errors = new ArrayList<>();

            // パラメータを取得
            String entYearStr = request.getParameter("f1");
            String classNum = request.getParameter("f2");
            String subject = request.getParameter("f3");
            String testNumStr = request.getParameter("f4");

            // 必須項目のチェック
            if (entYearStr == null || classNum == null || subject == null || testNumStr == null ||
                entYearStr.isEmpty() || classNum.isEmpty() || subject.isEmpty() || testNumStr.isEmpty()) {
                errors.add("入学年度とクラスと科目と回数を選択してください");
            }

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("test_regist.jsp").forward(request, response);
                return;
            }

            int entYear = Integer.parseInt(entYearStr);
            int testNum = Integer.parseInt(testNumStr);

            // 学校情報を取得
            School school = (School) request.getSession().getAttribute("school");
            if (school == null) {
                throw new IllegalStateException("School object is not found in session");
            }

            // DAOを使用して条件に合ったテストデータを取得
            TestDao testDao = new TestDao();
            List<Test> testList = testDao.filter(entYear, classNum, subject, testNum, school);

            // リクエストにセット
            request.setAttribute("testList", testList);
            request.setAttribute("f3", subject);
            request.setAttribute("f4", testNum);

            // 遷移先JSPページを指定
            request.getRequestDispatcher("TestRegist.action").forward(request, response);
        } catch (NumberFormatException e) {
            // 数値変換エラーが発生した場合の処理
            e.printStackTrace();
            request.setAttribute("errors", "数値を正しく入力してください");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        } catch (Exception e) {
            // その他の例外処理
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
