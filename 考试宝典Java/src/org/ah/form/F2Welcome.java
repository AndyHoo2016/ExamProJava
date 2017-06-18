package org.ah.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class F2Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		F2Welcome frame = new F2Welcome();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public F2Welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLast = new JButton("再充，你会更强");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.net.URI uri;
				try {
					uri = new java.net.URI("http://www.taobao.com");
					java.awt.Desktop.getDesktop().browse(uri);
				} catch (URISyntaxException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLast.setBounds(62, 438, 337, 38);
		contentPane.add(btnLast);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "高数", "英语",
				"专业课" }));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		comboBox.setBounds(62, 41, 178, 27);
		contentPane.add(comboBox);

		JButton btnStart = new JButton("开始答题");
		btnStart.setBounds(276, 40, 123, 29);
		contentPane.add(btnStart);

		JButton btn1 = new JButton("7天考上清华");
		btn1.setEnabled(false);
		btn1.setBounds(62, 108, 337, 38);
		contentPane.add(btn1);
	}
}
