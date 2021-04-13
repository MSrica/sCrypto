package sCrypto_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class sCrypto_basic {

	private JFrame frame;
	private JTextField txtValue;
	private JTextField txtQuantity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sCrypto_basic window = new sCrypto_basic();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sCrypto_basic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblScryptoTradingBot = new JLabel("sCrypto");
		lblScryptoTradingBot.setBounds(175, 12, 138, 15);
		frame.getContentPane().add(lblScryptoTradingBot);
		
		txtValue = new JTextField();
		txtValue.setText("value");
		txtValue.setBounds(260, 90, 165, 19);
		frame.getContentPane().add(txtValue);
		txtValue.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("quantity");
		txtQuantity.setBounds(260, 135, 165, 19);
		frame.getContentPane().add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(260, 39, 165, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("BTC");
		comboBox.addItem("ETH");
		comboBox.addItem("XRP");
		comboBox.addItem("LTC");
		comboBox.addItem("XMR");
		
		JButton btnBuy = new JButton("BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valute = (String)comboBox.getSelectedItem();
				int value = Integer.parseInt(txtValue.getText());
				int quantity = Integer.parseInt(txtQuantity.getText());
				System.out.println("Buy");
				System.out.println(valute);
				System.out.println(value);
				System.out.println(quantity);
			}
		});
		btnBuy.setBounds(55, 180, 165, 25);
		frame.getContentPane().add(btnBuy);
		
		JButton btnSell = new JButton("SELL");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				String valute = (String)comboBox.getSelectedItem();
				int value = Integer.parseInt(txtValue.getText());
				int quantity = Integer.parseInt(txtQuantity.getText());
				System.out.println("Sell");
				System.out.println(valute);
				System.out.println(value);
				System.out.println(quantity);
			}
		});
		btnSell.setBounds(260, 180, 165, 25);
		frame.getContentPane().add(btnSell);
		
		JLabel lblCrypto = new JLabel("Crypto: ");
		lblCrypto.setBounds(55, 44, 165, 15);
		frame.getContentPane().add(lblCrypto);
		
		JLabel lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(55, 92, 165, 15);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		lblQuantity.setBounds(55, 137, 165, 15);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblMethod = new JLabel("Method: ");
		lblMethod.setBounds(55, 237, 165, 15);
		frame.getContentPane().add(lblMethod);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"golden/death cross", "RSI/MACD", "MFI"}));
		comboBox_1.setBounds(260, 232, 165, 24);
		frame.getContentPane().add(comboBox_1);
	}
}
