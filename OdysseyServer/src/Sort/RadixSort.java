package Sort;

public class RadixSort {
	
	public void radix(int[] arreglo) {
		int x, i, j;
		for (x = Integer.SIZE - 1; x >= 0; x--) {
			int auxiliar[] = new int[arreglo.length];
			j = 0;
			for(i = 0; i < arreglo.length; i++) {
				boolean mover = arreglo[i] << x >= 0;
				if (x == 0 ? !mover:mover) {
					auxiliar[j] = arreglo[i];
					j ++;
				}else {
					arreglo[i - j] = arreglo[i];
				}
			}
			for (i = j; i < auxiliar.length; i ++) {
				auxiliar[i] = arreglo[i - j];
			}
			arreglo = auxiliar;
		}
		
	}
	
	public void radixSort(int[] arr){
        if(arr.length == 0)
            return;
        int[][] np = new int[arr.length][2];
        int[] q = new int[0x100];
        int i,j,k,l,f = 0;
        for(k=0;k<4;k++){
            for(i=0;i<(np.length-1);i++)
                np[i][1] = i+1;
            np[i][1] = -1;
            for(i=0;i<q.length;i++)
                q[i] = -1;
            for(f=i=0;i<arr.length;i++){
                j = ((0xFF<<(k<<3))&arr[i])>>(k<<3);
                if(q[j] == -1)
                    l = q[j] = f;
                else{
                    l = q[j];
                    while(np[l][1] != -1)
                        l = np[l][1];
                    np[l][1] = f;
                    l = np[l][1];
                }
                f = np[f][1];
                np[l][0] = arr[i];
                np[l][1] = -1;
            }
            for(l=q[i=j=0];i<q.length;i++)
                for(l=q[i];l!=-1;l=np[l][1])
                        arr[j++] = np[l][0];
        }
    }
	
}
