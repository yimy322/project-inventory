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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import RSMaterialComponent.RSButtonMaterialIconDos;
import rojeru_san.efectos.ValoresEnum.ICONS;
import rojeru_san.efectos.ValoresEnum.POSITIONICON;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FormMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, conteiner;
	private JLabel lblDate, lblHour;

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
		setBounds(100, 100, 1200, 630);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		conteiner = new JPanel(new BorderLayout());
		conteiner.setBackground(new Color(255, 255, 255));
		conteiner.setBounds(0, 75, 1186, 476);
		contentPane.add(conteiner);
		
		// Instanciar el nuevo panel
        vSales sales = new vSales();
        sales.setBackground(new Color(228, 233, 232));
        // Configurar el tamaño y ubicación del panel si es necesario
        sales.setSize(1186, 518);
        sales.setLocation(0, 0);
        // Limpiar el contenido actual del contenedor
        conteiner.removeAll();       
        // Agregar el nuevo panel
        conteiner.add(sales, BorderLayout.CENTER);
        // Revalidar y repintar el contenedor para reflejar los cambios
        conteiner.revalidate();
        conteiner.repaint();
        
        JPanel panel_header = new JPanel();
        panel_header.setBackground(new Color(198, 225, 241));
        panel_header.setBounds(0, 0, 1186, 78);
        contentPane.add(panel_header);
        panel_header.setLayout(null);
        
        RSButtonMaterialIconDos btnSales = new RSButtonMaterialIconDos();
        btnSales.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vSales sales = new vSales();
                sales.setBackground(new Color(228, 233, 232));
                sales.setSize(1186, 518);
                sales.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(sales, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnSales.setForegroundText(new Color(0, 0, 0));
        btnSales.setForegroundIconHover(new Color(255, 255, 255));
        btnSales.setForegroundIcon(new Color(0, 0, 0));
        btnSales.setForegroundHover(new Color(255, 255, 255));
        btnSales.setForeground(new Color(255, 255, 255));
        btnSales.setText("VENTAS");
        btnSales.setIcons(ICONS.HOME);
        btnSales.setBackground(new Color(198, 225, 241));
        btnSales.setBounds(10, 11, 170, 50);
        panel_header.add(btnSales);
        
        RSButtonMaterialIconDos btnCustomers = new RSButtonMaterialIconDos();
        btnCustomers.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vCustomers customers = new vCustomers();
                customers.setBackground(new Color(228, 233, 232));
                customers.setSize(1186, 518);
                customers.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(customers, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnCustomers.setForegroundIcon(new Color(0, 0, 0));
        btnCustomers.setForegroundText(new Color(0, 0, 0));
        btnCustomers.setIcons(ICONS.PERSON_ADD);
        btnCustomers.setText("CLIENTES");
        btnCustomers.setBackground(new Color(198, 225, 241));
        btnCustomers.setBounds(190, 11, 170, 50);
        panel_header.add(btnCustomers);
        
        RSButtonMaterialIconDos btnProducts = new RSButtonMaterialIconDos();
        btnProducts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vProducts products = new vProducts();
                products.setBackground(new Color(228, 233, 232));
                products.setSize(1186, 518);
                products.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(products, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnProducts.setForegroundText(new Color(0, 0, 0));
        btnProducts.setForegroundIcon(new Color(0, 0, 0));
        btnProducts.setText("PRODUCTOS");
        btnProducts.setIcons(ICONS.FLIP);
        btnProducts.setBackground(new Color(198, 225, 241));
        btnProducts.setBounds(370, 11, 170, 50);
        panel_header.add(btnProducts);
        
        RSButtonMaterialIconDos btnInventory = new RSButtonMaterialIconDos();
        btnInventory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vInventory inventory = new vInventory();
                inventory.setBackground(new Color(228, 233, 232));
                inventory.setSize(1186, 518);
                inventory.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(inventory, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnInventory.setForegroundIcon(new Color(0, 0, 0));
        btnInventory.setForegroundText(new Color(0, 0, 0));
        btnInventory.setText("INVENTARIO");
        btnInventory.setIcons(ICONS.FOLDER);
        btnInventory.setBackground(new Color(198, 225, 241));
        btnInventory.setBounds(550, 11, 170, 50);
        panel_header.add(btnInventory);
        
        RSButtonMaterialIconDos btnSuppliers = new RSButtonMaterialIconDos();
        btnSuppliers.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vSuppliers suppliers = new vSuppliers();
                suppliers.setBackground(new Color(228, 233, 232));
                suppliers.setSize(1186, 518);
                suppliers.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(suppliers, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnSuppliers.setForegroundText(new Color(0, 0, 0));
        btnSuppliers.setForegroundIcon(new Color(0, 0, 0));
        btnSuppliers.setText("PROVEEDORES");
        btnSuppliers.setIcons(ICONS.PERSON_PIN);
        btnSuppliers.setBackground(new Color(198, 225, 241));
        btnSuppliers.setBounds(730, 11, 170, 50);
        panel_header.add(btnSuppliers);
        
        RSButtonMaterialIconDos btnTransfers = new RSButtonMaterialIconDos();
        btnTransfers.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vTransfers transfers = new vTransfers();
                transfers.setBackground(new Color(228, 233, 232));
                transfers.setSize(1186, 518);
                transfers.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(transfers, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnTransfers.setForegroundIcon(new Color(0, 0, 0));
        btnTransfers.setForegroundText(new Color(0, 0, 0));
        btnTransfers.setText("TRANSLADOS");
        btnTransfers.setIcons(ICONS.DIRECTIONS_WALK);
        btnTransfers.setBackground(new Color(198, 225, 241));
        btnTransfers.setBounds(910, 11, 170, 50);
        panel_header.add(btnTransfers);
        
        RSButtonMaterialIconDos btnSettings = new RSButtonMaterialIconDos();
        btnSettings.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Instanciar el nuevo panel y repetir los pasos
                vSettings settings = new vSettings();
                settings.setBackground(new Color(228, 233, 232));
                settings.setSize(1186, 518);
                settings.setLocation(0, 0);
                conteiner.removeAll();       
                conteiner.add(settings, BorderLayout.CENTER);
                conteiner.revalidate();
                conteiner.repaint();
        	}
        });
        btnSettings.setForegroundIcon(new Color(0, 0, 0));
        btnSettings.setPositionIcon(POSITIONICON.RIGHT);
        btnSettings.setBounds(1090, 11, 66, 50);
        panel_header.add(btnSettings);
        btnSettings.setIcons(ICONS.SETTINGS);
        btnSettings.setBackground(new Color(198, 225, 241));
        
        JPanel panel_footer = new JPanel();
        panel_footer.setBackground(new Color(186, 199, 197));
        panel_footer.setBounds(0, 552, 1186, 41);
        contentPane.add(panel_footer);
        panel_footer.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Algorithm Squad");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel.setBounds(10, 0, 179, 35);
        panel_footer.add(lblNewLabel);
        
        JLabel lblJovenesTalentosPeruanos = new JLabel("Made in Perú");
        lblJovenesTalentosPeruanos.setHorizontalAlignment(SwingConstants.CENTER);
        lblJovenesTalentosPeruanos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblJovenesTalentosPeruanos.setBounds(243, 0, 118, 35);
        panel_footer.add(lblJovenesTalentosPeruanos);
        
        JLabel lblActiveUser = new JLabel("Unknown");
        lblActiveUser.setHorizontalAlignment(SwingConstants.LEFT);
        lblActiveUser.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblActiveUser.setBounds(577, 0, 118, 35);
        panel_footer.add(lblActiveUser);
        
        JLabel lblActiveUser_1 = new JLabel("Active User:");
        lblActiveUser_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblActiveUser_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
        lblActiveUser_1.setBounds(451, 0, 118, 35);
        panel_footer.add(lblActiveUser_1);
        
        lblHour = new JLabel("00:00:00");
        lblHour.setHorizontalAlignment(SwingConstants.CENTER);
        lblHour.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHour.setBounds(1058, 0, 118, 35);
        panel_footer.add(lblHour);
        
        JLabel lblFechaYHora_1 = new JLabel("Fecha y hora:");
        lblFechaYHora_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblFechaYHora_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFechaYHora_1.setBounds(779, 0, 118, 35);
        panel_footer.add(lblFechaYHora_1);
        
        lblDate = new JLabel("00/00/0000");
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDate.setBounds(930, 0, 118, 35);
        panel_footer.add(lblDate);
        
        startTimer();
	}
	private void updateDateTime() {
        // Obtener la hora y la fecha actuales
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        // Formateadores para hora y fecha
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Actualizar las etiquetas con la hora y la fecha actuales
        lblHour.setText(currentTime.format(timeFormatter));
        lblDate.setText(currentDate.format(dateFormatter));
    }
	private void startTimer() {
        // Crear un temporizador que se actualice cada 1000 ms (1 segundo)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime(); // Actualizar la fecha y la hora cada segundo
            }
        });
        timer.start(); // Iniciar el temporizador
    }
}
