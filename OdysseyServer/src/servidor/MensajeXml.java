/**
 * 
 */
package servidor;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Sort.ListaEnlazada;

/**
 * @author Luis Carlos Mora Fonseca
 *
 */
public class MensajeXml {
	
	public String xmlListaCanciones(ListaEnlazada songs) {
		String stringXml = "";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

		    Element root = doc.createElement("MensajeXML");
		    doc.appendChild(root);
		
		    Element datos = doc.createElement("Datos");
		    root.appendChild(datos);
		
		    Element codigo = doc.createElement("Code");
		    codigo.appendChild(doc.createTextNode("ordenadas"));
		    datos.appendChild(codigo);
		
//		    Song temp;
		    for (int i = 0; i < songs.getLarge(); i++) {
		    	Element song = doc.createElement("Cancion");
		        datos.appendChild(song);
		
		        Element nom = doc.createElement("Nombre");
		        nom.appendChild(doc.createTextNode(songs.getNodo(i).getSong().getTitle()+""));
		        song.appendChild(nom);
		
		        Element art = doc.createElement("Artista");
		        art.appendChild(doc.createTextNode(songs.getNodo(i).getSong().getArtist()+""));
		        song.appendChild(art);
		
		        Element album = doc.createElement("Album");
		        album.appendChild(doc.createTextNode(songs.getNodo(i).getSong().getAlbum()+""));
		        song.appendChild(album);
		
		        Element gen = doc.createElement("Genero");
		        gen.appendChild(doc.createTextNode(songs.getNodo(i).getSong().getGenere()+""));
		        song.appendChild(gen);
		            
		        Element letra = doc.createElement("Letra");
		        letra.appendChild(doc.createTextNode(songs.getNodo(i).getSong().getLyrics()+""));
		        song.appendChild(letra);
		
		        datos.appendChild(song);
		    }
				
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			StringWriter sw = new StringWriter();
			t.transform(new DOMSource(doc), new StreamResult(sw));
			stringXml = sw.toString();
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringXml;
	}

}
