package project;
import java.applet.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
public class Applet2 extends Applet{
	public void paint(Graphics g){
		
	    Graphics2D g2d=(Graphics2D)g;
	    g2d.setPaint( new GradientPaint( 5, 40, Color.BLUE, 15, 50, Color.YELLOW, true ) ); 
	    g2d.fill( new Ellipse2D.Double( 80, 30, 110, 150 ) );//渐变色椭圆
	    
	    super.paint( g );  
	      g.setColor( Color.RED );
	      g.drawLine( 200, 200, 180, 400 );//线
	      
	      g.setColor( Color.BLUE );
	      g.drawRect( 5, 200, 90, 55 );
	      g.fillRect( 100, 200, 90, 55 );//矩形
	      
	      g.setColor( Color.GRAY );
	      g.fillRoundRect( 195, 200, 90, 55, 50, 50 );
	      g.drawRoundRect( 290, 200, 90, 55, 20, 20 ); //圆角矩形     

	}

}
