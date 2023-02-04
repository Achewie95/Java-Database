
	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JTextField;

	public class HangmanGUI {
		private static final String FILE_NAME = "src/hangman.txt";
		private static ArrayList<String> words = new ArrayList<>();
		private static JFrame frame;
		private static JPanel panel;
		private static JLabel wordLabel;
		private static JTextField letterField;
		private static JButton guessButton;
		private static JLabel missesLabel;
		private static JLabel titleLabel;

		public static void main(String[] args) {
		// TODO Auto-generated method stub

			readWordsFromFile();

			frame = new JFrame("Hangman");
			frame.setSize(500, 500);
			frame.setLayout(new BorderLayout(10,10));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			//Create and change Icon Image
			ImageIcon image = new ImageIcon("src/hangman3.jpeg"); //creates an image
			frame.setIconImage(image.getImage()); // sets the image

			
			//Setting up Panel for Title, to be added to frame
			JPanel panelTitle = new JPanel();
			//Creating a title for the game
			titleLabel = new JLabel("Welcome to the Hangman Game");
			Font font2 = new Font("Ariel",Font.BOLD,20); //setting the font and size
			titleLabel.setFont(font2);
			titleLabel.setForeground(new Color(182,81,73));
			panelTitle.add(titleLabel);
			
			//Set panelTitle to the North border of the Frame
			frame.add(panelTitle, BorderLayout.NORTH);
			
			
			//Creating a New Panel for the Guessed Word
			JPanel PanelWord = new JPanel ();
			//Creating a display for the word picked
			wordLabel = new JLabel("Hangman Word: ");
			Font font = new Font("Ariel",Font.BOLD,20); //setting the font and size
			wordLabel.setFont(font);
			PanelWord.add(wordLabel);
			PanelWord.setPreferredSize(new Dimension(100,100)); //setting size of panel
			
			//Set PanelWord to the South of the Frame
			frame.add(PanelWord, BorderLayout.SOUTH);
			
			//Creating a New Panel for the Text field, button and misses
			JPanel PanelTextField = new JPanel();
			letterField = new JTextField();
			letterField.setPreferredSize(new Dimension(100,100));
			
			//adding button
			guessButton = new JButton("Guess");
			guessButton.addActionListener(new GuessButtonListener());
			
			//adding missed count Label
			missesLabel = new JLabel("Missed Count: ");
			
			missesLabel.setHorizontalTextPosition(JLabel.CENTER);
			missesLabel.setBackground(new Color(224,155,128));
			Font font3 = new Font("Ariel",Font.BOLD,20);
			missesLabel.setFont(font3);
			
			PanelTextField.setLayout(new GridLayout(3,1,10,10));
			PanelTextField.setBounds(50, 50, 250, 250);
			
			PanelTextField.add(missesLabel);
			PanelTextField.add(letterField);
			PanelTextField.add(guessButton);
			
			frame.add(PanelTextField, BorderLayout.CENTER);
			
			
			frame.setVisible(true);
		}

		private static class GuessButtonListener implements ActionListener {
			private int misses = 0;
			private String word;
			private char[] wordArray;
			private char[] asterisks;
			private ArrayList<Character> missedLetters;
			private boolean wordGuessed;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (word == null) {
					word = pickRandomWord();
					wordArray = word.toCharArray();
					asterisks = new char[wordArray.length];
					for (int i = 0; i < asterisks.length; i++) {
						asterisks[i] = '*';
					}
					missedLetters = new ArrayList<>();
					wordGuessed = false;
				}
				String guess = letterField.getText();
				if (guess.length() != 1) {
					JOptionPane.showMessageDialog(frame, "Please enter a single letter");
					return;
				}
				char letter = guess.charAt(0);
				if (word.contains(guess)) {
					for (int i = 0; i < wordArray.length; i++) {
						if (wordArray[i] == letter) {
							asterisks[i] = letter;
						}
					}
					wordLabel.setText("Word: " + String.valueOf(asterisks));
					wordGuessed = true;
					for (char c : asterisks) {
						if (c == '*') {
							wordGuessed = false;
							break;
						}
					}
					if (wordGuessed) {
						JOptionPane.showMessageDialog(frame, "You win! The word was: " + word);
						word = null;
						wordLabel.setText("Word: ");
					}
				} else {
					if (missedLetters.contains(letter)) {
						JOptionPane.showMessageDialog(frame, "You've already tried this letter, try another letter");
					} else {
						misses++;
						missesLabel.setText("Misses: " + misses);
						missedLetters.add(letter);
					}
				}
			}
		}

		private static String pickRandomWord() {
			Random rand = new Random();
			int index = rand.nextInt(words.size());
			return words.get(index);
		}

		private static void readWordsFromFile() {
			try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
				String line;
				while ((line = br.readLine()) != null) {
					words.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static void addWordToFile(String word) {
			try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
				writer.write(word + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}