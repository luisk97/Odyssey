package Trees;

public class BNode {
	
	public BNode nodoIzquierdo; 
	public BNode nodoDerecho; 
	public String dato; 

	public BNode(String dato) { 
	this.dato = dato; 
	nodoIzquierdo = nodoDerecho = null; 
	} 
	
	public synchronized void insertar(String iElemento){ 
		if(iElemento.compareTo(dato) < 0) {
			if(nodoIzquierdo == null) { 
				nodoIzquierdo = new BNode(iElemento); 
				nodoIzquierdo.insertar(iElemento);
			}
		}else if(iElemento.compareTo(dato) > 0){
			if(nodoDerecho == null) {
				nodoDerecho = new BNode( iElemento ); 
			}else {
				nodoDerecho.insertar(iElemento); 
			}
		} 
	} 

}
