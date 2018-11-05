package sample.mdatabase;

import java.sql.*;

public class MDataBase {
    private final String URL = "jdbc:mysql://localhost:3306/krosp_lab5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   // private final String URL = "jdbc:mysql://localhost:3306/krosp_lab5";
    private final String NAME = "root";
    private final String PASS = "123ALOALOPRIVATEnetrogayte123";
    private Statement stmt;
    private Connection connection;
    private ResultSet rs;
    private DataFromBase dataFromBase;

    public MDataBase(DataFromBase dataFromBase) {
        this.dataFromBase = dataFromBase;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASS);
            stmt = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTable(String tableName) {

        try {

            rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            String str = " ";
            System.out.println(resultSetMetaData.getColumnCount());
            while (rs.next()) {
                str = " ";
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++ ) {
                    dataFromBase.setElement(rs.getString(i) + "");
                    str += rs.getString(i)+ " ";
                }
                System.out.println("Продукция:" + str);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // rs.close();
        // stmt.close();


        // connection.close();
    }



    public void addRow(String tableName) throws SQLException {
        rs = stmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        String name = "INSERT INTO " + tableName + "(";
        for(int i = 2; i <= resultSetMetaData.getColumnCount(); i++) {
            if(i == resultSetMetaData.getColumnCount()){
                name += resultSetMetaData.getColumnName(i);
                break;
            }
            name += resultSetMetaData.getColumnName(i) + ", ";
        }
        name += ") VALUES (";
        for(int i = 2; i <= resultSetMetaData.getColumnCount(); i++) {
            if(i == resultSetMetaData.getColumnCount()){
                name += "?";
                break;
            }
            name += "?,";
        }
        name += ")";

        System.out.println(name);

        PreparedStatement stmt = connection.prepareStatement(name);

        for(int i = 1; i < resultSetMetaData.getColumnCount(); i++) {

            stmt.setString(i, "2");
        }
        stmt.executeUpdate();
    }

    public void deleteRow(String tableName, int num) throws SQLException {

        String sql = "delete from тзакупка where тзакупка.Код_товара = " + num;

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.executeUpdate();
    }
}

