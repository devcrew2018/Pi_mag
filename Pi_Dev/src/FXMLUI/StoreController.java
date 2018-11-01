/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLUI;

import Entites.Cart;
import Entites.Feedback;
import Entites.Product_Store;
import static FXMLUI.Launch.stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;
import services.Cart_service;
import services.Feedback_service;
import services.Product_Store_service;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class StoreController implements Initializable {

    @FXML
    private HBox parent_hbox;
    @FXML
    private Hyperlink showcart_map;
    @FXML
    private JFXButton logout_but_prod;
    @FXML
    private TreeView<String> TreeView;
    @FXML
    private Hyperlink showmap_but;
    
    private final Node rootIcon = 
        new ImageView(new Image(getClass().getResourceAsStream("/img/product.png")));
    @FXML
    private VBox vbox_tree;
    @FXML
    private MenuItem AddtoCart;
    @FXML
    private MenuItem Feedback_menu;
    @FXML
    private JFXTextArea giant_area_feedback;
    @FXML
    private Rating rating;
    @FXML
    private Label name_var;
    @FXML
    private JFXButton Submit_rating;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            treeviewsample();
        } catch (SQLException ex) {
            Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     
    }    
    
    public void treeviewsample() throws SQLException{
//        
        TreeItem<String> rootNode = 
        new TreeItem<String>("List of product: ", rootIcon);
        rootNode.setExpanded(true);
        
        
        Product_Store_service pss= new Product_Store_service();
        ArrayList<Product_Store> products = (ArrayList<Product_Store>)pss.getAllProducts();
        
        
        for (int i=0; i<products.size();i++) {
            TreeItem<String> prodNode = new TreeItem<String>(products.get(i).getName());
            
            
            TreeItem<String> PriceLeaf = new TreeItem<String>("Price: " +products.get(i).getPrice());
            TreeItem<String> AmountLeaf = new TreeItem<String>("Amount: " +products.get(i).getAmount());
            
            //TreeIterator<String> iterator = new TreeIterator<>(rootItem);
            
            
            boolean found = false;
            boolean found2 = false;
            
            
           
            for (TreeItem<String>  CathegoryNode : rootNode.getChildren()) {
                if (CathegoryNode.getValue().contentEquals(products.get(i).getCathegory()) ){
                    for (TreeItem<String>  C :CathegoryNode.getChildren()){
                    if (!C.getValue().contentEquals(products.get(i).getName())) {
////                    CathegoryNode.getChildren().add(prodNode);
////                    prodNode.getChildren().addAll(PriceLeaf,AmountLeaf);
                        found2=true;
//                    //break;
                
            
                
                }}
                    found=true;
                }
            }
            
            if (!found  ) {
                TreeItem<String> depNode = new TreeItem<String>(
                    products.get(i).getCathegory()
                    //new ImageView(depIcon)
                );
                prodNode.getChildren().addAll(PriceLeaf,AmountLeaf);
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(prodNode);
            }
            else if (found2){
                        for (TreeItem<String> CathegoryNode : rootNode.getChildren()) {
                            CathegoryNode.getChildren().add(prodNode);
                            prodNode.getChildren().addAll(PriceLeaf,AmountLeaf);
                        
                        }
                }
            
            
            
            
        }
        TreeView.setRoot(rootNode);
    }

    @FXML
    private void GotoCart(ActionEvent event) throws IOException {
        String url="Cart.fxml";
        Parent root;
        root = FXMLLoader.load(getClass().getResource(url));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void ShowMap(ActionEvent event) throws IOException {
        String url="/FXMLUI/Googlemaps.fxml";
         
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
        stage.setScene(scene);
        stage.show();
        
        
    }

    @FXML
    private void AddtoCart(ActionEvent event) throws SQLException {
       TreeItem<String> selecteditems =  TreeView.getSelectionModel().getSelectedItem();
        
        String val=selecteditems.getValue();
        Product_Store_service css=new Product_Store_service();
        Cart_service cs=new Cart_service();
        List <Product_Store> products= css.getp(val);   
        List <Cart> carts= cs.getCarts(val);
        
        if (carts.isEmpty()) {
                Cart c=new Cart(products.get(0).getName(),products.get(0).getPrice(),1,1);
                Cart_service cs1=new Cart_service();
                cs1.ajouter(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("You have Successfully added this product to your cart");
                alert.showAndWait();
            
        }
        else 
        {
            for (int j=0;j<carts.size();j++){
                if (!carts.get(j).getName().equals(products.get(0).getName())){
                Cart c=new Cart(products.get(0).getName(),products.get(0).getPrice(),1,1);
                Cart_service cs1=new Cart_service();
                cs1.ajouter(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("You have Successfully added this product to your cart");
                alert.showAndWait();               
                } 
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validation Error");
                    alert.setHeaderText(null);
                    alert.setContentText("You have Already added this product to cart");
                    alert.showAndWait();
                }
            }
        
    }
    }

    @FXML
    private void Feedback_menu(ActionEvent event) {
        TreeItem<String> selecteditems =  TreeView.getSelectionModel().getSelectedItem();
        
        name_var.setText(selecteditems.getValue());
        rating.setVisible(true);
        giant_area_feedback.setVisible(true);
        Submit_rating.setVisible(true);
        
    }

    @FXML
    private void Submit_rating(ActionEvent event) throws SQLException {
        String val=name_var.getText();
        Product_Store_service css=new Product_Store_service();
        List <Product_Store> products= css.getp(val);
        //Feedback f=new Feedback();
        rating.setPartialRating(true);
        rating.setUpdateOnHover(true);
        //rating.getRating();
        //giant_area_feedback.getText();
        Feedback_service fs= new Feedback_service();
         List <Feedback> feeds= fs.getAllFeeds();
        
        for (int i=0;i<feeds.size();i++){
            if (feeds.get(i).getId()==products.get(0).getId() && feeds.get(i).getUser_id()==1 ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("You have have already rated this product");
                alert.showAndWait(); 
            }
            else {
                Feedback f=new Feedback(products.get(0).getId(),val,rating.getRating(),giant_area_feedback.getText(),1);
                fs.ajouter(f);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("You have Successfully rated this product");
                alert.showAndWait(); 
            }
        }
        
    }
}    


    
        
        
    


    

