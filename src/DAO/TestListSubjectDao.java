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

    private String baseSql = "SELECT * FROM test_list_subject WHERE ent_year=? AND class_num=? AND subject=? AND school_cd=?";

    private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException {
        List<TestListSubject> list = new ArrayList<>();
        while (rSet.next()) {
            TestListSubject testListSubject = new TestListSubject();
            testListSubject.setEntYear(rSet.getInt("ent_year"));
            testListSubject.setStudentNo(rSet.getString("student_no"));
            testListSubject.setStudentName(rSet.getString("student_name"));
            testListSubject.setClassNum(rSet.getString("class_num"));

            Map<Integer, Integer> points = new HashMap<>();
            // Assuming you have columns like point_1, point_2, ..., point_n
            for (int i = 1; i <= 10; i++) {  // adjust the range as per your schema
                int point = rSet.getInt("point_" + i);
                if (!rSet.wasNull()) {
                    points.put(i, point);
                }
            }
            testListSubject.getPoints().putAll(points);

            list.add(testListSubject);
        }
        return list;
    }

    public List<TestListSubject> filter(int entYear, String classNum, String subject, String school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql)) {

            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject);
            statement.setString(4, school);

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
