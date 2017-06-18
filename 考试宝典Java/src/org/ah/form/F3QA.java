package org.ah.form;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;

import org.ah.core.Bus;
import org.ah.core.bean.Question;
import org.ah.dao.QuestionDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F3QA extends JFrame {

	private JPanel contentPane;
	private JCheckBox chckbxA;
	private JCheckBox chckbxB;
	private JCheckBox chckbxC;
	private JCheckBox chckbxD;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		F3QA frame = new F3QA();
		frame.setVisible(true);
	}

	private void clearOption(Question q) {
		this.chckbxA.setSelected(false);
		this.chckbxB.setSelected(false);
		this.chckbxC.setSelected(false);
		this.chckbxD.setSelected(false);
		String sUserAnswer = q.getUserAnswer();
		if (sUserAnswer != null) {
			if (sUserAnswer.contains("A")) {
				this.chckbxA.setSelected(true);
			}
			if (sUserAnswer.contains("B")) {
				this.chckbxB.setSelected(true);
			}
			if (sUserAnswer.contains("C")) {
				this.chckbxC.setSelected(true);
			}
			if (sUserAnswer.contains("D")) {
				this.chckbxD.setSelected(true);
			}
		}

	}

	private void ShowQuestion(Question q) {
		this.textArea.setText(q.getId() + "." + q.getTitle());
		this.chckbxA.setText("A. " + q.getOptA());
		this.chckbxB.setText("B. " + q.getOptB());
		this.chckbxC.setText("C. " + q.getOptC());
		this.chckbxD.setText("D. " + q.getOptD());
		if (q.getId() >= 5) {
			this.btnNext.setEnabled(false);
		} else {
			this.btnNext.setEnabled(true);
		}
		if (q.getId() <= 1) {
			this.btnPrevious.setEnabled(false);
		} else {
			this.btnPrevious.setEnabled(true);
		}
		clearOption(q);
	}

	int qId = 0;
	private JButton btnSubmit;
	private JButton btnPrevious;
	private JButton btnNext;

	private void intiForm() {
		QuestionDao dao = new QuestionDao();
		List<Question> qs全部題目 = dao.getAllQuestions();

		// 随机取题
		Bus.newQs = Bus.extractQuestions(qs全部題目, 5);

		for (int i = 0; i < Bus.newQs.size(); i++) {
			Question q = Bus.newQs.get(i);
			// 列表在变，此语句无效 for (Question q : newQs) {
			q.setId(i + 1);
		}

		ShowQuestion(Bus.newQs.get(qId));
	}

	private void recordAnswer(int i) {
		Question q = Bus.newQs.get(i);
		String sUserAnswer = "";
		if (this.chckbxA.isSelected()) {
			sUserAnswer += "A";
		}
		if (this.chckbxB.isSelected()) {
			sUserAnswer += "B";
		}
		if (this.chckbxC.isSelected()) {
			sUserAnswer += "C";
		}
		if (this.chckbxD.isSelected()) {
			sUserAnswer += "D";
		}
		q.setUserAnswer(sUserAnswer);
	}

	private void submit() {
		for (int i = 0; i < Bus.newQs.size(); i++) {
			Question q = Bus.newQs.get(i);

			if (q.getAnswer().equals(q.getUserAnswer())) {
			} else {
				Bus.recordErr(q);
				i--;
			}
		}
		// 计分
		int score = 100 * Bus.newQs.size()
				/ (Bus.newQs.size() + Bus.errQs.size());
		JOptionPane.showMessageDialog(null, score + "", "分数",
				JOptionPane.INFORMATION_MESSAGE);
		Bus.showErr();

		// 显示错题
		F4ShowErr f4 = new F4ShowErr(this);
		f4.setVisible(true);
		this.setEnabled(false);
	}

	/**
	 * Create the frame.
	 */
	public F3QA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		chckbxA = new JCheckBox("A");
		chckbxA.setBounds(96, 167, 149, 29);
		contentPane.add(chckbxA);

		chckbxB = new JCheckBox("B");
		chckbxB.setBounds(96, 203, 149, 29);
		contentPane.add(chckbxB);

		chckbxC = new JCheckBox("C");
		chckbxC.setBounds(96, 245, 149, 29);
		contentPane.add(chckbxC);

		chckbxD = new JCheckBox("D");
		chckbxD.setBounds(96, 291, 149, 29);
		contentPane.add(chckbxD);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setLineWrap(true);
		textArea.setText("《西游记》是中国古代第一部浪漫主义章回体长篇神魔小说。该书以“唐僧取经”这一历史事件为蓝本，通过作者的艺术加工，深刻地描绘了当时的社会现实。主要描写了孙悟空出世，后遇见了唐僧、猪八戒和沙和尚三人，一路降妖伏魔，保护唐僧西行取经，经历了九九八十一难，终于到达西天见到如来佛祖，最终五圣成真的故事。");
		textArea.setBounds(38, 15, 405, 139);
		contentPane.add(textArea);

		btnSubmit = new JButton("提交");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recordAnswer(qId);
				submit();
			}
		});
		btnSubmit.setBounds(162, 423, 123, 29);
		contentPane.add(btnSubmit);

		btnPrevious = new JButton("上一题");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recordAnswer(qId);
				ShowQuestion(Bus.newQs.get(--qId));
			}
		});
		btnPrevious.setBounds(52, 365, 123, 29);
		contentPane.add(btnPrevious);

		btnNext = new JButton("下一题");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				recordAnswer(qId);
				ShowQuestion(Bus.newQs.get(++qId));
			}
		});
		btnNext.setBounds(296, 365, 123, 29);
		contentPane.add(btnNext);
		intiForm();
	}
}
