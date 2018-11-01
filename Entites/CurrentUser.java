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
public class CurrentUser {
    public static  int id ; 
    public static String fullname , email ,   phone,   dateadhesion,   role,  specialite, sexe ;
    public CurrentUser() { 
        
        
    }

    @Override
    public String toString() {
        return "CurrentUser{" + id+fullname+email+phone+role+specialite+sexe + '}';
    }

public CurrentUser(User u) { 
    id = u.getId() ;
    fullname = u.getFullname() ;
    email=u.getEmail() ;
    phone=u.getPhone(); 
    role=u.getRole() ;
    specialite=u.getSpecialite() ;
    sexe = u.getSexe();
}    
}
