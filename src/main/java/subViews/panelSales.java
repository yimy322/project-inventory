package subViews;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class panelSales extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public panelSales() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 128, 128));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Soy Ventas");
		lblNewLabel.setBounds(188, 150, 739, 183);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(371, 230, 89, 23);
		add(btnNewButton);

	}

}
