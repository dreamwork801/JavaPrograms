/** Zach Colby*
 * 15 April 2013
 * Sierpinski Triangle Visualizer
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SierpinskiVisualizer {

	private static final int SIZE = 512; // should be a power of 2
	
	// Colors to display.
	private static enum COLOR {
		BLUE(Color.BLUE,"Blue"),
		BLACK(Color.BLACK, "Black"),
		CYAN(Color.CYAN, "Cyan"),
		YELLOW(Color.YELLOW, "Yellow"),
		GRAY(Color.GRAY, "Gray"),
		GREEN(Color.GREEN, "Green"),
		ORANGE(Color.ORANGE, "Orange"),
		PINK(Color.PINK, "Pink"),
		RED(Color.RED, "Red"),
		WHITE(Color.WHITE, "White"),
		DARK_GRAY(Color.DARK_GRAY, "Dark Gray");
		
		private Color color;
		private String name;
		
		COLOR(Color c, String n) {
			color = c;
			name = n;
		}
		
		public Color getColor() {
			return color;
		}
		
		public String toString() {
			return name;
		}
	}
	
	// GUI components
	private JFrame frame;
	private Canvas canvas;
	private Graphics graphics;
	private JTextField tfdepth;
	private JComboBox cOne;
	private JComboBox cTwo;
	private JComboBox cThree;
	private JComboBox cFour;
	private JComboBox cFive;
	private JCheckBox cb;
	
	// Used to keep track of coloring the triangles correctly.
	private COLOR[] c = new COLOR[5];
	
	private int dtext;
	int i = 0;
	int k = dtext;
	int flag = 0;
	// Generate the GUI
	public SierpinskiVisualizer() {
		
		// Build the frame.
		JFrame frame = new JFrame("Sierpinski Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(825,525);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		
		//Layout
		panel.setLayout(new FlowLayout());
		JPanel one = new JPanel();
		JPanel two = new JPanel();
		JPanel three = new JPanel();
		JPanel four = new JPanel();
		
		//Panel 1
		final Canvas canvas = new Canvas();
		canvas.setSize(SIZE,SIZE);
		canvas.setBackground(Color.black);
		one.add(canvas);

		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
	
		//Blank Panels
		JPanel blank = new JPanel();
		blank.setSize(5,5);
		two.add(blank);
		
		//Panel 3
		three.setLayout(new GridLayout(7, 2, 0, 10));
		JLabel depth = new JLabel("Recursive Depth");
		final JTextField tfdepth = new JTextField("0",5);
		three.add(depth);
		three.add(tfdepth);

		JLabel lOne = new JLabel("          Color 1");
		JLabel lTwo = new JLabel("          Color 2");
		JLabel lThree = new JLabel("          Color 3");
		JLabel lFour = new JLabel("          Color 4");
		JLabel lFive = new JLabel("          Color 5");
		final JComboBox cOne = new JComboBox(COLOR.values());
		cOne.setSelectedItem(COLOR.BLUE);
		final JComboBox cTwo = new JComboBox(COLOR.values());
		cTwo.setSelectedItem(COLOR.BLACK);
		final JComboBox cThree = new JComboBox(COLOR.values());
		cThree.setSelectedItem(COLOR.CYAN);
		final JComboBox cFour = new JComboBox(COLOR.values());
		cFour.setSelectedItem(COLOR.GRAY);
		final JComboBox cFive = new JComboBox(COLOR.values());
		cFive.setSelectedItem(COLOR.GREEN);
		three.add(lOne);
		three.add(cOne);
		three.add(lTwo);
		three.add(cTwo);
		three.add(lThree);
		three.add(cThree);
		three.add(lFour);
		three.add(cFour);
		three.add(lFive);
		three.add(cFive);
		two.add(three);
		
		// Add randomize input. Put a listener on the check box to control whether the colors are enabled.
		final JCheckBox cb = new JCheckBox();
		JLabel lb = new JLabel("Randomize colors at each level");
		cb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cb.isSelected() == true){
					cOne.setEnabled(false);
					cTwo.setEnabled(false);
					cThree.setEnabled(false);
					cFour.setEnabled(false);
					cFive.setEnabled(false);
				}
				else{
					cOne.setEnabled(true);
					cTwo.setEnabled(true);
					cThree.setEnabled(true);
					cFour.setEnabled(true);
					cFive.setEnabled(true);
				}
			}
		});
		four.add(cb);
		four.add(lb);
		two.add(four);
		
		//Panel 5
		JPanel five = new JPanel();
		JButton bt = new JButton("Draw!");
		bt.setPreferredSize(new Dimension(200,35));
		bt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Get Value From Text Field
				flag = 0;
				
				//Make sure the textfield is an integer between 0 and 10, otherwise throw NumberFormatException
				try {
				String stext = tfdepth.getText();
				dtext = Integer.parseInt(stext);
				if (dtext > 10 || dtext < 1)
					throw new NumberFormatException();
				}
				catch (NumberFormatException num){
					JOptionPane.showMessageDialog(null, "Please insert a number between 1 and 10");
					flag = 1;
				}
				if (flag == 0){
				// Generate the color scheme to use.
				if (cb.isSelected()) {
					for(int r = 0; r<5; r++){
					int rand = new Random().nextInt(COLOR.values().length);
					c[r] = (COLOR) COLOR.values()[rand];
					}
				}
				else{
				//Store ComboBox Values
				c[0] =  (COLOR) cOne.getSelectedItem();
				c[1] =  (COLOR) cTwo.getSelectedItem();
				c[2] =  (COLOR) cThree.getSelectedItem();
				c[3] =  (COLOR) cFour.getSelectedItem();
				c[4] =  (COLOR) cFive.getSelectedItem();
				i=0;
				k=0;
				}
				draw();
				}
			}
		});
		five.add(bt);
		two.add(five);
		
		//Orientation
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(one);
		panel.add(two);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		// Get the graphics object of the canvas. It's important to do this AFTER the frame is
		//	visible, since before this there is no graphics object associated with the canvas.
		graphics = canvas.getGraphics();
	}
	
	// Draw it!
	public void draw() {
		
//		// Draw over the previous canvas.
		int xP[] = {0, 0, SIZE, SIZE};
	    int yP[] = {0, SIZE, 0, SIZE};
	    int nP = 4;
	    graphics.setColor(Color.BLACK);
		graphics.fillPolygon(xP, yP, nP);
		
		int x = 0;
		int y = 0;
		int S = SIZE;
		// Draw the base triangle.
		int xPoints[] = {x, x+S/2, x+S};
	    int yPoints[] = {y+S, y, y+S};
	    int nPoints = 3;
	    graphics.setColor(Color.WHITE);
		graphics.fillPolygon(xPoints, yPoints, nPoints);
		
		// Now draw the rest of the inner triangles with the recursive function.
		
		draw(dtext,0,0,SIZE);
	}
	
	// Recursive function to draw triangles at a given depth at the specified square given.
	private void draw(int d, int x, int y, int S) {
		if (d==0)
			return;
		
		k = dtext-d;
		if (k>=5)
			k=k-5;
		// Otherwise, draw big triangle at this level, between the points
		// shown in the figure. You can use the fillPolygon() method of
		// the Graphics object of your Canvas. Make sure you get the color
		// right!
		int xPoints[] = {x+S/4, x+3*S/4, x+S/2};
	    int yPoints[] = {y+S/2, y+S/2, y+S};
	    int nPoints = 3;
	    graphics.setColor(c[k].getColor());
		graphics.fillPolygon(xPoints, yPoints, nPoints);
	
		// Draw the subtriangles. The self-similarity of fractals means
		// that they are themselves Sierpinski triangles of depth d-1.
		// draw top middle triangle
		draw(d-1, x+S/4, y, S/2);
		
		// draw bottom left triangle
		draw(d-1, x, y+S/2, S/2);
		
		// draw bottom right triangle
		draw(d-1, x+S/2, y+S/2, S/2);
	}
	
	public static void main(String[] args) {
		new SierpinskiVisualizer();
	}
}