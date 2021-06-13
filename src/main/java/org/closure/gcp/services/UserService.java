package org.closure.gcp.services;

import java.util.Optional;

import org.closure.gcp.entities.UserEntity;
import org.closure.gcp.exceptions.UserException;
import org.closure.gcp.mappers.UserMapper;
import org.closure.gcp.models.Gender;
import org.closure.gcp.models.UserModel;
import org.closure.gcp.repositories.CollegeRepo;
import org.closure.gcp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    CollegeRepo collegeRepo;

    public UserModel registerUser(UserModel user) throws UserException 
    {
        if(user.getEmail() != null && user.getPassword() != null && user.getUsername() != null)
        {
            if(userRepo.findByEmail(user.getEmail()).isEmpty())
            {
                UserEntity entity = UserMapper.INSTANCE.UserModelToUserEntity(user);
                entity.setCollege(collegeRepo.findByCollegeName(entity.getCollege().getCollegeName()).get());
                userRepo.save(entity);
                //TODO mapper from entity to model
                return user;
            }else{
                throw new UserException("this email is already exist");
            }
        }else{
            throw new UserException("required fields are null");
        }


    }

    public UserModel signIn(UserModel user) throws UserException
    {
        Optional<UserEntity> entity;
        if((entity = userRepo.findByEmail(user.getEmail())).isEmpty())
        {
            throw new UserException("no user with this email");
        }else{
            if(entity.get().getPassword().equals(user.getPassword()))
            {
                return UserMapper.INSTANCE.UserEntityToUserModel(entity.get());
            }else{
                throw new UserException("wrong password");
            }

        }
    }


    public UserEntity UserModelToUserEntity(UserModel user)
    {
        return new UserEntity()
            .withUsername(user.getUsername())
            .withEmail(user.getEmail())
            .withPassword(user.getPassword())
            .withAddress(user.getAddress())
            .withBirthday(user.getBirthday())
            .withGender(Gender.valueOf(user.getGender() != null ? user.getGender() : "male"))
            .withCollege(
                collegeRepo.findByCollegeName(
                    user.getCollege()
                    ).get()
                );
                //TODO fix college mapper
    }

    // public UserModel userEntityToUserModel(UserEntity user)
    // {
    //     return new UserModel()
    //         .withId(user.getId())
    //         .withUsername(user.getUsername())
    //         .withEmail(user.getEmail())
    //         .withAddress(user.getAddress())
    //         .withBirthday(user.getBirthday())
    //         .withGender(user.getGender().toString())
    //         .withPassword(user.getPassword());
    // }

    
}
