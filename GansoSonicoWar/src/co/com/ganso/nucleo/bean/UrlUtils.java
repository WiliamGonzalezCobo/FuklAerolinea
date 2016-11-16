package co.com.ganso.nucleo.bean;


public class UrlUtils {

	private String label;
	private String url;
	
	public UrlUtils(String url, String label) {
		this.label=label;
		this.url=url;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}