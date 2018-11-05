package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.data.Prodaja;
import sample.data.TableData;
import sample.data.Zakupka;
import sample.mdatabase.MDataBase;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;

public class Controller {


    private MDataBase mDataBase = new MDataBase();

    @FXML
    private TableView<TableData> tb1;
    @FXML
    public Button delbtn;

    @FXML
    private void setDelbtn(){
        try {
            mDataBase.deleteRow("тПродажа", tb1.getSelectionModel().getFocusedIndex() + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {


//        delbtn.addActionListener(actionEvent -> {
//            try {
//                mDataBase.deleteRow("тПродажа", tb1.getSelectionModel().getFocusedIndex());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });

        javafx.scene.control.TableColumn<TableData, Integer> idColumn = new TableColumn<>("ID");
        //nameColumn.setMinWidth(200);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        javafx.scene.control.TableColumn<TableData, String> dateColumn = new TableColumn<>("Date");
        //priceColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        javafx.scene.control.TableColumn<TableData, Integer> quantityColumn = new TableColumn<>("Quantity");
        //unitColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        javafx.scene.control.TableColumn<TableData, Integer> nameColumn = new TableColumn<>("Name");
        //unitColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        javafx.scene.control.TableColumn<TableData, Integer> firmColumn = new TableColumn<>("Firm");
        //unitColumn.setMinWidth(100);
        firmColumn.setCellValueFactory(new PropertyValueFactory<>("firm"));

        //mDataBase.createEntity();
        //tb1.setItems(getData());
        tb1.setItems(mDataBase.createEntity());
        //tb1.getItems().addAll(getData());
        //tb1.getItems().addAll(mDataBase.createEntity());
        tb1.getColumns().addAll(idColumn, dateColumn, quantityColumn, nameColumn, firmColumn);



        //System.out.println(tb1.getSelectionModel().getFocusedIndex());
    }

}
