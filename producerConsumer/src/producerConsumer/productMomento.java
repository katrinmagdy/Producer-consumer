package producerConsumer;

public class productMomento {
	
	Machine currentRandomlyChosenMachine;
	
	
	public productMomento(Machine randomlyChosenMachine) {
		super();
		this.currentRandomlyChosenMachine = randomlyChosenMachine;
	}


	public Machine getSavedRandomlyChosenMachine() {
		return currentRandomlyChosenMachine;
	}
	

}
