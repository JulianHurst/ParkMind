/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkmind;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author juju
 */
public class State {
    protected StackPane root = new StackPane();
    protected Stage stage = new Stage();
    
    public enum Sub {
        sub_MainMenu, sub_Wordex
    };
    protected Sub next;
    
    State(){
        next=State.Sub.sub_MainMenu;        
    }
    
    State(State.Sub next, Stage stage){
        this.next=next;
        this.stage=stage;
    }
            
    State changeState(){
        switch(next){
            case sub_MainMenu:
                return new MainMenu();                
            case sub_Wordex:
                return new WordEx();
            default:
                System.out.println("Error");
        }
        return this;
    }
    
    StackPane getRoot(){
        return root;
    }
    
    Sub getnext(){
        return next;
    }
    
    void setStage(Stage S){
        stage=S;
    }
}
