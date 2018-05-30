package servidor;

import Trees.AVLTree;
import Trees.BTree;
import Trees.BinarySearchTree;
import Sort.BubbleSort;
import Sort.ListaEnlazada;
import Sort.QuickSort;
import Sort.Song;

public class MainPruebas {

	public static void main(String[] args) {
////		BinarySearchTree b = new BinarySearchTree();
////		b.add("daadsv");
////		b.add("dvssd");
////		b.add("nhbgdvf");
////		b.add("ada");
////		b.add("a");
////		b.add("rthfg");
////		b.add("ploiu");
////		b.add("zaxscdvf");
////		b.add("gtfrdrf");
////		b.add("rbfgcf");
////		System.out.println(b.findMin().elemento);
////		System.out.println(b.search("gtfrdrf"));
////		b.delete("gtfrdrf");
////		System.out.println(b.search("gtfrdrf"));
////		System.out.println("");
////		System.out.println("///////////////////////////////////////////////////////////////////");
////		AVLTree arbolAVL = new AVLTree();
////		String elemento1 = "a";
////		String elemento2 = "b";
////		String elemento3 = "c";
////		String elemento4 = "d";
////		String elemento5 = "e";
////		String elemento6 = "f";
////		String elemento7 = "g";
////		String elemento8 = "h";
////		String elemento9 = "i";
////		String elemento10 = "j";
////		arbolAVL.add(elemento1);
////		arbolAVL.add(elemento2);
////		arbolAVL.add(elemento3);
////		arbolAVL.add(elemento4);
////		arbolAVL.add(elemento5);
////		arbolAVL.add(elemento6);
////		arbolAVL.add(elemento7);
////		arbolAVL.add(elemento8);
////		arbolAVL.add(elemento9);
////		arbolAVL.add(elemento10);
////		arbolAVL.imprimirPorNiveles();
////		System.out.println("\n");
////		arbolAVL.imprimir();
////		System.out.println(arbolAVL.search(elemento8));
////		System.out.println(arbolAVL.findMin().dato);
////		arbolAVL.delete(elemento8);
//////		arbolAVL.delete(elemento1);
//////		arbolAVL.delete(elemento3);
////		System.out.println(arbolAVL.search(elemento8));
//////		arbolAVL.imprimirPorNiveles();
//
/////////////////////////////////////////////////////////      PRUEBAS DE SORT
////		LinkedList L = new LinkedList();
////		L.add("f");
////		L.add("vd");
////		L.add("bt");
////		L.add("bg");
////		L.add("vc");
////		L.add("sdc");
////		L.add("sdv");
////		L.add("tvrfcd");
////		L.add("adfd");
////		L.printList();
////		QuickSort q = new QuickSort();
////		q.quicksort(L);
////		LinkedList L1 = new LinkedList();
////		L1.add("f");
////		L1.add("vd");
////		L1.add("bt");
////		L1.add("bg");
////		L1.add("vc");
////		L1.add("sdc");
////		L1.add("sdv");
////		L1.add("tvrfcd");
////		L1.add("adfd");
////		BubbleSort d = new BubbleSort();
////		d.bubbleSort(L1);
////		LinkedList L2 = new LinkedList();
////		L2.add("f");
////		L2.add("vd");
////		L2.add("bt");
////		L2.add("bg");
////		L2.add("vc");
////		L2.add("sdc");
////		L2.add("sdv");
////		L2.add("tvrfcd");
////		L2.add("adfd");
////		RadixSort r = new RadixSort();
////		
////		System.out.println("///// ORDENADOS /////\n");
////		System.out.println("-----QUICKSORT");
////		L.printList();
////		System.out.println("-----BUBBLESORT");
////		L1.printList();
////		System.out.println("-----RADIXSORT");
////		//L2.printList();
////		
////		int i;
////        int[] arr = new int[15];
////        System.out.print("original: ");
////        for(i=0;i<arr.length;i++){
////            arr[i] = (int)(Math.random() * 1024);
////            System.out.print(arr[i] + " ");
////        }
////        r.radixSort(arr);
////        System.out.print("\nsorted: ");
////        for(i=0;i<arr.length;i++)
////            System.out.print(arr[i] + " ");
////        System.out.println("\nDone ;-)");
////        System.out.println(0x100);
//		
//		
//			//Inicializamos el árbol binario de búsqueda 
//			BTree arbol = new BTree(); 
//			//Desplegar las instrucciones 
//			System.out.println("DEMOSTRACION ARBOL DE BUSQUEDA BINARIA ");
//			System.out.println("insert");
//			arbol.insertar("frefv"); 
//			arbol.insertar("vsc"); 
//			arbol.insertar("vawc"); 
//			arbol.insertar("asds"); 
//			arbol.insertar("awdsd"); 
//			arbol.insertar("lkiuhjgyh"); 
//			arbol.insertar("tgfb"); 
//			arbol.insertar("bgt"); 
//			arbol.insertar("dbcbhb"); 
//			arbol.insertar("tfbd"); 
//			arbol.imprimir(); 
//			System.out.println("del");
//			arbol.eliminar("vawc"); 
//			arbol.imprimir(); 
//			System.out.println("search");
//			arbol.buscar("asds");
//			System.out.println("INORDER");
//			arbol.recorridoInorden(); 
//			arbol.buscar("awdsd");
		
		Song s1 = new Song("Go", "Flow", "G");
		Song s2 = new Song("Pain", "Nano", "D");
		Song s3 = new Song("Howling", "Flow", "J");
		Song s4 = new Song("Niji no sora", "Flow", "W");
		Song s5 = new Song("Snow fairy", "Funkist", "I");
		Song s6 = new Song("Black", "Ni idea", "F");
		Song s7 = new Song("Never", "Nano", "H");
		Song s8 = new Song("Ame no iru", "Sky space", "U");
		Song s9 = new Song("Seven", "Flow", "D");
		Song s10 = new Song("Aoi shiori", "Galileo galilei", "G");
		ListaEnlazada L1 = new ListaEnlazada();
		BubbleSort B = new BubbleSort();
		QuickSort Q = new QuickSort();
		L1.add(s1);
		L1.add(s2);
		L1.add(s3);
		L1.add(s4);
		L1.add(s5);
		L1.add(s6);
		L1.add(s7);
		L1.add(s8);
		L1.add(s9);
		L1.add(s10);
		B.bubbleSort(L1); // imprime por orden de album
		L1.printList();  
		System.out.println("////////////////////////////////////////");
		Q.quicksort(L1); // imprime por orden de titulo
		L1.printList();
	}
}