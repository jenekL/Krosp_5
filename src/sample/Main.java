package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.data.TableData;
import sample.data.Zakupka;
import sample.mdatabase.DataFromBase;
import sample.mdatabase.MDataBase;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Laba 5");


        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        //primaryStage.setOnCloseRequest(event -> MDataBase.getInstance().close());
    }


    public static void main(String[] args) {

        DataFromBase dataFromBase = new DataFromBase();
        MDataBase mDataBase = new MDataBase();
        mDataBase.showTable("тзакупка");
        mDataBase.showTable("тклиенты");

        launch(args);
    }


}
