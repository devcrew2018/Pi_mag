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
public class User {
    private int id ; 
    private String fullname ; 
    private String email ; 
    private String phone ; 
    private String dateadhesion ;
    private String role ; 
    private String specialite ;
    private String sexe ;
    public User() {
    }

    public User(String fullname, String email, String phone, String dateadhesion, String role, String specialite, String sexe ) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.dateadhesion = dateadhesion;
        this.role = role;
        this.specialite = specialite;
        this.sexe = sexe;
    }

    public User(String fullname, String email, String phone, String role, String sexe) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.sexe = sexe;
    }

    public User(String fullname, String email, String phone, String dateadhesion, String role, String sexe) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.dateadhesion = dateadhesion;
        this.role = role;
        this.sexe = sexe;
    }

    public User(int id, String fullname, String email, String phone, String dateadhesion, String role) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.dateadhesion = dateadhesion;
        
        this.role = role;
    }

    public User( String fullname, String email, String phone, String role ) {
  
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.role=role ;
    }

    /**
     *
     * @param id
     * @param fullname
     * @param email
     * @param phone
     * @param dateadhesion
     */
    public User(int id, String fullname, String email, String phone, String dateadhesion) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.dateadhesion = dateadhesion;
    }

    public User(int id, String fullname, String email, String phone) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", dateadhesion=" + dateadhesion + ", role=" + role + ", sexe=" + sexe + '}';
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    
    



    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public int getId() {
        return id;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSexe() {
        return sexe;
    }



    public String getFullname() {
        return fullname;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
