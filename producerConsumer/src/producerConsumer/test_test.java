package producerConsumer;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import java.awt.Point;
import java.awt.Button;
import java.awt.Choice;

public class test_test extends JFrame {
	private JPanel contentPane;
    public static final int CANVAS_WIDTH  = 640;
    public static final int CANVAS_HEIGHT = 480;
    public DrawCanvas canvas;
    int xM=1;
    int xQ=0;
    int x,y;
    private JTextField From;
    private JTextField To;
    LinkedList Color_of_products = new LinkedList<>();
    
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    test_test frame = new test_test();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public test_test() {
        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 882, 615);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JButton Ms = new JButton("Ms");
        Ms.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Ms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LinkedList data = new LinkedList<>();
                data.add("M"+xM);
                data.add(Color.GREEN);
                canvas.l.add(data);
                xM++;
            }
        });
        Ms.setBounds(45, 123, 104, 36);
        contentPane.add(Ms);

        JButton Qs = new JButton("Qs");
        Qs.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Qs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LinkedList data = new LinkedList<>();
                data.add("Q"+xQ);
                data.add(Color.YELLOW);
                canvas.l.add(data);
                xQ++;
            }
        });
        Qs.setBounds(45, 184, 104, 30);
        contentPane.add(Qs);

        JPanel panel = new JPanel();
        panel.setBounds(199, 22, 640, 480);
        panel.add(canvas);
        panel.addMouseListener(canvas);
        contentPane.add(panel);

        JButton join = new JButton("Join");
        join.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LinkedList to = new LinkedList<>();
                LinkedList Data = new LinkedList();
                boolean found = false;
                //check if user enter more than one component in from text box
                if (From.getText().contains(",")) {
                    JOptionPane.showMessageDialog(null, "You cannot enter more than one component in from text box!");
                } else {
                    for (int i = 0; i < canvas.l.size(); i++) {
                        Data = (LinkedList) canvas.l.get(i);
                        if (Data.get(0).equals(From.getText().toUpperCase())) {
                            if (Data.size() > 4) {
                                to = (LinkedList) Data.get(4);
                            }
                            //convert linked list To to array
                            String[] temp = To.getText().split(",");
                            
                            //check if user enter more than queue to be connected to the machine
                            if ((From.getText().toUpperCase().charAt(0) == 'M') && (temp.length > 1)) {
                                JOptionPane.showMessageDialog(null, "You can connect the machine to only one queue!");
                            } else {
                                for (int t = 0; t < temp.length; t++) {
                                    //check if the user enter non existed component
                                    if(t>0&&!found){
                                        break;
                                    }
                                    //check if the user want to connect same kinds
                                    else if(From.getText().charAt(0) == temp[t].charAt(0)){
                                        JOptionPane.showMessageDialog(null, "You cannot connect same kind of components!");
                                        found = true;
                                        break;
                                    }
                                    found = false;
                                    for (int j = 0; j < canvas.l.size(); j++) {
                                        LinkedList list = (LinkedList) canvas.l.get(j);
                                        if ((list.get(0).equals(temp[t].toUpperCase())) && !(to.contains(j))) {
                                            found = true;
                                            to.add(j);
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    //check if all unwanted cases don't happen
                    if (!(From.getText().toUpperCase().charAt(0) == 'M' && to.size() > 1) && found) {

                        if (Data.size() > 4) {
                            Data.set(4, to);
                        } else {
                            Data.add(to);
                        }
                    }
                    //if you don't found component alert the user
                    if (!found){
                        JOptionPane.showMessageDialog(null, "Your components cannot be found!");
                    }
                }
                
                canvas.repaint();
            }
        });
        join.setFont(new Font("Tahoma", Font.PLAIN, 18));
        join.setBounds(45, 243, 104, 36);
        contentPane.add(join);

        From = new JTextField();
        From.setBounds(68, 303, 121, 36);
        contentPane.add(From);
        From.setColumns(10);

        To = new JTextField();
        To.setBounds(68, 353, 121, 36);
        contentPane.add(To);
        To.setColumns(10);

        JLabel lblNewLabel = new JLabel("From");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 307, 58, 27);
        contentPane.add(lblNewLabel);

        JLabel lblTo = new JLabel("To");
        lblTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTo.setBounds(10, 354, 58, 27);
        contentPane.add(lblTo);

        JButton btnNewButton = new JButton("Simulate");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(Color_of_products.size()==0) {
            		JOptionPane.showMessageDialog(null, "You must enter products");
            	}
            	else if(canvas.l.size()==0) {
            		JOptionPane.showMessageDialog(null, "You must add components to the canvas");
            	}
            	else {
            		//for loop to change strings to type color
            		for(int i=0;i<Color_of_products.size();i++) {
            			if(Color_of_products.get(i)=="RED") {
            				Color_of_products.set(i, Color.RED);
            			}
            			else if(Color_of_products.get(i)=="BLUE") {
            				Color_of_products.set(i, Color.BLUE);
            			}
            			else if(Color_of_products.get(i)=="MAGENTA") {
            				Color_of_products.set(i, Color.MAGENTA);
            			}
            			else if(Color_of_products.get(i)=="CYAN") {
            				Color_of_products.set(i, Color.CYAN);
            			}
            			else if(Color_of_products.get(i)=="GRAY") {
            				Color_of_products.set(i, Color.GRAY);
            			}
            			else if(Color_of_products.get(i)=="PINK") {
            				Color_of_products.set(i, Color.PINK);
            			}
            			else if(Color_of_products.get(i)=="ORANGE") {
            				Color_of_products.set(i, Color.ORANGE);
            			}
            		}
            		//for loop to add the name to the linked list of products
            		for(int i=0;i<Color_of_products.size();i++) {
            			LinkedList p = new LinkedList<>();
            			p.add(""+i+1);
            			p.add(Color_of_products.get(i));
            			Color_of_products.set(i, p);
            		}
            		xQ--;
            		BlockingQueue lastQueue = null;
            		LinkedList q = new LinkedList<>();
        			LinkedList m = new LinkedList<>();
        			Object[] extra = new Object[canvas.l.size()];
            		for(int i=canvas.l.size()-1;i>=0;i--) {
      
            			
            			
            			LinkedList data = (LinkedList) canvas.l.get(i);
            			String s = "Q"+xQ;
            			if(data.get(0).equals(s)) {
            				 lastQueue = new BlockingQueue("lastQueue",(int)data.get(2),(int)data.get(3));
            				 extra[i] = new Object();
            				extra[i]=lastQueue;
            				//System.out.println("extra :"+extra[i].toString());
            			}
            			else if(((String)data.get(0)).contains("M")) {
            				LinkedList to = (LinkedList) data.get(4);
            				Machine M =new Machine((BlockingQueue)extra[(int)to.get(0)],(String)data.get(0),(int)data.get(2),(int)data.get(3),canvas);
            				m.add(M);
            				data.set(1, M.getColor());
            				extra[i] = new Object();
            				extra[i]=M;
            				//System.out.println("extra :"+extra[i].toString()+" i="+i);
            			}
            			else {
            				LinkedList to = (LinkedList) data.get(4);
            				LinkedList machines = new LinkedList<Machine>();
            				for(int j=0;j<to.size();j++) {
            					//System.out.println(" extra :"+extra[1].toString());
            					//System.out.println("index"+(int)to.get(j)+  "  extra :"+extra[(int)to.get(j)].toString());
            					machines.add((Machine)extra[(int)to.get(j)]);
            				}
            				Queue products = new Queue();
            				if(data.get(0).equals("Q0")) {
            					for(int j=0;j<Color_of_products.size();j++) {
            						LinkedList p = (LinkedList) Color_of_products.get(j);
            						ProductOriginator po = new ProductOriginator((Color)p.get(1));
            						products.enqueue(po);
            					}
            				}
            				//System.out.println(machines.toString());
            				//System.out.println("extra :"+extra.toString());
            				BlockingQueue Q = new BlockingQueue(products,machines,(String)data.get(0),(int)data.get(2),(int)data.get(3));
            				extra[i] = new Object();
            				extra[i]=Q;
            				//System.out.println("extra :"+extra[i].toString());
            				q.add(Q);
            			
            				
            			}
            			 
            		}
            		lastQueue.setAllSimulation(m,q);
            		System.out.println("num of machines created from gui "+m.size());
            		
            	}
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(351, 519, 121, 38);
        contentPane.add(btnNewButton);

        JList products = new JList();
        products.setEnabled(false);
        products.setBounds(10, 480, 162, 66);
        contentPane.add(products);

        JComboBox options = new JComboBox();
        options.setModel(new DefaultComboBoxModel(new String[] {"RED", "BLUE", "MAGENTA", "CYAN", "GRAY", "PINK", "ORANGE"}));
        options.setBounds(10, 406, 162, 21);
        contentPane.add(options);

        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String colorproduct = (String) options.getSelectedItem();
                Color_of_products.add(colorproduct);

                String[] a = new String[Color_of_products.size()];
                for(int i=0;i<Color_of_products.size();i++) {
                    a[i]=(String) Color_of_products.get(i);
                }
                products.setListData(a);
            }
        });
        enter.setBounds(105, 445, 85, 21);
        contentPane.add(enter);

        JScrollPane scrollPane = new JScrollPane(products);
        scrollPane.setBounds(10, 480, 162, 77);
        contentPane.add(scrollPane);
        
        JButton redraw = new JButton("Re-draw");
        redraw.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(canvas.flag==1) {
        			canvas.l.clear();
        			canvas.repaint();
        		}
        	}
        });
        redraw.setFont(new Font("Tahoma", Font.PLAIN, 18));
        redraw.setBounds(45, 63, 104, 36);
        contentPane.add(redraw);



    }
    
    

    public class DrawCanvas extends JPanel implements MouseListener {
        LinkedList l = new LinkedList<>();
        public int flag=1;
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.WHITE);  // set background color for this JPanel

            for(int i=0;i<l.size();i++) {
                LinkedList s = (LinkedList) l.get(i);
                if(s.get(0).toString().contains("Q")) {
                    g.setColor((Color) s.get(1));
                    g.fillRect((int)s.get(2),(int) s.get(3), 50, 40);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    g.drawString((String)s.get(0),(int)s.get(2)+(50/2) , (int)s.get(3)+(40/2));

                }
                else {
                    g.setColor((Color) s.get(1));
                    g.fillOval((int)s.get(2),(int) s.get(3), 50, 40);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    g.drawString((String)s.get(0), (int)s.get(2)+(50/2) ,(int)s.get(3)+(40/2));
                }
                
               if(s.size()>4) {
                	LinkedList lines = (LinkedList) s.get(4);
                	for(int j=0; j<lines.size();j++) {
                		LinkedList to = (LinkedList) l.get((int)lines.get(j));
                		g.setColor(Color.BLACK);
                		int x1 =(int)s.get(2);
                		int y1 =(int)s.get(3)+(40/2);
                		int x2 =(int)to.get(2)+50;
                		int y2 =(int)to.get(3)+(40/2);
                		g.drawLine(x1,y1,x2,y2);
                		double theta = Math.atan2(y2 - y1, x2 - x1);
                		drawArrow((Graphics2D)g, theta, x2, y2);
                		
               	     }
                	
                }
            } 
          }
		@Override
		public void mouseClicked(MouseEvent m) {
			// TODO Auto-generated method stub
			x = m.getX();
            y = m.getY();
            LinkedList data = (LinkedList) l.getLast();
            if(data.size()==2) {
                data.add(x);
                data.add(y);
                repaint();
            }
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
        
        private void drawArrow(Graphics2D g2, double theta, double x0, double y0)
        {
            double x = x0 - 20 * Math.cos(theta + Math.PI/6);
            double y = y0 - 20 * Math.sin(theta + Math.PI/6);
            g2.draw(new Line2D.Double(x0, y0, x, y));
            x = x0 - 20 * Math.cos(theta - Math.PI/6);
            y = y0 - 20 * Math.sin(theta - Math.PI/6);
            g2.draw(new Line2D.Double(x0, y0, x, y));
        }
        
        public synchronized void changecolor (String name , Color c) {
        	
        	for(int i=0; i<canvas.l.size();i++) {
        		LinkedList data = (LinkedList) canvas.l.get(i);
        		if(data.get(0).equals(name)) {
        			data.set(1, c);
        			break;
        		}
        	}
        	canvas.repaint(20);
        	/*try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
        	
        }
        
       public void setFlag(int flag) {
    	   this.flag=flag;
       }
        
        

   
    }
    }