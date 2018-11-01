/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLUI;

import Entites.Cart;
import Entites.PaymentServiceImpl;
import Entites.Product_Store;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.stripe.exception.StripeException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.Cart_service;
import services.Product_Store_service;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class CartController implements Initializable {

    @FXML
    private HBox parent_hbox;
    @FXML
    private JFXButton logout_but_prod;
    @FXML
    private Hyperlink Store;
    @FXML
    private TableView<Cart> Cart_table;
    @FXML
    private TableColumn<Cart, String> Product_name;
    @FXML
    private TableColumn<Cart, Double> Product_price;
    @FXML
    private TableColumn<Cart, Integer> product_quantity;
    @FXML
    private Label total_label;
    @FXML
    private JFXButton checkout_button;
    @FXML
    private MenuItem Edit_quantity;
    @FXML
    private MenuItem deletecart;
    @FXML
    private JFXTextField Edit_quantity_label;
    @FXML
    private JFXButton Edit;
    @FXML
    private Label name;
    @FXML
    private JFXTextField Card_num_input;
    @FXML
    private JFXComboBox<String> exp_month_box;
    @FXML
    private JFXComboBox<String> exp_year_box;
    @FXML
    private JFXTextField ccv_input;
    @FXML
    private JFXButton Validate_but;
    
    private final ObservableList<String> months = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
    private final ObservableList<String> years = FXCollections.observableArrayList("2018","2019","2020","2022","2023","2024","2025");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exp_month_box.setItems(months);
        exp_year_box.setItems(years);
        try {
            // TODO
            setColumnProperties();
        } catch (SQLException ex) {
            Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    

    @FXML
    private void open_checkout(ActionEvent event)  {
        Card_num_input.setVisible(true);
        exp_month_box.setVisible(true);
        exp_year_box.setVisible(true);
        ccv_input.setVisible(true);
        Validate_but.setVisible(true);
    }
    private void setColumnProperties() throws SQLException{
                Double total=0.0;
                Cart_service cs= new Cart_service();
                ArrayList<Cart> carts = (ArrayList<Cart>)cs.getAllCarts();
                ObservableList obs = FXCollections.observableArrayList(carts);
                Cart_table.setItems(obs); 
		
            
		Product_name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                Product_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		product_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		//colEdit.setCellFactory(cellFactory);
                
                for (int i=0 ;i<carts.size();i++){
                    
                    total=total + carts.get(i).getPrice()*carts.get(i).getQuantity();
                }
                total_label.setText(total.toString());
        
                
	}

    @FXML
    private void Edit_quantity(ActionEvent event) {
        List<Cart> p = Cart_table.getSelectionModel().getSelectedItems();
        //updateUser(p);
        
                Edit_quantity_label.setVisible(true);
                Edit.setVisible(true);
                Edit_quantity_label.setText(p.get(0).getQuantity()+"");    
                name.setText(p.get(0).getName());
                
    }

    @FXML
    private void deletecart(ActionEvent event) throws SQLException {
        List<Cart> carts = Cart_table.getSelectionModel().getSelectedItems();
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
                    for (int i=0; i<carts.size();i++){
                    Cart_service cs=new  Cart_service();
                    cs.Supprimercart(carts.get(i));
                    }
                }
    	
    	setColumnProperties();
    }

    @FXML
    private void Gobackto_Store(ActionEvent event) throws IOException {
        String url="/FXMLUI/Store.fxml";
        Parent root;
        root = FXMLLoader.load(getClass().getResource(url));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void Edit(ActionEvent event) throws SQLException {
        //Edit_quantity_label.getText();
        Cart_service cs= new Cart_service();
        Cart c=new Cart();
        c.setQuantity(Integer.parseInt(Edit_quantity_label.getText()));
        cs.modifierProduct(c, 1, name.getText());
        
        Edit_quantity_label.setVisible(false);
        Edit.setVisible(false);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("You have Successfully Edited this product");
                alert.showAndWait();
        
        setColumnProperties();
    }

    @FXML
    private void Valide_but(ActionEvent event) throws StripeException, SQLException, FileNotFoundException, DocumentException {
        
        if (emptyValidation("Card_num", Card_num_input.getText().isEmpty()) &&
           emptyValidation("exp_year", exp_year_box.getSelectionModel().getSelectedItem()==null) &&
    	   emptyValidation("exp_month", exp_month_box.getSelectionModel().getSelectedItem() == null) &&
           emptyValidation("ccv", ccv_input.getText().isEmpty()) )
                {
            
        
        
        
        
        PaymentServiceImpl ss=new PaymentServiceImpl();
        //Double Montant=Double.parseDouble(convert_string(total_label.getText()))*100;
        String s=total_label.getText();
        int a=(int) Double.parseDouble(s)*100;
        String Montant1=Integer.toString(a);
        ss.chargeCreditCard(Montant1,ss.createToken(Card_num_input.getText(), exp_month_box.getSelectionModel().getSelectedItem() , exp_year_box.getSelectionModel().getSelectedItem(), ccv_input.getText()));
        
        Cart_service cs=new Cart_service();
        ArrayList<Cart> carts = (ArrayList<Cart>) cs.getAllCarts();
         
        //PFE
         
        
        JOptionPane jop = new JOptionPane();
          String link="C:\\Users\\Walid\\Documents\\NetBeansProjects\\Ad_Fitness\\esm.pdf";
          String ad = jop.showInputDialog("Veuillez saisir l'emplacement du ficher !", link);
                Document doc=new Document();
               
                        PdfWriter.getInstance(doc, new FileOutputStream(ad));
               doc.open();
                   Font f=new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.UNDERLINE, BaseColor.RED);
                   Paragraph p=new Paragraph("ADFitness\n",f);
                   p.setAlignment(Element.ALIGN_CENTER);
                   doc.add(p);
Font f1=new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.UNDERLINE, BaseColor.RED);
                   Paragraph p1=new Paragraph("Reçu de paiement ",f1);
                   p1.setAlignment(Element.ALIGN_CENTER);
                   doc.add(p1);
                   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
String d=   dtf.format(now);
 Font f2=new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.UNDERLINE, BaseColor.BLACK);
 Paragraph p2=new Paragraph("\nDate: "+d,f2);
   doc.add(p2);
      doc.add(new Paragraph("Utilisateur:" ,f2));
      doc.add(new Paragraph(" "));
                   PdfPTable table = new PdfPTable(3);
                   table.setWidthPercentage(100);
        table.setSpacingBefore(0f);
        table.setSpacingAfter(0f);
                   Font f3=new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDEFINED, BaseColor.BLUE);
                   PdfPCell c1=new PdfPCell(new Phrase("Nom",f3));
                   c1.setPadding(4f);
                   table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Prix ",f3));
                     c1.setPadding(4f);     
                   table.addCell(c1);
                     c1=new PdfPCell(new Phrase("Quantité ",f3));
                     c1.setPadding(4f);
                   table.addCell(c1);
                   
                     
                  
                    for(int i=0;i<carts.size();i++)
                    {
                        String n=carts.get(i).getName();
                        Font f4=new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.UNDEFINED, BaseColor.BLACK);
                        c1=new PdfPCell(new Phrase(n,f4));
                        table.addCell(c1);
                        String r= carts.get(i).getPrice().toString();
                        c1=new PdfPCell(new Phrase(r,f4));
                        table.addCell(c1);
                        String dom=carts.get(i).getQuantity().toString();
                        c1=new PdfPCell(new Phrase(dom,f4));
                        table.addCell(c1);
                     
                    }
                   doc.add(table);
               
               doc.close();
         
                    

         for (int i=0; i<carts.size();i++){
            cs.Supprimercart(carts.get(i));
         }
         
         setColumnProperties();
        Card_num_input.setVisible(false);
        exp_month_box.setVisible(false);
        exp_year_box.setVisible(false);
        ccv_input.setVisible(false);
        Validate_but.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("You have Successfully purchased the items in the cart");
                alert.showAndWait();

    }
    }
    
    public String convert_string(String nn){
        String newstring = nn.replaceAll(java.util.regex.Pattern.quote("."), ",");
        return newstring;
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

}


