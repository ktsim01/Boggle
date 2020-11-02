package assignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Boggle extends JComponent {
    private static final long serialVersionUID = 1L;

    //pixel width/height for the boggle grid
    private static final int PIXEL_SIZE = 400;
    //how much border to give each square
    private static final int OFFSET = 5;
    

    //default values
    private static final String DEFAULT_CUBE_FILE = "cubes.txt";
    private static final String DEFAULT_DICT_FILE = "words.txt";
    private static final int DEFAULT_SIZE = 4;
    private static final int TEXT_FIELD_WIDTH = 20;
    private static final String NO_WORDS_MSG = "You have not successfully guessed any words yet!";
    private static final int COMPUTER = 0;
    private static final int DEFAULT_PLAYER_COUNT = 2;
    
    //UI constants
    private static final String LETTER_FONT = "Arial";
    private static final int WORD_LIST_WIDTH = 30;

    public static void main(String[] args) throws IOException {
        //create JFrame window for the game
        JFrame frame = new JFrame();
        frame.setTitle("Boggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add boggle and components to the JFrame
        Boggle boggle = new Boggle();
        boggle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //boggle board
        c.gridy = 0;
        c.weighty = 0;
        panel.add(boggle,c);

        //word lists
        c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(boggle.createWordListPanel(),c);
        
        //control panel
        c.gridy = 2;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(boggle.createControlPanel(),c);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.getContentPane().add(panel);
        
        //display the window
        frame.pack();
        frame.setVisible(true);

        //stop the program once the window closes
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //game variables
    private BoggleGame gameInstance;
    private BoggleDictionary dictInstance;
    private boolean currentGame;
    private boolean showHighlightedWord;
    private int currentPlayer;
    private TreeSet<String>[] guessedWords;
    
    //game settings
    private int SIZE = DEFAULT_SIZE;
    private String cubeFile = DEFAULT_CUBE_FILE;
    private String dictFile = DEFAULT_DICT_FILE;
    private int numPlayers = DEFAULT_PLAYER_COUNT; 
    
    //swing components
    private JTextField textField;
    private JButton newGame;
    private JButton finishGame;
    private JTextArea playerWordList;
    private JLabel playerScore;
    private JPanel wordPanel;
    private JLabel warningLabel;

    public Boggle() throws IOException {
    	//fix the size of the board
        setPreferredSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
        setMaximumSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
        setMinimumSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));

        //initialize game variables
        gameInstance = new GameManager();
        dictInstance = new GameDictionary();
        dictInstance.loadDictionary(dictFile);
        
    }
    
    /*
     * Creates control panel and sets up user input listeners
     */
    private JPanel createControlPanel() throws IOException {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());

        textField = new JTextField(TEXT_FIELD_WIDTH);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGame = new JButton("New Game");
        finishGame = new JButton("Finish Turn");
        warningLabel = new JLabel("Enter your guess below");

        //warning label
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        controlPanel.add(warningLabel, c);

        //text field
        c.gridx = 0;
        c.gridy = 1;
        JPanel tempPanel = new JPanel();
        textField.setMinimumSize(textField.getPreferredSize());
        tempPanel.add(textField);
        controlPanel.add(textField, c);

        //new game/finish game buttons
        c.gridx = 0;
        c.gridy = 2;
        tempPanel = new JPanel();
        tempPanel.add(newGame);
        tempPanel.add(finishGame);
        controlPanel.add(tempPanel, c);
        
        //setup input listeners
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onTextFieldChange();
            }
        });
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    newGame();
                } catch(IOException exec) {
                    System.err.println("There was an issue loading the cubes file.");
                }
            }
        });
        finishGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finishTurn();
            }
        });
        
        //start the new game
        newGame();
        return controlPanel;
    }
    
    /*
     * Instantiates the word list panel, and returns it.
     */
    private JPanel createWordListPanel() {
        wordPanel = new JPanel();
        wordPanel.setLayout(new GridBagLayout());
        
        playerWordList = new JTextArea();
        playerWordList.setLineWrap(true);
        playerWordList.setWrapStyleWord(true);
        playerWordList.setColumns(WORD_LIST_WIDTH);
        playerWordList.setEditable(false);
        playerWordList.setText(NO_WORDS_MSG);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        playerScore = new JLabel("Player 1's Score: 0");
        wordPanel.add(playerScore, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        wordPanel.add(playerWordList, c);
        
        wordPanel.setPreferredSize(new Dimension(0,400));
        wordPanel.setMinimumSize(new Dimension(0,400));
        return wordPanel;
    }
    
    /*
     * Starts a new game.
     * Only works if the old one has finished.
     */
    private void newGame() throws IOException {
        if(currentGame)
            updateWarning("Finish the current game before starting a new one!");
        else {
        	String error = null;
        	while(true) {
        		try{
        			displayConfigWindow(error);
        			break;
        		} catch(IOException e) {
        			error = "Please enter a valid file!";
        		} catch(Exception e) {
        			error = e.getMessage();
        		}
        	}
            guessedWords = new TreeSet[numPlayers];
            for(int i = 0; i < numPlayers; i++)
            	guessedWords[i] = new TreeSet<>();
            currentPlayer = 0;

            currentGame = true;
            textField.setText("");
            //reset UI components
            finishTurn();
        }
    }
    
    private void displayConfigWindow(String errorMsg) throws IOException {
    	JTextField playerField = new JTextField(20);
        JTextField sizeField = new JTextField(20);
    	JTextField cubeFileField = new JTextField(20);
        JTextField dictionaryFileField = new JTextField(20);
        playerField.setText(""+(numPlayers-1));
        sizeField.setText(""+SIZE);
        cubeFileField.setText(cubeFile);
        dictionaryFileField.setText(dictFile);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        if(errorMsg != null)
        	panel.add(new JLabel(errorMsg));
        panel.add(new JLabel("Number of human players:"));
        panel.add(playerField);
        panel.add(new JLabel("Size of the board:"));
        panel.add(sizeField);
        panel.add(new JLabel("Cubes File:"));
        panel.add(cubeFileField);
        panel.add(new JLabel("Dictionary File:"));
        panel.add(dictionaryFileField);

        int result = JOptionPane.showConfirmDialog(null, panel, 
                 "New Game Configuration", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
        	int tempPlayers = 0, tempSize = 0;
        	String tempCubes = "", tempDict = "";
        	try {
        		tempPlayers = Integer.parseInt(playerField.getText())+1;
        		tempSize = Integer.parseInt(sizeField.getText());
        		tempCubes = cubeFileField.getText();
        		tempDict = dictionaryFileField.getText();
        		
        	} catch(NumberFormatException e) {
        		throw new IllegalArgumentException("Please enter a valid number!");
        	}
        	if(tempPlayers == 1)
        		throw new IllegalArgumentException("There needs to be at least 1 human player!");
            dictInstance.loadDictionary(tempDict);
            gameInstance.newGame(tempSize, tempPlayers, tempCubes, dictInstance);
            dictFile = tempDict;
            numPlayers = tempPlayers;
            cubeFile = tempCubes;
            SIZE = tempSize;
        } else {
            dictInstance.loadDictionary(dictFile);
            gameInstance.newGame(SIZE, numPlayers, cubeFile, dictInstance);
        }
    }
    /*
     * Plays the computer's turn to finish the game.
     * Only works if there is currently an unfinished game.
     */
    private void finishTurn() {
        if(!currentGame)
            updateWarning("Start a new game first.");
        else {
        	currentPlayer++;
        	//computer's turn
        	if(currentPlayer == numPlayers) {
        		currentPlayer = 0;
	        	//get all words for the computer
	            Collection<String> allWords = gameInstance.getAllWords();
	            for(String word : allWords) {
	            	boolean guessed = false;
	            	for(int player = 0; player < numPlayers; player++)
	            		if(player != COMPUTER && guessedWords[player].contains(word.toUpperCase())) guessed = true;
	            	
	            	//do not guess words that any player has already guessed
	            	if(!guessed) {
	                //only consider words that are valid guesses
	                int score = gameInstance.addWord(word, COMPUTER);
	                if(score != 0)
	                    guessedWords[COMPUTER].add(word);
	            	}
	            }
	            
	            //update UI components
	            int[] finalScores = gameInstance.getScores();
	            
	            int maxScore = 0;
	            for(int score : finalScores)
	            	maxScore = Math.max(score, maxScore);
	            ArrayList<String> winners = new ArrayList<>();
	            for(int i = 0; i < numPlayers; i++) {
	            	if(finalScores[i] != maxScore) continue;
	            	if(i == COMPUTER) winners.add("The computer");
	            	else winners.add("Player " + i);
	            }
	            
	            //special handling when there is only 1 computer player
	            if(numPlayers == 2) {
		            if(winners.size() == 2)
		                updateWarning("Game over! You tied the computer!");
		            else if(maxScore == finalScores[COMPUTER])
		                updateWarning("Game over! You lost...");
		            else
		                updateWarning("Congratulations! You beat the computer!");
	            } else if(winners.size() == 1)
	            	updateWarning("Game over! " + winners.get(0) + " won!");
	            else {
	            	String winnerString = winners.remove(winners.size()-1);
	            	winnerString = winners.toString().replace("[", "").replace("]", "") + ", and " + winnerString;
	            	updateWarning("Game over! " + winnerString + " won!");
	            }
	            
	            currentGame = false;
        	} else updateWarning("Welcome Player " + currentPlayer + "! Enter your guess below.");
            //don't highlight the previous player's guesses
            showHighlightedWord = false;
            
            
            updateBoggleUI();
        }
    }
    
    /*
     * Changes the label above the text bar.
     */
    private void updateWarning(String warning) {
        warningLabel.setText(warning);
    }

    /*
     * Processes a guess by the user.
     */
    private void onTextFieldChange() {
    	if(!currentGame) {
            updateWarning("Start a new game first.");
            return;
    	}
        String guess = textField.getText().toUpperCase();
        textField.setText("");

        int points = gameInstance.addWord(guess, currentPlayer);
        if(points != 0) {
        	//successful guess
            addWord(guess);
            //highlight the word
            showHighlightedWord = true;
            updateWarning("Nice! Enter another guess below.");
        } else {
        	if(guess.length() >= 1)
        		guess = guess.charAt(0) + guess.substring(1).toLowerCase();
        	
        	if(guess.length() == 0)
        		updateWarning("Please enter a guess!");
        	else if(!dictInstance.contains(guess))
                updateWarning(guess + " was not a valid word in the dictionary! Try again.");
        	else if(guess.length() <= 3)
        		updateWarning(guess + " is not long enough! Try again.");
        	else if(guessedWords[currentPlayer].contains(guess.toUpperCase()))
        		updateWarning(guess + " has already been guessed! Try again.");
        	else
        		updateWarning(guess + " could not be found on the board! Try again.");
        	//failed guess
            //remove any highlight
            showHighlightedWord = false;
        }
        //update the UI to reflect these changes
        updateBoggleUI();
    }
    /*
     * Adds a word to the player word list.
     */
    private void addWord(String s) {
    	guessedWords[currentPlayer].add(s.toUpperCase());
    }
    /*
     * Update the word list UI to reflect changes in word lists, points, etc.
     */
    private void updateBoggleUI() {
    	if(currentPlayer < numPlayers) {
	        if(guessedWords[currentPlayer].isEmpty()) playerWordList.setText(NO_WORDS_MSG);
	        else playerWordList.setText(guessedWords[currentPlayer].toString().replaceAll("[\\[\\],]", ""));
	       
	        if(currentPlayer == COMPUTER) playerScore.setText("Computer's Score: " + gameInstance.getScores()[currentPlayer]);
	        else playerScore.setText("Player " + currentPlayer + "'s Score: " + gameInstance.getScores()[currentPlayer]);
    	}
        wordPanel.revalidate();
        wordPanel.repaint();
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //pixels per boggle tile
        final int PIXELS = PIXEL_SIZE / SIZE;
        
        //draw boundaries of the board
        g.drawRect(0,0, getWidth()-1, getHeight()-1);
        
        //get game values
        char[][] board = gameInstance.getBoard();
        
        java.util.List<Point> wordPoints = gameInstance.getLastAddedWord();
        
        //draw the arrows if there are last word points
        if(wordPoints != null && showHighlightedWord) {
            Graphics2D g2 = (Graphics2D) g;
            
            //change the stroke style 
            g.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(10));
            
            for(int i = 0; i < wordPoints.size()-1; i++) {
                Point p1 = wordPoints.get(i);
                Point p2 = wordPoints.get(i+1);
                
                //calculate the centers of the squares
                int x1 = p1.x*PIXELS+PIXELS/2;
                int y1 = p1.y*PIXELS+PIXELS/2;
                int x2 = p2.x*PIXELS+PIXELS/2;
                int y2 = p2.y*PIXELS+PIXELS/2;
                
                g.drawLine(x1, y1, x2, y2);
            }
            
            //reset the stroke
            g.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
        }

        //set font for the grid
        Font font = new Font(LETTER_FONT, Font.BOLD, 240/SIZE);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        
        //draw each grid cell and letter
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++) {
            	//fill the background of each board cell
                g.setColor(Color.WHITE);
                g.fillRect(i*PIXELS+OFFSET, j*PIXELS+OFFSET, PIXELS-2*OFFSET, PIXELS-2*OFFSET);

            	//change the color if this letter was part of the last word
                if(wordPoints != null && wordPoints.contains(new Point(i,j)) && showHighlightedWord)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.BLACK);
                
                //center the letter in the center of the grid using font metrics values
                String letter = board[i][j]+"";
                int x = (PIXELS-2*OFFSET - fm.stringWidth(letter))/2;
                int y = (PIXELS-2*OFFSET - fm.getHeight())/2;
                y += fm.getAscent();
                g.drawString(""+board[i][j], i*PIXELS+OFFSET+x, j*PIXELS+OFFSET+y);

                //draw board cell outline
                g.setColor(Color.BLACK);
                g.drawRect(i*PIXELS+OFFSET, j*PIXELS+OFFSET, PIXELS-2*OFFSET, PIXELS-2*OFFSET);
            }
        
    }
}
