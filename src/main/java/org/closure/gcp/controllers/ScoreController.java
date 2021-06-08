package org.closure.gcp.controllers;

import org.closure.gcp.models.ScoreModel;
import org.closure.gcp.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/score")
public class ScoreController {
   
    @Autowired
   ScoreService scoreService;


   @RequestMapping(value="/addscore/{id}", method=RequestMethod.POST)
   public Object requestMethodName(@RequestBody ScoreModel score,@PathVariable(name = "id") int id) throws Exception {
     try {
        return scoreService.createScore(score,id);
     } catch (Exception e) {
         return e.getMessage();
     }
   }

   @RequestMapping(value="/readscores/{id}",method=RequestMethod.GET)
   public Object readScore(@PathVariable(name = "id") Integer id) throws Exception
   {
    try {
        return scoreService.readScore(id);
    } catch (Exception e) {
        return e.getMessage();
    }
   }

   @RequestMapping(value="/updatescore/{id}",method=RequestMethod.PUT)
   public Object updateScore (@RequestBody ScoreModel scoreModel,@PathVariable(name = "id") int id) throws Exception
   {
    try {
       return scoreService.updateScore(scoreModel,id);
    } catch (Exception e) {
        return e.getMessage();
    }
   }


   @RequestMapping(value="/deletescore/{id}",method=RequestMethod.GET)
   public Object deleteScore(@PathVariable(name = "id") Integer id) throws Exception
   {
    try {

        scoreService.deleteScore(id);

        return true;
    } catch (Exception e) {
        return e.getMessage();
    }
       
   }




  
       
       
       

   


}
