package project_v02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MySnipTool extends JFrame implements ActionListener{

    public int flag=0,prevx=0,prevy=0,backx=0,backy=0,firstvalue,secondvalue;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    String[]name={"ROOM1","ROOM2","ROOM3","ROOM4","ROOM5","ROOM6","ROOM7","ROOM8","ROOM9","ROOM10","ROOM11","ROOM12","ROOM13","ROOM14","ROOM15"};
 
    public MySnipTool(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setUndecorated(true);
                frame.setBackground(new Color(0, 0, 0, 0));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new CapturePane());
                Rectangle bounds = getVirtualBounds();
                frame.setLocation(bounds.getLocation());
                frame.setSize(bounds.getSize());
                frame.setAlwaysOnTop(true);
                frame.setVisible(true);
            }
        });
       
        JFrame frame2= new JFrame("Drawing line");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel p = new JPanel();
                p.setLayout(new BorderLayout());
                Container container = frame2.getContentPane();
                container.setLayout(new BorderLayout());

                Line line =new Line();
           
                button1= new JButton("DOOR");
                button1.addActionListener(this);
                button2= new JButton("ROOM");
                button2.addActionListener(this);
                button3= new JButton("NAME");
                button3.addActionListener(this);
                button4= new JButton("PATH");
                button4.addActionListener(this);
                p.add(button1,BorderLayout.EAST);
                p.add(line,BorderLayout.CENTER);
                p.add(button2,BorderLayout.WEST);
                p.add(button3,BorderLayout.SOUTH);
                p.add(button4,BorderLayout.NORTH);
                container.add(p);
                frame2.setSize(2000,1000);
                frame2.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== button1){    
            flag=2;
        }
        else if(ae.getSource()== button2){
            flag=1;
        }
        else if(ae.getSource()== button3){
            flag=3;
        }
        else if(ae.getSource()== button4){    
            flag=4;
            System.out.println(flag);
        }
        
    } 

    public class CapturePane extends JPanel {

        private Rectangle selectionBounds;
        private Point clickPoint;
        
        public int xcoor=0,ycoor=0,widlen=0,heilen=0,x1,x2,x3,x4,y1,y2,y3,y4;
        int[] arax1=new int[20];
        int[] arax2=new int[20];
        int[] arax3=new int[20];
        int[] arax4=new int[20];
        int[] aray1=new int[20];
        int[] aray2=new int[20];
        int[] aray3=new int[20];
        int[] aray4=new int[20];

        public CapturePane() {
            
            setOpaque(false);

            MouseAdapter mouseHandler;
            mouseHandler = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                        System.exit(0);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    clickPoint = e.getPoint();
                    selectionBounds = null;
                    File file=new File("exapmle.txt");
                    if(flag==2){
                        try {
                                Writer wr=new FileWriter("exapmle2.txt",true);
                                wr.write(String.valueOf(clickPoint.x));
                                wr.write(" ");
                                wr.write(String.valueOf(clickPoint.y));
                                wr.write(" ");
                                
                                wr.close();
                            } catch (IOException ex) {
                                Logger.getLogger(MySnipTool.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                    else if(flag==3){
                        int i=0,counter=0;
                        try {
                        
                        Scanner sc = new Scanner(file);

                        while (sc.hasNextInt()) {
                            if(counter==0){
                                arax1[i]=sc.nextInt();
                                counter=1;
                            }
                            else if(counter==1){
                                aray1[i]=sc.nextInt();
                                counter=2;
                            }
                            else if(counter==2){
                                arax2[i]=sc.nextInt();
                                counter=3;
                            }
                            else if(counter==3){
                                aray2[i]=sc.nextInt();
                                counter=4;
                            }
                            else if(counter==4){
                                arax3[i]=sc.nextInt();
                                counter=5;
                            }
                            else if(counter==5){
                                aray3[i]=sc.nextInt();
                                counter=6;
                            }
                            else if(counter==6){
                                arax4[i]=sc.nextInt();
                                counter=7;
                            }
                            else if(counter==7){
                                aray4[i]=sc.nextInt();
                                counter=0;
                                i++;
                            }
                            
                        }
                        sc.close();
                    } 
                    catch (FileNotFoundException f) {
                        f.printStackTrace();
                    }
                        
                        for(int j=0;j<arax1.length;j++){
                            if(clickPoint.x>arax1[j]&&clickPoint.x<arax2[j]&&clickPoint.y>aray1[j]&&clickPoint.y<aray4[j])
                            {
                               System.out.println(name[j]);
                            
                            }
                        
                        
                    }
                    
                    }
                    
                    else if(flag==4){
                        System.out.println("asdf");
                        if(backx==0&&backy==0){
                            backx=clickPoint.x;
                            backy=clickPoint.y;
                        }
                        else{
                            
                            System.out.println(backx);
                            System.out.println(backy);
                            if(clickPoint.x>=264&&clickPoint.x<=443&&clickPoint.y>=343&&clickPoint.y<=488){
                                firstvalue=1;
                            }
                            else if(clickPoint.x>=447&&clickPoint.x<=637&&clickPoint.y>=345&&clickPoint.y<=479){
                                firstvalue=2;
                            }
                            else if(clickPoint.x>=637&&clickPoint.x<=736&&clickPoint.y>=344&&clickPoint.y<=480){
                                firstvalue=3;
                            }
                            else if(clickPoint.x>=738&&clickPoint.x<=920&&clickPoint.y>=347&&clickPoint.y<=521){
                                firstvalue=4;
                            }
                            else if(clickPoint.x>=922&&clickPoint.x<=1063&&clickPoint.y>=346&&clickPoint.y<=523){
                                firstvalue=5;
                            }
                            else if(clickPoint.x>=925&&clickPoint.x<=1061&&clickPoint.y>=530&&clickPoint.y<=705){
                                firstvalue=6;
                            }
                            else if(clickPoint.x>=858&&clickPoint.x<=926&&clickPoint.y>=546&&clickPoint.y<=705){
                                firstvalue=7;
                            }
                            else if(clickPoint.x>=736&&clickPoint.x<=860&&clickPoint.y>=572&&clickPoint.y<=707){
                                firstvalue=8;
                            }
                            else if(clickPoint.x>=611&&clickPoint.x<=738&&clickPoint.y>=584&&clickPoint.y<=704){
                                firstvalue=9;
                            }
                            else if(clickPoint.x>=443&&clickPoint.x<=611&&clickPoint.y>=587&&clickPoint.y<=704){
                                firstvalue=10;
                            }
                            else if(clickPoint.x>=258&&clickPoint.x<=434&&clickPoint.y>=578&&clickPoint.y<=709){
                                firstvalue=11;
                            }
                            
                            
                            
                            if(backx>=264&&backx<=443&&backy>=343&&backy<=488){
                                secondvalue=1;
                            }
                            else if(backx>=447&&backx<=637&&backy>=345&&backy<=479){
                                secondvalue=2;
                            }
                            else if(backx>=637&&backx<=736&&backy>=344&&backy<=480){
                                secondvalue=3;
                            }
                            else if(backx>=738&&backx<=920&&backy>=347&&backy<=521){
                                secondvalue=4;
                            }
                            else if(backx>=922&&backx<=1063&&backy>=346&&backy<=523){
                                secondvalue=5;
                            }
                            else if(backx>=925&&backx<=1061&&backy>=530&&backy<=705){
                                secondvalue=6;
                            }
                            else if(backx>=858&&backx<=926&&backy>=546&&backy<=705){
                                secondvalue=7;
                            }
                            else if(backx>=736&&backx<=860&&backy>=572&&backy<=707){
                                secondvalue=8;
                            }
                            else if(backx>=611&&backx<=738&&backy>=584&&backy<=704){
                                secondvalue=9;
                            }
                            else if(backx>=443&&backx<=611&&backy>=587&&backy<=704){
                                secondvalue=10;
                            }
                            else if(backx>=258&&backx<=434&&backy>=578&&backy<=709){
                                secondvalue=11;
                            }
                            
                            
                            backx=0;
                            backy=0;
                            
                        }
                    }
                    
                }

                @Override
                public void mouseReleased(MouseEvent g) {
                    if(flag==1){
                        System.out.println(xcoor+" "+ycoor+" "+widlen+" "+heilen);
                        x1=xcoor;
                        y1=ycoor;
                        x2=xcoor+widlen;
                        y2=ycoor;
                        x3=xcoor+widlen;
                        y3=ycoor+heilen;
                        x4=xcoor;
                        y4=ycoor+heilen;
                        System.out.println(x1+" "+y1);
                        System.out.println(x2+" "+y2);
                        System.out.println(x3+" "+y3);
                        System.out.println(x4+" "+y4);
                            try {
                                Writer wr=new FileWriter("exapmle.txt",true);
                                wr.write(String.valueOf(x1));
                                wr.write(" ");
                                wr.write(String.valueOf(y1));
                                wr.write(" ");
                                wr.write(String.valueOf(x2));
                                wr.write(" ");
                                wr.write(String.valueOf(y2));
                                wr.write(" ");
                                wr.write(String.valueOf(x3));
                                wr.write(" ");
                                wr.write(String.valueOf(y3));
                                wr.write(" ");
                                wr.write(String.valueOf(x4));
                                wr.write(" ");
                                wr.write(String.valueOf(y4));
                                wr.write(" ");
                                wr.close();
                            } catch (IOException ex) {
                                Logger.getLogger(MySnipTool.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                    clickPoint = null;
                    }
                @Override
                public void mouseDragged(MouseEvent e) {
                    Point dragPoint = e.getPoint();
                    int x = Math.min(clickPoint.x, dragPoint.x);
                    int y = Math.min(clickPoint.y, dragPoint.y);
                    int width = Math.max(clickPoint.x - dragPoint.x, dragPoint.x - clickPoint.x);
                    int height = Math.max(clickPoint.y - dragPoint.y, dragPoint.y - clickPoint.y);
                    selectionBounds = new Rectangle(x, y, width, height);
                    
                    xcoor=x;
                    ycoor=y;
                    widlen=width;
                    heilen=height;
                    
                    flag=1;
                    
                    repaint();
                }
            };

            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(255, 255, 255, 128));

            Area fill = new Area(new Rectangle(new Point(75, 60), new Dimension(1080,900)));
            if (selectionBounds != null) {
                fill.subtract(new Area(selectionBounds));
            }
            g2d.fill(fill);
            if (selectionBounds != null) {
                g2d.setColor(Color.BLACK);
                g2d.draw(selectionBounds);
            }
            g2d.dispose();
        }
    }
    public static Rectangle getVirtualBounds() {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice lstGDs[] = ge.getScreenDevices();
        for (GraphicsDevice gd : lstGDs) {
            bounds.add(gd.getDefaultConfiguration().getBounds());
        }
        return bounds;
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.WHITE);        
        if(firstvalue==1&&secondvalue==2||(firstvalue==2&&secondvalue==1)){
            int[] araxxx={310,310,520,520,510,510,320,320,310};
            int[] arayyy={420,480,480,410,410,470,470,420,420};
            g2.drawPolyline(araxxx,arayyy,araxxx.length);
            firstvalue=0;
            secondvalue=0;
            }
    }
    
}

