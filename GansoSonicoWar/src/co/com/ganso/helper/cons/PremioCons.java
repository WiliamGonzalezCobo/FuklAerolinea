package co.com.ganso.helper.cons;

import java.util.HashMap;
import java.util.Map;

public class PremioCons {
	
	public static final String PREMIO_ACTIVO = "S";
	public static final String PREMIO_INACTIVO = "N";
	
	public static final String PREMIO_ACTIVO_DESC = "Activo";
	public static final String PREMIO_INACTIVO_DESC = "Inactivo";
	
	 public static Map<String, String> PREMIO_ESTADOS;
     static{
    	 PREMIO_ESTADOS = new HashMap<String, String>();
    	 PREMIO_ESTADOS.put(PREMIO_ACTIVO, PREMIO_ACTIVO_DESC);
    	 PREMIO_ESTADOS.put(PREMIO_INACTIVO, PREMIO_INACTIVO_DESC);
     }

}
