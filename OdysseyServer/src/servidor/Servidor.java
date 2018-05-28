/**
 * 
 */
package servidor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v1_1;
import org.farng.mp3.id3.ID3v2_2;
import org.farng.mp3.id3.ID3v2_4;
import org.omg.CORBA.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.javafx.fxml.expression.BinaryExpression;

import Sort.ListaEnlazada;
import Sort.Song;
import Trees.BinarySearchTree;
import javafx.scene.text.TextAlignment;
import usuario.User;

/**
 * @author Luis Carlos Mora
 * @author Daniel Acuña Mora
 */
public class Servidor {
	protected static ArrayList<Song> songs = new ArrayList<>();
	protected static ArrayList<User> users = new ArrayList<>();
	public static ListaEnlazada canciones = new ListaEnlazada();
	protected static BinarySearchTree usertree = new BinarySearchTree();

	public static void loadJson() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		try {
			File songfile = new File("Songs.json");
			songs = mapper.readValue(songfile, new TypeReference<ArrayList<Song>>() {
			});
			for(int i = 0; i < songs.size(); i++) {
				canciones.add(songs.get(i));
			}
			System.out.println("Se cargaron las canciones");
			File userfile = new File("Users.json");
			users = mapper.readValue(userfile, new TypeReference<ArrayList<User>>() {
			});
			System.out.println("Se cargaron los usuarios");
//			for(int i=0; i < users.size();i++) {
//				usertree.add(users.get(i));
//			}
		}catch(IOException e) {
			System.out.println("No hay Jsons de Canciones");
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws TagException
	 * @throws IOException
	 */
	public static void sendXml() throws Exception {
		ServerFunctions.writeXmlFile();
	}

	public static void main(String[] args) throws IOException, TagException {
		// File sourceFile;
		//ServerFunctions.generateUsers();
		loadJson();
		try {
			sendXml();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < users.size(); i++)
			System.out.println(users.get(i).getUsuario());
		try {
			System.out.println("entramos al try");
			ServerSocket servidor = new ServerSocket(8000);
			while (true) {
				System.out.println("Esperando un cliente");
				Socket clienteNuevo = servidor.accept();
				System.out.println("Se conecto el cliente");
				InputStream entrada = clienteNuevo.getInputStream();
				String received = getStringFromInputStream(entrada);
				// String received = new String(buf);
				String lol = received.trim();
				String lal = lol.substring(3);
				String lel = lal + "</MensajeXML>";
				// System.out.println(lel);
				// System.out.println(lel.length());

				// A ver que
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				builder = factory.newDocumentBuilder();
				// System.out.println("hasta aqui bien");
				Document doc = builder.parse(new InputSource(new StringReader(lel)));
				// System.out.println(doc.getNodeName());
				// System.out.println(lel);
				NodeList listaNodos = doc.getElementsByTagName("Code");
				Node nodo = listaNodos.item(0);
				if (nodo.getTextContent().equals("add")) {
					ServerFunctions.addSong(doc, clienteNuevo, lel);
				} else if (nodo.getTextContent().equals("cargar")) {
					ServerFunctions.sortSongs(clienteNuevo, doc);
				} else if (nodo.getTextContent().equals("eliminar")) {
					NodeList nodos = doc.getElementsByTagName("Cancion");
					PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
					if (nodos.getLength() > 0) {
						System.out.println("Se eliminaran las siguientes canciones:");
						for (int i = 0; i < nodos.getLength(); i++) {
							System.out.println(nodos.item(i).getTextContent());
							// Aqui debe ir el metodo para eliminar las canciones que vienen en la NodeList
							// y tiene que
							// eliminarlas de la SongList y borrar el archivo .mp3
						}
						System.out.println("Respondiendo al cliente");
						resp.println("Las canciones se eliminaron correctamente");
						System.out.println("Mensaje enviado");
						clienteNuevo.close();
					} else {
						System.out.println("Respondiendo al cliente");
						resp.println("No se eligio ninguna cancion para eliminar");
						System.out.println("Mensaje enviado");
						clienteNuevo.close();
					}
				} else if (nodo.getTextContent().equals("logIn")) {
					System.out.println("Un usuario esta iniciando sesion");
					NodeList nodos = doc.getElementsByTagName("User");
					Node nod1 = nodos.item(0);
					nodos = doc.getElementsByTagName("Password");
					Node nod2 = nodos.item(0);
					// string confirmacion =
					// algunMetodoQueValidaUsuario(nod1.getTextContent(),nod2.getTextContent());
					// Por ahora lo validaremos asi para ver si sirve:
					PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
					if (nod1.getTextContent().equals("luisk")) {
						if (nod2.getTextContent().equals("pass")) {
							System.out.println("Se concedio el acceso al usuario");
							System.out.println("Respondiendo al cliente");
							resp.println("acceso permitido");
							System.out.println("Mensaje enviado");
							clienteNuevo.close();
							System.out.println("Sesion Iniciada");
						} else {
							System.out.println("Contraseña incorrecta");
							System.out.println("Respondiendo al cliente");
							resp.println("error pass");
							System.out.println("Mensaje enviado");
							clienteNuevo.close();
						}
					} else {
						System.out.println("Usuario invalido");
						System.out.println("Respondiendo al cliente");
						resp.println("error user");
						System.out.println("Mensaje enviado");
						clienteNuevo.close();
					}
				} else if (nodo.getTextContent().equals("signIn")) {
					ServerFunctions.addUser(clienteNuevo, doc);
					
					// System.out.println("Se esta registrando un usuario");
					// PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
					// NodeList nodos = doc.getElementsByTagName("User");
					// Node nod1 = nodos.item(0);
					// //string confirmacion =
					// algunMetodoQueValidaSiYaExisteElUsuario(nod1.getTextContent(),nod2.getTextContent());
					// //Por ahora lo validaremos asi para ver si sirve:
					// if(!nod1.getTextContent().equals("LuisK")) {
					// nodos = doc.getElementsByTagName("FullName");
					// Node nod2 = nodos.item(0);
					// nodos = doc.getElementsByTagName("Age");
					// Node nod3 = nodos.item(0);
					// nodos = doc.getElementsByTagName("Password");
					// Node nod4 = nodos.item(0);
					// System.out.println("El usuario se registro con exito!");
					// System.out.println("Respondiendo al cliente");
					// resp.println("exito");
					// System.out.println("Mensaje enviado");
					// clienteNuevo.close();
					// }else{
					// System.out.println("El usuario que se desea registrar ya existe!");
					// System.out.println("Respondiendo al cliente");
					// resp.println("usuario existente");
					// System.out.println("Mensaje enviado");
					// clienteNuevo.close();
					// }
				} else if (nodo.getTextContent().equals("buscar")) {
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
				} else if (nodo.getTextContent().equals("play")) {
					System.out.println("Se solicito reproducir una cancion");
					PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
					NodeList nodos = doc.getElementsByTagName("Nombre");
					Node nod = nodos.item(0);
					String nombre = nod.getTextContent();
					System.out.println("Se solicito reproducir la cancion " + nombre);
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
				} else if (nodo.getTextContent().equals("infoUsuario")) {
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
					User user = new User("luisk", "Luis Carlos Mora Fonseca", "20", "pass");
					MensajeXml msj = new MensajeXml();
					String stringXml = msj.xmlInfoUser(user);
					resp.println(stringXml);
					System.out.println("Mensaje enviado");
					clienteNuevo.close();
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				// for pretty print
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				System.out.println();
				// write to console or file
				// StreamResult console = new StreamResult(System.out);
				StreamResult file = new StreamResult(new File("C:\\xml\\archivo.xml"));

				// write data
				// transformer.transform(source, console);
				transformer.transform(source, file);
				System.out.println("DONE");

				// System.out.println("Respondiendo al cliente");
				// PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
				// resp.println(lel);
				// System.out.println("Mensaje enviado");
				// clienteNuevo.close();
				// servidor.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			// } catch (ClassNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	private static String getStringFromInputStream(InputStream is) {
		System.out.println("Nos metimos al getStringFromImputStream");
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			System.out.println("Entramos al try");
			br = new BufferedReader(new InputStreamReader(is));
			line = br.readLine();
			sb.append(line);
			while (line.equals("  </Datos>") != true) {
				line = br.readLine();
				sb.append(line);
			}
			System.out.println("Salimos bien del while");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

}
