package producerConsumer;

import java.util.LinkedList;
import java.util.Random;


public class BlockingQueue {
	Queue q = new Queue();
	LinkedList nextMachines = new LinkedList<Machine>();
	Random rand = new Random();
	boolean valuePop = false;
	String name;
	static int flagStartCheckEnd=0;
	int x;
	int y;
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	LinkedList allMachines = new LinkedList<Machine>();
	LinkedList allQueues = new LinkedList<BlockingQueue>();//except last queue
	
	public void setAllSimulation(LinkedList allMachines,LinkedList allQueues) {
		flagStartCheckEnd=0;
		System.out.println("hi from the set all simulation");
		this.allMachines = allMachines;
		this.allQueues = allQueues;
		if(this.name.equalsIgnoreCase("lastQueue")) {
			flagStartCheckEnd=1;
		}
	}


	public void checkSimulationEnd() {
		if(this.name.equalsIgnoreCase("lastQueue")) {
			//flagStartCheckEnd=0;
		System.out.println("hi from checkSimulationEnd");
		int flag =0;
		for(Object queue : allQueues) {
			if(((BlockingQueue)queue).size()!=0) {
				flag=1;
				break;}
		}
		if(flag==0) {
		for(Object machine :  allMachines) {
			if(((Machine) machine).isBusy()==true){
				flag=1;
				break;
			}}}
		
		if(flag==0) {
			for(Object machine :  allMachines) {
				((Machine) machine).setStop(true);
				/*try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		}}
		}}

//for first queue
	public BlockingQueue(Queue q, LinkedList nextMachines,String name,int x,int y) {
		super();
		this.q = q;
		this.nextMachines = nextMachines;
		this.name=name;
		this.x=x;
		this.y=y;
		//while(q.size()>0) {this.pop();}
		System.out.println("current queue size is "+q.size()+" the name is"+this.name);
		this.pop();
	}
	
	
	//for last queue
	public BlockingQueue(String name,int x,int y) {
		super();
		this.name = name;
		this.x=x;
		this.y=y;
	}

// for machine queue
	public BlockingQueue() {
		super();
	}


	public synchronized void put (ProductOriginator product) {
		while(valuePop) {
			try {
				System.out.println("The queue is busy poping some other elements");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
		/*if(flagStartCheckEnd==1) {
			checkSimulationEnd();
			
		}*/
		/*try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		q.enqueue(product);
		System.out.println("done the queue put the current element");
		System.out.println("current queue size is "+q.size()+" the name is"+this.name);
		
		if(/*this.size()==1&&*/( !this.name.equalsIgnoreCase("lastQueue"))&&( !this.name.equalsIgnoreCase("Q0"))) {
			this.pop();
		}
		
		if(flagStartCheckEnd==1) {
			checkSimulationEnd();
			
		}
		
		
	}
	Machine currentMachine;
	int i=1;//for testing
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public synchronized void pop () {
		//int n = rand.nextInt(nextMachines.size());
		while(q.size()>0 /*&& this.nextMachines!=null*/) {
		System.out.println("the operation num"+i);
			//int n = rand.nextInt(nextMachines.size());
		/*while(n>nextMachines.size()-1) {
			n = rand.nextInt(4);
		}*/
		
		
		 currentMachine = (Machine) nextMachines.get(0);
		 ProductOriginator currentProduct = (ProductOriginator)q.peek();
		 
			if(currentProduct.firstTime==false) {
				int index=currentProduct.getIndex();
				currentMachine = currentProduct.restoreFromMomemto(currentProduct.getProductCartaker().getMomento(index));
				currentProduct.setIndex(index+1);
				
			}else {
			currentProduct.setRandomlyChosenMachine(currentMachine);
			currentProduct.getProductCartaker().addMomento(currentProduct.storeInMomento());}
		 
		
		while(currentMachine.isBusy()) {
			try {
				System.out.println("The Machine "+currentMachine.getName()+"  is busy right now we gonna wait");
				
				int count = Thread.activeCount();
				System.out.println("currently active threads = " + count);
				
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(!currentMachine.isBusy()) {
			System.out.println("ohh Machine "+currentMachine.getName()+"  is ready now pass the product to it");
			valuePop=true;
			//the machine is not busy so give it a product from the current queue
		    q.dequeue();
			boolean machineState = currentMachine.setCurrentProduct(currentProduct,this);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			valuePop=false;
			//System.out.println("Machine m1 took the task succssessfully");
			//System.out.println("after the machine took current queue size is "+q.size()+" the name is"+this.name);
			
		}
		i++;
}
	return;
			}
	
	public synchronized void update() {
		System.out.println("hello from our new nella method");
		//System.out.println("the current queue from the nella method"+this.name);
		/*if(name.equalsIgnoreCase("mainQueue")&&this.size()==0) {
			for(Object machine :  nextMachines) {
				((Machine) machine).setStop(true);
			}
		}*/
		/*try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(!currentMachine.isBusy()) {
			notify();
		}
		//notify();
		
	}
	public int size() {
		return q.size();
	}
}