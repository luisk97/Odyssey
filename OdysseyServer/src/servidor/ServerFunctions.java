/**
 * 
 */
package servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1_1;
import org.farng.mp3.id3.ID3v2_2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.xml.internal.txw2.annotation.XmlElement;

import SongMgmt.Song;
import jdk.internal.dynalink.linker.LinkerServices.Implementation;

/**
 * @author Daniel Acuña Mora
 *
 */
public class ServerFunctions {

	public static void setMeta() throws IOException, TagException {
		MP3File mp3file = new MP3File("C:\\xml\\Juanpa.mp3");
		ID3v2_2 tag = (ID3v2_2) mp3file.getID3v2Tag();
		ID3v1_1 tag1 = (ID3v1_1) mp3file.getID3v1Tag();
		tag.setSongTitle("8BIT");
		tag.setAlbumTitle("Synth");
		tag.setSongGenre("Retro");
		tag1.setArtist("DAni");

		System.out.println(tag.getAlbumTitle());
		mp3file.save();
		// File filemp3 = new File("C:\\xml\\Juanpa.mp3");
		// File file2 = new File("C:\\xml\\"+tag.getSongTitle()+".mp3");
		// filemp3.renameTo(file2);
	}

	 ArrayList<Integer> add = new ArrayList<>();
	 private static ArrayList<Song> songs = Servidor.songs;

	protected static void addSong(Document d, Socket s, String sd) throws IOException {
		//ArrayList<Song> songs = Servidor.songs;
		//songs.add(new Song());


		Document doc = d;
		Socket clienteNuevo = s;
		String lel = sd;
		System.out.println("Agregamos una nueva cancion");
		NodeList nodos = doc.getElementsByTagName("Song");
		for (int ind = 0; ind < nodos.getLength(); ind++) {
			Node nod = nodos.item(ind);
			String encodedString = nod.getTextContent();
			// System.out.println(encodedString);
			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
			nodos = doc.getElementsByTagName("Nombre");
			nod = nodos.item(0);
			String path = "C:\\xml\\" + nod.getTextContent() + ".mp3";
			File file2 = new File(path);
			FileOutputStream os = new FileOutputStream(file2, true);
			os.write(decodedBytes);
			os.close();
			System.out.println("Respondiendo al cliente");
			PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
			resp.println(lel);
			System.out.println("Mensaje enviado");

			clienteNuevo.close();

			createJson(nodos, doc,"casa");
			for (int i = 0; i < songs.size(); i++) {
				System.out.println(songs.get(i).getTitle());}

		}
		
	}
	

	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * 
	 */
	public static void createJson(NodeList n, Document d,String p)
			throws JsonGenerationException, JsonMappingException, IOException {
		String path = p;
		NodeList nodos = n;
		Document doc = d;
		Node nod = nodos.item(0);
		songs.add(0, new Song());
		//songs.add(new Song());
		String[] tags = new String[] { "Nombre", "Artista", "Album", "Genero" };

		for (int i = 0; i < tags.length; i++) {
			nodos = doc.getElementsByTagName(tags[i]);
			nod = nodos.item(0);
			songs.get(0).set(tags[i], nod.getTextContent());
		}
		songs.get(0).setPath(path);

		File file = new File("canciones.json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, songs);
		// TODO Auto-generated method stub
		//System.out.println("Vieja cancion:"+songs.get(1).getTitle());
		System.out.println("Nueva cancion:"+songs.get(0).getTitle());

	}
	public static void writeXmlFile() {
		//aqui cambiar este ArrayList por una SongList si se desea
		ArrayList<Song> songs = Servidor.songs;
//		XmlMapper xmlmapper = new XmlMapper();
//		String xml1 = null;
//		File xml = new File("canciones.xml");
//		try {
//		    xml1 = xmlmapper.writeValueAsString(songs);		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		;
//		System.out.println(xml1);
		
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
	
	        Song temp;
	        for (int i = 0; i < songs.size(); i++) {
	        	temp = songs.get(i);
	            Element song = doc.createElement("Cancion");
	            datos.appendChild(song);
	
	            Element nom = doc.createElement("Nombre");
	            nom.appendChild(doc.createTextNode(temp.getTitle()+""));
	            song.appendChild(nom);
	
	            Element art = doc.createElement("Artista");
	            art.appendChild(doc.createTextNode(temp.getArtist()+""));
	            song.appendChild(art);
	
	            Element album = doc.createElement("Album");
	            album.appendChild(doc.createTextNode(temp.getAlbum()+""));
	            song.appendChild(album);
	
	            Element gen = doc.createElement("Genero");
	            gen.appendChild(doc.createTextNode(temp.getGenere()+""));
	            song.appendChild(gen);
	
	            datos.appendChild(song);
	        }
	        
	        //Esto es para guardar el xml como archivo en disco pero no hace falta despues cuando
	        //no se ocupe ver como queda el xml se puede quitar
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult file = new StreamResult(new File("C:\\xml\\canciones.xml"));
			transformer.transform(source, file);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static void sortSongs(Socket s,Document d) throws IOException {
		Socket clienteNuevo = s;
		System.out.println("Cargando lista de canciones en el cliente");
		Document document = d;
		NodeList nodos = document.getElementsByTagName("Orden");
		Node nod = nodos.item(0);
		PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		String stringXml = "No hay canciones";
		//Aqui valida si se desea ordenar por nombre, artista o album
		if(nod.getTextContent().equals("nombre")) {
			System.out.println("Ordenando lista de canciones por nombre");
			//Mae esto es provicional para que me mande todas las canciones pero ahi lo hace en una funcion es basicamente
			//la misma funcion writeXmlFile() pero que reciba una lista se supone que ordenada por lo que se le pide y
			//devuelva el xml que seria stringXml = sw.toString(); y que retorne stringXml para enviarlo
			ArrayList<Song> songs = Servidor.songs;
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
			
//			    Song temp;
			    for (int i = 0; i < songs.size(); i++) {
			    	Element song = doc.createElement("Cancion");
			        datos.appendChild(song);
			
			        Element nom = doc.createElement("Nombre");
			        nom.appendChild(doc.createTextNode(songs.get(i).getTitle()+""));
			        song.appendChild(nom);
			
			        Element art = doc.createElement("Artista");
			        art.appendChild(doc.createTextNode(songs.get(i).getArtist()+""));
			        song.appendChild(art);
			
			        Element album = doc.createElement("Album");
			        album.appendChild(doc.createTextNode(songs.get(i).getAlbum()+""));
			        song.appendChild(album);
			
			        Element gen = doc.createElement("Genero");
			        gen.appendChild(doc.createTextNode(songs.get(i).getGenere()+""));
			        song.appendChild(gen);
			            
			        Element letra = doc.createElement("Letra");
			        letra.appendChild(doc.createTextNode(songs.get(i).getLyrics()+""));
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
			 
			 resp.println(stringXml);
			 System.out.println("Mensaje enviado");
			 clienteNuevo.close();
		 }else if(nod.getTextContent().equals("artista")) {
			 System.out.println("Respondiendo al cliente");
			 resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MensajeXML><Code>ordenadas</Code><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>AllHope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Enter Sadman</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion></MensajeXML>");
			 System.out.println("Mensaje enviado");
			 clienteNuevo.close();
		 }else if(nod.getTextContent().equals("album")) {
			 System.out.println("Respondiendo al cliente");
			 resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MensajeXML><Code>ordenadas</Code><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>EnterSadman</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>AllHope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra></Letra></Cancion></MensajeXML>");
			 System.out.println("Mensaje enviado");
			 clienteNuevo.close();
		 }
	}
}