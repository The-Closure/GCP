package org.closure.gcp.services;

import java.rmi.server.UID;
import java.util.Date;

import org.closure.gcp.entities.ScoreEntity;
import org.closure.gcp.entities.UserEntity;
import org.closure.gcp.models.ScoreModel;
import org.closure.gcp.repositories.ScoreRepo;
import org.closure.gcp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    ScoreRepo scoreRepo;

    @Autowired
    UserRepo userRepo;

    UserService userService;

    public ScoreModel createScore(ScoreModel scoreModel, int uId) throws Exception
    {
        UserEntity userEntity = userRepo.findById(uId)
            .orElseThrow(()-> new Exception("no user with this id"));
        
        ScoreEntity scoreEntity=scoreRepo.save(new ScoreEntity()
        .id(scoreModel.getId())
        .user(userRepo.findById(uId).get())
        .score(scoreModel.getScore())
        .created_at(new Date()));
        //userService.userEntityToUserModel(userRepo.findById(uId)))

        return new ScoreModel()
        .id(scoreEntity.getId())
        .user(scoreModel.getUser())
        .score(scoreEntity.getScore())
        .created_at(scoreEntity.getCreated_at());
    }

     
    public ScoreModel readScore (Integer id) throws Exception{
        ScoreEntity entity = scoreRepo.findById(id)
        .orElseThrow(()->new Exception("no Score with this id"));
        return new ScoreModel()
        .id(entity.getId())
        //.user(scoreModel.getUser())
        .score(entity.getScore())
        .created_at(entity.getCreated_at());
    }



    public ScoreModel updateScore(ScoreModel scoreModel, int uId) throws Exception
    {
        ScoreEntity entity = scoreRepo.findById(scoreModel.getId()).orElseThrow(()->new Exception("no Score with this id"));
        ScoreEntity scoreEntity=scoreRepo.save(new ScoreEntity()
        .id(scoreModel.getId())
        .user(userRepo.findById(uId).get())
        .score(scoreModel.getScore())
        .created_at(new Date()));
        

        return new ScoreModel()
        .id(scoreEntity.getId())
        .user(scoreModel.getUser())
        .score(scoreEntity.getScore())
        .created_at(scoreEntity.getCreated_at());
        
    }



public boolean deleteScore(Integer id) throws Exception
    {
         ScoreEntity entity=scoreRepo.findById(id)
        .orElseThrow(()->new Exception("no Score with this id"));

        scoreRepo.deleteById(id);

        

        return true;

    }

}


