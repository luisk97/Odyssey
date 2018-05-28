package Sort;

public class BubbleSort {

	public  void bubbleSort(LinkedList list) {

		if (list.getLarge() > 1) {

			for (int i = 0; i < list.getLarge(); i++ ) {

				Nodo current = list.getHead();
				Nodo next = list.getHead().getNext();
				for (int j = 0; j < list.getLarge() - 1; j++) {

					if (current.getDato().compareTo(next.getDato()) > 0) {

						String temp = current.getDato();
						current.setDato(next.getDato());
						next.setDato(temp);

					} 
					current = next;
					next = next.getNext();   
				}
			}
		}
	}
}