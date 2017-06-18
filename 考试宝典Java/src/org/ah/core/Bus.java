package org.ah.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.ah.core.bean.Question;
import org.ah.dao.QuestionDao;

public class Bus {

	/**
	 * 随机取题
	 * 
	 * @param sourceQs题集源
	 * @param count取多少道题
	 * @return 新题集
	 */
	public static List<Question> extractQuestions(List<Question> sourceQs,
			int count) {
		Random r = new Random();
		int n随机数 = -1;
		List<Question> newQs = new ArrayList<Question>();

		for (int i = 1; i <= count; i++) {
			n随机数 = r.nextInt(sourceQs.size());
			Question q选中的题 = sourceQs.get(n随机数);
			newQs.add(q选中的题);
			sourceQs.remove(n随机数);// 防止重复选中同一道题
		}
		return newQs;
	}

	public static List<Question> errQs = new ArrayList<Question>();
	public static List<Question> newQs = new ArrayList<Question>();// 提取出来

	/**
	 * 记录错题
	 * 
	 * @param q
	 */
	public static void recordErr(Question q) {
		errQs.add(q);
		newQs.remove(q);
	}

	/**
	 * 统计分数
	 */
	public static void calcScore() {
		int score = 100 * newQs.size() / (newQs.size() + errQs.size());
		System.out.println("得分：" + score);
	}

	public static void showErr() {
		for (Question q : errQs) {
			System.out.println(q.getId() + "." + q.getTitle());
			System.out.println("\tA." + q.getOptA());
			System.out.println("\tB." + q.getOptB());
			System.out.println("\tC." + q.getOptC());
			System.out.println("\tD." + q.getOptD());
			System.out.println("正确答案：" + q.getAnswer());
			System.out.println("您的答案：" + q.getUserAnswer());

		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		QuestionDao dao = new QuestionDao();
		List<Question> qs全部題目 = dao.getAllQuestions();

		// 随机取题
		newQs = extractQuestions(qs全部題目, 5);
		int newId = 1;
		for (int i = 0; i < newQs.size(); i++) {
			Question q = newQs.get(i);
			// 列表在变，此语句无效 for (Question q : newQs) {
			q.setId(newId++);
			System.out.println(q.getId() + "." + q.getTitle());
			System.out.println("\tA." + q.getOptA());
			System.out.println("\tB." + q.getOptB());
			System.out.println("\tC." + q.getOptC());
			System.out.println("\tD." + q.getOptD());

			String _userAnswer = sc.next();

			// 判斷
			if (_userAnswer.toUpperCase().equals(q.getAnswer())) {
			} else {
				q.setUserAnswer(_userAnswer.toUpperCase());
				recordErr(q);
				i--;
			}
		}

		System.out.println("答题结束");
		calcScore();
		System.out.println("输入任意键查看错题");
		System.in.read();
		sc.close();
		showErr();
	}
}
