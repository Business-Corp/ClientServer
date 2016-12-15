package application;

import java.net.URL;
import java.util.ResourceBundle;

import csa.server.mailserver.MailServer;
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
	private MailServer mailServer = new MailServer(); 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		mailserverButton.setOnAction((ActionEvent e) -> {
			ToggleButton source = (ToggleButton) e.getSource();
			
			if(source.isSelected()){
				mailServer=new MailServer();
				System.out.println("button an");
				mailServer.start();
			}else{
				System.out.println("button aus");
				mailServer.interruptServer();
				
				try {
					mailServer.join();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
	}
	
}
