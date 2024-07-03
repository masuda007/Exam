package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import bean.Student;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String no = request.getParameter("no");
        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String classNum = request.getParameter("class_num");
        boolean isAttend = request.getParameter("is_attend") != null; // checkboxの値の取得

        // 学生情報を設定
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        // 学校情報の設定は必要に応じて追加する

        // 学生情報の保存
        StudentDao studentDao = new StudentDao();
        boolean success = studentDao.save(student);

        if (success) {
            // 成功時の処理
	        response.sendRedirect("student_update_done.jsp");
        } else {
            // 失敗時の処理
	        response.sendRedirect("error.jsp");
        }

    }
}
