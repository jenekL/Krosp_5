package sample.mdatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.data.Klients;
import sample.data.Prodaja;
import sample.data.TableData;
import sample.data.Zakupka;

import java.sql.*;

public class MDataBase {
    private final String URL = "jdbc:mysql://localhost:3306/krosp_lab5?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   // private final String URL = "jdbc:mysql://localhost:3306/krosp_lab5";
    private final String NAME = "root";
    private final String PASS = "123ALOALOPRIVATEnetrogayte123";
    private Statement stmt;
    private Statement stmt1;
    private Statement stmt2;
    private Connection connection;
    private ResultSet rs;
   // private DataFromBase dataFromBase;
    private ObservableList<TableData> tableData = FXCollections.observableArrayList();

    public MDataBase() {
     //   this.dataFromBase = dataFromBase;
        try {
            connection = DriverManager.getConnection(URL, NAME, PASS);
            stmt = connection.createStatement();
            stmt1 = connection.createStatement();
            stmt2 = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MDataBase getInstance() {
        return new MDataBase();
    }

    public ObservableList<TableData> createEntity(){
        try {
            rs = stmt.executeQuery("SELECT * FROM тПродажа");
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            System.out.println(resultSetMetaData.getColumnCount());
            while (rs.next()) {

                ResultSet resZakup = stmt1.executeQuery("SELECT * FROM тзакупка where Код_товара = " + rs.getString(4));
                ResultSet resKlient = stmt2.executeQuery("SELECT * FROM тклиенты where Код_клиента = " + rs.getString(5));

                //Zakupka z =  new Zakupka(resZakup.getString(2), Float.parseFloat(resZakup.getString(3)),resZakup.getString(4));
                //Klients k = new Klients(resKlient.getString(2), resKlient.getString(3),resKlient.getString(4), Integer.parseInt(resKlient.getString(5)));
                while(resZakup.next() && resKlient.next()) {
                    tableData.add(new Prodaja(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)),
                            new Klients(resKlient.getString(2), resKlient.getString(3), resKlient.getString(4), Integer.parseInt(resKlient.getString(5))),
                            new Zakupka(resZakup.getString(2), Float.parseFloat(resZakup.getString(3)), resZakup.getString(4))));
                    System.out.println(Integer.parseInt(rs.getString(1)) + rs.getString(2) + Integer.parseInt(rs.getString(3)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableData;
    }


    public void showTable(String tableName) {

        try {
            String[] data = new String[5];
            rs = stmt.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            String str = " ";
            System.out.println(resultSetMetaData.getColumnCount());
            while (rs.next()) {
                str = " ";
                for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++ ) {
                    data[i-1] = rs.getString(i);
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

        String sql = "delete from "+ tableName + " where " + tableName + ".Код_соглашения = " + num;

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.executeUpdate();
    }

//    public void close(){
//        try {
//            rs.close();
//            stmt.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("closed");
//    }
}

