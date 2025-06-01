package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField surnameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField idNumberTF;
    @FXML
    private TextField requestedVacTF;
    @FXML
    private TextField boothNumTF;

    @FXML
    public void navigate (ActionEvent actionEvent) throws Exception{
        String firstName = firstNameTF.getText();
        String surname = surnameTF.getText();
        String age = ageTF.getText();
        String city = cityTF.getText();
        String idNumber = idNumberTF.getText();
        String requestedVac = requestedVacTF.getText();
        String boothNum = boothNumTF.getText();
        Stage newStage = new Stage();
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("receipt.fxml"));
        newStage.setTitle("Vaccination Receipt");
        Loader.load();
        Parent root = Loader.getRoot();
        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add("css/style.css");
        newStage.setScene(scene);
        newStage.show();
        Stage previousStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        previousStage.close();
        receiptController receiptCon = Loader.getController();
        receiptCon.setText(firstName,surname,age,city,idNumber,requestedVac,boothNum);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
