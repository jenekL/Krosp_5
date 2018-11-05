package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.data.TableData;
import sample.data.Zakupka;
import sample.mdatabase.MDataBase;

import java.awt.*;
import java.sql.Date;

public class Controller {

    private MDataBase mDataBase = new MDataBase();

    @FXML
    private TableView<TableData> tb1;


    @FXML
    private void initialize() {
        javafx.scene.control.TableColumn<TableData, Integer> nameColumn = new TableColumn<>("ID");
        //nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        javafx.scene.control.TableColumn<TableData, String> priceColumn = new TableColumn<>("Date");
        //priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        javafx.scene.control.TableColumn<TableData, Integer> unitColumn = new TableColumn<>("Quantity");
        //unitColumn.setMinWidth(100);
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        mDataBase.createEntity();
        tb1.setItems(getData());
        //tb1.setItems(mDataBase.createEntity());
        //tb1.getItems().addAll(getData());
        //tb1.getItems().addAll(mDataBase.createEntity());
        tb1.getColumns().addAll(nameColumn, priceColumn, unitColumn);
    }

    public ObservableList<TableData> getData(){
        ObservableList<TableData> tableData = FXCollections.observableArrayList();
        tableData.add(new Zakupka("Milk", 100, "ml"));
        tableData.add(new Zakupka("Milk", 100, "ml"));
        tableData.add(new Zakupka("Milk", 100, "ml"));


        return tableData;
    }




}
