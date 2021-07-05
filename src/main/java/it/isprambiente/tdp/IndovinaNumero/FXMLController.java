/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.isprambiente.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.isprambiente.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader
    
    @FXML // fx:id="lyoTentativo"
    private HBox lyoTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumero"
    private TextField txtNumero; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//inizio la partita
    	this.model.nuovaPartita();
    	
    	// gestisce l'interfaccia
    	this.txtRisultato.clear();
    	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()));  	
    	this.lyoTentativo.setDisable(false);
    	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura dell'input utente
    	String ts = this.txtNumero.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    		
    	} catch (NumberFormatException e) {
    		this.txtRisultato.appendText("ATTENZIONE: Devi inserire un numero.\n");
    		return;
    	}
    	
    	//pulizia casella di input numero
    	this.txtNumero.setText("");
    
    	this.txtRisultato.setText("Tentativo num. " + this.model.getTentativiEseguiti() + ": Hai inserito il numero: "+ tentativo + "\n");

    	int result;
    	
    	try {
	    	result = this.model.tentativo(tentativo) ;
    	} catch (IllegalStateException se) {
    		this.txtRisultato.setText(se.getMessage());
    		this.lyoTentativo.setDisable(true);
    		return;
    	} catch (InvalidParameterException pe) {
    		this.txtRisultato.setText(pe.getMessage());
    		return;
    	}
  
      	this.txtTentativi.setText(Integer.toString(this.model.getTMAX()-this.model.getTentativiEseguiti()));
        
    	if(result == 0) {
    		// Hai vinto
    		this.txtRisultato.setText("BRAVO: Hai indovinato il numero segreto con "+ this.model.getTentativiEseguiti() +" tentativi.\n");
    		this.lyoTentativo.setDisable(true);
    		return;
    	} else if (result < 0) {
    		this.txtRisultato.setText("ATTENZIONE: Tentativo troppo basso.\n");
    		return;
    	} else {
    		this.txtRisultato.setText("ATTENZIONE: Tentativo troppo alto.\n");
    		return;
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}

