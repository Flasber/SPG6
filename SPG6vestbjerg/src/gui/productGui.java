package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.BillableItemController;

public class productGui extends JFrame {
	private JFrame frame;
	private BillableItemController controller;
	private JButton createProductButton;
	private JButton deleteProductButton;

	public productGui(BillableItemController controller) {
		this.controller = controller;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Product GUI");
		frame.setBounds(100, 100, 637, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		createProductButton = new JButton("Create Product");
		createProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openCreateProductDialog();
			}
		});
		panel.add(createProductButton);

		deleteProductButton = new JButton("Delete Product");
		deleteProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDeleteProductDialog();
			}
		});
		panel.add(deleteProductButton);

		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	private void openCreateProductDialog() {
		createProductDialog dialog = new createProductDialog(frame, controller, new Runnable() {
			@Override
			public void run() {

			}
		});
		dialog.setVisible(true);
	}

	private void openDeleteProductDialog() {
		deleteProductDialog dialog = new deleteProductDialog(frame, controller);
		dialog.setVisible(true);
	}

	public void show() {
		frame.setVisible(true);
	}
}
