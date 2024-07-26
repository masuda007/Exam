package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TestListStudentDao;
import bean.School;
import bean.Student;
import bean.TestListStudent;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<String> errors = new ArrayList<>();

            Util util = new Util();
            List<Integer> yearList = util.setYearSet(request);
            List<String> classNumList = util.setClassNumSet(request);
            List<String> subjectsList = util.setSubjectsSet(request);

            // パラメータを取得
            String entYearStr = request.getParameter("f1");
            String classNum = request.getParameter("f2");
            String subject = request.getParameter("f3");
            String studentNo = request.getParameter("f4");

            // 必須項目のチェック
            if (studentNo == null || studentNo.isEmpty()) {
                errors.add("学生番号を入力してください");
            }


            // 学校情報を取得
            School school = (School) request.getSession().getAttribute("school");
            if (school == null) {
                throw new IllegalStateException("School object is not found in session");
            }

            // DAOを使用して学生情報を取得
            TestListStudentDao testListStudentDao = new TestListStudentDao();
            Student student = testListStudentDao.getStudent(studentNo, school);

            if (student == null) {
                errors.add("指定された学生は存在しません");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
                return;
            }

            // 学生の成績データを取得
            List<TestListStudent> testList = testListStudentDao.filter(studentNo, school);

            // リクエストにセット
            request.setAttribute("testList", testList);
            request.setAttribute("student", student);
            request.setAttribute("f1", entYearStr);
            request.setAttribute("f2", classNum);
            request.setAttribute("f3", subject);

            // リクエストに各種リストを設定
            request.setAttribute("yearSet", yearList);
            request.setAttribute("classNumSet", classNumList);
            request.setAttribute("subjectsSet", subjectsList);

            // 遷移先JSPページを指定
            request.getRequestDispatcher("test_list_student.jsp").forward(request, response);

        } catch (Exception e) {
            // 例外処理
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
