package fp.spotify.pojo;

import java.util.List;

public class SpotifyPage<T> { //Hay que indicar que es genérica en la cabecera porque contiene un atributo genérico.

	//Atributos
	private List<T> items;	
	private String next;
	private String href;			//POR LAS DIAPOSITIVAS DE CLASE, PAG 45 
	private String previous;
	private Integer total;
	
	//Constructor vacío
	public SpotifyPage(){
		
	}

	//Getters
	public List<T> getItems() {
		return items;
	}

	public String getNext() {
		return next;
	}

	public String getHref() {
		return href;
	}

	public String getPrevious() {
		return previous;
	}

	public Integer getTotal() {
		return total;
	}

	//hashCode & equals
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		result = prime * result + ((previous == null) ? 0 : previous.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpotifyPage other = (SpotifyPage) obj;
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		if (previous == null) {
			if (other.previous != null)
				return false;
		} else if (!previous.equals(other.previous))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}

	//toString
	public String toString() {
		return "SpotifyPage [getItems()=" + getItems() + ", getNext()=" + getNext() + ", getHref()=" + getHref()
				+ ", getPrevious()=" + getPrevious() + ", getTotal()=" + getTotal() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
}
