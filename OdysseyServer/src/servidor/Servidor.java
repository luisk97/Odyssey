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
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.omg.CORBA.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author luisk
 *
 */
public class Servidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		
		try {
			System.out.println("entramos al try");
			ServerSocket servidor = new ServerSocket(8000);
			while(true) {
				System.out.println("Esperando un cliente");
				Socket clienteNuevo = servidor.accept();
				System.out.println("Se conecto el cliente");
				InputStream entrada = clienteNuevo.getInputStream();
				String received = getStringFromInputStream(entrada);
//				String received = new String(buf);
                String lol = received.trim();
                String lal = lol.substring(3);
                String lel = lal + "</MensajeXML>";
				System.out.println(lel);
                System.out.println(lel.length());
				
				//A ver que
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
				DocumentBuilder builder;
		        builder = factory.newDocumentBuilder();  
//		        System.out.println("hasta aqui bien");
		        Document doc = builder.parse(new InputSource(new StringReader(lel)));
		        System.out.println(doc.getNodeName());
		        NodeList listaNodos = doc.getElementsByTagName("Code");
		        Node nodo = listaNodos.item(0);
		        if (nodo.getTextContent().equals("add")){  
		           System.out.println("Agregamos una nueva cancion");
		           NodeList nodos = doc.getElementsByTagName("Song");
		           for(int ind=0;ind<nodos.getLength();ind++){
				        Node nod = nodos.item(ind);
				        String encodedString = nod.getTextContent();
				        System.out.println(encodedString);
				        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
				        nodos = doc.getElementsByTagName("Nombre");
					    nod = nodos.item(0);
				        File file2 = new File("C:\\xml\\"+nod.getTextContent()+".mp3");
				        FileOutputStream os = new FileOutputStream(file2, true);
				        os.write(decodedBytes);
				        os.close();
				        System.out.println("Respondiendo al cliente");
						PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
						resp.println(lel); 
						System.out.println("Mensaje enviado");
						clienteNuevo.close();
		           }     
		        }else if(nodo.getTextContent().equals("cargar")) {
		     	   NodeList nodos = doc.getElementsByTagName("Orden");
		       	   Node nod = nodos.item(0);
		       	   if(nod.getTextContent().equals("nombre")) {
		       		   System.out.println("Respondiendo al cliente");
		       		   PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		       		   resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><MensajeXML><Code>ordenadas</Code><ArrayOfCancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>All Hope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra> </Letra></Cancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra> </Letra></Cancion><Cancion><Nombre>Enter Sadman</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra> </Letra></Cancion></ArrayOfCancion></MensajeXML>"); 
		       		   System.out.println("Mensaje enviado");
		       		   clienteNuevo.close();
		       	   }else if(nod.getTextContent().equals("artista")) {
		       		   System.out.println("Respondiendo al cliente");
		       		   PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		       		   resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><MensajeXML><Code>ordenadas</Code><ArrayOfCancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra> </Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>All Hope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra> </Letra></Cancion><Cancion><Nombre>Enter Sadman</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra> </Letra></Cancion></ArrayOfCancion></MensajeXML>"); 
		       		   System.out.println("Mensaje enviado");
		       		   clienteNuevo.close();
		       	   }else if(nod.getTextContent().equals("album")) {
		       		   System.out.println("Respondiendo al cliente");
		       		   PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
		       		   resp.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><MensajeXML><Code>ordenadas</Code><ArrayOfCancion><Cancion><Nombre>Sad But True</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra> </Letra></Cancion><Cancion><Nombre>Enter Sadman</Nombre><Artista>Metallica</Artista><Album>Black Album</Album><Genero>(9)</Genero><Letra> </Letra></Cancion><Cancion><Nombre>Psychosocial</Nombre><Artista>Slipknot</Artista><Album>All Hope Is Gone [Special Edition] Disc 1</Album><Genero>(9)</Genero><Letra> </Letra></Cancion></ArrayOfCancion></MensajeXML>"); 
		       		   System.out.println("Mensaje enviado");
		       		   clienteNuevo.close();
		       	   }
		        }
			    System.out.println("si aparece esto no hay error");
				
		        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            //for pretty print
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            DOMSource source = new DOMSource(doc);

	            //write to console or file
	            StreamResult console = new StreamResult(System.out);
	            StreamResult file = new StreamResult(new File("C:\\xml\\archivo.xml"));

	            //write data
	            transformer.transform(source, console);
	            transformer.transform(source, file);
	            System.out.println("DONE");
		        
				
//				System.out.println("Respondiendo al cliente");
//				PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
//				resp.println(lel); 
//				System.out.println("Mensaje enviado");
//				clienteNuevo.close();
//			 	servidor.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	
	
//	private static String getStringFromInputStream(InputStream is) {
//		System.out.println("Nos metimos al getStringFromImputStream");
//		BufferedReader br = null;
//		StringBuilder sb = new StringBuilder();
//
//		String line;
//		try {
//			System.out.println("Entramos al try");
//			br = new BufferedReader(new InputStreamReader(is));
//			int ind = 0;
//			int cond = 3;
//			line = br.readLine();
//			sb.append(line);
//			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//				sb.append(line);
//				ind++;
//				System.out.println(ind);
//			}
//			System.out.println("Salimos bien del while");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return sb.toString();
//
//	}
	
	private static String getStringFromInputStream(InputStream is) {
		System.out.println("Nos metimos al getStringFromImputStream");
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			System.out.println("Entramos al try");
			br = new BufferedReader(new InputStreamReader(is));
			int ind = 0;
			int cond = 3;
			line = br.readLine();
			sb.append(line);
			while (line.equals("  </Datos>") != true) {
				line = br.readLine();
				System.out.println(line);
				sb.append(line);
				ind++;
				System.out.println(ind);
			}
			System.out.println("Salimos bien del while");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();

	}
	
	

}
