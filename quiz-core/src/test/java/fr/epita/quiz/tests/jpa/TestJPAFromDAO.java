package fr.epita.quiz.tests.jpa;

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
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.DAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestJPAFromDAO {
	
	@Inject
	DAO<Question> questionDAO;
	
	@Test
	public void testCreate() {
		
		Question question = new Question("What is Dependency Injection ?");
				
		questionDAO.create(question);
		
		Question retrievedQuestion = questionDAO.getById(question.getId());
		Assert.assertEquals(question.getQuestionContent(),retrievedQuestion.getQuestionContent());
	}
	
	@Test
	public void testDelete() {
		
		Question question = new Question("What is Dependency Injection ?");
		questionDAO.create(question);
		
		questionDAO.delete(question);

		Question retrievedQuestion = questionDAO.getById(question.getId());
		Assert.assertEquals(retrievedQuestion, null);
		
	}
	
	@Test
	public void testUpdate() {
		
		String questionContentModified = "How to define a parent pom in maven?";
		Question question = new Question("How to define a property in a maven pom file?");
		
		questionDAO.create(question);
		question.setQuestionContent(questionContentModified);
		questionDAO.update(question);
		
		Question retrievedQuestion = questionDAO.getById(question.getId());
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
