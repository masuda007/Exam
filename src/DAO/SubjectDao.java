package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    // 科目取得
    public Subject get(String cd, String school) throws Exception {
        Subject subject = null;
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
                "SELECT * FROM subject WHERE cd = ? AND school = ?");
        st.setString(1, cd);
        st.setString(2, school);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchool(rs.getString("school"));
        }

        st.close();
        con.close();
        return subject;
    }

    // 科目一覧取得
    public List<Subject> filter(String school) throws Exception {
        List<Subject> list = new ArrayList<Subject>();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
                "SELECT cd, name FROM subject WHERE school = ?");
        st.setString(1, school);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            list.add(subject);
        }

        st.close();
        con.close();
        return list;
    }

    // 科目登録
    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
                "INSERT INTO subject (cd, name, school) VALUES (?, ?, ?)");
        st.setString(1, subject.getCd());
        st.setString(2, subject.getName());
        st.setString(3, subject.getSchool());

        int rows = st.executeUpdate();
        st.close();
        con.close();
        return rows > 0;
    }

    // 科目削除
    public boolean delete(Subject subject) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
                "DELETE FROM subject WHERE cd = ? AND school = ?");
        st.setString(1, subject.getCd());
        st.setString(2, subject.getSchool());

        int rows = st.executeUpdate();
        st.close();
        con.close();
        return rows > 0;
    }
}
