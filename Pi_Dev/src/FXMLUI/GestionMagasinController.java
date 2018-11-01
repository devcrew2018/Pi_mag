/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLUI;

import Entites.Feedback;
import Entites.Product_Store;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import services.Product_Store_service;
import javafx.scene.control.IndexedCell;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import services.Feedback_service;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class GestionMagasinController implements Initializable {

    @FXML
    private HBox parent_hbox;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private JFXButton logout_but_prod;
    @FXML
    private TableView<Product_Store> table_prod;
    @FXML
    private TableColumn<Product_Store,String > name_prod;
    @FXML
    private TableColumn<Product_Store, Integer> quantity_prod;
    @FXML
    private TableColumn<Product_Store, String> fabdate_prod;
    @FXML
    private TableColumn<Product_Store, String> expdate_prod;
    @FXML
    private TableColumn<Product_Store, Double> amount_prod;
    @FXML
    private TableColumn<Product_Store, String> cathegory_prod;
    @FXML
    private JFXTextField input_name_prod;
    @FXML
    private JFXDatePicker input_fab_date_prod;
    @FXML
    private JFXTextField input_quantity_prod;
    @FXML
    private JFXDatePicker input_exp_date_prod;
    @FXML
    private JFXButton reset_but_prod;
    @FXML
    private JFXButton save_but_prod;
    @FXML
    private JFXTextField input_amount_prod;
    @FXML
    private JFXComboBox<String> combo_cathegory_prod;
    
    private final ObservableList<String> Stores = FXCollections.observableArrayList("1","2");
    private final ObservableList<String> cathegories = FXCollections.observableArrayList("Protein", "AminoAcids","Creatine","Carbohydrates","Fibre and Essential fats","Weight management","food and snacks","accessories");
    @FXML
    private Label Prod_id;
    @FXML
    private TableColumn<Product_Store, Double> Price;
    @FXML
    private TableColumn<Product_Store, Integer> StoreID;
    @FXML
    private JFXComboBox<String> combo_Store_prod;
    @FXML
    private JFXTextField input_Price_prod;
    @FXML
    private Hyperlink Stats_hyperlink;
    @FXML
    private MenuItem deleteproducts;
    @FXML
    private MenuItem Editproduct;
    @FXML
    private VBox input_exp_date;
    @FXML
    private HBox input_amout_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        make_window_drageable();
        try {
            setColumnProperties();
        } catch (SQLException ex) {
            Logger.getLogger(GestionMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        combo_cathegory_prod.setItems(cathegories);
        combo_Store_prod.setItems(Stores);
        
        reset_but_prod.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                reset(event);
             }
    });
        
        save_but_prod.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 try {
                     saveProduct(event);
                 } catch (SQLException ex) {
                     Logger.getLogger(GestionMagasinController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
    });
        
    }    
    
    public String getCombo_cathegory_prod() {
        return (String) combo_cathegory_prod.getSelectionModel().getSelectedItem();
    }

    public String getInput_name_prod() {
        return input_name_prod.getText();
    }

    public LocalDate getInput_fab_date_prod() {
        return input_fab_date_prod.getValue();
    }

    public String getInput_quantity_prod() {
        return input_quantity_prod.getText();
    }

    public LocalDate getInput_exp_date_prod() {
        return input_exp_date_prod.getValue();
    }

    public String getInput_amount_prod() {
        return input_amount_prod.getText();
    }

    public String getCombo_Store_prod() {
        return  combo_Store_prod.getSelectionModel().getSelectedItem();
    }

    public String getInput_Price_prod() {
        return input_Price_prod.getText();
    }
    
    
    
    private void saveProduct(ActionEvent event) throws SQLException{
    	Boolean bool = null;
    	if(validate("Name", getInput_name_prod(), "[a-zA-Z]+") &&
    	   emptyValidation("Fabrication_date", input_fab_date_prod.getEditor().getText().isEmpty()) &&
           emptyValidation("expiration_date", input_exp_date_prod.getEditor().getText().isEmpty()) &&
           emptyValidation("Amount", input_amount_prod.getText().isEmpty()) &&
           emptyValidation("Quantity", input_quantity_prod.getText().isEmpty()) &&
    	   emptyValidation("Cathegory", getCombo_cathegory_prod() == null) ){
            
                
//                    List<Product_Store> products = table_prod.getSelectionModel().getSelectedItems();
//                        
                        Product_Store_service pss= new Product_Store_service();
//                        for (int i=0;i<products.size();i++)
//                        {  
//                            bool=pss.chercherproduitt(products.get(i).getId());
//                
//                        }
                        if(Prod_id.getText().equals("Prod ID:")){
                        
                                Product_Store product = new Product_Store();
        			product.setName(getInput_name_prod());
        			product.setQuantity(Integer.parseInt(getInput_quantity_prod()));
        			product.setFabrication_date(getInput_fab_date_prod().toString());
        			product.setExpiration_date(getInput_exp_date_prod().toString());
        			product.setAmount(Double.parseDouble(getInput_amount_prod()));
        			product.setCathegory(getCombo_cathegory_prod());
                                product.setPrice(Double.parseDouble(getInput_Price_prod()));
                                product.setStoreID(Integer.parseInt(getCombo_Store_prod()));

        			
        			
        			//Product_Store_service pss =new Product_Store_service();
                                pss.ajouterProduit(product);
        			
        			saveAlert(product);
                    
                        }
                        else {   
                        Product_Store p =new Product_Store();
    			p.setName(getInput_name_prod());
                        p.setPrice(Double.parseDouble(getInput_Price_prod()));
                        p.setQuantity(Integer.parseInt(getInput_quantity_prod()));
        		p.setFabrication_date(getInput_fab_date_prod().toString());
        		p.setExpiration_date(getInput_exp_date_prod().toString());
        		p.setAmount(Double.parseDouble(getInput_amount_prod()));
        		p.setCathegory(getCombo_cathegory_prod());
                        p.setStoreID(Integer.parseInt(getCombo_Store_prod()));
    			
                        pss.modifierProduct(p,Integer.parseInt(Prod_id.getText()));
    			updateAlert(p);
                        }
                        
        }
                    
    		
    		
    		clearFields();
                setColumnProperties();
    		//loadUserDetails();
    	
        }
    


        private void setColumnProperties() throws SQLException{
                Product_Store_service pss= new Product_Store_service();
                ArrayList<Product_Store> products = (ArrayList<Product_Store>)pss.getAllProducts();
                ObservableList obs = FXCollections.observableArrayList(products);
                table_prod.setItems(obs); 
		
                
		//id_prod.setCellValueFactory(new PropertyValueFactory<>("id"));
		name_prod.setCellValueFactory(new PropertyValueFactory<>("name"));
		quantity_prod.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		fabdate_prod.setCellValueFactory(new PropertyValueFactory<>("Fabrication_date"));
		expdate_prod.setCellValueFactory(new PropertyValueFactory<>("Expiration_date"));
		amount_prod.setCellValueFactory(new PropertyValueFactory<>("Amount"));
		cathegory_prod.setCellValueFactory(new PropertyValueFactory<>("cathegory"));
                Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
                StoreID.setCellValueFactory(new PropertyValueFactory<>("StoreID"));
		//colEdit.setCellFactory(cellFactory);
                
                
	}


@FXML
    private void deleteproducts(ActionEvent event) throws SQLException{
    	List<Product_Store> products = table_prod.getSelectionModel().getSelectedItems();
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
                    for (int i=0; i<products.size();i++){
                    Product_Store_service pss=new  Product_Store_service();
                    pss.SupprimerProduit(products.get(i));
                    }
                }
    	
    	setColumnProperties();
    }






    
    private void saveAlert(Product_Store p){
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Product saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The Product "+p.getName()+ " has been created.");
		alert.showAndWait();
	}
    
    private void updateAlert(Product_Store p){
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Product updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The Product "+p.getName()+ " has been updated.");
		alert.showAndWait();
	}
        
        
        private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }
        
        
        private void validationAlert(String field, boolean empty){
		Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}
    
    void reset(ActionEvent event) {
    	clearFields();
    }
    
    private void clearFields() {
                Prod_id.setText("Prod ID:");
		input_name_prod.clear();
		input_quantity_prod.clear();
		input_fab_date_prod.getEditor().clear();
                input_exp_date_prod.getEditor().clear();
		combo_cathegory_prod.getSelectionModel().clearSelection();
                combo_Store_prod.getSelectionModel().clearSelection();
		input_amount_prod.clear();
                input_Price_prod.clear();
	}
    
    private void make_window_drageable() {
        parent_hbox.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent_hbox.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Launch.stage.setX(event.getScreenX() - xOffset);
                Launch.stage.setY(event.getScreenY() - yOffset);
                Launch.stage.setOpacity(0.7f);
            }
        });
        parent_hbox.setOnDragDone((e) -> {
            Launch.stage.setOpacity(1.0f);
        });
        parent_hbox.setOnMouseReleased((e) -> {
            Launch.stage.setOpacity(1.0f);
        });

    }
@FXML
    private void Editproduct(ActionEvent event) throws SQLException {
        
        List<Product_Store> p = table_prod.getSelectionModel().getSelectedItems();
        //updateUser(p);
        for (int i=0;i<p.size();i++) {
        
                Prod_id.setText(p.get(i).getId()+"");
                input_name_prod.setText(p.get(i).getName());
                input_quantity_prod.setText(p.get(i).getQuantity()+"");
                input_Price_prod.setText(p.get(i).getPrice()+"");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

                LocalDate localDate = LocalDate.parse(p.get(i).getFabrication_date(),formatter);
                LocalDate localDate2 = LocalDate.parse(p.get(i).getExpiration_date(),formatter);
                input_fab_date_prod.setValue(localDate);
                input_exp_date_prod.setValue(localDate2);
                input_amount_prod.setText(p.get(i).getAmount()+"");
                combo_cathegory_prod.getSelectionModel().select(p.get(i).getCathegory());
                combo_Store_prod.getSelectionModel().select(p.get(i).getStoreID());
                                    
                }
                        
                        
    }
                    
//    private void updateUser(List<Product_Store> p) { 
//        
//                for (int i=0;i<p.size();i++) {
//        
//                Prod_id.setText(p.get(i).getId()+"");
//                input_name_prod.setText(p.get(i).getName());
//                input_quantity_prod.setText(p.get(i).getQuantity()+"");
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
//
//                LocalDate localDate = LocalDate.parse(p.get(i).getFabrication_date(),formatter);
//                LocalDate localDate2 = LocalDate.parse(p.get(i).getExpiration_date(),formatter);
//                input_fab_date_prod.setValue(localDate);
//                input_exp_date_prod.setValue(localDate2);
//                input_amount_prod.setText(p.get(i).getAmount()+"");
//                combo_cathegory_prod.getSelectionModel().select(p.get(i).getCathegory());
//                                    
//                }
//	}

    @FXML
    private void open_Stats(ActionEvent event) throws IOException, SQLException {
    DefaultPieDataset dataSet=null;
        
        Feedback_service fs=new Feedback_service();
        ArrayList<Feedback> tops = (ArrayList<Feedback>)fs.gettoprated();
        ArrayList<Feedback> lows = (ArrayList<Feedback>)fs.getlowrated();
        
    dataSet = new DefaultPieDataset();
    dataSet.setValue("top rated product: " + tops.get(0).getName(),     tops.get(0).getRating());
    dataSet.setValue("lowest rated product: "+ lows.get(0).getName(),     lows.get(0).getRating());
    
        

     JFreeChart chart=ChartFactory.createPieChart(
      "Ratings", dataSet, true, true, false
    );
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     int screenHeight = screenSize.height;
     int screenWidth = screenSize.width;
     
    
     ChartFrame frame=new ChartFrame("bar dfsdfts",chart);
                 frame.setVisible(true);
                 frame.setLocation(480,270);
                 frame.setSize(screenWidth/2, screenHeight/2);
    }
    

   
    
    
}
