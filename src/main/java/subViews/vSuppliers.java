package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class vSuppliers extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public vSuppliers() {
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Soy Proveedores");
		lblNewLabel.setBounds(97, 168, 308, 141);
		add(lblNewLabel);

	}

}
