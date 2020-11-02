package assignment;

import java.util.*;
import java.io.*;
import java.awt.Point;
import java.io.IOException;

public class GameManager implements BoggleGame {
	private int size;
	private int numPlayers;
	private BoggleDictionary dict;
	private char[][] gameBoard;
	private HashSet<String>[] usedWords;
	private ArrayList<String> cubes;
	private Random rand;
	private int[] scores;
	private List<Point> wordPoints;
	private SearchTactic searchTactic;

	public GameManager() {
		rand = new Random();
		searchTactic = SEARCH_DEFAULT;
	}

	public void newGame(int size, int numPlayers, String cubeFile, BoggleDictionary dict) throws IOException {
		if(size <= 0) throw new IllegalArgumentException(size + " is not a valid size!");
		if(numPlayers <= 0) throw new IllegalArgumentException(numPlayers + " is not a valid number of players!");
		loadCubes(cubeFile, size);
		
		this.size = size;
		this.numPlayers = numPlayers;
		this.dict = dict;
		scores = new int[numPlayers];
		usedWords = new HashSet[numPlayers];
		for(int i = 0; i < numPlayers; i++)
			usedWords[i] = new HashSet<String>();
		setGame(createBoard());
		
	}
	//Loads the 16 strings into an ArrayList
	private void loadCubes(String cubeFile, int size) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(cubeFile));
		ArrayList<String> tempCubes = new ArrayList<String>();
		for (int i = 0; i < size * size; i++) {
			String line  = f.readLine();
			tempCubes.add(line);
			if(line == null)
				throw new IllegalArgumentException("The cubes file did not contain enough cubes!");
			if(line.length() != 6)
				throw new IllegalArgumentException("The cubes file contained an invalid cube!");
		}
		cubes = tempCubes;
	}
	//Creates the board by picking a random character from each 16 strings
	private char[][] createBoard() {
		Collections.shuffle(cubes);
		char[][] b = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				b[i][j] = roll(i, j);
			}
		}
		return b;
	}
	//Choose a random letter from a cube ("rolling the die")
	private char roll(int i, int j) {
		return cubes.get(i * size + j).charAt(rand.nextInt(6));
	}

	public char[][] getBoard() {
		char[][] copyBoard = new char[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				copyBoard[i][j] = gameBoard[i][j];
		return copyBoard;
	}
	
	public int addWord(String word, int player) {
		if(player < 0 || player >= numPlayers)
			throw new IllegalArgumentException("Player " + player + " does not exist!");
		//check for invalid cases
		if (usedWords[player].contains(word))
			return 0;
		if (word.length() <= 3)
			return 0;
		if (!dict.contains(word.toLowerCase()))
			return 0;
		
		boolean found = findWord(word);
		if (!found)
			return 0;
		
		int score = word.length() - 3;
		usedWords[player].add(word);
		scores[player] += score;
		return score;
	}
	
	private boolean findWord(String word) {
		int[][] visited = new int[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
					visited[i][j] = -1;
		
		boolean found = false;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (findWord(i, j, word, 0, visited)) {
					found = true;
					break;
				}
			}
			if (found)
				break;
		}
		
		//compute the points of the found word
		if(found) {
			wordPoints = new ArrayList<Point>();
			for (int k = 0; k < word.length(); k++) {
				boolean check = false;
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (visited[i][j] == k) {
							wordPoints.add(new Point(i, j));
							check = true;
							break;
						}
					}
					if (check)
						break;
				}
			}
		}
		return found;
	}
	
	//Finding a word in a board
	private boolean findWord(int i, int j, String word, int index, int[][] visited) {
		//Returns true if we found the word
		if (index == word.length())
			return true;
		//Checks bounds
		if (i >= size || j >= size || i < 0 || j < 0) {
			return false;
		}
		//Does not visit already visited indexes
		if (visited[i][j] != -1)
			return false;
		//Only continues the search when each character matches
		if (Character.toLowerCase(gameBoard[i][j]) != Character.toLowerCase(word.charAt(index)))
			return false;
		visited[i][j] = index;
		//Iterate over all neighbors
		for (int dr = -1; dr <= 1; dr++) {
			for (int dc = -1; dc <= 1; dc++) {
				if (findWord(i + dr, j + dc, word, index + 1, visited))
					return true;
			}
		}
		visited[i][j] = -1;
		return false;
	}
	//DFS search on boards
	//Finds all valid words from the board
	private void findAllWords(int i, int j, String word, TreeSet<String> allWords, boolean[][] visited) {
		if (!dict.isPrefix(word.toLowerCase()))
			return;
		if (dict.contains(word.toLowerCase()))
			allWords.add(word);
		if (i >= size || j >= size || i < 0 || j < 0)
			return;
		if (visited[i][j])
			return;
		visited[i][j] = true;
		for (int dr = -1; dr <= 1; dr++)
			for (int dc = -1; dc <= 1; dc++) 
				findAllWords(i + dr, j + dc, word + gameBoard[i][j], allWords, visited);
		visited[i][j] = false;
	}
	
	//Returns the precomputed list from addWord
	public List<Point> getLastAddedWord() {
		return wordPoints;
	}
	
	//sets the game using the given board
	public void setGame(char[][] board) {
		if(board.length != board[0].length)
			throw new IllegalArgumentException("The given board was not square!");
		size = board.length;
		gameBoard = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				gameBoard[i][j] = board[i][j];
			}
		}
		
		for(int i = 0; i < numPlayers; i++) {
			scores[i] = 0;
			usedWords[i].clear();
		}
	}

	public Collection<String> getAllWords() {
		TreeSet<String> allWords = new TreeSet<>();
		//DFS search through Board
		if (searchTactic == SearchTactic.SEARCH_BOARD) {
			for(int i = 0; i < size; i++)
					for(int j = 0; j < size; j++) {
							boolean[][] visited = new boolean[size][size];
							findAllWords(i, j, "", allWords, visited);
					}
		} 
		//Iterating through dictionary
		else if (searchTactic == SearchTactic.SEARCH_DICT) {
			List<Point> oldPoints = wordPoints;
			for (String s : dict) {
				if (findWord(s))
					allWords.add(s.toUpperCase());
			}
			wordPoints = oldPoints;
		}
		return allWords;
	}

	public void setSearchTactic(SearchTactic tactic) {
		if(tactic != SearchTactic.SEARCH_BOARD && tactic != SearchTactic.SEARCH_DICT)
			searchTactic = SEARCH_DEFAULT;
		else 
			searchTactic = tactic;
	}

	public int[] getScores() {
		int[] tempScores = new int[numPlayers];
		for(int i = 0; i < numPlayers; i++)
			tempScores[i] = scores[i];
		return tempScores;
	}
}
