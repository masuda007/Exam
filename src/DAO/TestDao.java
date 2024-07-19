package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {
	private String baseSql = "SELECT s.ent_year, s.class_num, s.no, s.name AS student_name, t.point, t.no, sub.name " +
                              "FROM test t " +
                              "JOIN student s ON t.student_no = s.no " +
                              "JOIN subject sub ON t.subject_cd = sub.cd " +
                              "WHERE t.school_cd=?";

    public Test get(Student student, String subject, School school, int no) throws Exception {
        Test test = new Test();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM test WHERE school_cd=? AND no=?");
            statement.setString(1, school.getCd());
            statement.setInt(2, no);
            rSet = statement.executeQuery();
            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()) {
            	test.setStudent(student);
                test.setClassNum(rSet.getString("class_num"));
                test.setSubject(rSet.getString("subject_cd"));
                test.setSchool(schoolDao.get(rSet.getString("school_cd")));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
            } else {
                test = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            save(rSet, statement, connection);
        }
        return test;
    }

    public List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();

                Student student = new Student();
                student.setEntYear(rSet.getInt("ent_year"));
                student.setNo(rSet.getString("no"));
                student.setClassNum(rSet.getString("class_num"));
                student.setName(rSet.getString("student_name"));

                test.setStudent(student);
                test.setClassNum(rSet.getString("class_num"));
                test.setSubject(rSet.getString("name"));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));

                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Test> filter(int entYear, String classNum, String subject, int no, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        if (school == null) {
            throw new IllegalArgumentException("School cannot be null");
        }

        StringBuilder query = new StringBuilder(baseSql);
        List<Object> parameters = new ArrayList<>();
        parameters.add(school.getCd());

        if (entYear != 0) {
            query.append(" AND s.ent_year = ?");
            parameters.add(entYear);
        }

        if (classNum != null && !classNum.isEmpty()) {
            query.append(" AND s.class_num = ?");
            parameters.add(classNum);
        }

        if (subject != null && !subject.isEmpty()) {
            query.append(" AND sub.name = ?");
            parameters.add(subject);
        }

        if (no != 0) {
            query.append(" AND t.no = ?");
            parameters.add(no);
        }

        query.append(" ORDER BY t.no ASC");

        try {
            statement = connection.prepareStatement(query.toString());

            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            rSet = statement.executeQuery();
            if (rSet == null) {
                throw new SQLException("ResultSet is null");
            }
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            save(rSet, statement, connection);
        }

        return list;
    }

    public boolean save(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "UPDATE test SET point=? WHERE student_no=? AND class_num=? AND subject_cd=? AND school_cd=? AND no=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, test.getPoint());
            statement.setString(2, test.getStudent().getNo());
            statement.setString(3, test.getClassNum());
            statement.setString(4, test.getSubject());
            statement.setString(5, test.getSchool().getCd());
            statement.setInt(6, test.getNo());

            System.out.println("Executing SQL: " + statement.toString()); // SQL文を出力
            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated); // 更新された行数を出力
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace(); // コンソールにエラーメッセージを出力
            throw e;
        } finally {
            save(null, statement, connection);
        }
    }




    private void save(ResultSet resultSet, PreparedStatement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqle) {
                throw sqle;
            }
        }
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
}
