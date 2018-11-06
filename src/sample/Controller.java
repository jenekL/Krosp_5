package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.data.Prodaja;
import sample.data.TableData;
import sample.data.Zakupka;
import sample.mdatabase.MDataBase;

import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class Controller {
    private MDataBase mDataBase = new MDataBase();

    @FXML
    private TableView<TableData> tb1;
    @FXML
    public Button delbtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button refbtn1;

    @FXML
    private void initialize() {

        addbtn.setOnAction(actionEvent -> {
            addbtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("add.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

        refbtn1.setOnAction(actionEvent -> {
            tb1.getItems().clear();
            tb1.setItems(mDataBase.createEntity());
        });

        delbtn.setOnAction(actionEvent -> {
            try {
                Prodaja prodaja = (Prodaja) tb1.getSelectionModel().getSelectedItem();
                mDataBase.deleteRow("тПродажа", prodaja.getId());
                tb1.getItems().clear();
                tb1.setItems(mDataBase.createEntity());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        javafx.scene.control.TableColumn<TableData, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(50);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        javafx.scene.control.TableColumn<TableData, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        javafx.scene.control.TableColumn<TableData, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        javafx.scene.control.TableColumn<TableData, Integer> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        javafx.scene.control.TableColumn<TableData, Integer> firmColumn = new TableColumn<>("Firm");
        firmColumn.setMinWidth(150);
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
