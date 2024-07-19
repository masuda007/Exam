package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class NumDao extends Dao {

    public List<Integer> No(School school) throws Exception {
        List<Integer> numList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String sql = "SELECT no FROM test WHERE school_cd = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int testNum = resultSet.getInt("no");
                numList.add(testNum);
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching numbers by school from database", e);
        } finally {
            close(resultSet, statement, connection);
        }

        return numList;
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
