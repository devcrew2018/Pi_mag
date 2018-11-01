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
public class Product_Store {
    
   
    
    private int id;
    private String name;
    private double Price;
    private int quantity;
    private String Fabrication_date;
    private String Expiration_date;
    private double Amount;
    private String cathegory;
    private int StoreID;

    public Product_Store() {
    }

    public Product_Store(String name, double Price, int quantity, String Fabrication_date, String Expiration_date, double Amount, String cathegory, int StoreID) {
        this.name = name;
        this.Price = Price;
        this.quantity = quantity;
        this.Fabrication_date = Fabrication_date;
        this.Expiration_date = Expiration_date;
        this.Amount = Amount;
        this.cathegory = cathegory;
        this.StoreID = StoreID;
    }

    public Product_Store(int id, String name, double Price, int quantity, String Fabrication_date, String Expiration_date, double Amount, String cathegory, int StoreID) {
        this.id = id;
        this.name = name;
        this.Price = Price;
        this.quantity = quantity;
        this.Fabrication_date = Fabrication_date;
        this.Expiration_date = Expiration_date;
        this.Amount = Amount;
        this.cathegory = cathegory;
        this.StoreID = StoreID;
    }

    
    public Product_Store(int id, String name, int quantity, String Fabrication_date, String Expiration_date, double Amount, String cathegory) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.Fabrication_date = Fabrication_date;
        this.Expiration_date = Expiration_date;
        this.Amount = Amount;
        this.cathegory = cathegory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFabrication_date() {
        return Fabrication_date;
    }

    public void setFabrication_date(String Fabrication_date) {
        this.Fabrication_date = Fabrication_date;
    }

    public String getExpiration_date() {
        return Expiration_date;
    }

    public void setExpiration_date(String Expiration_date) {
        this.Expiration_date = Expiration_date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getCathegory() {
        return cathegory;
    }

    public void setCathegory(String cathegory) {
        this.cathegory = cathegory;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    @Override
    public String toString() {
        return "Product_Store{" + "id=" + id + ", name=" + name + ", Price=" + Price + ", quantity=" + quantity + ", Fabrication_date=" + Fabrication_date + ", Expiration_date=" + Expiration_date + ", Amount=" + Amount + ", cathegory=" + cathegory + ", StoreID=" + StoreID + '}';
    }

    
    

    
    
    
}
