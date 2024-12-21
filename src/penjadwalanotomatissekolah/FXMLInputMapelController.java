/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package penjadwalanotomatissekolah;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import static javax.xml.bind.DatatypeConverter.parseTime;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXMLInputMapelController implements Initializable {

    boolean editdata = false;

    @FXML
    private Button btnexit;
    @FXML
    private Button btncancel;
    @FXML
    private Button btnsave;
    @FXML
    private TextField txtidmapel;
    @FXML
    private TextField txtnamamapel;
    @FXML
    private TextField txtpengajar;
    @FXML
    private TextField txtwaktumulai;
    @FXML
    private TextField txtwaktuselesai;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

    public void execute(MapelModel d) {
        if (!d.getIdmapel().isEmpty()) {
            editdata = true;
            txtidmapel.setText(d.getIdmapel());
            txtnamamapel.setText(d.getNamamapel());
            txtpengajar.setText(d.getPengajar());
            txtwaktumulai.setText(d.getWaktumulai().toString());
            txtwaktuselesai.setText(d.getWaktuselesai().toString());
            txtidmapel.setEditable(false);
            txtnamamapel.requestFocus();
        }
    }

    @FXML
    private void exitclick(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void cancelclick(ActionEvent event) {
        txtidmapel.setText("");
        txtnamamapel.setText("");
        txtpengajar.setText("");
        txtwaktumulai.setText("");
        txtwaktuselesai.setText("");
        txtidmapel.requestFocus();
    }

    @FXML
    private void saveclick(ActionEvent event) {
        MapelModel n = new MapelModel();
        n.setIdmapel(txtidmapel.getText());
        n.setNamamapel(txtnamamapel.getText());
        n.setPengajar(txtpengajar.getText());
        Time waktumulai = parseTime(txtwaktumulai.getText());
        Time waktuselesai = parseTime(txtwaktuselesai.getText());
        if (waktumulai != null && waktuselesai != null) {
            n.setWaktumulai(txtwaktumulai.getText());
            n.setWaktuselesai(txtwaktuselesai.getText());
        } else {
            showAlert(Alert.AlertType.ERROR, "Format waktu tidak valid");
        }

        FXMLDocumentController.dtmapel.setMapeldModel(n);
        if (editdata) {
            if (FXMLDocumentController.dtmapel.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtidmapel.setEditable(true);
                cancelclick(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }

        } else if (FXMLDocumentController.dtmapel.validasi(n.getIdmapel()) <= 0) {
            if (FXMLDocumentController.dtmapel.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                cancelclick(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtidmapel.requestFocus();
        }
//        else {
//            showAlert(Alert.AlertType.ERROR, "Lengkapi semua field terlebih dahulu");
//        }
    }

    private Time parseTime(String timeStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            long ms = sdf.parse(timeStr).getTime();
            return new Time(ms);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }


}
