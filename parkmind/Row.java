/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package parkmind;

import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author juju
 */
public class Row {
    private final SimpleStringProperty oned,twod,threed;
    
    Row(String one,String two,String three){
        this.oned=new SimpleStringProperty(this,one);
        this.twod=new SimpleStringProperty(this,two);
        this.threed=new SimpleStringProperty(this,three);
        oned.set(one);
        twod.set(two);
        threed.set(three);
    }
    
    public String getoned(){
        //return oned.get();
        return oned.getName();
    }
    
    public void setoned(String s){
        oned.set(s);
    }
    
    public SimpleStringProperty onedProperty(){
        return oned;
    }
    
    public String gettwod(){
        return twod.get();
    }
    
    public void settwod(String s){
        twod.set(s);
    }
    
    public SimpleStringProperty twodProperty(){
        return twod;
    }
    
    public String getthreed(){
        return threed.get();
    }
    
    public void setthreed(String s){
        threed.set(s);
    }
    
    public SimpleStringProperty threedProperty(){
        return threed;
    }
}
