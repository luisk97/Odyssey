/**
 * 
 */
package servidor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1_1;
import org.farng.mp3.id3.ID3v2_2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import SongMgmt.Song;

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
		ArrayList<Song> songs = Servidor.songs;
		XmlMapper xmlmapper = new XmlMapper();
		String xml1 = null;
		String doc; 
		File xml = new File("canciones.xml");
		try {
		    xml1 = xmlmapper.writeValueAsString(songs);		} catch (IOException e) {
			e.printStackTrace();
		}
		
		;
		System.out.println(xml1);

	}
	public static void sortSongs(Socket s,Document d) throws IOException {
		Socket clienteNuevo = s;
		Document doc = d;
		NodeList nodos = doc.getElementsByTagName("Orden");
		 Node nod = nodos.item(0);
		if(nod.getTextContent().equals("nombre")) {
		 System.out.println("Respondiendo al cliente");
		 PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		 resp.println("\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"standalone=\\\"no\\\"?><MensajeXML><Code>ordenadas</Code><ArrayOfCancion><Cancion><Nombre>SadBut True</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>EnterSadman</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>AllHope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra></Letra></Cancion></ArrayOfCancion></MensajeXML>\"");
		 System.out.println("Mensaje enviado");
		 clienteNuevo.close();
		 }else if(nod.getTextContent().equals("artista")) {
		 System.out.println("Respondiendo al cliente");
		 PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		 resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\"standalone=\"no\"?><MensajeXML><Code>ordenadas</Code><ArrayOfCancion><Cancion><Nombre>SadBut True</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>AllHope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>EnterSadman</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion></ArrayOfCancion></MensajeXML>");
		 System.out.println("Mensaje enviado");
		 clienteNuevo.close();
		 }else if(nod.getTextContent().equals("album")) {
		 System.out.println("Respondiendo al cliente");
		 PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		 resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\"standalone=\"no\"?><MensajeXML><Code>ordenadas</Code><ArrayOfCancion><Cancion><Nombre>SadBut True</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>EnterSadman</Nombre><Artista>Metallica</Artista><Album>BlackAlbum</Album><Genero>(9)</Genero><Letra></Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>AllHope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra></Letra></Cancion></ArrayOfCancion></MensajeXML>");
		 System.out.println("Mensaje enviado");
		 clienteNuevo.close();
		 }
	}
}
