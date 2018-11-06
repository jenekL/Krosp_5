package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.mdatabase.MDataBase;

import java.io.IOException;
import java.sql.SQLException;

public class AddController {

    private MDataBase mDataBase = MDataBase.getInstance();

    @FXML
    private TextField txtfdate;

    @FXML
    private TextField txtfquan;

    @FXML
    private TextField txtfname;

    @FXML
    private TextField txtffirm;

    @FXML
    private Button confbtn;

    @FXML
    public void initialize(){
        confbtn.setOnAction(actionEvent -> {
            if(!txtfdate.getText().equals("") && !txtfquan.equals("") && !txtfname.equals("") && !txtffirm.equals("")) {
                String[] tmp = {txtfdate.getCharacters().toString(), txtfquan.getCharacters().toString(),
                        txtfname.getCharacters().toString(), txtffirm.getCharacters().toString()};
                try {
                    mDataBase.addRow("тПродажа", tmp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                confbtn.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("sample.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Wrong data");
                alert.setContentText("Fill all cells");

                alert.showAndWait();
            }

        });
    }
}
