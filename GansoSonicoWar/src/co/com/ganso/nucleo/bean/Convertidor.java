package co.com.ganso.nucleo.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.model.SelectItem;

public class Convertidor {
	
	public static List<SelectItem> convertirMapToListSelectItem(
			Map<String, String> map) {

		List<Entry<String, String>> listado = new ArrayList<Entry<String, String>>();

		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Entry<String, String> valor : map.entrySet()) {
			listado.add(valor);
		}
		Collections.sort(listado, new Comparator<Entry<String, String>>() {

			public int compare(Entry<String, String> o1,
					Entry<String, String> o2) {
				if ( o1.getValue() == null ) {
					return 1;
				}
				if ( o2.getValue() == null ) {
					return -1;
				}
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		for (Entry<String, String> entry : listado) {
			items.add(new SelectItem(entry.getKey(), entry.getValue()));
		}
		return items;
	}
	
	public static Map<Integer, String> convertirListSelectItemToMapInteger(
			List<SelectItem> lista) {
		Map<Integer, String> mapa= new HashMap<Integer, String>();
		for (SelectItem element: lista) {
			mapa.put(Integer.parseInt(element.getValue().toString()), element.getLabel()!=null?element.getLabel().toString():"");
		}
		return mapa;
	}
	
	public static List<SelectItem> convertirMapIntegerStringToListSelectItem(
			Map<Integer, String> map) {

		List<Entry<Integer, String>> listado = new ArrayList<Entry<Integer, String>>();

		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Entry<Integer, String> valor : map.entrySet()) {
			listado.add(valor);
		}
		Collections.sort(listado, new Comparator<Entry<Integer, String>>() {

			public int compare(Entry<Integer, String> o1,
					Entry<Integer, String> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		for (Entry<Integer, String> entry : listado) {
			items.add(new SelectItem(entry.getKey(), entry.getValue()));
		}
		return items;
	}
}
