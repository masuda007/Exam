package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private String baseSql = "SELECT s.ent_year, t.class_num, s.no AS student_no, s.name AS student_name, t.point, t.no AS test_count, sub.name " +
                              "FROM test t " +
                              "JOIN student s ON t.student_no = s.no " +
                              "JOIN subject sub ON t.subject_cd = sub.cd " +
                              "WHERE s.ent_year = ? AND t.class_num = ? AND sub.name = ? " +
                              "ORDER BY s.no, t.no";

    private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException {
        Map<String, TestListSubject> map = new HashMap<>();
        while (rSet.next()) {
            String studentNo = rSet.getString("student_no");
            TestListSubject testListSubject = map.getOrDefault(studentNo, new TestListSubject());
            testListSubject.setEntYear(rSet.getInt("ent_year"));
            testListSubject.setStudentNo(studentNo);
            testListSubject.setStudentName(rSet.getString("student_name"));
            testListSubject.setClassNum(rSet.getString("class_num"));

            // テスト番号(t.no)とポイント(t.point)をマップに格納
            testListSubject.putPoint(rSet.getInt("test_count"), rSet.getInt("point"));

            // デバッグログに出力
            System.out.println("Debug: " + testListSubject.getStudentNo() + ", TestNo: " + rSet.getInt("test_count") + ", Point: " + rSet.getInt("point"));

            map.put(studentNo, testListSubject);
        }
        return new ArrayList<>(map.values());
    }


    public List<TestListSubject> filter(int entYear, String classNum, String subject) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql)) {

            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject);

            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error filtering test list subjects", e);
        }
        return list;
    }
}
