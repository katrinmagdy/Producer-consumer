package producerConsumer;

import java.awt.Color;
import java.util.LinkedList;

public class ProductOriginator {
Color color;
boolean firstTime= true;
String name;
Machine randomlyChosenMachine;
int index=0;
Caretaker productCartaker=new Caretaker();





public Caretaker getProductCartaker() {
	return productCartaker;
}

public int getIndex() {
	return index;
}

public void setIndex(int index) {
	this.index = index;
}


public ProductOriginator(Color color) { 
	super();
	this.color = color;
	
}

public boolean isFirstTime() {
	return firstTime;
}

public void setFirstTime(boolean firstTime) {
	this.firstTime = firstTime;
}

public Color getColor() {
	return color;
}

public String getName() {
	return name;
}
@Override
public String toString() {
	return "Product [color=" + color + "]";
}

public void setRandomlyChosenMachine(Machine randomlyChosenMachine) {
	this.randomlyChosenMachine = randomlyChosenMachine;
}

public productMomento storeInMomento() {
	return new productMomento(randomlyChosenMachine);
}

public Machine restoreFromMomemto(productMomento pm) {
	randomlyChosenMachine=pm.getSavedRandomlyChosenMachine();
	return randomlyChosenMachine;
}


}
