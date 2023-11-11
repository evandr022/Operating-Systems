import java.io.*;
import java.util.*;
import java.util.LinkedList;

class FIFO {
	// POSPRIMEIRO posição do elemento mais antigo
	private int order = 0;
	protected int numFaults;
	protected int numFrames;
	LinkedList<String> frames = new LinkedList<String>();

	public FIFO(int numFrames) {
		if (numFrames < 0) {
			throw new IllegalArgumentException();
		}else {
			this.numFrames = numFrames;
			this.numFaults = 0;
		}
	}
	
	public int getNumFaults() {
		return numFaults;
	}

	public void insert(String pageNumber) {
		// Se a pagina nao estiver na memoria
		if(!frames.contains(pageNumber)) {
			// Se a quantidade de pag na memoria for menor que a quantidade de frames
			if(frames.size() < numFrames) {
				frames.add(pageNumber);
			}else {
				frames.remove(order);
				frames.add(order, pageNumber);
				order ++;
				// se o mais velho for o ultimo, volta pro comeco
				if(order == numFrames) {
					order = 0;
				}
			}
			numFaults++;
		}
	}
}

class LRU {
	protected int numFrames;
	protected int numFaults = 0;
	LinkedList<String> frames = new LinkedList<String>();

	public LRU(int numFrames) {
		if (numFrames < 0) {
			throw new IllegalArgumentException();
		}else {
			this.numFrames = numFrames;
		}
	}
		
	public int getNumFaults() {
		return numFaults;
	}

	public void insert(String pageNumber) {
		// Se a pagina nao estiver na memoria
		if(!frames.contains(pageNumber)) {
			// Se a quantidade de pag na memoria for menor que a quantidade de frames
			if(frames.size() < numFrames) {
				frames.add(pageNumber);
			}else {
				// remove o primeiro da lista que e o mais antigo
				frames.remove(0);
				// adiciona no final da lista o novo valor sendo o mais recente
				frames.add(pageNumber);
			}
			numFaults++;
		}else {
			// se estiver na lista
			// remove ele da posição atual
			frames.remove(frames.indexOf(pageNumber));
			// insere novamente no final como mais recente
			frames.add(pageNumber);
		}
	}
}

class CLOCK {
	private int pointer = 0;
	protected int numFrames;
	protected int numFaults = 0;
	LinkedList<String> frames = new LinkedList<String>();
	LinkedList<Integer> bits = new LinkedList<Integer>();
	
	public CLOCK(int numFrames) {
		if (numFrames < 0) {
			throw new IllegalArgumentException();
		}else {
			this.numFrames = numFrames;
		}
	};
	
	public int getNumFaults() {
		return numFaults;
	}
	
	public void insert(String pageNumber) {
		// Se a pagina nao estiver na memoria
		if(!frames.contains(pageNumber)) {
			// Se a quantidade de pag na memoria for menor que a quantidade de frames
			// insere na lista de frames o novo valor
			// insere na lista de bits o bit do novo valor == 0
			if(frames.size() < numFrames) {
				frames.add(pageNumber);
				bits.add(0);
			}else {
				// se nao possui coloca todos os que sao igual a 1 em 0 ate achar o
				// primeiro zero
				while (bits.get(pointer).equals(1)) {
					bits.set(pointer, 0);
					pointer++;
					if(pointer == numFrames) {
						pointer = 0;
					}
				}
				frames.remove(pointer);
			    bits.remove(pointer);
			    frames.add(pointer, pageNumber);
			    bits.add(pointer, 0);
			    pointer++;
			    if (pointer == numFrames) {
			        pointer = 0;
			    }
			}
			numFaults++;
		}else {
			// se ja esta na memoria seu ponteiro vai para zero
			bits.set(frames.indexOf(pageNumber),1);	
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.printf("Digite a referencia: ");
			String reference = scanner.nextLine();
			String[] stringReference = reference.split(";");	
			System.out.println();
			
			FIFO fifo = new FIFO(8000);
			for (int i = 0; i < (stringReference.length - 1); i++) {
				fifo.insert(stringReference[i]);
			}
			System.out.println("Page Faults FIFO: " + fifo.getNumFaults());
			
			LRU lru = new LRU(8000);
			for (int i = 0; i < (stringReference.length - 1); i++) {
				lru.insert(stringReference[i]);
			}
			System.out.println("Page Faults LRU: " + lru.getNumFaults());
			
			CLOCK clock = new CLOCK(8000);
			for (int i = 0; i < (stringReference.length - 1); i++) {
				clock.insert(stringReference[i]);
			}
			System.out.println("Page Faults CLOCK: " + clock.getNumFaults());
		}
	}
}