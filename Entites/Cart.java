/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Objects;

/**
 *
 * @author Walid
 */
public class Cart {
    private String Name;
    private Double Price;
    private Integer quantity;
    private Integer id;

    public Cart() {
    }

    public Cart(String Name, Double Price, Integer quantity,Integer id) {
        this.Name = Name;
        this.Price = Price;
        this.quantity = quantity;
        this.id=id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cart{" + "Name=" + Name + ", Price=" + Price + ", quantity=" + quantity +", id=" + id+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cart other = (Cart) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
