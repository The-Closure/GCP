package org.closure.gcp.services;

import org.closure.gcp.entities.AnswerEntity;
import org.closure.gcp.entities.QuestionEntity;
import org.closure.gcp.exceptions.AnswerException;
import org.closure.gcp.models.AnswerModel;
import org.closure.gcp.models.QuestionModel;
import org.closure.gcp.repositories.AnswerRepo;
import org.closure.gcp.repositories.QuestionRepo;
import org.hibernate.cfg.QuerySecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    
    @Autowired
    AnswerRepo answerRepo;

    @Autowired
    QuestionRepo questionRepo;

    
    public AnswerModel createAnswer(AnswerModel answer)
    {
        AnswerEntity entity = answerRepo.save(
             new AnswerEntity().answer(answer.getAnswer())
                               .status(answer.getStatus()));
        return answer.id(entity.getId());
    }

    public AnswerModel createAnswerWithQuestion(AnswerModel answer, String question) throws AnswerException
    {
        QuestionEntity questionEntity = questionRepo
            .findByQuestion(question)
            .orElseThrow(
                ()-> new AnswerException("no question with this value"));
         AnswerEntity entity = answerRepo.save(
            new AnswerEntity().answer(answer.getAnswer())
                              .status(answer.getStatus()));
        return answer.id(entity.getId()).question(new QuestionModel().id(questionEntity.getId())
                                                                     .pionts(questionEntity.getpoints())
                                                                     .question(questionEntity.getQuestion()));
    }

    public AnswerModel ReadAnswer (int id) throws AnswerException
    {
        AnswerEntity answer = answerRepo
            .findById(id)
            .orElseThrow(
                ()-> new AnswerException("Unable to find an answer to this number ..."));
        
        QuestionEntity questionEntity = questionRepo.findByQuestion((answer.getQuestion()).getQuestion()).orElse(new QuestionEntity());
        return new AnswerModel().id(answer.getId())
                                .answer(answer.getAnswer())
                                .status(answer.getStatus())
                                .question(new QuestionModel().id(questionEntity.getId())
                                                             .pionts(questionEntity.getpoints())
                                                             .question(questionEntity.getQuestion()));
    }


public AnswerModel updateAnswer (int id) throws AnswerException
{
    AnswerEntity answer = answerRepo
            .findById(id)
            .orElseThrow(
                ()-> new AnswerException("Unable to find an answer to this number ..."));
     QuestionEntity questionEntity = questionRepo.findByQuestion((answer.getQuestion()).getQuestion()).orElse(new QuestionEntity());

         answer = answerRepo.save(
        new AnswerEntity().answer(answer.getAnswer())
                          .status(answer.getStatus()));
    return new AnswerModel().id(answer.getId()).question(new QuestionModel().id(questionEntity.getId())
                                                                 .pionts(questionEntity.getpoints())
                                                                 .question(questionEntity.getQuestion()));
}

public void DeleteAnswer(int id) throws AnswerException
    {
        AnswerEntity answer = answerRepo
        .findById(id)
        .orElseThrow(
            ()-> new AnswerException("Unable to find an answer to this number ..."));
            answerRepo.delete(answer);
            System.out.println("The answer was successfully deleted");
}

}