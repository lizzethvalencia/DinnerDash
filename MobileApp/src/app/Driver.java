package app;
import ordering.Menu;
import ordering.MenuItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class Driver extends JFrame {
	private double totalCost;
	private Menu menu;
	private Payment payment;
	private JPanel receipt;
	private JPanel centerPanel;
	private JTextField orderPrice;
	private ArrayList <MenuItem> itemsOrdered;
	private JTextPane orderItems;
	private String itemInformation;
	
	public Driver(File storedCreditCards, File givenMenu) throws FileNotFoundException {
		totalCost = 0;
		itemInformation = "";
		
		payment = new Payment(storedCreditCards);
		payment.readInputFile();
		itemsOrdered = new ArrayList<MenuItem>();
		menu = new Menu(givenMenu);
		menu.readInputFile();
		create();
		
		setSize(1200,1000);
		setTitle("Dinner Dash Mobile App");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	public void create() {
		JPanel mainPanel = (JPanel) getContentPane();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getItemButtons(), getReceipt());
		splitPane.setEnabled(false);;
		splitPane.setDividerLocation(780);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);
	}

	private JScrollPane getItemButtons() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(0,2));
		
		ArrayList<MenuItem> itemButtons = menu.getMenuItems();

		for (final MenuItem itemButton: itemButtons) {

			final JButton createButton = new JButton(itemButton.getName());
			createButton.setToolTipText(itemButton.getName());
			
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					refreshPanel(itemButton);
				}
			});
			pan.add(createButton);
			createButton.setPreferredSize(new Dimension(30,60));
			
		}

		JScrollPane scroller = new JScrollPane(pan);
		Border etchedBorder = BorderFactory.createEtchedBorder();
		Border border = BorderFactory.createTitledBorder(etchedBorder, "Menu Items",TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Lucida", Font.BOLD, 20) , Color.BLACK);
		pan.setBorder(border);
		return scroller;

		}

	private JPanel getReceipt() {
		
		receipt = new JPanel();
		JLabel label = new JLabel("Cart:");
		receipt.setLayout(new BorderLayout());
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BorderLayout());
		
		receipt.add(lowerPanel,BorderLayout.SOUTH);
		receipt.add(label, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,1));
		
		orderItems = new JTextPane();
		centerPanel.add(orderItems);
		
		orderItems.setEditable(false);
		
		JScrollPane centerPanelScroller = new JScrollPane(centerPanel);
		receipt.add(centerPanelScroller, BorderLayout.CENTER);
		
		orderPrice = new JTextField(20);
		orderPrice.setText("Total Cost = $0.00");
		orderPrice.setEditable(false);
		
		JButton placeOrder = new JButton("Place Order");
		JButton clearOrder = new JButton("Clear Order");
		
		placeOrder.setPreferredSize(new Dimension(30,50));
		clearOrder.setPreferredSize(new Dimension(30,50));
		
		centerPanel.setBackground(Color.LIGHT_GRAY);
		placeOrder.setForeground(Color.BLUE);
		clearOrder.setForeground(Color.RED);
		
		placeOrder.setFont(new Font ("Times New Roman", Font.BOLD,40));
		clearOrder.setFont(new Font ("Times New Roman", Font.BOLD,40));
		
		clearOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
			
		});
		
		placeOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (!orderPrice.getText().equals("Total Cost = $0.00")) {
						menu.logOrder(itemsOrdered, totalCost);
						JOptionPane.showMessageDialog(getContentPane(), "Order has been sent to kitchen", "Order has been logged", JOptionPane.INFORMATION_MESSAGE);
						delete();
					}
					else {
						JOptionPane.showMessageDialog(null,"No items ordered", "Place order", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (IOException g) {
					 JOptionPane.showMessageDialog(null, "Error! Program terminated"
							 , " Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		lowerPanel.add(orderPrice, BorderLayout.NORTH);
		lowerPanel.add(placeOrder, BorderLayout.CENTER);
		lowerPanel.add(clearOrder, BorderLayout.SOUTH);
		lowerPanel.setBackground(Color.LIGHT_GRAY);
		receipt.setBackground(Color.WHITE);
		return receipt;
		
	}
	
	private void delete() {
		
		orderPrice.setText("Total Cost = $0.00");
		totalCost = 0;
		itemsOrdered.clear();
		itemInformation = "";
		orderItems.setText(null);
		
	}

	private void refreshPanel(final MenuItem itemButton) {
		String item = itemButton.getName();
		double itemPrice = itemButton.getPrice();
		itemInformation += "\n" + item + "\n" + itemPrice + "\n";
		orderItems.setText(itemInformation);
		itemsOrdered.add(itemButton);
		totalCost += itemPrice;
		orderPrice.setText("Total cost = $" + totalCost);
	}

}
