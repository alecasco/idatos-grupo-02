package controllers;


import domain.Contacto;
import domain.MercadoLibreProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MercadoLibreController {

    public List<MercadoLibreProperty> LoadAlojamientos(String idBarrio) throws IOException {
        List<MercadoLibreProperty> mercadoLibreProperties = new ArrayList<>();

        try {

            URL url = new URL("https://api.mercadolibre.com/sites/MLU/search?category=MLU1459&city=" + idBarrio);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            InputStream is = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            JSONObject obj = new JSONObject(response.toString());
            JSONArray results = obj.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);
                mercadoLibreProperties.add(
                        new MercadoLibreProperty(
                                item.getDouble("price"),
                                item.getJSONObject("location").getString("address_line"),
                                item.getJSONObject("address").getString("city_name"),
                                buscarPropiedad(item.getJSONArray("attributes"), "COVERED_AREA"),
                                item.getString("title"),
                                buscarPropiedad(item.getJSONArray("attributes"), "PROPERTY_TYPE").equals("Apartamento") ? "Apartment" : "House",
                                buscarPropiedad(item.getJSONArray("attributes"), "FULL_BATHROOMS"),
                                buscarPropiedad(item.getJSONArray("attributes"), "BEDROOMS"),
                                new Contacto(item.getJSONObject("seller_contact").getString("contact"),
                                        item.getJSONObject("seller_contact").getString("phone"))
                        ));
            }

            /*for (MercadoLibreProperty mp : mercadoLibreProperties) {
                mp.printProperty();
            }*/

        } catch (ProtocolException | JSONException e) {
            e.printStackTrace();
        }

        return mercadoLibreProperties;
    }

    String buscarPropiedad(JSONArray propiedades, String nombre) throws JSONException {
        for (int i=0; i<propiedades.length(); i++) {
            JSONObject propiedad = propiedades.getJSONObject(i);
            if (propiedad.get("id").equals(nombre)) {
                return propiedad.getString("value_name");
            }
        }

        return "";
    }
}
