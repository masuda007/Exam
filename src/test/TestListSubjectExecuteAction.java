package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TestListSubjectDao;
import bean.TestListSubject;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Utilクラスのインスタンスを生成して各種リストを取得
        Util util = new Util();
        List<Integer> yearList = util.setYearSet(request);
        List<String> classNumList = util.setClassNumSet(request);
        List<String> subjectsList = util.setSubjectsSet(request);

        // パラメータを取得
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subject = request.getParameter("f3");

        // パラメータがnullまたは空でないかチェック
        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("0")) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "入学年度の形式が正しくありません。");
                forwardWithLists(request, response, yearList, classNumList, subjectsList);
                return;
            }
        }

        // 必須パラメータが存在するかチェック
        if ((entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0")) ||
            (classNum == null || classNum.isEmpty() || classNum.equals("0")) ||
            (subject == null || subject.isEmpty() || subject.equals("0"))) {
            request.setAttribute("error", "入学年度とクラスと科目を選択してください");
            forwardWithLists(request, response, yearList, classNumList, subjectsList);
            return;
        }


        // TestListSubjectDaoを使用してデータを取得
        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> list;
        try {
            list = dao.filter(entYear, classNum, subject);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "データ取得中にエラーが発生しました。");
            forwardWithLists(request, response, yearList, classNumList, subjectsList);
            return;
        }

        // リクエストにデータを設定
        request.setAttribute("testList", list);
        request.setAttribute("selectedSubject", subject);
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subject);

        // リクエストに各種リストを設定
        request.setAttribute("yearSet", yearList);
        request.setAttribute("classNumSet", classNumList);
        request.setAttribute("subjectsSet", subjectsList);

        // JSPページにフォワード
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }

    private void forwardWithLists(HttpServletRequest request, HttpServletResponse response, List<Integer> yearList, List<String> classNumList, List<String> subjectsList) throws Exception {
        // リクエストに各種リストを設定
        request.setAttribute("yearSet", yearList);
        request.setAttribute("classNumSet", classNumList);
        request.setAttribute("subjectsSet", subjectsList);

        // JSPページにフォワード
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}
