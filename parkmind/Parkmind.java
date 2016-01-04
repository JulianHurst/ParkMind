/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package parkmind;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author juju
 */
public class Parkmind extends Application {   
    
    @Override
    public void start(Stage primaryStage) throws IOException {           
        ParkScene P= new ParkScene();                
        //Parent root=FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        Scene scene = new Scene(P.getState().getRoot(), 600, 500);
        
        primaryStage.setTitle("ParkMind");
        primaryStage.setScene(scene);
        primaryStage.show();          
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(50), (ActionEvent event) -> { 
            if(P.getchanged()){
                P.touch();                
            }
            if(P.getState() instanceof WordEx){                
                P.getState().inctimer();
                if(P.getState().gettimer()==100)
                    P.getState().hide();
            }                           
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        launch(args);            
    }
    
}
