/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package penjadwalanotomatissekolah;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXMLInputMuridController implements Initializable {
    
    boolean editdata=false;

    @FXML
    private TextField txtidmurid;
    @FXML
    private TextField txtnamamurid;
    @FXML
    private TextField txtkelas;
    @FXML
    private TextField txtumur;
    @FXML
    private Button btnexit;
    @FXML
    private Button btnsave;
    @FXML
    private Button btncancel;
    @FXML
    private TextField txtgambar;
    @FXML
    private Button btnuploadfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(MuridModel d){
        if(!d.getIdmurid().isEmpty()){
          editdata=true;
          txtidmurid.setText(d.getIdmurid());
          txtnamamurid.setText(d.getNamamurid());
          txtkelas.setText(d.getKelas());
          txtumur.setText(String.valueOf(d.getUmur()));
          txtgambar.setText(d.getGambar());
          txtidmurid.setEditable(false);
          txtnamamurid.requestFocus();         
        }
    }


    @FXML
    private void exitclick(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void saveclick(ActionEvent event) {
        MuridModel n=new MuridModel();        
        n.setIdmurid(txtidmurid.getText());
        n.setNamamurid(txtnamamurid.getText());
        n.setKelas(txtkelas.getText());
        n.setUmur(Integer.parseInt(txtumur.getText()));
        n.setGambar(txtgambar.getText());
        
        FXMLDocumentController.dtmurid.setMuridModel(n);
        if(editdata){
            if(FXMLDocumentController.dtmurid.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtidmurid.setEditable(true);        
               cancelclick(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            
            }else if(FXMLDocumentController.dtmurid.validasi(n.getIdmurid())<=0){
            if(FXMLDocumentController.dtmurid.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               cancelclick(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtidmurid.requestFocus();
        }
    }

    @FXML
    private void cancelclick(ActionEvent event) {
        txtidmurid.setText("");
        txtnamamurid.setText("");
        txtkelas.setText("");
        txtumur.setText("");
        txtgambar.setText("");
        txtidmurid.requestFocus();
    }

    @FXML
    private void uplodafileclick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
    
        // Set extension filter for JPG files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(btnuploadfile.getScene().getWindow());

        if (file != null) {
            // Set the file path to the text field
            txtgambar.setText(file.getAbsolutePath());
        }
    }
    
}
