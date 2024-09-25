package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class vSales extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public vSales() {
		setBackground(new Color(228, 233, 232));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Soy Ventas");
		lblNewLabel.setBounds(161, 161, 242, 131);
		add(lblNewLabel);

	}
}
