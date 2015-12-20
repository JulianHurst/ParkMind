/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkmind;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author juju
 */
public class MainMenu extends State{    
    
    MainMenu(){
        ListView<String> list = new ListView();
        ObservableList<String> items=FXCollections.observableArrayList ("3 Word Exercise", "Other");    
        list.setItems(items);
        list.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount()==2 && list.getSelectionModel().getSelectedIndex()==0)
                next=State.Sub.sub_Wordex;                                              
        });
        root.getChildren().add(list);
    }
}
