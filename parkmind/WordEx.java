/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkmind;

import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author juju
 */
public class WordEx extends State {
    WordEx(){
        next=State.Sub.sub_Wordex;
        ListView<String> list = new ListView();
        int x,y,z;
        Random R=new Random();
        x=R.nextInt(6)+1;   //1 to 6 (included)
        y=R.nextInt(6)+1;
        z=R.nextInt(6)+1;        
        ObservableList<String> items=FXCollections.observableArrayList (Integer.toString(x), Integer.toString(y), Integer.toString(z));    
        list.setItems(items);
        list.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount()==2 && list.getSelectionModel().getSelectedIndex()==2)
                next=State.Sub.sub_MainMenu;                               
        });
        root.getChildren().add(list);
    }
    
}
