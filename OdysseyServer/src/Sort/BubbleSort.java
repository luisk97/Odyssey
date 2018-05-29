package Sort;

public class BubbleSort {

	public  void bubbleSort(ListaEnlazada list) {

		if (list.getLarge() > 1) {

			for (int i = 0; i < list.getLarge(); i++ ) {

				Nodo current = list.getHead();
				Nodo next = list.getHead().getNext();
				for (int j = 0; j < list.getLarge() - 1; j++) {

					if (current.getSong().getAlbum().compareToIgnoreCase(next.getSong().getAlbum()) > 0) {

						Song temp = current.getSong();
						current.setSong(next.getSong());
						next.setSong(temp);

					} 
					current = next;
					next = next.getNext();   
				}
			}
		}
	}
}