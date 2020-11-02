package test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.*;

import assignment.*;

public class GameManagerGetAllWordsTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	//checks if getAllWords returns a correct collection using the default search
	@Test
	public void defaultTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		gameManager.setGame(testboard);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void defaultTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='A';
			}
		}
		gameManager.setGame(testboard);
		List<String> words = Arrays.asList(new String[] {});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void defaultTest3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]= i % 2 == 0 ? 'A' : 'B';
			}
		}
		gameManager.setGame(testboard);
		List<String> words = Arrays.asList(new String[] {"B", "BB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void defaultTest4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=10;
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]= (i+j) % 3 == 0 ? 'B' : 'A';
			}
		}
		gameManager.setGame(testboard);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB", "BBBBB", "BBBBBB", "BBBBBBB", "BBBBBBBB", "BBBBBBBBB", "BBBBBBBBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	

	//checks if getAllWords returns a correct collection using the SearchBoard tactic
	@Test
	public void searchBoardTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_BOARD);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void searchBoardTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='A';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_BOARD);
		List<String> words = Arrays.asList(new String[] {});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void searchBoardTest3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]= i % 2 == 0 ? 'A' : 'B';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_BOARD);
		List<String> words = Arrays.asList(new String[] {"B", "BB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void searchBoardTest4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=10;
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]= (i+j) % 3 == 0 ? 'B' : 'A';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_BOARD);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB", "BBBBB", "BBBBBB", "BBBBBBB", "BBBBBBBB", "BBBBBBBBB", "BBBBBBBBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}

	//checks if getAllWords returns a correct collection using the SearchDict tactic
	@Test
	public void searchDictTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_DICT);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void searchDictTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='A';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_DICT);
		List<String> words = Arrays.asList(new String[] {});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void searchDictTest3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=2;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]= i % 2 == 0 ? 'A' : 'B';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_DICT);
		List<String> words = Arrays.asList(new String[] {"B", "BB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
	@Test
	public void searchDictTest4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		
		int boardsize=10;
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]= (i+j) % 3 == 0 ? 'B' : 'A';
			}
		}
		gameManager.setGame(testboard);
		gameManager.setSearchTactic(BoggleGame.SearchTactic.SEARCH_DICT);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB", "BBBBB", "BBBBBB", "BBBBBBB", "BBBBBBBB", "BBBBBBBBB", "BBBBBBBBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s.toLowerCase())||gameWords.contains(s.toUpperCase()));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
}
