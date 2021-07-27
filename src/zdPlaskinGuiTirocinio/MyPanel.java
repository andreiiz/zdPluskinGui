package zdPlaskinGuiTirocinio;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import listenerPackage.RadioButtonListener;


public class MyPanel extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	
	
	private int tipoTemperatura= -1; //per capire se costante o meno
	private JButton buttonSfoglia;
	private static JButton buttonSalva;
	public String path;
	private JLabel durataSimulazioneLab;
	private JTextField durataSimulazioneArea;
	private JLabel time_stepLab;
	private JTextField time_stepArea;
	private JLabel save_timeLab;
	private JTextField save_timeArea;
	private  JLabel tempCost;
	private JLabel plasmaTemp;
	private JTextField plasmaTempArea;
	private JLabel etichettaEsempio1;
	private JLabel etichettaEsempio2;
	
	//4 flag boolean 
	private boolean cliccato = false;
	private boolean correttoCostante;  //valore1 di controllo sulla correttenza dei cammpi
	private boolean correttoCostante2 ;  //valore2 di controllo sulla correttenza dei cammpi
	private boolean correttoCostante3 ;  //valore3 di controllo sulla correttenza dei cammpi
	
	
	//2 radio button
	
	private JRadioButton yes;
	private JRadioButton no;
	private ButtonGroup group = new ButtonGroup();
	
	//inizio static;  inserite perchè ho voluto separare il listener del radioButton ma non sono necessari
	
	private static JLabel costTempLab;
	private static JTextField costTempField;
	private static JLabel temp_iniziale;
	private static JLabel temp_finale;
	private static JLabel tao;
	private static JTextField temp_inizialeField;
	private static JTextField temp_finaleField;
	private static JTextField taoField;
	private  static JLabel	outputState; //aggiunta volontariamente (si può omettere);
	
	
	//fine variabili swing
	
	private String durataSimulazioneString;
	private String timeStepString;
	private String saveTimeString;
	private String tempCostanteString;
	private String tempInizialeString;
	private String tempFinaleString;
	private String taoString;
	private String plasmaTempString;
	private String timeStepStringTokenizzato;
	//fine dichiarazioni
	
	public MyPanel()
	{
		initialize();
	}
	public void initialize() {
			this.setLayout(null); 	//tolgo il layout, perchè piazzo componente per componente per comodità
			buttonSfoglia = new JButton("Sfoglia");
			buttonSfoglia.setBounds(4, 16, 160, 35);
			buttonSfoglia.addActionListener(this);
			this.add(buttonSfoglia);
			durataSimulazioneLab = new JLabel("Inserisci la durata della simulazione [s]");
			durataSimulazioneLab.setFont(new Font("sansserif",Font.BOLD,13)); //qui per cambiare il font
			durataSimulazioneLab.setBounds(10,60,260,70);
			this.add(durataSimulazioneLab);
			durataSimulazioneArea = new JTextField(7);
			durataSimulazioneArea.setBounds(270,77,78,30);
			//durataSimulazioneArea.addActionListener(this);
			this.add(durataSimulazioneArea);
				
			time_stepLab = new JLabel("Inserisci il time_step ");
			time_stepLab.setFont(new Font("sansserif",Font.BOLD,13)); //font
			time_stepLab.setBounds(10, 120, 230, 70); //120
			time_stepArea = new JTextField(5);
			time_stepArea.setBounds(270, 139, 78, 30); //era 174 il primo campo
			etichettaEsempio1 = new JLabel("[10d-06 sono 10microsecondi]");
			etichettaEsempio1.setFont(new Font("sansserif",Font.BOLD,13)); //font
			etichettaEsempio1.setBounds(370, 80, 230, 150);
			save_timeLab = new JLabel("Inserisci il tempo di salvataggio [ms]");
			save_timeLab.setFont(new Font("sansserif",Font.BOLD,13)); //font
			save_timeLab.setBounds(10, 170, 250, 70);
			save_timeArea = new JTextField(5);
			save_timeArea.setBounds(270, 190, 78, 30);
			etichettaEsempio2 = new JLabel("[40d-03 sono 40millisecondi]");
			etichettaEsempio2.setFont(new Font("sensserif",Font.BOLD, 13));
			etichettaEsempio2.setBounds(373, 130, 230, 150);
			this.add(time_stepLab);
			this.add(time_stepArea);
			this.add(save_timeLab);
			this.add(save_timeArea);
			this.add(etichettaEsempio1);
			this.add(etichettaEsempio2);
			tempCost = new JLabel("Temperatura Costante?");
			tempCost.setFont(new Font("sansserif",Font.BOLD,13));
			tempCost.setBounds(10, 240, 220, 30);
			this.add(tempCost);
			
			/*INIZIO 2 RADIO BUTTON*/		
			no = new JRadioButton("No");	
			no.setBounds(220, 230, 50, 50);   //radio button
			yes = new JRadioButton("Si");
			yes.setBounds(290, 230, 50, 50);
			group.add(no);
			group.add(yes);
			no.addActionListener(new RadioButtonListener());	
			yes.addActionListener(new RadioButtonListener());
			this.add(no);
			this.add(yes);
			/* FINE RADIO BUTTON */
			
			
			/*se è SI  TEMPERATURA COSTANTE*/
			costTempLab = new JLabel("Inserisci la temperatura costante [K]");
			costTempLab.setBounds(10,200,260,230);
			costTempLab.setFont(new Font("sansserif",Font.BOLD,13));
			costTempLab.setVisible(false);
			this.add(costTempLab);
			costTempField = new JTextField(5);
			costTempField.setBounds(270,300,78,30);
			//costTempField.addActionListener(this);
			costTempField.setVisible(false);
			this.add(costTempField);
		/*--------------------------------------------------------*/	
			
			/*	Se è NO 
			 *
			 * TEMPERATURA INIZIALE*/
			
			temp_iniziale = new JLabel("Inserisci la temperatura iniziale [K]");
			temp_iniziale.setFont(new Font("sansserif",Font.BOLD,13));
			//non sovrapposti	temp_iniziale.setBounds(10,230,300,300);
			temp_iniziale.setBounds(10, 200, 260, 230); //sovrapposti
			temp_iniziale.setVisible(false);
			this.add(temp_iniziale);
			temp_inizialeField = new JTextField(5);
			//non sovrapposti	temp_inizialeField.setBounds(250,366,78,30);
			temp_inizialeField.setBounds(270, 300, 78, 30); //sovrapposti
			temp_inizialeField.setVisible(false);
			
			this.add(temp_inizialeField);
			
			
			/*TEMPERATURA FINALE     */
			temp_finale = new JLabel("Inserisci la temperatura finale [K]");
			temp_finale.setFont(new Font("sansserif",Font.BOLD,13));
			temp_finale.setBounds(10, 220, 330, 300); 
			temp_finale.setVisible(false);
			this.add(temp_finale);
			temp_finaleField = new JTextField(5);
			temp_finaleField.setBounds(269, 357, 78, 30); //SOVRAPPOSTI
			
			temp_finaleField.setVisible(false);
			this.add(temp_finaleField);
			
			/* TAO     */
			tao = new JLabel("Inserisci la costante di tempo tao [S]");
			tao.setFont(new Font("sansserif",Font.BOLD,13));
			tao.setBounds(10,280,330,300);
			tao.setVisible(false);
			this.add(tao);
			
			taoField = new JTextField(5);
			taoField.setBounds(270, 420, 78, 30);
		
			taoField.setVisible(false);
			this.add(taoField);
			
			/* PLASMA */
			
			plasmaTemp = new JLabel("Inserisci la temperatura del plasma [K]");
			plasmaTemp.setFont(new Font("sansserif", Font.BOLD, 13));
			plasmaTemp.setBounds(10, 345, 330, 300); //label plasma
			plasmaTemp.setVisible(true);
			this.add(plasmaTemp);
			
			plasmaTempArea = new JTextField(5);
			plasmaTempArea.setBounds(270,480, 78,30);
			plasmaTempArea.setVisible(true);
			this.add(plasmaTempArea);
			
			
			buttonSalva = new JButton("Salva Dati");
			buttonSalva.setBounds(468, 540, 113, 50);  //precedente
	
			buttonSalva.addActionListener(this);  
			this.add(buttonSalva);
			
			
			/*OUTPUT FIELD */
			
			outputState = new JLabel("");
			MyPanel.outputState.setBounds(428, 425, 320,120);
			MyPanel.outputState.setFont(new Font("sansserif",Font.BOLD,13));
			this.add(outputState);
			
			
			/* fine my panel*/
	}
	
	public static void setLabell(String mex) {
		outputState.setText(mex);
	}
	
	
	public static void settaVisibilità(String scelta) {
		
		if(scelta.equalsIgnoreCase("si") && !temp_iniziale.isVisible() ) {
		
			costTempLab.setVisible(true);
			costTempField.setVisible(true);
		
		}else if(scelta.equalsIgnoreCase("no") && !costTempLab.isVisible()) {
			
			temp_iniziale.setVisible(true);
			temp_inizialeField.setVisible(true);
			temp_finale.setVisible(true);
			temp_finaleField.setVisible(true);
			tao.setVisible(true);
			taoField.setVisible(true);
		}
		else if(scelta.equalsIgnoreCase("si") && temp_iniziale.isVisible()) {
			temp_iniziale.setVisible(false);
			temp_inizialeField.setVisible(false);
			temp_finale.setVisible(false);
			temp_finaleField.setVisible(false);
			tao.setVisible(false);
			taoField.setVisible(false);
			
			costTempLab.setVisible(true);
			costTempField.setVisible(true);
		}
		else if(scelta.equalsIgnoreCase("no") && costTempLab.isVisible()) {
			
			costTempLab.setVisible(false);
			costTempField.setVisible(false);
			
			temp_iniziale.setVisible(true);
			temp_inizialeField.setVisible(true);
			temp_finale.setVisible(true);
			temp_finaleField.setVisible(true);
			tao.setVisible(true);
			taoField.setVisible(true);
			
			
		}
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//gestione dei 2 button (sfoglia,salva)
		
		//sfoglia
		Object source = arg0.getSource();
		if(source == buttonSfoglia) {
			
			try{
				 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //per il look del fileChooser
				}catch(Exception e){  }
			
			JFileChooser jf = new JFileChooser();
			jf.setFileFilter(new FiltroTesto());
			int result = jf.showOpenDialog(this);
			if( result == JFileChooser.APPROVE_OPTION) {
				 File f = jf.getSelectedFile();
				 path = f.getAbsolutePath();
				 
				
				 MyFileWriter.createPathFile(f);
				 MyPanel.setLabell("File caricato correttamente");
				 cliccato = true;
			}else if( result == JFileChooser.ERROR_OPTION) {
				MyPanel.setLabell("C'e' stato un errore nel caricamento del file");
			}
		}
		//salva
		if(source == buttonSalva) {
			
			correttoCostante = true;
			correttoCostante2 = true;
			correttoCostante3 = true;
			
			//se il file non è stato caricato dai errore!
			if(cliccato == false) {
				MyPanel.setLabell("Caricare il file prima");
				correttoCostante = false;
			}
			
			//se i campi non sono riempiti da errore
			if((durataSimulazioneArea.getText().equals("") && correttoCostante )|| (time_stepArea.getText().equals("") && correttoCostante) ||( save_timeArea.getText().equals("")  && correttoCostante) || plasmaTempArea.getText().equals("") && correttoCostante) {
				MyPanel.setLabell("Tutti i campi sono obbligatori");
				correttoCostante2 = false;
			}
			durataSimulazioneString =  durataSimulazioneArea.getText();
			
			
			timeStepString = time_stepArea.getText();
			
			
			
			saveTimeString = save_timeArea.getText();
			
			plasmaTempString = plasmaTempArea.getText();
			
			StringTokenizer tokenizer = new StringTokenizer(timeStepString,"d");
			timeStepStringTokenizzato = tokenizer.nextToken();
			
			 if((Double.parseDouble(timeStepStringTokenizzato) > 10.00 ) && correttoCostante2 && correttoCostante){
					MyPanel.setLabell("Time_Step deve essere minore o uguale a 10 ms!");
					correttoCostante3 = false;
			}
			
			if((!costTempLab.isVisible() &&  !temp_iniziale.isVisible()) && correttoCostante3 && correttoCostante2 && correttoCostante) {
				MyPanel.setLabell("Devi selezionare il tipo di temperatura");
				
			}
			
			
			//se la temperatura è costante
			
			if( (costTempLab.isVisible() && !temp_iniziale.isVisible()) && (  correttoCostante3 && correttoCostante2 && correttoCostante)) {
				tempCostanteString = costTempField.getText();
				tempInizialeString = null;  //oppure invece dei null posso usare un flag
				tempFinaleString = null;
				taoString = null;
				
			/*	if(plasmaTempArea.getText().equals("") && correttoCostante && correttoCostante2 && correttoCostante3) {
					MyPanel.setLabell("Devi inserire la temperatura del plasma");
				}
					*/		
			if(durataSimulazioneString.trim().isEmpty() || timeStepString.trim().isEmpty() || saveTimeString.trim().isEmpty() || tempCostanteString.trim().isEmpty()) {
						MyPanel.setLabell("Tutti i campi devono essere riempiti!");
						correttoCostante = false;
						
				}
			
				
			else if(correttoCostante && correttoCostante2 && correttoCostante3) {
					tipoTemperatura = 1;
					MyFileWriter.createResultFile(durataSimulazioneString, timeStepString, saveTimeString, tempCostanteString,tempInizialeString,tempFinaleString,taoString,tipoTemperatura,plasmaTempString);
				
				MyPanel.setLabell("File salvato correttamente!");
				}
				
				 
			// se la temperatura è variabile 
				 
			}else if(temp_iniziale.isVisible() && !costTempLab.isVisible() && (correttoCostante3 && correttoCostante2 && correttoCostante)) {
				tempInizialeString = temp_inizialeField.getText();
				tempFinaleString = temp_finaleField.getText();
				taoString = taoField.getText();
				tempCostanteString = null;
				
				
				 if(durataSimulazioneString.trim().isEmpty() || timeStepString.trim().isEmpty() || saveTimeString.trim().isEmpty() || tempInizialeString.trim().isEmpty() || tempFinaleString.trim().isEmpty() || taoString.trim().isEmpty()) {
					MyPanel.setLabell("Tutti i campi devono essere riempiti");
					
				}else  {
					tipoTemperatura = 0;
					MyFileWriter.createResultFile(durataSimulazioneString.trim(), timeStepString.trim(), saveTimeString.trim(), tempCostanteString,tempInizialeString.trim(),tempFinaleString.trim(),taoString.trim(), tipoTemperatura, plasmaTempString.trim());
					MyPanel.setLabell("File salvato correttamente");
				}
				
				
			}
		}
		
	}
	
	
}
 