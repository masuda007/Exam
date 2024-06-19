package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClassNumDao;
import DAO.StudentDao;
import bean.Student;
import bean.Teacher;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String entYearStr = "";
        String classNum = "";
        String isAttendStr = "";
        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null;
        LocalDate todayDate = LocalDate.now();
        int year = todayDate.getYear();
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        isAttendStr = request.getParameter("f3");

        // 入学年度が設定されている場合
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (isAttendStr != null) {
            isAttend = true;
        }

        // クラスが指定されているのに入学年度が指定されていない場合のエラーチェック
        if ((classNum != null && !classNum.equals("0")) && entYear == 0) {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            if (entYear != 0 && !classNum.equals("0")) {
                students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
            } else if (entYear != 0) {
                students = sDao.filter(teacher.getSchool(), entYear, isAttend);
            } else {
                students = sDao.filter(teacher.getSchool(), isAttend);
            }
        }

        List<String> list = cNumDao.filter(teacher.getSchool());

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        if (isAttendStr != null) {
            request.setAttribute("f3", isAttendStr);
        }

        request.setAttribute("students", students);
        request.setAttribute("class_num_set", list);
        request.setAttribute("ent_year_set", entYearSet);

        request.getRequestDispatcher("/test/student_list.jsp").forward(request, response);
    }
}
