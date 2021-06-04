package org.closure.gcp.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colleges")
public class CollegeEntity {
    //test
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private int i;
    @Column(nullable = false)
    private String collegeName;

    @OneToMany(mappedBy = "college")
    private List<UserEntity> users;

    public CollegeEntity() {
    }

    public CollegeEntity(Integer id, String collegeName) {
        this.id = id;
        this.collegeName = collegeName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollegeName() {
        return this.collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public CollegeEntity id(Integer id) {
        setId(id);
        return this;
    }

    public CollegeEntity collegeName(String collegeName) {
        setCollegeName(collegeName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CollegeEntity)) {
            return false;
        }
        CollegeEntity collegeEntity = (CollegeEntity) o;
        return Objects.equals(id, collegeEntity.id) && Objects.equals(collegeName, collegeEntity.collegeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, collegeName);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", collegeName='" + getCollegeName() + "'" +
            "}";
    }

}
