package zdPlaskinGuiTirocinio;

import javax.swing.*;


public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private MyPanel panel;
	
	public MainFrame(){
		this.setBounds(450,230,1000,700);
		initialize();
	}

	private void initialize() {
		this.setTitle("Interfaccia grafica tool zdPlskin");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new MyPanel();
		this.getContentPane().add(panel);
		
		
		
	}

}