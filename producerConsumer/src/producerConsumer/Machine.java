package producerConsumer;

import java.awt.Color;

import producerConsumer.test_test.DrawCanvas;

public class Machine implements Runnable {
	
	BlockingQueue nextQueue = new BlockingQueue();
	BlockingQueue currentQueue = new BlockingQueue();
	ProductOriginator currentProduct ;
	boolean busy = false;
	Color color=Color.GREEN;
	boolean stop = false;
	String name;
	int x;
	int y;
	DrawCanvas canvas ;
	//Thread m;
	

	public Color getColor() {
		return color;
	}


	public Machine(BlockingQueue nextQueue,String name,int x,int y,DrawCanvas canvas ) {
		this.nextQueue = nextQueue;
		this.name=name;
		this.x=x;
		this.y=y;
		//this.canvas= canvas;
		Thread m = new Thread (this);
		m.start();
	}
	
	
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


	public String getName() {
		return name;
	}


	public boolean isStop() {
		return stop;
	}


	public boolean isBusy() {
		System.out.println("The machine busy = "+ busy+ " the name is "+name);
		return busy;
	}

	//as when the main queue finishes its tasks the thread should stop
	public void setStop(boolean stop) {
		this.stop = stop;
	/*	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	//this function will be called by the queue to give the machine work 
	public synchronized boolean setCurrentProduct(ProductOriginator currentProduct,BlockingQueue currentQueue) {
	
		if(!busy) {	
		this.currentProduct = currentProduct;
		this.currentQueue=currentQueue;
		this.busy=true;
		System.out.println("From Machine "+this.getName()+"   I take a new work see");
		//System.out.println(this.toString());
		//m.start();
		//this.run();
		return true;}
		return false;
	}


	@Override
	public void run() {
		while(!stop) {
			if(currentProduct!=null) {
				/*try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				System.out.println("from machine m1 I  have products right now ");
			    this.color= this.currentProduct.getColor();
			    this.canvas.changecolor(this.name, this.color);
			//this.canvas.repaint();
			try {
				System.out.println("From Machine "+this.getName()+" I am gonna sleep doing my work");
				
				Thread.sleep(1000);
				
				System.out.println("From Machine "+this.getName()+" I did my work see");
				System.out.println(this.toString());
				
				
				
				notifyQueue();
				Thread.sleep(20);
				
				//this.canvas.changecolor(this.name, this.color);
				//Thread.sleep(20);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//return;
				
			}}}
		System.out.println("some one stopped me bye bye"+" my name is "+name);
		return;
		
	}
	
	public void notifyQueue() {
		this.busy = false;
		nextQueue.put(currentProduct);
		//this.busy = false;
		this.currentProduct=null;
		this.color=Color.GREEN;
		//this.canvas.changecolor(this.name, this.color);
		currentQueue.update();
	/*	try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


	@Override
	public String toString() {
		return "Machine [busy=" + busy + ", color=" + color + ", stop=" + stop + ", name=" + name + "]";
	}
	
	
	

	
}
