package co.com.ganso.archivo;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import co.com.ganso.entities.Vuelo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class ArchivoJson {
	
	public static <T> List<T> leer(StringBuilder datosJson) throws Exception{
		List<T> listaDatos = null;
		if(StringUtils.isNotBlank(datosJson)){
			try {
				Type type = new TypeToken<ArrayList<Vuelo>>(){}.getType();
				listaDatos = new Gson().fromJson(datosJson.toString(), type);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        if(listaDatos==null){
        	listaDatos = new ArrayList<T>();
        }
        return listaDatos;
	}
	
	public static <T> List<T> leer(StringBuilder datosJson, Class<T> clase) throws Exception{
		List<T> listaDatos = new ArrayList<T>();
		 // Consuming remote method
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(datosJson.toString()).getAsJsonArray();

		for (final JsonElement json : array) {
			T entity = new Gson().fromJson(json, clase);
			listaDatos.add(entity);
		}

        return listaDatos;
	}
	
	public static StringBuilder cargar(InputStream inputStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder data = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		return data;
	}
	
	public static void escrituraJson(List<Vuelo> listaRecetas)throws Exception {
		FileWriter datosJson = null;
		try {
			String json = new Gson().toJson(listaRecetas);
			datosJson = new FileWriter("D:\\temp\\tmp.json", true);
			datosJson.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datosJson.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		Vuelo vuelo = new Vuelo();
		vuelo.setNIdvuelo(new BigDecimal(1));
		vuelo.setTOrigen("SKBO");
		vuelo.setTDestino("SKCL");
		vuelo.setDFechahorasalida(new Date());
		vuelo.setNCapacidad(new BigDecimal("120"));
		vuelo.setNValorbase(new BigDecimal("120000"));
		vuelos.add(vuelo);
		escrituraJson(vuelos);
		
	}
}
