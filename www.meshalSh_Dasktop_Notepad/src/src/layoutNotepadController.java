
package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class layoutNotepadController implements Initializable {
   
    
    @FXML
    private ToggleButton rightToLeft , leftToRight ;
    
    @FXML
    private TextArea textarea ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // set ToggleButton in one Group 
        ToggleGroup group = new ToggleGroup();
        rightToLeft.setToggleGroup(group);
        leftToRight.setToggleGroup(group);
        
        
        // set Image For ToggleButton
        rightToLeft.setGraphic(new ImageView(new Image("/image/align-right.png")));
        leftToRight.setGraphic(new ImageView(new Image("/image/align-left.png")));
    }    
    
    
    
    // Save Text From TextArea to Your PC
    @FXML
    private void save(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
             
            if (file != null) {
                
                String filePath = file.getPath();
                try {
                   
                    FileOutputStream fos = new FileOutputStream(filePath);
                    
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    
                    BufferedWriter bw = new BufferedWriter(osw);
                    
                    bw.write(textarea.getText());
                   
                    bw.flush();
                    bw.close();
                    
                    
                }
                catch (IOException ioe) {
                    
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("There is a problem");
                    alert.setContentText(ioe.getMessage());
                    alert.show();
                }
            }
        

}

        
    
    // get Text From Your PC to TextArea
    @FXML
    private void openFile(ActionEvent event){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll( new ExtensionFilter("Text Files", "*.txt"));
        
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
           
            if (selectedFile != null) {
                
                String filePath = selectedFile.getPath();
                try {
                   
                    BufferedReader br = new BufferedReader(new FileReader(filePath));
                    String line = "";
                    String text = "";
                    while ((line = br.readLine()) != null) {
                        text += line + "\n";
                    }
                    
                    textarea.setText(text);
                    br.close();
                }
                catch (IOException ioe) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Opps.. There is a problem");
                    alert.setContentText(ioe.getMessage());
                    alert.show();
                }
            }
        }
    
    
    
    // Choose Direction Text in TextArea
    @FXML
    private void direction(ActionEvent event){
        
        if (event.getSource().equals(leftToRight))
            textarea.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        
        else 
             textarea.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        
    }
 
    }
    


