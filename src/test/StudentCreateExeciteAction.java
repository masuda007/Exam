package test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.StudentDao;
import bean.Student;
import bean.Teacher;
import tool.Action;

public class StudentCreateExeciteAction extends Action {

	 @Override
	    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	        HttpSession session = request.getSession();
	        Teacher teacher = (Teacher) session.getAttribute("teacher");

	        String no = request.getParameter("no");
	        String name = request.getParameter("name");
	        String entYearStr = request.getParameter("ent_year");
	        String classNum = request.getParameter("class_num");
	        Map<String, String> errors = new HashMap<>();

	        // 名前の長さチェック
	        if (name != null && name.length() > 10) {
	            errors.put("name", "名前は10文字以内で入力してください。");
	        }

	        // 入学年度のチェック
	        if (entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0")) {
	            errors.put("ent_year", "入学年度を選択してください");
	        }

	        int entYear = 0;
	        try {
	            entYear = Integer.parseInt(entYearStr);
	        } catch (NumberFormatException e) {
	            errors.put("ent_year", "有効な入学年度を入力してください。");
	        }

	        // 学生番号の重複チェック
	        StudentDao studentDao = new StudentDao();
	        Student existingStudent = null;
	        try {
	            existingStudent = studentDao.get(no);
	        } catch (Exception e) {
	            e.printStackTrace();
	            errors.put("db_error", "データベースエラーが発生しました。");
	        }

	        if (existingStudent != null) {
	            errors.put("no", "学生番号が重複しています。");
	        }

	        // エラーメッセージがある場合
	        if (!errors.isEmpty()) {
	            request.setAttribute("errors", errors);
	            request.getRequestDispatcher("StudentCreate.action").forward(request, response);
	            return;
	        }

	        // 学生データの作成
	        Student student = new Student();
	        student.setNo(no);
	        student.setName(name);
	        student.setEntYear(entYear);
	        student.setClassNum(classNum);
	        student.setAttend(true);
	        student.setSchool(teacher.getSchool());

	        // データの保存（DAOを通して行う）
	        try {
	            studentDao.save(student);
	        } catch (Exception e) {
	            e.printStackTrace();
	            errors.put("db_error", "データベースエラーが発生しました。");
	            request.setAttribute("errors", errors);
	            request.getRequestDispatcher("StudentCreate.action").forward(request, response);
	            return;
	        }

	        // リダイレクトまたはフォワード
	        response.sendRedirect("student_create_done.jsp");
	    }
	}