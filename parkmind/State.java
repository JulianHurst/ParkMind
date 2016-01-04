/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package parkmind;

import javafx.scene.layout.VBox;

/**
 *
 * @author juju
 */
interface State {
    VBox root = new VBox(10);              

    void hide();                
    
    VBox getRoot();
    
    void inctimer();
    int gettimer();
       
}
