package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    }
}
