/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_v02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author User
 */


public class MySnipTool() {
    public MySnipTool() {
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
    }

    public class CapturePane extends JPanel {

        private Rectangle selectionBounds;
        private Point clickPoint;
        
        public int flag=0,xcoor=0,ycoor=0,widlen=0,heilen=0,x1,x2,x3,x4,y1,y2,y3,y4;
        
      
        File file=new File("example.txt");
        
       

        public CapturePane() {
            setOpaque(false);

            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                        System.exit(0);
                    }
                    else{
                        
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    clickPoint = e.getPoint();
                    selectionBounds = null;
                    flag=0;
                    //System.out.println(clickPoint);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
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

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(255, 255, 255, 128));

            Area fill = new Area(new Rectangle(new Point(0, 0), getSize()));
            if (selectionBounds != null) {
                //fill.subtract(new Area(selectionBounds));
                
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
}
