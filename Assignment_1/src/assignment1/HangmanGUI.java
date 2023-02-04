package assignment1;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class HangmanGUI {
	private static final String FILE_NAME = "src/assignment1/hangman.txt";
	private static ArrayList<String> words = new ArrayList<>();
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel wordLabel;
	private static JTextField letterField;
	private static JButton guessButton;
	private static JLabel missesLabel;

	public static void main(String[] args) {
	// TODO Auto-generated method stub

		readWordsFromFile();

		frame = new JFrame("Hangman");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new GridLayout(3, 1));

		wordLabel = new JLabel("Word: ");
		panel.add(wordLabel);

		letterField = new JTextField();
		panel.add(letterField);

		guessButton = new JButton("Guess");
		guessButton.addActionListener(new GuessButtonListener());
		panel.add(guessButton);

		missesLabel = new JLabel("Misses: ");
		panel.add(missesLabel);

		frame.add(panel);
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
