package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import subViews.*;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

public class FormMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel conteiner;

	/**
	 * Create the method of init the frame
	 */
	public void run() {
		try {
			FormMain frame = new FormMain();
	        frame.setVisible(true);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public FormMain() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 590);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnHome = new JButton("Menu");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Instanciar el nuevo panel
		        Home home = new Home();		        
		        // Configurar el tamaño y ubicación del panel si es necesario
		        home.setSize(1166, 510);
		        home.setLocation(0, 0);
		        // Limpiar el contenido actual del contenedor
		        conteiner.removeAll();	        
		        // Agregar el nuevo panel
		        conteiner.add(home, BorderLayout.CENTER);
		        // Revalidar y repintar el contenedor para reflejar los cambios
		        conteiner.revalidate();
		        conteiner.repaint();
			}
		});
		btnHome.setBackground(new Color(255, 128, 192));
		menuBar.add(btnHome);
		
		JMenu mnSale = new JMenu("Venta");
		menuBar.add(mnSale);
		
		JMenuItem mntmSales = new JMenuItem("Mis Ventas");
		mntmSales.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Instanciar el nuevo panel
		        panelSales vSales = new panelSales();   
		        // Configurar el tamaño y ubicación del panel si es necesario
		        vSales.setSize(1166, 510);
		        vSales.setLocation(0, 0);
		        // Limpiar el contenido actual del contenedor
		        conteiner.removeAll();
		        // Agregar el nuevo panel
		        conteiner.add(vSales, BorderLayout.CENTER);
		        // Revalidar y repintar el contenedor para reflejar los cambios
		        conteiner.revalidate();
		        conteiner.repaint();
		    }
		});
		mnSale.add(mntmSales);
		
		JMenu mnCustomers = new JMenu("Clientes");
		menuBar.add(mnCustomers);
		
		JMenu mnSuppliers = new JMenu("Proveedores");
		menuBar.add(mnSuppliers);
		
		JMenu mnProducts = new JMenu("Productos");
		menuBar.add(mnProducts);
		
		JMenu mnInventory = new JMenu("Inventario");
		menuBar.add(mnInventory);
		
		JMenu mnProfile = new JMenu("Perfil");
		menuBar.add(mnProfile);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Settings");
			}
		});
		mnProfile.add(mntmSettings);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnProfile.add(mntmLogOut);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		conteiner = new JPanel(new BorderLayout());
		conteiner.setBackground(new Color(255, 255, 255));
		conteiner.setBounds(10, 11, 1166, 510);
		contentPane.add(conteiner);
		
        
		// Instanciar el nuevo panel
        Home home = new Home();
        // Configurar el tamaño y ubicación del panel si es necesario
        home.setSize(1166, 510);
        home.setLocation(0, 0);
        // Limpiar el contenido actual del contenedor
        conteiner.removeAll();       
        // Agregar el nuevo panel
        conteiner.add(home, BorderLayout.CENTER);      
        // Revalidar y repintar el contenedor para reflejar los cambios
        conteiner.revalidate();
        conteiner.repaint();
	}
}
