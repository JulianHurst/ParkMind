/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author juju
 */
public class ParkScene {
    private State S;    
    private boolean changed=false;
    
    ParkScene(){
        S=new MainMenu(this);        
    }    
    
    State getState(){
        return S;
    }
    
    void setState(final State S){
        this.S=S;
        changed=true;
    }
    
    void touch(){
        changed=false;
    }
    
    boolean getchanged(){
        return changed;
    }      
}
