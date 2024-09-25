package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class vTransfers extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public vTransfers() {
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Soy Translados");
		lblNewLabel.setBounds(111, 128, 257, 182);
		add(lblNewLabel);

	}

}
