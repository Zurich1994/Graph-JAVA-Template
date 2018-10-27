package project;
import java.applet.*;
import java.awt.geom.*;
import java.awt.*;

public class Applet1 extends Applet{
    
    public void paint(Graphics g){

    g.setColor(Color.blue);
    g.drawOval(200,100,80,100);
    g.setColor(Color.green);
    g.fillOval(220,120,14,7);
    g.setColor(Color.red);
    g.fillOval(250,120,14,7);
    g.setColor(Color.black);
    g.drawArc(220,140,40,20,180,180);
    Graphics2D g_2d=(Graphics2D)g;//
    String s="天空蓝蓝白云飘";
    Ellipse2D ellipse=new Ellipse2D.Double(30,30,80,30);//
    AffineTransform trans=new AffineTransform();//
    g.setColor(Color.yellow);
    for(int i=1;i<=24;i++)
    {
    trans.rotate(15.0*Math.PI/180,70,70);//
    g_2d.setTransform(trans);//现在画的就是旋转后的椭圆样子//
    g_2d.draw(ellipse);//
    }
    g.setColor(Color.pink);
    for(int i=1;i<=12;i++)
    {
    trans.rotate(30.0*Math.PI/180,100,200);//
    g_2d.setTransform(trans);//现在画的就是旋转后的字符串//
    g_2d.drawString(s,100,200);//
    }
    }
}

