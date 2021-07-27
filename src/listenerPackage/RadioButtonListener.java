package listenerPackage;

import zdPlaskinGuiTirocinio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonListener implements ActionListener {

	private String scelta;
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		scelta = e.getActionCommand();
		
		MyPanel.settaVisibilità(scelta);
	}

}