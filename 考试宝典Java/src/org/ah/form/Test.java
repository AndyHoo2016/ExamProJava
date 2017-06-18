package org.ah.form;

import java.awt.*;
import javax.swing.*;

public class Test {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class MyFrame extends JFrame {
	public MyFrame() {
		setTitle("Text");
		setSize(300, 300);
		JPanel panel = new JPanel();
		JTextArea textField = new JTextArea(1, 5);
		textField.setText("dfdfdfasfjeiursljdfldsjfsldjfoeirujlsdflsdf");

		JScrollPane scrollpane = new JScrollPane(textField);
		panel.add(scrollpane);
		add(panel);
	}
}