/*
 * File: Target.java
 * Name: Deepa
 * Program that draws an image of an archery target
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final double InchEquivalent = 72;
	public void run() {
		
		/*to get the co ordinates of the Middle of the canvas from which point GOval(since it does not start from 
		 * Middle) starts to draw. Constant value 36 is the half of height=width=diameter=72, of the outermost circle.
		 * Hence (deltax,deltay) are the coordinates at which the outermost circle will be drawn*/
		double deltax = (getWidth()/2)-36;
		double deltay = (getHeight()/2)-36;
		println("DeltaX = "+deltax);
		println("DeltaY = "+deltay);		
		
		double RedOutmostDiameter = InchEquivalent;
		
		/*outermost red circle drawn at origin (0,0) &filled with red*/
		GOval RedOutmost= new GOval(RedOutmostDiameter,RedOutmostDiameter);
		RedOutmost.setFilled(true);
		RedOutmost.setColor(Color.RED);
		add(RedOutmost);
		
		/*then moved to Middle, which was calculated*/
		RedOutmost.move(deltax, deltay);
		
		/*since Diameter was given in inches, needs to be converted to pixels to draw*/
		double MiddleCircleDiameterInches = 0.65;
		double MiddleCircleDiameterPixels = getPixels(MiddleCircleDiameterInches);
		println("Middle circle Diameter in pixels is " + MiddleCircleDiameterPixels);
		
		/*Middle white circle is drawn at (12.6,12.6) which is obtained by :
		 * height=width=diameter=46.8 after getPixels(), the displacement between the outermost & middle circle is
		 * (radius of outermost circle - radius of middle circle)=36-23.4=12.6 */
		GOval WhiteMiddle = new GOval(12.6,12.6,MiddleCircleDiameterPixels, MiddleCircleDiameterPixels);
		WhiteMiddle.setFilled(true);
		WhiteMiddle.setColor(Color.WHITE);
		add(WhiteMiddle);
		
		/*then moved to Middle, which was calculated*/
		WhiteMiddle.move(deltax, deltay);
		
		/*since Diameter was given in inches, needs to be converted to pixels to draw*/
		double InnermostCircleDiameterInches = 0.3;
		double InnermostCircleDiameterPixels = getPixels(InnermostCircleDiameterInches);
		println("Innermost circle Diameter in pixels is " + InnermostCircleDiameterPixels);
		
		/*Middle white circle is drawn at (25.2,25.2) which is obtained by :
		 * height=width=diameter=25.59 after getPixels(), the displacement between the outermost & innermost
		 * circle is (radius of outermost circle - radius of innermost circle)=36-10.795=25.205 */
		GOval RedInnermost = new GOval(25.2,25.2,InnermostCircleDiameterPixels, InnermostCircleDiameterPixels);
		RedInnermost.setFilled(true);
		RedInnermost.setColor(Color.RED);
		add(RedInnermost);
		
		/*then moved to Middle, which was calculated*/
		RedInnermost.move(deltax, deltay);
	}

	/*since Diameter was given in inches, needs to be converted to pixels to draw*/
	private double getPixels(double c){
		double d = InchEquivalent*c;
		return d;
	}
}
