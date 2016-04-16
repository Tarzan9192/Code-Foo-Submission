import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * This class constructs a java gui which
 * allows the user to enter strings using the AZERTY
 * keyboard layout. It converts those strings to their
 * QWERTY equivalents, then displays the converted string
 * to the user.
 * 
 * ---NOTES---
 * CAPS-LOCK support has not been implemented.
 * -----------
 * @author Joshua Whitney
 *
 */
public class KeyboardConverter extends KeyAdapter{
	//Fields to hold incoming text from user
	//and text converted to qwerty keyboard layout.
	private JTextField incoming, converted;
	
	public static void main(String[] args){
		new KeyboardConverter();
	}
	
	KeyboardConverter(){
		JFrame jfrm = new JFrame("Keyboard Converter");
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setLocationRelativeTo(null);
		jfrm.add(buildPanel());
		jfrm.pack();
		jfrm.setResizable(false);
		jfrm.setVisible(true);
		
	}
	
	/**
	 * This method adds the text fields to the frame
	 * along with a button to clear both fields if
	 * user desires to do so.
	 * @return A panel containing the two text fields
	 * 			and the clear button.
	 */
	private JPanel buildPanel(){
		//Define text field objects
		incoming = new JTextField(15);
		converted = new JTextField(15);
		converted.setEditable(false);
		incoming.addKeyListener(this);
		
		//Main panel that method will return.
		JPanel jpnl = new JPanel();
		jpnl.setLayout(new BoxLayout(jpnl, BoxLayout.Y_AXIS));
		
		//AZERTY panel to handle user input.
		JPanel field1 = new JPanel();
		field1.add(new JLabel("AZERTY:"));
		field1.add(incoming);
		
		//Panel to show input converted to QWERTY
		JPanel field2 = new JPanel();
		field2.add(new JLabel("QWERTY:"));
		field2.add(converted);
		
		//Panel to hold 'clear' button.
		JPanel buttonField = new JPanel(new BorderLayout());				
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				incoming.setText("");
				converted.setText("");
			}
		});		
		buttonField.add(clearButton, BorderLayout.EAST);
		
		//Add all panels to main panel.
		jpnl.add(field1);
		jpnl.add(field2);
		jpnl.add(buttonField);
		jpnl.setVisible(true);
		
		return jpnl;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//If backspace is pressed, remove a character from
		//the converted string.
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if(converted.getText().length() > 0){
				converted.setText(converted.getText().substring(0,
						converted.getText().length() - 1));
			}
		}
		//Otherwise convert input AZERTY character to QWERTY	
		// and add to converted textfield.
		else{
			//Don't output keys with undefined chars, i.e. SHIFT,
			//ALT, TAB, etc.
			if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED){	
				char c = convert(e.getKeyChar());
				converted.setText(converted.getText() + c);
				System.out.println(e.getKeyCode());
			}
		}			
	}
	
	/**
	 * This method converts AZERTY input to QWERTY
	 * @param key
	 * @return QWERTY converted char.
	 */
	private char convert(char key){
		switch(key){
			//check '²' key
			case '²':			
				return '`';
			//check '&' key
			case '&':
				return '1';
			//check 'é' key
			case 'é':
				return '2';
			case '"':
				return '3';
			case '\'':
				return '4';
			case '(':
				return '5';
			case '-':
				return '6';
			case 'è':
				return '7';
			case '_':
				return '8';
			case 'ç':
				return '9';
			case 'à':
				return '0';
			case ')':
				return '-';
			case '1':
				return '!';			
			case '2':
				return '@';
			case '3':
				return '#';
			case '4':
				return '$';
			case '5':
				return '%';
			case '6':
				return '^';
			case '7':
				return '&';
			case '8':
				return '*';
			case '9':
				return '(';
			case '0':
				return ')';
			case '°':
				return '_';
			case 'a':
				return 'q';
			case 'A':
				return 'Q';
			case 'z':
				return 'w';
			case 'Z':
				return 'W';
			case '^':
				return '[';
			case '¨':
				return '{';
			case '$':
				return '}';
			case 'q':
				return 'a';
			case 'Q':
				return 'A';
			case 'm':
				return ';';
			case 'M':
				return ':';
			case 'ù':
				return '\'';
			case '%':
				return '"';
			case '*':
				return '\\';
			case 'µ':
				return '|';
			case 'w':
				return 'z';
			case 'W':
				return 'Z';
			case ',':
				return 'm';
			case '?':
				return 'M';
			case ';':
				return ',';
			case '.':
				return '<';
			case ':':
				return '.';
			case '/':
				return '>';
			case '!':
				return '/';
			case '§':
				return '?';	
			case '<':
				return '>';
			case '>':
				return '<';
			default:
				return key;
		}		
	}
}
