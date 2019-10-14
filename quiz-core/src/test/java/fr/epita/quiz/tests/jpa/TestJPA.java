package fr.epita.quiz.tests.jpa;
\
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestJPA {
	
	@Inject
	SessionFactory sessionFactory;
	
	@Inject
	fr.epita.quiz.services.QuestionDAO dao;
	
	@Test
	public void testHibernateSession() {
		//given sessionFactory
		
		//when
		Session session = sessionFactory.openSession();
		
		
		//then
		Assert.assertEquals(true,session.isConnected());
	}
	
	@Test
	public void testCreate() {
		//given sessionFactory
		String questionContent = "How to define a property in a maven pom file?";
		Question question = new Question(questionContent);
		
		//when
		Session session = sessionFactory.openSession();
		session.save(question);
		
		
		//then
		Question retrievedQuestion = session.get(Question.class, question.getId());
		Assert.assertEquals(questionContent,retrievedQuestion.getQuestionContent());
	}
	
	
	@Test
	public void testUpdate() {
		//given sessionFactory
		String questionContent = "How to define a property in a maven pom file?";
		String questionContentModified = "How to define a parent pom in maven?";
		Question question = new Question(questionContent);
		Session session = sessionFactory.openSession();
		session.save(question);
		
		//when
		question.setQuestionContent(questionContentModified);
		session.update(question);
		
		
		//then
		Question retrievedQuestion = session.get(Question.class, question.getId());
		Assert.assertEquals(questionContentModified,retrievedQuestion.getQuestionContent());
	}

	@Test
	public void testDelete() {
		//given sessionFactory
		String questionContent = "How to define a property in a maven pom file?";
		Question question = new Question(questionContent);
		Session session = sessionFactory.openSession();
		session.save(question);
		
		//when
		session.delete(question);
		//then
		Question retrievedQuestion = session.get(Question.class, question.getId());
		Assert.assertEquals(retrievedQuestion, null);
		}

	@Test
	public void testSearch() {
		//given sessionFactory
		String questionContent = "How to define a property in a maven pom file?";
		
		Question question = new Question(questionContent);
		Session session = sessionFactory.openSession();
		session.save(question);
		
		//when
		org.hibernate.query.Query<Question> query = session.createQuery("from Question q where q.questionContent like :pContent", Question.class);
		query.setParameter("pContent", "%maven%");
		List<Question> results = query.getResultList();
		
		//then
		List<String> notContainingMaven = new ArrayList<>();
		results.stream().forEach(q -> {
			if (q.getQuestionContent().contains("maven")){
				notContainingMaven.add("");
			}
		});
		Assert.assertTrue(notContainingMaven.isEmpty());
		}
}