import java.awt.*;
import javax.swing.*;

public class Triangle{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Sierpinski Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(825,525);
		JPanel panel = new JPanel();
		
		//Layout
		panel.setLayout(new FlowLayout());
		JPanel one = new JPanel();
		JPanel two = new JPanel();
		JPanel three = new JPanel();
		JPanel four = new JPanel();
		
		//Panel 1
		Canvas canvas = new Canvas();
		canvas.setSize(525,525);
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
		JTextField tfdepth = new JTextField("0",5);
		three.add(depth);
		three.add(tfdepth);
		String colors[] = {"Black", "Blue", "Cyan", "Dark Gray", "Gray", 
				"Green", "Orange", "Pink", "Red", "White", "Yellow"};
		JLabel lOne = new JLabel("          Color 1");
		JLabel lTwo = new JLabel("          Color 2");
		JLabel lThree = new JLabel("          Color 3");
		JLabel lFour = new JLabel("          Color 4");
		JLabel lFive = new JLabel("          Color 5");
		JComboBox cOne = new JComboBox(colors);
		cOne.setSelectedItem(colors[1]);
		JComboBox cTwo = new JComboBox(colors);
		cTwo.setSelectedItem(colors[2]);
		JComboBox cThree = new JComboBox(colors);
		cThree.setSelectedItem(colors[3]);
		JComboBox cFour = new JComboBox(colors);
		cFour.setSelectedItem(colors[4]);
		JComboBox cFive = new JComboBox(colors);
		cFive.setSelectedItem(colors[5]);
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
		
		//Panel 4
		JCheckBox cb = new JCheckBox();
		JLabel lb = new JLabel("Randomize colors at each level");
		four.add(cb);
		four.add(lb);
		two.add(four);
		
		//Panel 5
		JPanel five = new JPanel();
		JButton bt = new JButton("Draw!");
		bt.setPreferredSize(new Dimension(200,35));
		five.add(bt);
		two.add(five);
		
		
		//Orientation
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.add(one);
		panel.add(two);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	void draw(int d, int x, int y, int S) {
		// Done drawing, stop. if(d == 0) return;
		// Otherwise, draw big triangle at this level, between the points
		// shown in the figure. You can use the fillPolygon() method of
		// the Graphics object of your Canvas. Make sure you get the color
		// right!
		/*<INSERT YOUR CODE HERE>*/
		// Draw the subtriangles. The self-similarity of fractals means
		// that they are themselves Sierpinski triangles of depth d-1. draw(d-1, x+S/4, y, S/2);
		draw(d-1, x, y+S/2, S/2);
		draw(d-1, x+S/2, y+S/2, S/2);
	}
	
}

