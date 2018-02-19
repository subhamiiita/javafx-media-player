/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* refrences https://www.youtube.com/watch?v=7Gdxl2045l8&t=1863s */
/* www.stackoverflow.com */


package playit;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Ankit
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private String path;
    @FXML
    private MediaPlayer med = null; 
    @FXML
    private Slider volSlider ;
    
    @FXML
    private MediaView medV;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter fl= new FileChooser.ExtensionFilter("Open File ","*.mp4","*.mp3");
        
            fc.getExtensionFilters().add(fl);
            File f = fc.showOpenDialog(null);
            path = f.toURI().toString();
    
            if(path != null){
                if(med!=null)
                {
                    med.stop();
                }
                Media med1 = new Media(path);
                med = new MediaPlayer(med1);
                medV.setMediaPlayer(med);
               
                    DoubleProperty w = medV.fitWidthProperty();
                    DoubleProperty h = medV.fitHeightProperty();
                    w.bind(Bindings.selectDouble(medV.sceneProperty(),"width"));
                    h.bind(Bindings.selectDouble(medV.sceneProperty(),"height"));
                    medV.setSmooth(true);
                    med.play();
                    
                    volSlider.setValue(med.getVolume() * 100);
                    
                    volSlider.valueProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        med.setVolume(volSlider.getValue()/100);
                    }
                });
               
            }
    }
    @FXML
    private void playMedia(ActionEvent event)
    {
        med.play();
    }
    @FXML
    private void pauseMedia(ActionEvent e)
    {
        med.pause();
    }
    @FXML
    private void stopMedia(ActionEvent e)
    {
        med.stop();
    }
    @FXML
    private void slowMedia(ActionEvent e)
    {
        med.setRate(.8);
    }
    @FXML
    private void slowestMedia(ActionEvent e)
    {
        med.setRate(.6);
    }
    @FXML
    private void fasterMedia(ActionEvent e)
    {
        med.setRate(1.25);
    }
    @FXML
    private void fastesttMedia(ActionEvent e)
    {
        med.setRate(1.7);
    }
    @FXML
    private void exitMedia(ActionEvent e)
    {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
