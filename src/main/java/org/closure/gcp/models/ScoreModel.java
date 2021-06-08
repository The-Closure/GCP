package org.closure.gcp.models;

import java.util.Date;
import java.util.Objects;



public class ScoreModel {
    private int id;
    private UserModel user;
    private String score;
    private Date created_at;

    public ScoreModel() {
    }

    public ScoreModel(int id, UserModel user, String score, Date created_at) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.created_at = created_at;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public ScoreModel id(int id) {
        setId(id);
        return this;
    }

    public ScoreModel user(UserModel user) {
        setUser(user);
        return this;
    }

    public ScoreModel score(String score) {
        setScore(score);
        return this;
    }

    public ScoreModel created_at(Date created_at) {
        setCreated_at(created_at);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ScoreModel)) {
            return false;
        }
        ScoreModel serviceModel = (ScoreModel) o;
        return id == serviceModel.id && Objects.equals(user, serviceModel.user) && Objects.equals(score, serviceModel.score) && Objects.equals(created_at, serviceModel.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, score, created_at);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", score='" + getScore() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            "}";
    }

}
