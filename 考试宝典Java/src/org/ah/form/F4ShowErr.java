package org.ah.form;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import org.ah.core.Bus;
import org.ah.core.bean.Question;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class F4ShowErr extends JFrame {

	private JPanel contentPane;
	private JScrollBar scrollBar;
	private JScrollPane scrollPane;

	public F4ShowErr(final F3QA f3) {
		this();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				f3.setEnabled(true);
				f3.setVisible(true);
			}
		});
	}

	public F4ShowErr() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea txtaShow = new JTextArea();
		txtaShow.setEditable(false);
		txtaShow.setLineWrap(true);
		txtaShow.setText("1\r\n2\r\n3\r\n4\r\n5\r\n6\r\n7\r\n8\r\n9\r\n10\r\n11\r\n12\r\n13\r\n14\r\n15\r\n16\r\n17\r\n18\r\n19\r\n20\r\n21\r\n22\r\n23\r\n24\r\n25\r\n26\r\n27\r\n28\r\n29\r\n30\r\n31\r\n32\r\n33\r\n34\r\n35\r\n36\r\n37\r\n38\r\n39\r\n40\r\n41\r\n42\r\n43\r\n44\r\n45\r\n46\r\n47\r\n48\r\n49\r\n50\r\n51\r\n52\r\n53\r\n54\r\n55\r\n56\r\n57\r\n58\r\n59\r\n60\r\n61\r\n62\r\n63\r\n64\r\n65\r\n66\r\n67\r\n68\r\n69\r\n70\r\n71\r\n72\r\n73\r\n74\r\n75\r\n76\r\n77\r\n78\r\n79\r\n80\r\n81\r\n82\r\n83\r\n84\r\n85\r\n86\r\n87\r\n88\r\n89\r\n90\r\n");
		txtaShow.setBounds(15, 15, 343, 405);
		contentPane.add(txtaShow);

		scrollPane = new JScrollPane(txtaShow);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(15, 15, 398, 405);

		contentPane.add(scrollPane);

		JButton btnNewButton = new JButton("回到开始");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollBar = scrollPane.getVerticalScrollBar();
				scrollBar.setValue(1);
			}
		});
		btnNewButton.setBounds(140, 444, 123, 29);
		contentPane.add(btnNewButton);

		// 显示内容
		if (Bus.errQs.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (Question q : Bus.errQs) {
				sb.append(q.getId() + "." + q.getTitle() + "\n");
				sb.append("A." + q.getOptA() + "\n");
				sb.append("B." + q.getOptB() + "\n");
				sb.append("C." + q.getOptC() + "\n");
				sb.append("D." + q.getOptD() + "\n");
				sb.append("标准答案是：" + q.getAnswer() + "\n");
				sb.append("您的答案是：" + q.getUserAnswer() + "\n");
			}
			txtaShow.setText(sb.toString());
		} else {
			txtaShow.setText("学霸V5");
		}
	}
}
