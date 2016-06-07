import acm.graphics.*;
import acm.program.*;
public class times extends GraphicsProgram {
/** Runs the program */
public void run() {
GLabel label = new GLabel(HEADLINE);
label.setFont("Times-72");
add(label, getWidth(), (getHeight() + label.getAscent()) / 2);
while (label.getX() + label.getWidth() > 0) {
label.move(-DELTA_X, 0);
pause(PAUSE_TIME);
}
}
/** The number of pixels to shift the label on each cycle */
private static final double DELTA_X = 2.0;
/** The number of milliseconds to pause on each cycle */
private static final int PAUSE_TIME = 20;
/** The string to use as the value of the label */
private static final String HEADLINE =
"I like to move it , move it. Ya like to move it!!! " +
"I gotta feeling that tonight's gonna be a good night " +
"That tonight's gonna be a good night . . .";
}

