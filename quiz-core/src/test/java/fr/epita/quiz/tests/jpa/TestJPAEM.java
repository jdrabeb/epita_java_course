package fr.epita.quiz.tests.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.QuestionDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@Transactional
public class TestJPAEM {

	@PersistenceContext
	EntityManager em;
	
	@Autowired private QuestionDAO questionDAO;
	
	@Test
	@Transactional
	public void testCreate() {
		String questionContent = "What is Dependency Injection ?";
		Question question = new Question(questionContent);

		questionDAO.create(question);
		//persistQuestion(question);
		
		Question retrievedQuestion = em.find(Question.class, question.getId());
		
		Assert.assertNotNull(retrievedQuestion);
		Assert.assertEquals(questionContent, retrievedQuestion.getQuestionContent());	
	}
	
	@Test
	public void testDelete() {
		
		Question question = new Question("What is Dependency Injection ?");
		questionDAO.create(question);
		
		questionDAO.delete(question);

		Question retrievedQuestion = questionDAO.getById(question.getId(), Question.class);
		Assert.assertEquals(retrievedQuestion, null);
	}
	
	@Test
	public void testUpdate() {
		
		String questionContentModified = "How to define a parent pom in maven?";
		Question question = new Question("How to define a property in a maven pom file?");
		
		questionDAO.create(question);
		question.setQuestionContent(questionContentModified);
		questionDAO.update(question);
		
		Question retrievedQuestion = questionDAO.getById(question.getId(), Question.class);
		Assert.assertEquals(questionContentModified,retrievedQuestion.getQuestionContent());
	}
	
	@Test
	public void testSearch() {
		
		Question question = new Question("What is Dependency Injection ?");			
		questionDAO.create(question);
		
		
		List<Question> results = questionDAO.search(question);
		Assert.assertTrue(!results.isEmpty());
	}	
}