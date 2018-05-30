package Sort;

public class RadixSort {
	
	public ListaEnlazada radix(ListaEnlazada list) {
		return radix(list, 0, list.getLarge() - 1);
	}
	
	public ListaEnlazada radix(ListaEnlazada list, int left, int right) {
		if(left >= right) {
			return list;
		}
		int izquierda = left, derecha= right;
		if (left != right) {
			int pivote;
			Song aux;
			pivote = left;
			while(left != right) {
				while(list.getNodo(right).getSong().getArtist().compareToIgnoreCase(list.getNodo(pivote).getSong().getArtist()) >= 0 && left < right) {
					right --;
				}
				while(list.getNodo(left).getSong().getArtist().compareToIgnoreCase(list.getNodo(pivote).getSong().getArtist()) < 0 && left < right) {
					left ++;	
				}
				if (right != left) {
					aux = list.getNodo(right).getSong();
					list.getNodo(right).setSong(list.getNodo(left).getSong());
					list.getNodo(left).setSong(aux);
				}
				if (left == right) {
					radix(list, izquierda, left - 1);
					radix(list, left + 1, derecha);
				}
			}
		}
		else {
			return list;
		}
		return list;
	}
}