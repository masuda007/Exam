package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    public Subject get(String cd, School school) throws Exception {
        String sql = "SELECT * FROM SUBJECT WHERE cd = ? AND school_cd = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, cd);
        	ps.setString(2, school.getCd());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Subject(rs.getString("cd"), rs.getString("name"), school);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error getting subject", e);
        }
        return null;
    }

    public List<Subject> filter(School school) throws Exception {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM SUBJECT WHERE school_cd = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, school.getCd());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subjects.add(new Subject(rs.getString("cd"), rs.getString("name"), school));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error filtering subjects", e);
        }
        return subjects;
    }

    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            Subject old = get(subject.getCd(), subject.getSchool());
            if (old == null) {
                // 新規挿入
                String sql = "INSERT INTO SUBJECT (cd, name, school_cd) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, subject.getCd());
                statement.setString(2, subject.getName());
                statement.setString(3, subject.getSchool().getCd());
            } else {
                // 更新
                String sql = "UPDATE SUBJECT SET name=? WHERE cd=? AND school_cd=?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getSchool().getCd());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error saving subject", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count > 0;
    }


    public boolean delete(Subject subject) throws Exception {
        String sql = "DELETE FROM SUBJECT WHERE cd = ? AND school_cd = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getSchool().getCd());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error deleting subject", e);
        }
    }
}
