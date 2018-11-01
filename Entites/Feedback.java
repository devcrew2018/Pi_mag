/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Walid
 */
public class Feedback {
    private int product_id;
    private String name;
    private Double rating;
    private String feedback;
    private int user_id;

    /**
     *
     * @param id
     * @param name
     * @param rating
     * @param feedback
     * @param user_id
     */
    public Feedback(int product_id, String name, Double rating, String feedback,int user_id) {
        this.product_id = product_id;
        this.name = name;
        this.rating = rating;
        this.feedback = feedback;
        this.user_id=user_id;
    }

    public int getId() {
        return product_id;
    }

    public void setId(int id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + product_id + ", name=" + name + ", rating=" + rating + ", feedback=" + feedback + '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    
}
