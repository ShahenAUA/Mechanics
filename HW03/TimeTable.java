import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TimeTable extends JFrame implements ActionListener {

	private JPanel screen = new JPanel(), tools = new JPanel();
	private JButton tool[];
	private JTextField field[];
	private CourseArray courses;
	private Color CRScolor[] = {Color.RED, Color.GREEN, Color.BLACK};
	
	private Autoassociator autoassociator;

	public TimeTable() {
		super("Dynamic Time Table");

		setSize(600, 800);
		setLayout(new FlowLayout());
		
		screen.setPreferredSize(new Dimension(400, 800));
		add(screen);
		
		setTools();
		add(tools);
		
		setVisible(true);
	}
	
	public void setTools() {
		String capField[] = {"Slots:", "Courses:", "Clash File:", "Cycles", "Iters:", "Shift:", "Train Slot:"};
		field = new JTextField[capField.length];
		
		String capButton[] = {"Load", "Start", "Train", "Step", "Print", "Continue", "Exit", "Trained Start"};
		tool = new JButton[capButton.length];
		
		tools.setLayout(new GridLayout(2 * capField.length + capButton.length, 1));
		
		for (int i = 0; i < field.length; i++) {
			tools.add(new JLabel(capField[i]));
			field[i] = new JTextField(7);
			tools.add(field[i]);
		}
		
		for (int i = 0; i < tool.length; i++) {
			tool[i] = new JButton(capButton[i]);
			tool[i].addActionListener(this);
			tools.add(tool[i]);
		}
		
		field[0].setText("19");
		field[1].setText("181");
		field[2].setText("yor-f-83.stu");
		field[3].setText("10");

		// field[0].setText("21");
		// field[1].setText("486");
		// field[2].setText("rye-s-93.stu");
		// field[3].setText("10");
		field[4].setText("1");
	}
	
	public void draw() {
		Graphics g = screen.getGraphics();
		int width = Integer.parseInt(field[0].getText()) * 10;
		for (int courseIndex = 1; courseIndex < courses.length(); courseIndex++) {
			g.setColor(CRScolor[courses.status(courseIndex) > 0 ? 0 : 1]);
			g.drawLine(0, courseIndex, width, courseIndex);
			g.setColor(CRScolor[CRScolor.length - 1]);
			g.drawLine(10 * courses.slot(courseIndex), courseIndex, 10 * courses.slot(courseIndex) + 10, courseIndex);
		}
	}
	
	private int getButtonIndex(JButton source) {
		int result = 0;
		while (source != tool[result]) result++;
		return result;
	}
	
	public void actionPerformed(ActionEvent click) {
		int min, step, clashes;
		
		switch (getButtonIndex((JButton) click.getSource())) {
		case 0:
			int slots = Integer.parseInt(field[0].getText());
			courses = new CourseArray(Integer.parseInt(field[1].getText()) + 1, slots);
			autoassociator = new Autoassociator(courses);
			courses.readClashes(field[2].getText());
			draw();
			break;
		case 1:
			min = Integer.MAX_VALUE;
			step = 0;
			for (int i = 1; i < courses.length(); i++) courses.setSlot(i, 0);
			
			for (int iteration = 1; iteration <= Integer.parseInt(field[4].getText()); iteration++) {
				courses.iterate(Integer.parseInt(field[5].getText()));
				draw();
				clashes = courses.clashesLeft();
				if (clashes < min) {
					min = clashes;
					step = iteration;
				}
			}

			System.out.println("Shift = " + field[5].getText() + "\tMin clashes = " + min + "\tat step " + step);
			setVisible(true);
			break;
		case 2:
			int trainSlot;
			if (field[6].getText().equals("")) {
				trainSlot = (int) (Math.random() * courses.length());
			}
			else {
				trainSlot = Integer.parseInt(field[6].getText());
			}
			// System.out.println("Before training:");
			// autoassociator.printWeights();
			// printPattern(courses.getTimeSlot(randomSlot));

        	autoassociator.training(courses.getTimeSlot(trainSlot));
			System.out.println("Training finished with random slot: " + trainSlot);

			// System.out.println("Before training:");
			// autoassociator.printWeights();
			// printPattern(courses.getTimeSlot(randomSlot));
			
			break;
		case 3:
			courses.iterate(Integer.parseInt(field[5].getText()), autoassociator);
			draw();
			break;
		case 4:
			System.out.println("Exam\tSlot\tClashes");
			for (int i = 1; i < courses.length(); i++)
				System.out.println(i + "\t" + courses.slot(i) + "\t" + courses.status(i));
			break;
		case 5:
			courses.iterate(Integer.parseInt(field[5].getText()), autoassociator);
			draw();
			clashes = courses.clashesLeft();
			System.out.println("Remaining clashes: " + clashes);
			setVisible(true);
			break;
		case 6:
			System.exit(0);
		case 7:
			min = Integer.MAX_VALUE;
			step = 0;
			for (int i = 1; i < courses.length(); i++) courses.setSlot(i, 0);
			
			for (int cycle = 1; cycle <= Integer.parseInt(field[3].getText()); cycle++) {
				for (int iteration = 1; iteration <= Integer.parseInt(field[4].getText()); iteration++) {
					courses.iterate(Integer.parseInt(field[5].getText()), autoassociator);
					draw();
					clashes = courses.clashesLeft();
					if (clashes < min) {
						min = clashes;
						step = iteration;
					}
				}
				int randomSlot = (int) (Math.random() * courses.length());
				autoassociator.unitUpdate(courses.getTimeSlot(randomSlot));
			}
			System.out.println("Shift = " + field[5].getText() + "\tMin clashes = " + min + "\tat step " + step);
			setVisible(true);
			break;
		}
	}

    // private void printPattern(int[] pattern) {
    //     System.out.println("Pattern:");
    //     for (int value : pattern) {
    //         System.out.print(value + " ");
    //     }
    //     System.out.println();
    // }

	public static void main(String[] args) {
		new TimeTable();
	}
}
