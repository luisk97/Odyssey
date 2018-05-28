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
import usuario.User;

/**
 * @author Luis Carlos Mora Fonseca
 * En esta clase se construyen los mensajes xml para enviarlos al cliente
 */
public class MensajeXml {
	
	/**
	 * Este metodo recibe un parametro ListaEnlazada y construye un documento xml a partir de esta
	 * @param ListaEnlazada songs
	 * @return String stringXml
	 */
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
	
	
	public String xmlPlay(String encodedString) {
		String stringXml = "";
		try {
			DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2;
			builder2 = factory2.newDocumentBuilder();
			Document doc2 = builder2.newDocument();

		    Element root = doc2.createElement("MensajeXML");
		    doc2.appendChild(root);
		
		    Element datos = doc2.createElement("Datos");
		    root.appendChild(datos);
		
		    Element codigo = doc2.createElement("Code");
		    codigo.appendChild(doc2.createTextNode("toPlay"));
		    datos.appendChild(codigo);
		    
		    Element song = doc2.createElement("Cancion");
		    datos.appendChild(song);
		
		    Element nom = doc2.createElement("Bytes");
		    nom.appendChild(doc2.createTextNode(encodedString));
		    song.appendChild(nom);
		
		    datos.appendChild(song);
				
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			StringWriter sw = new StringWriter();
			t.transform(new DOMSource(doc2), new StreamResult(sw));
			stringXml = sw.toString();
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringXml;
	}
	
	public String xmlInfoUser(User user) {
		String stringXml = "";
		try {
			DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder2;
			builder2 = factory2.newDocumentBuilder();
			Document doc2 = builder2.newDocument();

		    Element root = doc2.createElement("MensajeXML");
		    doc2.appendChild(root);
		
		    Element datos = doc2.createElement("Datos");
		    root.appendChild(datos);
		
		    Element codigo = doc2.createElement("Code");
		    codigo.appendChild(doc2.createTextNode("infoUser"));
		    datos.appendChild(codigo);
		    
		    Element usua = doc2.createElement("User");
		    datos.appendChild(usua);
		
		    Element usuario = doc2.createElement("usuario");
		    usuario.appendChild(doc2.createTextNode(user.getUsuario().toString()));
		    usua.appendChild(usuario);
		    
		    Element nom = doc2.createElement("nombre");
		    nom.appendChild(doc2.createTextNode(user.getNombre().toString()));
		    usua.appendChild(nom);
		    
		    Element edad = doc2.createElement("edad");
		    edad.appendChild(doc2.createTextNode(user.getEdad().toString()));
		    usua.appendChild(edad);
		
		    datos.appendChild(usua);
				
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			StringWriter sw = new StringWriter();
			t.transform(new DOMSource(doc2), new StreamResult(sw));
			stringXml = sw.toString();
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stringXml;
	}

}
