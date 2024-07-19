package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private static final String baseSql = "SELECT s.name as subject_name, t.subject_cd, t.num, t.point " +
                                          "FROM test_list t " +
                                          "JOIN subject s ON t.subject_cd = s.cd " +
                                          "WHERE t.student_no = ?";

    public List<TestListStudent> postFilter(ResultSet rSet) throws SQLException {
        List<TestListStudent> testListStudents = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setSubjectName(rSet.getString("subject_name"));
            testListStudent.setSubjectCd(rSet.getString("subject_cd"));
            testListStudent.setNum(rSet.getInt("num"));
            testListStudent.setPoint(rSet.getInt("point"));
            testListStudents.add(testListStudent);
        }
        return testListStudents;
    }

    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> testListStudents = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(baseSql)) {
            ps.setString(1, student.getNo()); // getNo() を使用して学生番号を取得
            try (ResultSet rs = ps.executeQuery()) {
                testListStudents = postFilter(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error filtering test list students", e);
        }
        return testListStudents;
=======
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
>>>>>>> branch 'master' of https://github.com/masuda007/Exam.git
    }
}
