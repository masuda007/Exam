package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
    private String baseSql = "SELECT s.no, s.name AS student_name, t.point, t.no AS test_no, sub.name AS subject_name, sub.cd AS subject_cd " +
                             "FROM test t " +
                             "JOIN student s ON t.student_no = s.no " +
                             "JOIN subject sub ON t.subject_cd = sub.cd " +
                             "WHERE t.school_cd=?";

    public List<TestListStudent> postFilter(ResultSet rSet, School school) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListStudent test = new TestListStudent();

                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("student_name"));

                test.setStudent(student);
                test.setSubjectName(rSet.getString("subject_name"));
                test.setSubjectCd(rSet.getString("subject_cd"));
                test.setNo(rSet.getInt("test_no"));
                test.setPoint(rSet.getInt("point"));

                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TestListStudent> filter(String studentNo, School school) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        if (school == null) {
            throw new IllegalArgumentException("School cannot be null");
        }

        StringBuilder query = new StringBuilder(baseSql);
        List<Object> parameters = new ArrayList<>();
        parameters.add(school.getCd());

        if (studentNo != null && !studentNo.isEmpty()) {
            query.append(" AND s.no = ?");
            parameters.add(studentNo);
        }

        query.append(" ORDER BY sub.cd ASC");

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

    public Student getStudent(String studentNo, School school) throws Exception {
        Student student = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM student WHERE no = ? AND school_cd = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, studentNo);
            st.setString(2, school.getCd());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                // 他のフィールドを設定
            }
        }
        return student;
    }
}
