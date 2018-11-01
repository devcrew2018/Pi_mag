/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLUI;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Walid
 */
public class GooglemapsController implements Initializable,MapComponentInitializedListener {

    
 
    private GoogleMap map;
    @FXML
    private GoogleMapView webView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         webView.addMapInializedListener(this);
    }    
    
    @Override
    public void mapInitialized() {
        LatLong Adfitness1 = new LatLong(36.874720,10.244803);
        LatLong Adfitness2 = new LatLong(36.835543, 10.245854 );
        
        
        //geocodingService = new GeocodingService();
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(Adfitness1)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = webView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(Adfitness1);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(Adfitness2);
        
       
        
        Marker Adfitness1Marker = new Marker(markerOptions1);
        Marker Adfitness2Marker = new Marker(markerOptions2);
        
        
        map.addMarker( Adfitness1Marker );
        map.addMarker( Adfitness2Marker );
        
        
        /*InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Fred Wilkie"
                                + "Current Location: Safeway"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);*/
    } 
}
