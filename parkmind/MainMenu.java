/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkmind;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


/**
 *
 * @author juju
 */
public class MainMenu implements State{      
    ListView<String> list = new ListView();
    MainMenu(ParkScene context){
        root.getChildren().clear();
        Button b=new Button("Quit");
        ObservableList<String> items=FXCollections.observableArrayList ("3 Word Exercise", "Other");    
        list.setItems(items);
        list.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount()==2 && list.getSelectionModel().getSelectedIndex()==0){
                try {
                    context.setState(new WordEx(context));
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); 
        b.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });
        list.setStyle("-fx-background-insets: 0 ;-fx-font-size: 20;");  
        b.setStyle("-fx-font-size: 15;");
        root.getChildren().add(b); 
        root.getChildren().add(list);               
    }
    
    @Override
    public VBox getRoot(){
        return root;
    }
    
    @Override
    public void hidewords(){}
    
    @Override
    public void inctimer(){}
    
    @Override
    public int gettimer(){return 0;}
}
