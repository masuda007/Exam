package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.TestDao;
import bean.School;
import bean.Test;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 学校情報を取得
            HttpSession session = request.getSession();
            School school = (School) session.getAttribute("school");
            if (school == null) {
                throw new IllegalStateException("School object is not found in session");
            }

            // パラメータを取得
            String entYearStr = request.getParameter("f1");
            String classNum = request.getParameter("f2");
            String subject = request.getParameter("f3");
            String testNumStr = request.getParameter("f4");

            // 入力チェック
            if (entYearStr == null || entYearStr.equals("0") || classNum == null || classNum.equals("0") ||
                subject == null || subject.equals("0") || testNumStr == null || testNumStr.equals("0")) {
                request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
                request.getRequestDispatcher("TestRegist.action").forward(request, response);
                return;
            }

            // 数値への変換
            int entYear = Integer.parseInt(entYearStr);
            int testNum = Integer.parseInt(testNumStr);

            // ログ出力
            System.out.println("entYear: " + entYear);
            System.out.println("classNum: " + classNum);
            System.out.println("subject: " + subject);
            System.out.println("testNum: " + testNum);

            // DAOを使用して条件に合ったテストデータを取得
            TestDao testDao = new TestDao();
            List<Test> testList = testDao.filter(entYear, classNum, subject, testNum, school);

            // リクエストにセット
            request.setAttribute("testList", testList);
            request.setAttribute("f3", subject);
            request.setAttribute("f4", testNum);

            // 遷移先JSPページを指定
            request.getRequestDispatcher("TestRegist.action").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "エラーが発生しました");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        }
    }
}
