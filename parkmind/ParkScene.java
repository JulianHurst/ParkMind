/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkmind;

import javafx.stage.Stage;

/**
 *
 * @author juju
 */
public class ParkScene {
    private State S=new State();
    private State.Sub next;
    
    ParkScene(){
        next=S.getnext();
    }
    
    ParkScene(State.Sub S,Stage stage){
        this.S=new State(S,stage);
    }
    void updateState(){
        next=S.getnext();
        S=S.changeState();        
    }
    
    State getState(){
        return S;
    }
    
    boolean stateChanged(){
        if(this.next!=S.getnext())
            return true;
        return false;
    }
    
    State.Sub getnext(){
        return next;
    }
}
