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

import Sort.BubbleSort;
import Sort.ListaEnlazada;
import Sort.QuickSort;
import Sort.RadixSort;
import Sort.Song;
import Trees.BinarySearchTree;
import jdk.internal.dynalink.linker.LinkerServices.Implementation;
import usuario.User;

/**
 * @author Daniel Acuña Mora
 * @author Luis Carlos Mora
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

			createJson(nodos, doc,path,"songs");
			System.out.println("Canciones en arrayList:");
			for (int i = 0; i < songs.size(); i++) {
				System.out.println(songs.get(i).getTitle());
			}
			
			System.out.println("Canciones en ListaEnlazada:");
			for (int i = 0; i < Servidor.canciones.getLarge(); i++) {
				System.out.println(Servidor.canciones.getNodo(i).getSong().getTitle());
			}

		}
		
	}

	

	/**
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * 
	 */
	public static void createJson(NodeList n, Document d,String p,String filename)
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
		Servidor.canciones.add(songs.get(0));

		File file = new File(filename+".json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, songs);
		// TODO Auto-generated method stub
		//System.out.println("Vieja cancion:"+songs.get(1).getTitle());
		System.out.println("Nueva cancion:"+songs.get(0).getTitle());
	}

//	public static void writeXmlFile() {
//
//		ListaEnlazada songs = Servidor.canciones;
//
//		
//		try {
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder;
//			builder = factory.newDocumentBuilder();
//			Document doc = builder.newDocument();
//
//	        Element root = doc.createElement("MensajeXML");
//	        doc.appendChild(root);
//	
//	        Element datos = doc.createElement("Datos");
//	        root.appendChild(datos);
//	
//	        Element codigo = doc.createElement("Code");
//	        codigo.appendChild(doc.createTextNode("ordenadas"));
//	        datos.appendChild(codigo);
//	
//	        Song temp;
//	        for (int i = 0; i < songs.getLarge(); i++) {
//	        	temp = songs.getNodo(i).getSong();
//	            Element song = doc.createElement("Cancion");
//	            datos.appendChild(song);
//	
//	            Element nom = doc.createElement("Nombre");
//	            nom.appendChild(doc.createTextNode(temp.getTitle()+""));
//	            song.appendChild(nom);
//	
//	            Element art = doc.createElement("Artista");
//	            art.appendChild(doc.createTextNode(temp.getArtist()+""));
//	            song.appendChild(art);
//	
//	            Element album = doc.createElement("Album");
//	            album.appendChild(doc.createTextNode(temp.getAlbum()+""));
//	            song.appendChild(album);
//	
//	            Element gen = doc.createElement("Genero");
//	            gen.appendChild(doc.createTextNode(temp.getGenere()+""));
//	            song.appendChild(gen);
//	
//	            datos.appendChild(song);
//	        }
//	        
//	        //Esto es para guardar el xml como archivo en disco pero no hace falta despues cuando
//	        //no se ocupe ver como queda el xml se puede quitar
//	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			DOMSource source = new DOMSource(doc);
//			StreamResult file = new StreamResult(new File("C:\\xml\\canciones.xml"));
//			transformer.transform(source, file);
//			
//			
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	
	
	
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
			
			ListaEnlazada songs = Servidor.canciones;
			
			QuickSort Q = new QuickSort();
			Q.quicksort(songs);
			
			MensajeXml msj = new MensajeXml();
			stringXml = msj.xmlListaCanciones(songs);
			 
			resp.println(stringXml);
			System.out.println("Mensaje enviado");
			clienteNuevo.close();
		 }else if(nod.getTextContent().equals("artista")) {
				System.out.println("Ordenando lista de canciones por artista");
				
				ListaEnlazada songs = Servidor.canciones;
				
				RadixSort R = new RadixSort();
				R.radix(songs);
				
				MensajeXml msj = new MensajeXml();
				stringXml = msj.xmlListaCanciones(songs);
				 
				resp.println(stringXml);
				System.out.println("Mensaje enviado");
				clienteNuevo.close();
		 }else if(nod.getTextContent().equals("album")) {
			System.out.println("Ordenando lista de canciones por album");
				
			ListaEnlazada songs = Servidor.canciones;
			
			BubbleSort B = new BubbleSort();
			B.bubbleSort(songs);
				
			MensajeXml msj = new MensajeXml();
			stringXml = msj.xmlListaCanciones(songs);
				 
			resp.println(stringXml);
			System.out.println("Mensaje enviado");
			clienteNuevo.close();
		 }
	}
	public static void addUser(Socket s, Document d) throws IOException {
		Document doc = d;
		Socket clienteNuevo = s;
		ArrayList<User> users = Servidor.users;

		System.out.println("Se esta registrando un usuario");
		PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		NodeList nodos = doc.getElementsByTagName("User");
		Node nod1 = nodos.item(0);
		//string confirmacion = algunMetodoQueValidaSiYaExisteElUsuario(nod1.getTextContent(),nod2.getTextContent());
		//Por ahora lo validaremos asi para ver si sirve:
		if(!Trees.BinarySearchTree.search(nod1.getTextContent())) {
			nodos = doc.getElementsByTagName("FullName");
			Node nod2 = nodos.item(0);
			nodos = doc.getElementsByTagName("Age");
			Node nod3 = nodos.item(0);
			nodos = doc.getElementsByTagName("Password");
			Node nod4 = nodos.item(0);
			users.add(new User(nod1.getTextContent(),nod2.getTextContent(),nod3.getTextContent(),nod4.getTextContent()));
			BinarySearchTree.add(new User(nod1.getTextContent(),nod2.getTextContent(),nod3.getTextContent(),nod4.getTextContent()));
			System.out.println("El usuario se registro con exito!");
			System.out.println("Respondiendo al cliente");
			resp.println("exito");
			System.out.println("Mensaje enviado");
			
			File file = new File("Users.json");
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, users);
			clienteNuevo.close();
		}else{
			System.out.println("El usuario que se desea registrar ya existe!");
			System.out.println("Respondiendo al cliente");
			resp.println("usuario existente");
			System.out.println("Mensaje enviado");
			clienteNuevo.close();
		}
	}
//	public static void generateUsers() throws JsonGenerationException, JsonMappingException, IOException {
//		songs.add(0, new Song());
//		ArrayList<User> users = Servidor.users;
//		//songs.add(new Song());
//		String[] tags = new String[] { "Daniel", "Greivin", "Luisk", "Elba leado" };
//
//		for (int i = 0; i < tags.length; i++) {
//			users.add(new User());	
//			users.get(i).setUsuario(tags[i]);
//		}
//		File file = new File("Users.json");
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(file, users);
//		
//	}
	public static void playsong(Socket clienteNuevo, Document doc) throws IOException {
		System.out.println("Se solicito reproducir una cancion");
		PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		NodeList nodos = doc.getElementsByTagName("Nombre");
		Node nod = nodos.item(0);
		String nombre = nod.getTextContent();
		System.out.println("Se solicito reproducir la cancion: " + nombre);
		File file = new File("C:\\xml\\" + nombre + ".mp3");
		byte[] fileBytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(fileBytes);
		fis.close();
		String encodedString = Base64.getEncoder().encodeToString(fileBytes);
		// toda la construccion de xml ahora se hace en la clase MensajeXml
		MensajeXml msj = new MensajeXml();
		String stringXml = msj.xmlPlay(encodedString);
		resp.println(stringXml);
		System.out.println("Mensaje enviado");
		clienteNuevo.close();
	}
	public static void buscar(Socket clienteNuevo, Document doc) throws IOException {
		PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		NodeList nodos = doc.getElementsByTagName("Por");
		Node nod1 = nodos.item(0);
		if (nod1.getTextContent().equals("nombre")) {
			nodos = doc.getElementsByTagName("Nombre");
			nod1 = nodos.item(0);
			System.out.println("Buscando las canciones llamadas " + nod1.getTextContent());
			// Aqui debe ir un metodo que busque el nombre dentro del arbolB como talvez
			// search(nod1.getTextContent());
			// y que es metodo nos debuelva la lista de canciones con ese nombre ejemplo
			// SongList lista = arbolB.search(nod1.getTextContent());
			// y convertirla a xml con el metodo de ServerFunctions writeXmlFile() o hacer
			// un metodo parecido que reciba la SongList y devuelva
			// el xml convertido a String como por ejemplo string msjEnviar =
			// ServerFunctions.writeXmlFile(lista); y devolver eso al cliente

			// Y si no encuentra canciones con ese nombre, osea if(lista.size > 0 == false)
			// devuelve esto:
			System.out.println("No se encontraron canciones con ese nombre");
			System.out.println("Respondiendo al cliente");
			resp.println("No encontrado");
			System.out.println("Mensaje enviado");
			clienteNuevo.close();
		} else if (nod1.getTextContent().equals("artista")) {
			nodos = doc.getElementsByTagName("Artista");
			nod1 = nodos.item(0);
			System.out.println("Buscando canciones del artista " + nod1.getTextContent());
			// Lo mismo que en el anterior pero con el arbol AVL

			// Y si no encuentra canciones de ese artista devuelve esto:
			System.out.println("No se encontraron canciones de ese artista");
			System.out.println("Respondiendo al cliente");
			resp.println("No encontrado");
			System.out.println("Mensaje enviado");
			clienteNuevo.close();
		} else if (nod1.getTextContent().equals("album")) {
			nodos = doc.getElementsByTagName("Album");
			nod1 = nodos.item(0);
			System.out.println("Buscando canciones del album " + nod1.getTextContent());
			// Lo mismo que en el anterior pero con el arbol Splay

			// Y si no encuentra canciones de ese album devuelve esto:
			System.out.println("No se encontraron canciones de ese album");
			System.out.println("Respondiendo al cliente");
			resp.println("No encontrado");
			System.out.println("Mensaje enviado");
			clienteNuevo.close();
		}
	}
	protected static void infoUsuario(Socket clienteNuevo, Document doc) throws IOException {
		PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		NodeList nodos = doc.getElementsByTagName("Usuario");
		Node nod = nodos.item(0);
		String usuario = nod.getTextContent();
		// aqui debe ir un metodo que busque en la lista de usuarios a ese usuario
		// y nos debuelva la intancia de la clase User que debe estar contenida en los
		// nodos
		// de la lista de usuarios que se debe implementar, luego mediante un metodo de
		// la
		// clase MensajeXml que reciba la clase User se crea un XML con la informacion
		// del
		// usuario y retorne un string de ese XML y este se envie por el socket.

		// Por ahora hagamoslo asi para probar
		User user = BinarySearchTree.searchUser(usuario);
		MensajeXml msj = new MensajeXml();
		String stringXml = msj.xmlInfoUser(user);
		resp.println(stringXml);
		System.out.println("Mensaje enviado");
		clienteNuevo.close();
	}

}