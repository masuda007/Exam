package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // リクエスト属性の設定
        try {
            Util util = new Util(); // 修正: Utilクラスのインスタンスを生成

            // 各種リストの取得
            List<Integer> yearList = util.setYearSet(request);
            List<String> classNumList = util.setClassNumSet(request);
            List<String> subjectsList = util.setSubjectsSet(request);

            // パラメータを取得
            String entYearStr = request.getParameter("f1");
            int entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : 0;
            String classNum = request.getParameter("f2");
            String subjectCd = request.getParameter("f3");

            // リクエストにセット
            request.setAttribute("yearSet", yearList);
            request.setAttribute("classNumSet", classNumList);
            request.setAttribute("subjectsSet", subjectsList);

            request.setAttribute("f1", entYear);
            request.setAttribute("f2", classNum);
            request.setAttribute("f3", subjectCd);


            // 遷移先JSPページを指定
            request.getRequestDispatcher("test_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            try {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
