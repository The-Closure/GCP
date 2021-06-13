package org.closure.gcp.models;

import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer id;

    private String username;

    private String email;

    private String password;

    private Date birthday;

    
    private String address; 

    private String college;

    private String gender;


}
