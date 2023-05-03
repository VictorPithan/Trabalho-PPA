package sqlite.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSqlite {
  private static Connection instance = null;

  private ConexaoSqlite() {}
  
  public static synchronized Connection getInstance(String name) throws SQLException{
      String url = "jdbc:sqlite:" + name;
      if (instance == null) {
          instance = DriverManager.getConnection(url);
      }

      return instance;
  }
}
