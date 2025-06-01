package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class receiptController implements Initializable{
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    private Label firstNameR;
    @FXML
    private Label surnameR;
    @FXML
    private Label ageR;
    @FXML
    private Label cityR;
    @FXML
    private Label idNumberR;
    @FXML
    private Label requestedVacR;
    @FXML
    private Label boothNumR;
    @FXML
    private Label time;

    public void setText(String firstNameRef,String surnameRef,String ageRef,String cityRef,String idNumberRef,String requestedVacRef,String boothNumRef){
        this.firstNameR.setText(firstNameRef);
        this.surnameR.setText(surnameRef);
        this.ageR.setText(ageRef);
        this.cityR.setText(cityRef);
        this.idNumberR.setText(idNumberRef);
        this.requestedVacR.setText(requestedVacRef);
        this.boothNumR.setText(boothNumRef);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=formatter.format(timestamp);
        this.time.setText(str);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

