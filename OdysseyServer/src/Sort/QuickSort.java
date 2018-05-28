package Sort;

public class QuickSort {
	
	public LinkedList quicksort(LinkedList list) {
		return quicksort(list, 0, list.getLarge() - 1);
	}
	
	public LinkedList quicksort(LinkedList list, int left, int right) {
		if(left >= right) {
			return list;
		}
		int izquierda = left, derecha= right;
		if (left != right) {
			int pivote;
			String aux;
			pivote = left;
			while(left != right) {
				while(list.getNodo(right).getDato().compareTo(list.getNodo(pivote).getDato()) >= 0 && left < right) {
					right --;
				}
				while(list.getNodo(left).getDato().compareTo(list.getNodo(pivote).getDato()) < 0 && left < right) {
					left ++;	
				}
				if (right != left) {
					aux = list.getNodo(right).getDato();
					list.getNodo(right).setDato(list.getNodo(left).getDato());
					list.getNodo(left).setDato(aux);
				}
				if (left == right) {
					quicksort(list, izquierda, left - 1);
					quicksort(list, left + 1, derecha);
				}
			}
		}
		else {
			return list;
		}
		return list;
	}
}
