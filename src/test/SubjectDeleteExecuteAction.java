package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SubjectDao;
import bean.School;
import bean.Subject;
import bean.Teacher;
import tool.Action;


public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession();
    	Teacher teacher =(Teacher) session.getAttribute("teacher");


    	String cd = request.getParameter("cd"); // 科目コードを取得
    	String name=request.getParameter("name");
    	School school=teacher.getSchool();

    	SubjectDao subjectDao = new SubjectDao();
    	Subject subject = new Subject(cd, name, school);


    	try {
            boolean delete = subjectDao.delete(subject);
            if (delete) {
            	response.sendRedirect("subject_delete_done.jsp");
            } else {
            	response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "科目情報の削除中にエラーが発生しました。");
            response.sendRedirect("error.jsp");
        }


    }
}