package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.mdatabase.DataFromBase;
import sample.mdatabase.MDataBase;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Laba 5");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

        DataFromBase dataFromBase = new DataFromBase();
        MDataBase mDataBase = new MDataBase(dataFromBase);
        mDataBase.showTable("тзакупка");
        mDataBase.showTable("тклиенты");
        launch(args);
}
}
