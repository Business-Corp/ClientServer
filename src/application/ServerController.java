package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class ServerController implements Initializable{

	@FXML
	ToggleButton mailserverButton = new ToggleButton();
	
	@FXML
	ListView mailAccountListe = new ListView();
	
	@FXML
	ListView mailMailListe = new ListView();
	
	@FXML
	ListView mailLog = new ListView<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		mailserverButton.setOnAction((ActionEvent e) -> {
			ToggleButton source = (ToggleButton) e.getSource();
			
			if(source.isSelected()){
				System.out.println("button an");
			}else{
				System.out.println("button aus");
			}
		});		
	}
	
}
