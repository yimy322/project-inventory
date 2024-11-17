package extras;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class Input {
	public static void onlyNumbers(JTextField textField) {
		// para escuchar eventos del teclado
		textField.addKeyListener(new KeyAdapter() {
			// se ejecuta cuando se presiona una tecla
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// si no es digito se cancela el evento
				if (!Character.isDigit(c)) {
					e.consume();// cancela evento
				}
			}
		});

	}

	public static void validateMaxLength(JTextField textField, int maxLength) {
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text = textField.getText();
				// cancela el evento si es mayo al tamano requerido
				if (text.length() >= maxLength) {
					e.consume();// cancela evento
				}
			}
		});
	}

}
