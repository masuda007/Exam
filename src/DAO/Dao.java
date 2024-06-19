package DAO;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * データベース接続を管理するDAOクラス。
 * データソースを使用してデータベースへの接続を提供する
 */
public class Dao {
    // データソースの静的フィールド
    static DataSource ds;

    /**
     * データベースへの接続を取得する
     */
    public Connection getConnection() throws Exception {
        // データソースがまだ初期化されていない場合、初期化する
        if (ds == null) {
            InitialContext ic = new InitialContext();
            // JNDIを使用してデータソースをルックアップする
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/test");
        }
        // データソースから接続を取得して返す
        return ds.getConnection();
    }
}
