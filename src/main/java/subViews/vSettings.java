package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class vSettings extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public vSettings() {
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Soy Ajustes");
		lblNewLabel.setBounds(90, 139, 232, 121);
		add(lblNewLabel);
		
	}

}
