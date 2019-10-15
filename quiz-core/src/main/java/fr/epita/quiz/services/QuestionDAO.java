package fr.epita.quiz.services;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.quiz.datamodel.Question;

@Repository
@Transactional
public class QuestionDAO extends DAO<Question>{

	@Override
	protected String getQueryString() {
		return "from Question q where q.questionContent like :pContent";
	}

	@Override
	protected void fillParametersMap(Map<String,Object> map, Question question) {
		map.put("pContent", question.getQuestionContent());
	}

}