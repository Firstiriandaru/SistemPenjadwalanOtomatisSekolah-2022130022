/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package penjadwalanotomatissekolah;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Acer
 */
public class FXMLDocumentController implements Initializable {
    
    public static DBMurid dtmurid = new DBMurid();
    public static DBMapel dtmapel = new DBMapel();
    
    private Label label;
    
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DataMapelClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDisplayMapel.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   } 
    }

    @FXML
    private void DataMuridClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDisplayMurid.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   } 
    }

    @FXML
    private void InputMapelClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMapel.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   } 
    }

    @FXML
    private void InputMuridClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLInputMurid.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   } 
    }

    @FXML
    private void SimulasiClick(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLSimulasiPenjadwalan.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   e.printStackTrace();   }
    }
    
}
