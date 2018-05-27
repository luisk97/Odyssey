package Trees;

public class BTree {

	private BNode nodoRaiz; 

	public BTree(){ 
		nodoRaiz = null; 
	} 

	public void insertar(String iElemento){ 
		if(nodoRaiz == null) {
			nodoRaiz = new BNode(iElemento); 
		}else { 
			nodoRaiz.insertar(iElemento); 
		}
	} 

	public BNode buscar(String iElemento){ 
		return buscar(iElemento, nodoRaiz); 
	} 

	private BNode buscar(String iElemento, BNode nodo){ 
		int iNivel = 1; 
		while(nodo != null){ 
			if(iElemento.compareTo(nodo.dato) < 0) {
				nodo = nodo.nodoIzquierdo; 
			}else if(iElemento.compareTo(nodo.dato) > 0) {
				nodo = nodo.nodoDerecho; 
			}else{
				System.out.println(iElemento+" encontrado en el nivel "+iNivel); 
				return nodo; 
			} 
			iNivel++; 
		} 
		System.out.println(iElemento+" no encontrado"); 
		return null;
	} 

	public void eliminar(String iElemento){ 
		nodoRaiz = eliminar(iElemento, nodoRaiz); 
	} 

	public BNode eliminar(String iElemento, BNode nodo){ 
		if(nodo == null){ 
			System.out.println(iElemento+" no encontrado"); 
			return null; 
		} 
		if(iElemento.compareTo(nodo.dato) < 0) {
			nodo.nodoIzquierdo = eliminar(iElemento, nodo.nodoIzquierdo); 
		}else if(iElemento.compareTo(nodo.dato) > 0) {
			nodo.nodoDerecho = eliminar(iElemento, nodo.nodoDerecho); 
		}else if(nodo.nodoIzquierdo != null && nodo.nodoDerecho != null){ 
			nodo.dato = nodoMin(nodo.nodoDerecho).dato; 
			nodo.nodoDerecho = eliminarMin(nodo.nodoDerecho); 
		}else{  
			if(nodo.nodoIzquierdo != null) {
				nodo = nodo.nodoIzquierdo; 
			}else { 
				nodo = nodo.nodoDerecho;
			}
		} 
		return nodo; 
	} 

	private BNode eliminarMin(BNode nodo){ 
		if(nodo == null) {
			return null; 
		}
		if(nodo.nodoIzquierdo != null) {
			nodo.nodoIzquierdo = eliminarMin(nodo.nodoIzquierdo); 
		}else {
			nodo = nodo.nodoDerecho; 
		}
		return nodo; 
	} 

	public BNode nodoMin(BNode nodo){ 
		if(nodo == null) {
			return null; 
		}
		while(nodo.nodoIzquierdo != null) {
			nodo = nodo.nodoIzquierdo; 
		}
		return nodo; 
	} 

	public BNode nodoMax(BNode nodo){ 
		if(nodo == null) {
			return null; 
		}
		while(nodo.nodoDerecho != null) {
			nodo = nodo.nodoDerecho; 
		}
		return nodo; 
	} 

	public void recorridoPreorden(){ 
		ayudantePreorden(nodoRaiz); 
	} 

	private void ayudantePreorden(BNode nodo){ 
		if(nodo == null) {
			return; 
		}
		System.out.print(nodo.dato + " " ); 
		ayudantePreorden(nodo.nodoIzquierdo ); 
		ayudantePreorden(nodo.nodoDerecho ); 
	} 

	public void recorridoInorden(){ 
		ayudanteInorden(nodoRaiz); 
	} 

	private void ayudanteInorden(BNode nodo){ 
		if(nodo == null) {
			return; 
		}
		ayudanteInorden(nodo.nodoIzquierdo); 
		System.out.print(nodo.dato + " " ); 
		ayudanteInorden(nodo.nodoDerecho); 
	} 

	public void recorridoPostorden(){ 
		ayudantePostorden(nodoRaiz); 
	} 

	private void ayudantePostorden(BNode nodo){ 
		if(nodo == null) {
			return; 
		}
		ayudantePostorden(nodo.nodoIzquierdo); 
		ayudantePostorden(nodo.nodoDerecho); 
		System.out.print(nodo.dato + " " ); 
	} 

	public void imprimir(){ 
		imprimir(nodoRaiz, 0); 
	} 

	private void imprimir(BNode nodo, int iAltura){ 
		char c[] = new char[iAltura]; 
		for(int i = 0; i < c.length; i++) { 
			c[i] = '-';
		}
		if(nodo == null) {
			return; 
		}
		System.out.println(new String(c) + nodo.dato); 
		iAltura++; 
		imprimir(nodo.nodoIzquierdo, iAltura); 
		imprimir(nodo.nodoDerecho, iAltura); 
	} 

	public boolean estaVacio(){ 
		return nodoRaiz == null; 
	} 

	public void vaciar(){ 
		nodoRaiz = null; 
	} 
} 

