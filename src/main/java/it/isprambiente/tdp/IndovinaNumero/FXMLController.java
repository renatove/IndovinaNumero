/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.isprambiente.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	
	private int segreto;
	private int tentativiRimasti;
	private int tentativiEseguiti;
	private boolean inGioco = false;
	

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
    	//inizializza il numero da indovinare
    	this.segreto = (int)(Math.random() * NMAX) + 1 ;
    	this.tentativiRimasti = 8;
    	this.tentativiEseguiti = 0;
    	this.inGioco = true;
    	
    	this.txtTentativi.setText(Integer.toString(TMAX));
    	
    	this.lyoTentativo.setDisable(false);
    	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura dell'input utente
    	if(!inGioco) {
    		this.txtRisultato.appendText("ATTENZIONE: Non sei in gioco.\n");
    	}
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
    	
    	this.tentativiEseguiti++;
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiEseguiti));
    	this.txtRisultato.appendText("Tentativo num. " + this.tentativiEseguiti + ": Hai inserito il numero: "+ tentativo + "\n");

    	if(tentativo == this.segreto) {
    		// Hai vinto
    		this.txtRisultato.appendText("BRAVO: Hai indovinato il numero segreto con "+ this.tentativiEseguiti +" tentativi.\n");
    		this.inGioco = false;
    		this.lyoTentativo.setDisable(true);
    		return;
    	}
    	
    	// controllo i tentativi
    	if (this.tentativiEseguiti == TMAX) {
    		this.txtRisultato.appendText("HAI PERSO: Tentativi esauriti. Il numero segreto era: "+ this.segreto+"\n");
    		this.inGioco = false;
    		this.lyoTentativo.setDisable(true);
    		return;
    	}
    	
    	//controllo se il numero inserito e minoro o maggiore di segreto
    	if (tentativo < this.segreto) {
    		this.txtRisultato.appendText("ATTENZIONE: Tentativo troppo basso.\n");
    	} else {
    		this.txtRisultato.appendText("ATTENZIONE: Tentativo troppo alto.\n");
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
}

