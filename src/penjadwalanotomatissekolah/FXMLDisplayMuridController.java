/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package penjadwalanotomatissekolah;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class FXMLDisplayMuridController implements Initializable {

    @FXML
    private TableView<MuridModel> tbvmurid;
    @FXML
    private Button btnfirst;
    @FXML
    private Button btnprev;
    @FXML
    private Button btnnext;
    @FXML
    private Button btnlast;
    @FXML
    private Button btninsert;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnexit;
    @FXML
    private ImageView fotoMurid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        tbvmurid.getSelectionModel().selectFirst();
        showgambar();
    }
    
    public void showdata(){
        ObservableList<MuridModel> data=FXMLDocumentController.dtmurid.Load();
        if(data!=null){            
            tbvmurid.getColumns().clear();            
            tbvmurid.getItems().clear();
            
             TableColumn col=new TableColumn("ID");
            col.setCellValueFactory(new PropertyValueFactory<MuridModel, String>("idmurid"));
            tbvmurid.getColumns().addAll(col);
            
            col=new TableColumn("NAMA LENGKAP");
            col.setCellValueFactory(new PropertyValueFactory<MuridModel, String>("namamurid"));
            tbvmurid.getColumns().addAll(col);
            
            col=new TableColumn("KELAS");
            col.setCellValueFactory(new PropertyValueFactory<MuridModel, String>("kelas"));
            tbvmurid.getColumns().addAll(col);
            
            col=new TableColumn("USIA");
            col.setCellValueFactory(new PropertyValueFactory<MuridModel, String>("umur"));
            tbvmurid.getColumns().addAll(col);
            
            col=new TableColumn("JENIS KELAMIN");
            col.setCellValueFactory(new PropertyValueFactory<MuridModel, String>("jeniskelamin"));
            tbvmurid.getColumns().addAll(col);
            
            tbvmurid.setItems(data);
        }else { Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmurid.getScene().getWindow().hide();;
            
        }
    }
    
    public void showgambar(){
        Image gambar = null;
        try {
            gambar = new Image(new FileInputStream(tbvmurid.getSelectionModel().getSelectedItem().getGambar()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDisplayMuridController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        fotoMurid.setImage(gambar);
    }

    @FXML
    private void firstclick(ActionEvent event) {
        tbvmurid.getSelectionModel().selectFirst();
        tbvmurid.requestFocus();
        showgambar();
    }

    @FXML
    private void prevclick(ActionEvent event) {
        tbvmurid.getSelectionModel().selectPrevious();
        tbvmurid.requestFocus();
        showgambar();
    }

    @FXML
    private void nextclick(ActionEvent event) {
        tbvmurid.getSelectionModel().selectNext();
        tbvmurid.requestFocus();
        showgambar();
    }

    @FXML
    private void lastclick(ActionEvent event) {
        tbvmurid.getSelectionModel().selectLast();
        tbvmurid.requestFocus();
        showgambar();
    }

    @FXML
    private void insertclick(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMurid.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        firstclick(event);
    }

    @FXML
    private void deleteclick(ActionEvent event) {
        MuridModel s= new MuridModel();       
        s=tbvmurid.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtmurid.delete(s.getIdmurid())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           firstclick(event);       
        }
    }

    @FXML
    private void updateclick(ActionEvent event) {
        MuridModel s= new MuridModel();
        s=tbvmurid.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMurid.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputMuridController isidt=(FXMLInputMuridController)loader.getController();
        isidt.execute(s);
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        firstclick(event);
    }

    @FXML
    private void exitclick(ActionEvent event) {
        btnexit.getScene().getWindow().hide();
    }

    @FXML
    private void tabletclick(MouseEvent event) {
        showgambar();
    }
    
}
