package Sort;

public class QuickSort {
	
	public ListaEnlazada quicksort(ListaEnlazada list) {
		return quicksort(list, 0, list.getLarge() - 1);
	}
	
	public ListaEnlazada quicksort(ListaEnlazada list, int left, int right) {
		if(left >= right) {
			return list;
		}
		int izquierda = left, derecha= right;
		if (left != right) {
			int pivote;
			Song aux;
			pivote = left;
			while(left != right) {
				while(list.getNodo(right).getSong().getTitle().compareToIgnoreCase(list.getNodo(pivote).getSong().getTitle()) >= 0 && left < right) {
					right --;
				}
				while(list.getNodo(left).getSong().getTitle().compareToIgnoreCase(list.getNodo(pivote).getSong().getTitle()) < 0 && left < right) {
					left ++;	
				}
				if (right != left) {
					aux = list.getNodo(right).getSong();
					list.getNodo(right).setSong(list.getNodo(left).getSong());
					list.getNodo(left).setSong(aux);
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