package org.ah.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.ah.core.bean.Question;

public class QuestionDao extends BaseDao {
	public static void main(String[] args) {
		QuestionDao dao = new QuestionDao();
		dao.getAllQuestions();
	}

	public List<Question> getAllQuestions() {
		List<Question> lst = new ArrayList<Question>();
		super.getConn();
		try {
			PreparedStatement pStat = super.conn
					.prepareStatement("SELECT * FROM question");
			ResultSet rs = pStat.executeQuery();
			while (rs.next()) {
				int nId = rs.getInt(1);
				String sTitle = rs.getString(2);
				String sA = rs.getString(3);
				String sB = rs.getString(4);
				String sC = rs.getString(5);
				String sD = rs.getString(6);
				String sAnser = rs.getString(7);

				// 使用对象封装数据
				Question q = new Question(nId, sTitle, sA, sB, sC, sD, sAnser);

				lst.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeConn();
		}
		return lst;
	}
}
