package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.ClassNumDao;
import DAO.NumDao;
import DAO.SubjectsDao;
import DAO.YearDao;
import bean.School;
import bean.Teacher;

public class Util {

    public Teacher getUser(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            throw new Exception("ログインしていません。");
        }
        return teacher;
    }


    public List<String> setClassNumSet(HttpServletRequest request) throws Exception {
        // ユーザーの学校情報を取得
        School school = getUser(request).getSchool();

        // ClassNumDao を使用してクラスのリストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(school);

        // 取得したクラスのリストを HttpServletRequest の属性に設定
		return classNumList;
    }


    public List<Integer> setYearSet(HttpServletRequest request) throws Exception {
        // ユーザーの学校情報を取得
        School school = getUser(request).getSchool();

        // YearDao を使用して年度のリストを取得
        YearDao yearDao = new YearDao();
        List<Integer> yearList = yearDao.filter(school);

        // 取得した年度のリストを HttpServletRequest の属性に設定
        return yearList;
    }


    public List<String> setSubjectsSet(HttpServletRequest request) throws Exception {
        // ユーザーの学校情報を取得
        School school = getUser(request).getSchool();

        // SubjectDao を使用して教科のリストを取得
        SubjectsDao subjectsDao = new SubjectsDao();
        List<String> subjectsList = subjectsDao.filter(school);

        // 取得した教科のリストを HttpServletRequest の属性に設定
        return subjectsList;
    }


    public List<Integer> setNumSet(HttpServletRequest request) throws Exception {
        // ユーザーの学校情報を取得
        School school = getUser(request).getSchool();

        // NumDao を使用して指定された回数のテストのリストを取得
        NumDao numDao = new NumDao();
        List<Integer> numList = numDao.No(school);

        return numList; // 数値のリストを戻り値として返す
    }
}
