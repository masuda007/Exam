package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class YearDao extends Dao {

    public List<Integer> filter(School school) throws Exception {
        List<Integer> yearList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String sql = "SELECT DISTINCT ent_year FROM student WHERE school_cd = ? order by ent_year";
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int entYear = resultSet.getInt("ent_year");
                yearList.add(entYear);
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching years by school from database", e);
        } finally {
            close(resultSet, statement, connection);
        }

        return yearList;
    }

    private void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
