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
//		try {
//			ServerSocket servidor = new ServerSocket(8000);
//			Socket clienteNuevo = servidor.accept();
//			ObjectInputStream entrada = new ObjectInputStream(clienteNuevo.getInputStream());
//			System.out.println("Llego el Objeto");
//			System.out.println("Mandando otro mensaje al cliente");
//			String mensaje = (String)entrada.readObject();
//			System.out.println("Mensaje: "+mensaje);
//			ObjectOutputStream resp = new ObjectOutputStream(clienteNuevo.getOutputStream());
//			resp.writeObject("Hola cliente");
//			System.out.println("Mensaje enviado");
//			clienteNuevo.close();
//			servidor.close();
//			
//		}catch (IOException e) {
//			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE,null,e);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			System.out.println("entramos al try");
			ServerSocket servidor = new ServerSocket(8000);
			while(true) {
				System.out.println("Esperando un cliente");
				Socket clienteNuevo = servidor.accept();
				System.out.println("Se conecto el cliente");
				InputStream entrada = clienteNuevo.getInputStream();
//				int a = entrada.read();
//				byte[] msjCliente = new byte[100000];
//				String prueba;
//				for(int i = 0; i < msjCliente.length; i++) {
//					
//				}
//				BufferedInputStream bis = new BufferedInputStream(entrada);
//				ByteArrayOutputStream buf = new ByteArrayOutputStream();
//				int result = entrada.read(msjCliente,0,100000);
//				String k;
//				while(result != -1) {
////					System.out.println(buf.toString("UTF-8"));
//					System.out.println(result);
////				    buf.write((byte) result);
//				    result = entrada.read(msjCliente,0,100000);
//				    System.out.println(result);
//				    k = new String(msjCliente);
//				    System.out.println(k);
//				}
				// StandardCharsets.UTF_8.name() > JDK 7
//				return buf.toString("UTF-8");
				
				
//                entrada.read(msjCliente,0,100000);
//                FileOutputStream fos = new FileOutputStream("C:\\xml\\archivo.xml");
//				fos.write(msjCliente);
//				fos.close();
//				System.out.println(msjCliente.length);
//				System.out.println(entrada.read());
//                byte[] buf = getBytesFromInputStream(entrada);
//				byte[] buf = readFully(entrada,200000,true);
                //convierte a string
//                String received = new String(msjCliente);
//				String mensaje = (String)entrada.readObject();
//				String received = new String(buf.toString());
				String received = getStringFromInputStream(entrada);
//				String received = new String(buf);
                String lol = received.trim();
                String lal = lol.substring(3);
                String lel = lal + "</MensajeXML>";
				System.out.println(lel+"lol");
                System.out.println(lel.length());
//				BufferedWriter writer = null;
//				try
//				{
//				    writer = new BufferedWriter( new FileWriter("C:\\xml\\archivo.xml"));
//				    writer.write(lel);
//
//				}
//				catch ( IOException e)
//				{
//				}
//				finally
//				{
//				    try
//				    {
//				        if ( writer != null)
//				        writer.close( );
//				    }
//				    catch ( IOException e)
//				    {
//				    }
//				}
//				PrintWriter out = new PrintWriter("C:\\xml\\archivo.xml");
//				out.println(msjCliente);
				
//				String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Emp id=\"1\"><name>Pankaj</name><age>25</age><role>Developer</role><gen>Male</gen></Emp>";
//				System.out.println("Mensaje: "+xmlStr+"quac");
				
				
				//A ver que
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
				DocumentBuilder builder;
		        builder = factory.newDocumentBuilder();  
//		        System.out.println("hasta aqui bien");
		        Document doc = builder.parse( new InputSource( new StringReader( lel ) ) );
		        System.out.println(doc.getNodeName());
		        NodeList listaNodos = doc.getElementsByTagName("Code");
		        for(int i=0;i<listaNodos.getLength();i++){  
		           Node nodo = listaNodos.item(i);  
		           if (nodo.getTextContent().equals("add")){  
		              System.out.println("Agregamos una nueva cancion");
		              NodeList nodos = doc.getElementsByTagName("Song");
		              for(int ind=0;ind<nodos.getLength();ind++){  
				           Node nod = nodos.item(ind);
				           String encodedString = nod.getTextContent();
				           System.out.println(encodedString);
				           byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
				           File file2 = new File("C:\\xml\\hello-5.mp3");
				           FileOutputStream os = new FileOutputStream(file2, true);
				           os.write(decodedBytes);
				           os.close();
		              }     
		           }  
		        } 
//		        System.out.println("si aparece esto no es aqui el error");
//				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			    factory.setNamespaceAware(true);
//			    DocumentBuilder builder = factory.newDocumentBuilder();
//			    Document doc = builder.parse(new ByteArrayInputStream(msjCliente));
			    System.out.println("si aparece esto no hay error");
				
//				TransformerFactory transformerFactory = TransformerFactory.newInstance();
//				Transformer transformer = transformerFactory.newTransformer();
//				DOMSource source = new DOMSource(doc);
//				StreamResult result = new StreamResult(new File("C:\\xml\\archivo.xml"));
//				transformer.transform(source, result);
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
		        
				
				System.out.println("Respondiendo al cliente");
				PrintStream resp = new PrintStream(clienteNuevo.getOutputStream());
				resp.println(lel); 
//				ObjectOutputStream resp = new ObjectOutputStream(clienteNuevo.getOutputStream());
//				System.out.println("Mandando otro mensaje al cliente");
//				resp.writeObject("vida:");
//				System.out.println("Mensaje enviado");
				clienteNuevo.close();
//			 	servidor.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
	    ByteArrayOutputStream os = new ByteArrayOutputStream(); 
	    byte[] buffer = new byte[10000];
	    for (int len = 0; len < 10; len++) { 
	    	System.out.println(len);
	        os.write(buffer, 0, len);
	        System.out.println(len+"lrl");
	    }
	    System.out.println("salimos seee");
	    return os.toByteArray();
	}
	
	public static byte[] readFully(InputStream is, int length, boolean readAll) throws IOException {
	    byte[] output = {};
	    if (length == -1) length = Integer.MAX_VALUE;
	    System.out.println(length);
	    int pos = 0;
	    while (pos < length) {
	        int bytesToRead;
	        if (pos >= output.length) { // Only expand when there's no room
	            bytesToRead = Math.min(length - pos, output.length + 1024);
	            if (output.length < pos + bytesToRead) {
	                output = Arrays.copyOf(output, pos + bytesToRead);
	            }
	        } else {
	            bytesToRead = output.length - pos;
	        }
	        int cc = is.read(output, pos, bytesToRead);
	        if (cc < 0) {
	            if (readAll && length != Integer.MAX_VALUE) {
	                throw new EOFException("Detect premature EOF");
	            } else {
	                if (output.length != pos) {
	                    output = Arrays.copyOf(output, pos);
	                }
	                break;
	            }
	        }
	        pos += cc;
	    }
	    System.out.println("logramos salir de un puto siclo Aaaahhhhhh!!!");
	    return output;
	}
	
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
			while (ind < cond) {
				line = br.readLine();
				if(line.equals("  <Code>add</Code>")) {
					System.out.println("como que si se puede lo que estoy pensando");
					cond = 11;
				}else if(line.equals("  <Code>elim</Code>")){
					System.out.println("va a dar error");
					cond = 4;
				}
				sb.append(line);
				ind++;
				System.out.println(ind);
			}
			System.out.println("Salimos bien del while");

		} catch (IOException e) {
			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}

		return sb.toString();

	}

}
