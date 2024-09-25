package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class vCustomers extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public vCustomers() {
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Soy Clientes");
		lblNewLabel.setBounds(178, 197, 104, 81);
		add(lblNewLabel);

	}
}
