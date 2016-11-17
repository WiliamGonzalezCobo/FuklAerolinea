package co.com.ganso.helper.cons;

import java.util.HashMap;
import java.util.Map;

public class BookingCons {
	
	public static final String IDA = "ID";
	public static final String IDA_REGRESO = "IR";
	
	public static final String IDA_DESC = "Ida";
	public static final String IDA_REGRESO_DESC = "Ida y Regreso";
	
	public static final Map<String, String> LISTA_TRAYECTO;
	static {
		LISTA_TRAYECTO = new HashMap<String, String>();
		LISTA_TRAYECTO.put(IDA_REGRESO, IDA_REGRESO_DESC);
		LISTA_TRAYECTO.put(IDA, IDA_DESC);
	}
	
	
	public static final Map<Integer, String> cantidades;
	static {
		cantidades = new HashMap<Integer, String>();
		cantidades.put(0, "0");
		cantidades.put(1, "1");
		cantidades.put(2, "2");
		cantidades.put(3, "3");
		cantidades.put(4, "4");
		cantidades.put(5, "5");
		cantidades.put(6, "6");
		cantidades.put(7, "7");
		cantidades.put(8, "8");
		cantidades.put(9, "9");
	}

}
