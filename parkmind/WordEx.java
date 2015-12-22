/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author juju
 */
public class WordEx implements State {
    ListView<String> list = new ListView<>();
    Text t=new Text();
    Button back=new Button("Back");
    private String one,two,three,four;
    private int wordtimer,wins;
    Random R=new Random();

    WordEx(ParkScene context) throws FileNotFoundException, IOException{
        root.getChildren().clear();
        int x,y,z;
        wordtimer=wins=0;
        t.setText("Memorize these words and their positions :");
        x=R.nextInt(2328)+1;   //1 to 2328 (included)
        y=R.nextInt(2328)+1;
        z=R.nextInt(2328)+1;
        //BufferedReader br = new BufferedReader(new FileReader("nounlist-en.txt"));
	InputStream in = getClass().getResourceAsStream("/nounlist-en.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
        for(int i=0;i<=x;i++)
            one=br.readLine();
        in = getClass().getResourceAsStream("/nounlist-en.txt");
	br = new BufferedReader(new InputStreamReader(in));
	//br = new BufferedReader(new FileReader("nounlist-en.txt"));
        for(int i=0;i<=y;i++)
            two=br.readLine();
       // br = new BufferedReader(new FileReader("nounlist-en.txt"));
       	in = getClass().getResourceAsStream("/nounlist-en.txt");
	br = new BufferedReader(new InputStreamReader(in));

        for(int i=0;i<=z;i++)
            three=br.readLine();
        br.close();
        ObservableList<String> items=FXCollections.observableArrayList (one, two, three);
        list.setItems(items);
        back.setOnMouseClicked((MouseEvent event) -> {
            context.setState(new MainMenu(context));
        });
        list.setStyle("-fx-background-insets: 0 ;-fx-font-size: 20;");
        list.setPrefHeight(110);
        t.setStyle("-fx-font-size: 20;");
        back.setStyle("-fx-font-size: 15;");
        root.getChildren().add(back);
        root.getChildren().add(t);
        root.getChildren().add(list);
    }


    @Override
    public VBox getRoot(){
        return root;
    }

    @Override
    public void inctimer(){
        wordtimer++;
    }

    void settimer(int time){
        wordtimer=time;
    }

    @Override
    public int gettimer(){
        return wordtimer;
    }

    void reset() throws FileNotFoundException, IOException{
        int x,y,z,a;
        ObservableList<String> items;
        x=R.nextInt(2328)+1;   //1 to 2328 (included)
        y=R.nextInt(2328)+1;
        z=R.nextInt(2328)+1;
        t.setText("Memorize these words and their positions :");
        BufferedReader br = new BufferedReader(new FileReader("nounlist-en.txt"));
        for(int i=0;i<x;i++)
            br.readLine();
        one=br.readLine();
        br = new BufferedReader(new FileReader("nounlist-en.txt"));
        for(int i=0;i<y;i++)
            two=br.readLine();
        two=br.readLine();
        br = new BufferedReader(new FileReader("nounlist-en.txt"));
        for(int i=0;i<z;i++)
            three=br.readLine();
        three=br.readLine();
        if(wins>=5){
            list.setPrefHeight(150);
            a=R.nextInt(2328)+1;
            br = new BufferedReader(new FileReader("nounlist-en.txt"));
            for(int i=0;i<a;i++)
                four=br.readLine();
            four=br.readLine();
            items=FXCollections.observableArrayList (one, two, three, four);
        }
        else
            items=FXCollections.observableArrayList (one, two, three);
        br.close();
        list.setItems(items);
        root.getChildren().clear();
        root.getChildren().add(back);
        root.getChildren().add(t);
        root.getChildren().add(list);
        wordtimer=0;
    }

    void showwords(String guess,int id){
        id-=1;
        ObservableList<String> l =list.getItems();
        Button again=new Button("Again!");
        again.setOnMouseClicked((MouseEvent event) -> {
            try {
                reset();
            } catch (IOException ex) {
                Logger.getLogger(WordEx.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        if(l.get(id).equals(guess)){
            t.setText("Well done! \""+guess+"\" is correct!");
            wins++;
        }
        else if(!guess.isEmpty())
            t.setText("Sorry, you guessed \""+guess+"\". The correct answer was \""+l.get(id)+"\"");
        else
            t.setText("Sorry, the correct answer was \""+l.get(id)+"\"");
        again.setStyle("-fx-font-size: 15;");
        root.getChildren().clear();
        root.getChildren().add(back);
        root.getChildren().add(t);
        root.getChildren().add(list);
        root.getChildren().add(again);
    }

    @Override
    public void hide(){
        int i;
        TextField input = new TextField();
        Button b=new Button("Enter");
        if(wins<5)
            i=R.nextInt(3)+1;
        else
            i=R.nextInt(4)+1;
        input.setOnAction((ActionEvent event) -> {
            showwords(input.getText(),i);
        });
        b.setOnMouseClicked((MouseEvent event) ->{
          showwords(input.getText(),i);
        });
        root.getChildren().remove(list);
        t.setText("What was word number "+i+"?");
        HBox H=new HBox(5);
        H.getChildren().add(input);
        H.getChildren().add(b);
        root.getChildren().add(H);
    }
}
