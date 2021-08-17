package producerConsumer;

import java.awt.Color;
import java.util.LinkedList;

public class Main {
/*
	public static void main(String[] args) {
		
	// TODO Auto-generated method stub
  ProductOriginator p1= new ProductOriginator(Color.blue);
 // ProductOriginator p2= new ProductOriginator(Color.red);
 /* ProductOriginator p3= new ProductOriginator("yellow");
  ProductOriginator p4= new ProductOriginator("purple");
  ProductOriginator p5= new ProductOriginator("Green");*/
  
 /* Queue q0 = new Queue();
 /* q0.enqueue(p1);
 // q0.enqueue(p2);
  /*q0.enqueue(p3);
  q0.enqueue(p4);
  q0.enqueue(p5);*/
 
 /* BlockingQueue lastQueue = new BlockingQueue("lastQueue",5,1);
  Machine m2 = new Machine(lastQueue,"m2");
 // Machine m2 = new Machine(lastQueue,"m2");
  //System.out.print(m1.toString());
  
  LinkedList machines = new LinkedList();
  machines.add(m2);
 // machines.add(m2);
  
  //BlockingQueue firstQueue = new BlockingQueue(q0,machines,"firstQueue",5,1);
  
  Queue emptyProducts = new Queue();
  
  BlockingQueue q1 = new BlockingQueue(emptyProducts,machines,"q1",5,1);
  
  Machine m1 = new Machine(q1,"m1");
  
   LinkedList machines1 = new LinkedList();
   machines1.add(m1);
  
   BlockingQueue firstQueue = new BlockingQueue(q0,machines1,"Q0",5,1);
   
  LinkedList allQueues = new LinkedList<BlockingQueue>();
  allQueues.add(firstQueue);
  allQueues.add(q1);
  //allQueues.add(lastQueue);
  
  LinkedList allMachines = new LinkedList<Machine>();
  allMachines.add(m1);
  allMachines.add(m2);
  lastQueue.setAllSimulation(allMachines, allQueues);
  
 //we will need to have all the machines in the system to be saved in one list 
 /* if(lastQueue.size()==firstQueue.size()) {
	  m1.setStop(true);
	  m2.setStop(true);
  }
  
  int count = Thread.activeCount();
  System.out.println("currently active threads = " + count);*/
 /* try {
	Thread.sleep(10000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/
		/*
boolean still = false;
  while(still) {
	 /* try {
			Thread.sleep(110);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	/*  System.out.println("hi from main new simulation nononononono");
      int flag=0;
  for(Object machine :  machines) {
		if(((Machine) machine).isStop()==false) {
			flag=1;
			break;
		}}
    if(flag==0)	{
    	for(Object machine :  machines) {
    		((Machine) machine).setStop(false); }
    	 p1.setFirstTime(false);
    	 p2.setFirstTime(false);
    	 p3.setFirstTime(false);
    	 p4.setFirstTime(false);
    	// p5.setFirstTime(false);
    	  q0.enqueue(p1);
    	  q0.enqueue(p2);
    	  q0.enqueue(p3);
    	  q0.enqueue(p4);
    	 // q0.enqueue(p5);
    	 
    	  firstQueue.pop();
    	  still=false;
    }
	}
*/
 }


