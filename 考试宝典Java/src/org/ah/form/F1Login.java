package org.ah.form;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.Toolkit;

public class F1Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F1Login frame = new F1Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public F1Login() {
	
		setIconImage(Toolkit.getDefaultToolkit().getImage("ahicon.jpg"));
		setTitle("考试宝典");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Lable标签（用户名，密码）
		JLabel lblUserName = new JLabel("用户名");
		lblUserName.setBounds(124, 65, 81, 21);
		contentPane.add(lblUserName);

		JLabel lblPwd = new JLabel("密码");
		lblPwd.setBounds(124, 106, 81, 21);
		contentPane.add(lblPwd);

		// 输入框（用户名，密码）
		txtUserName = new JTextField();
		txtUserName.setBounds(193, 62, 159, 27);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		// 按钮
		JButton btnLogin = new JButton("登录");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(169, 169, 123, 29);
		contentPane.add(btnLogin);

		txtPwd = new JPasswordField();
		txtPwd.setBounds(193, 103, 159, 27);
		contentPane.add(txtPwd);
	}
}
