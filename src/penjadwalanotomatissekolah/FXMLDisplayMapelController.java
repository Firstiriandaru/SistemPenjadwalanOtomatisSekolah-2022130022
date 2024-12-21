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
public class FXMLDisplayMapelController implements Initializable {

    @FXML
    private TableView<MapelModel> tbvmapel;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
        tbvmapel.getSelectionModel().selectFirst();
    }    
    
    public void showdata(){
        ObservableList<MapelModel> data=FXMLDocumentController.dtmapel.Load();
        if(data!=null){            
            tbvmapel.getColumns().clear();            
            tbvmapel.getItems().clear();
            
             TableColumn col=new TableColumn("ID");
            col.setCellValueFactory(new PropertyValueFactory<MapelModel, String>("idmapel"));
            tbvmapel.getColumns().addAll(col);
            
            col=new TableColumn("NAMA MATA PELAJAR");
            col.setCellValueFactory(new PropertyValueFactory<MapelModel, String>("namamapel"));
            tbvmapel.getColumns().addAll(col);
            
            col=new TableColumn("GURU");
            col.setCellValueFactory(new PropertyValueFactory<MapelModel, String>("pengajar"));
            tbvmapel.getColumns().addAll(col);
            
            col=new TableColumn("HARI");
            col.setCellValueFactory(new PropertyValueFactory<MapelModel, String>("hari"));
            tbvmapel.getColumns().addAll(col);
            
            col=new TableColumn("WAKTU MULAI");
            col.setCellValueFactory(new PropertyValueFactory<MapelModel, String>("waktumulai"));
            tbvmapel.getColumns().addAll(col);
            
            col=new TableColumn("WAKTU SELESAI");
            col.setCellValueFactory(new PropertyValueFactory<MapelModel, String>("waktuselesai"));
            tbvmapel.getColumns().addAll(col);
            
            tbvmapel.setItems(data);
        }else { Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvmapel.getScene().getWindow().hide();
            
        }
    }

    @FXML
    private void firstclick(ActionEvent event) {
        tbvmapel.getSelectionModel().selectFirst();
        tbvmapel.requestFocus();
    }

    @FXML
    private void prevclick(ActionEvent event) {
        tbvmapel.getSelectionModel().selectPrevious();
        tbvmapel.requestFocus();
    }

    @FXML
    private void nextclick(ActionEvent event) {
        tbvmapel.getSelectionModel().selectNext();
        tbvmapel.requestFocus();
    }

    @FXML
    private void lastclick(ActionEvent event) {
        tbvmapel.getSelectionModel().selectLast();
        tbvmapel.requestFocus();
    }

    @FXML
    private void insertclick(ActionEvent event) {
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMapel.fxml"));    
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
        MapelModel s= new MapelModel();       
        s=tbvmapel.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtmapel.delete(s.getIdmapel())){
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
        MapelModel s= new MapelModel();
        s=tbvmapel.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMapel.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLInputMapelController isidt=(FXMLInputMapelController)loader.getController();
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
} 