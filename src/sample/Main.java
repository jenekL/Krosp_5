package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.mdatabase.MDataBase;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Laba 5");

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> MDataBase.getInstance().close());
    }


    public static void main(String[] args) { launch(args); }
}
