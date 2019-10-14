package fr.epita.quiz.services;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import fr.epita.quiz.datamodel.Question;

@Repository
public class QuestionDAO extends DAO<Question> {

	public QuestionDAO()
	{
		this.setClass(Question.class);
	}
	
	@Override
	public List<Question> search (Question criteria)
	{
		
		Session session = this.getSession();
	    String searchQuery = "from Question q where q.questionContent like :content";
		org.hibernate.query.Query<Question> query = session.createQuery(searchQuery, Question.class);
		query.setParameter("content", criteria.getQuestionContent());
		return query.list();
	}
}