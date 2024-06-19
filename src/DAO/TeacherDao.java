package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao {

    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        java.sql.Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM teacher WHERE id=?");
            statement.setString(1, id);
            ResultSet rSet = statement.executeQuery();
            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));
                teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
            }

        } catch (SQLException e) {
            throw new Exception("SQL exception occurred: " + e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw new Exception("SQL exception occurred: " + sqle.getMessage());
                }
            }
        }
        return teacher;
    }

    public Teacher login(String id, String password) {
        Teacher teacher = null;

        try {
            teacher = get(id); // IDに基づいてTeacherオブジェクトを取得する

            if (teacher != null && teacher.getPassword().equals(password)) {
                // パスワードが一致した場合
                return teacher;
            } else {
                // パスワードが一致しない場合、またはteacherがnullの場合
                return null;
            }
        } catch (Exception e) {
            // 例外処理
            e.printStackTrace();
            return null;
        }
    }
}
