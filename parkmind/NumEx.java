/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author juju
 */
public class NumEx implements State {
    HBox H;
    HBox A;
    int highest=0,high=0;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random R=new Random();
    Button back=new Button("Back");      
    Canvas C=new Canvas();
    GraphicsContext gc;
    TableView<Row> T;
    ObservableList<Row> items=FXCollections.observableArrayList(); 
    TextField ans=new TextField();
    Text exp=new Text();    
    
    NumEx(ParkScene context){
        int x,n;
        char c,y;
        T=new TableView<>();
        TableColumn<Row,String> one=new TableColumn<>();
        TableColumn<Row,String> two=new TableColumn<>();
        TableColumn<Row,String> three=new TableColumn<>();        
        one.setSortable(false);
        one.setResizable(false);
        two.setSortable(false);
        two.setResizable(false);
        three.setSortable(false);
        three.setResizable(false);
                
                
        root.getChildren().clear();
        back.setOnMouseClicked((MouseEvent event) -> {            
            context.setState(new MainMenu(context));
        });
        
        ans.setOnAction((ActionEvent e) -> {
            hide();
        });
                
        
        for(int i=0;i<15;i++){
            int d;
            n=R.nextInt(3);
            c=alphabet.charAt(R.nextInt(alphabet.length()));
            x=R.nextInt(7)+1;
            y=alphabet.charAt(R.nextInt(alphabet.length()));
            if(i%3!=0)
                switch (n) {
                    case 0:
                        items.add(new Row(Character.toString(c),Character.toString(y),Integer.toString(x)));
                        break;
                    case 1:
                        items.add(new Row(Character.toString(c),Integer.toString(x),Character.toString(y)));            
                        break;
                    case 2:
                        items.add(new Row(Integer.toString(x),Character.toString(c),Character.toString(y)));
                        break;
                    default:
                        break;
                }            
            else{
                d=R.nextInt(3)+1;
                switch (n) {
                    case 0:
                        items.add(new Row(Character.toString(c),Integer.toString(d),Integer.toString(x)));
                        break;
                    case 1:
                        items.add(new Row(Integer.toString(d),Integer.toString(x),Character.toString(y)));            
                        break;
                    case 2:
                        items.add(new Row(Integer.toString(x),Character.toString(c),Integer.toString(d)));
                        break;
                    default:
                        break;
                }
            }
        }
        one.setMinWidth(100);
        one.setCellValueFactory(
                new PropertyValueFactory<>("oned"));      
        two.setMinWidth(100);
        two.setCellValueFactory(
                new PropertyValueFactory<>("twod"));    
        three.setMinWidth(100);
        three.setCellValueFactory(
                new PropertyValueFactory<>("threed")); 
        T.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        T.getSelectionModel().setCellSelectionEnabled(true);
        T.setItems(items);
        //T.getColumns().addAll(one,two,three);     
        T.getColumns().add(one);
        T.getColumns().add(two);
        T.getColumns().add(three);
        H=new HBox(5);        
        Region spacerl = new Region();
        Region spacerr = new Region();        
        HBox.setHgrow(spacerl,Priority.ALWAYS);
        HBox.setHgrow(spacerr,Priority.ALWAYS);               
        T.widthProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
            {
                TableHeaderRow header = (TableHeaderRow) T.lookup("TableHeaderRow");
                header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {                        
                        header.setReordering(false);
                    }
                });
            }
        });
        H.getChildren().addAll(spacerl,T,spacerr);           
        
        ObservableList d=T.getColumns();
        ObservableList<Row> list = T.getItems();
        int[] b=new int[8];
        for(int i=0;i<b.length;i++)
            b[i]=0;
        for(int i=0;i<15;i++){
                if(list.get(i).getoned().matches("[0-9]")){
                    b[Integer.parseInt(list.get(i).getoned())]++;
                }
                if(list.get(i).gettwod().matches("[0-9]")){
                    b[Integer.parseInt(list.get(i).gettwod())]++;
                }
                if(list.get(i).getthreed().matches("[0-9]")){
                    b[Integer.parseInt(list.get(i).getthreed())]++;
                }                
        }
        for (int i=0;i<b.length;i++){           
            if(high<=b[i]){
                high=b[i];
                highest=i;
            }
        }
        Region sl = new Region();
        Region sr = new Region();        
        HBox.setHgrow(sl,Priority.ALWAYS);
        HBox.setHgrow(sr,Priority.ALWAYS);
        exp.setText("Count the sum of "+highest+"'s");        
        T.setStyle("-fx-font-size: 20;");
        exp.setStyle("-fx-font-size: 20;");
        back.setStyle("-fx-font-size: 15;");
        VBox.setVgrow(H, Priority.ALWAYS);
        H.setMaxHeight(600);
        T.setMaxHeight(600);
        
        A=new HBox(5);   
        Text rep=new Text("Answer : ");
        rep.setStyle("-fx-font-size: 20;");
        ans.setMaxWidth(200);
        ans.setStyle("-fx-font-size: 15;");
        Button enter=new Button("Enter");
        
        enter.setOnMouseClicked((MouseEvent e) -> {
            hide();
        });
        
        enter.setStyle("-fx-font-size: 15;");                     
        A.getChildren().addAll(sl,rep,ans,enter,sr);            
        root.getChildren().add(back);
        root.getChildren().add(exp);        
        root.getChildren().add(H); 
        root.getChildren().add(A);
    }
    
    @Override
    public int gettimer(){
        return 0;
    }
    
    @Override
    public void inctimer(){}
    
    @Override
    public VBox getRoot(){
        return root;
    }
    
    @Override
    public void hide(){
        root.getChildren().remove(A);
        if(ans.getText().isEmpty())
            exp.setText("Sorry. The correct sum was : "+high*highest); 
        else if(Integer.parseInt(ans.getText())==high*highest){
            exp.setText("Well done! You have the correct sum!");            
        }
        else
            exp.setText("Sorry, that sum is incorrect. The correct sum was : "+high*highest); 
        ObservableList<Row> list=T.getItems();                                
    }
    
}
