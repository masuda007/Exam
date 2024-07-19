package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectCdDao;
import DAO.TestDao;
import bean.School;
import bean.Student;
import bean.Teacher;
import bean.Test;
import tool.Action;

public class TestExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        // パラメータを取得
        String studentNo = request.getParameter("studentNo");
        String classNum = request.getParameter("classNum");
        String subjectName = request.getParameter("subjectCd"); // フォームからの名前を取得

        SubjectCdDao subjectCdDao = new SubjectCdDao();
        String subjectCd = null;
        try {
            subjectCd = subjectCdDao.getSubjectCd(subjectName); // 名前からCDを取得
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "科目のCD取得中にエラーが発生しました。");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        int testNo = Integer.parseInt(request.getParameter("testNo"));
        int point = Integer.parseInt(request.getParameter("point"));

        // デバッグ用ログ
        System.out.println("Debug: studentNo: " + studentNo);
        System.out.println("Debug: classNum: " + classNum);
        System.out.println("Debug: subjectCd: " + subjectCd);
        System.out.println("Debug: testNo: " + testNo);
        System.out.println("Debug: point: " + point);

        // テストオブジェクトを作成
        Test test = new Test();
        Student student = new Student();
        student.setNo(studentNo);
        test.setStudent(student);
        test.setClassNum(classNum);
        test.setSubject(subjectCd); // CDを設定する
        test.setSchool(school);
        test.setNo(testNo);
        test.setPoint(point);

        TestDao testDao = new TestDao();

        try {
            boolean isUpdated = testDao.save(test);
            System.out.println("Debug: isUpdated: " + isUpdated);
            if (isUpdated) {
                response.sendRedirect("test_regist_done.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace(); // コンソールにエラーメッセージを出力
            request.setAttribute("error", "テスト情報の更新中にエラーが発生しました。");
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        }
    }
}
