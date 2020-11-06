package controllers;


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
    private List<MercadoLibreProperty> mercadoLibreProperties = new ArrayList<>();

    public void LoadAlojamientos() throws IOException {
        System.out.println("Obtener alojamientos en Pocitos desde Mercado Libre");
        try {
            URL url = new URL("https://api.mercadolibre.com/classified_locations/cities/TUxVQ1BPQzM5ZGRi");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            JSONObject obj = new JSONObject(content.toString());
            JSONObject location = obj.getJSONObject("geo_information").getJSONObject("location");
            double lat = location.getDouble("latitude");
            double longitude = location.getDouble("longitude");

            System.out.println("Coordenadas de Pocitos: " + lat + " , " + longitude);

            double range = 0.02;
            url = new URL("https://api.mercadolibre.com/sites/MLA/search?item_location=lat:" + (lat - range) + "_" + (lat + range) + ",lon:" + (longitude - range) + "_" + (longitude + range) + "&category=MLA1459&limit=10#json");
            connection = (HttpURLConnection) url.openConnection();
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
            System.out.println("RQ: " + url + response.toString());

            obj = new JSONObject(response.toString());
            JSONArray results = obj.getJSONArray("results");
            System.out.println("RESULTS: " + results);
            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);
                mercadoLibreProperties.add(
                        new MercadoLibreProperty(
                                item.getString("title"),
                                item.getInt("price"),
                                item.getString("permalink"),
                                item.getJSONObject("location").getString("address_line"),
                                item.getJSONObject("location").getJSONObject("neighborhood").getString("name")
                        ));
            }

            for (MercadoLibreProperty mp : mercadoLibreProperties) {
                mp.printProperty();
            }



        } catch (ProtocolException | JSONException e) {
            e.printStackTrace();
        }
    }

}
