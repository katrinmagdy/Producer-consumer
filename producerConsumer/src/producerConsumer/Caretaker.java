package producerConsumer;

import java.util.LinkedList;

public class Caretaker {
	LinkedList savedMachines = new LinkedList<productMomento>();
	
	public void  addMomento(productMomento pm) {
		savedMachines.add(pm);
	}
	public productMomento getMomento(int index) {
		return ((productMomento)savedMachines.get(index));
	}
}
