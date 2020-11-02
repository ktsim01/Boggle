package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Rule;
import org.junit.rules.Timeout;

import assignment.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.awt.Point;
import java.util.*;

public class GameManagerGetLastAddedWordTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	@Test
	public void pointCheck1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'A', 'P', 'P', 'L' }, 
			{ 'Z', 'Z', 'Z', 'E' }, 
			{ 'Z', 'Z', 'Z', 'Z' },
			{ 'Z', 'Z', 'Z', 'Z' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
		
	}
	@Test
	public void pointCheck2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'A', 'Z', 'Z', 'Z' }, 
			{ 'Z', 'P', 'Z', 'Z' }, 
			{ 'Z', 'Z', 'P', 'E' },
			{ 'Z', 'Z', 'Z', 'L' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
	}
	@Test
	public void pointCheck3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'Z', 'Z', 'Z', 'Z' }, 
			{ 'Z', 'P', 'E', 'Z' }, 
			{ 'A', 'P', 'L', 'Z' },
			{ 'Z', 'Z', 'Z', 'Z' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
		
	}
	@Test
	public void pointCheck4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'E', 'L', 'Z', 'Z' }, 
			{ 'Z', 'P', 'Z', 'Z' }, 
			{ 'Z', 'P', 'Z', 'Z' },
			{ 'Z', 'A', 'Z', 'Z' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
		
	}
	@Test
	public void pointCheck5() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'E', 'L', 'Z', 'Z' }, 
			{ 'Z', 'P', 'Z', 'E' }, 
			{ 'Z', 'P', 'Z', 'L' },
			{ 'Z', 'A', 'P', 'P' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
		
	}
	@Test
	public void pointCheck6() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'Z', 'Z', 'Z', 'A' }, 
			{ 'Z', 'E', 'Z', 'P' }, 
			{ 'Z', 'L', 'P', 'L' },
			{ 'Z', 'Z', 'P', 'P' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
		
	}
	@Test
	public void badGuessReset() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard = {
			{ 'Z', 'Z', 'Z', 'A' }, 
			{ 'Z', 'E', 'Z', 'P' }, 
			{ 'Z', 'L', 'P', 'L' },
			{ 'Z', 'Z', 'P', 'P' }, 
			};

		gameManager.setGame(testboard);
		String apple = "APPLE";
		gameManager.addWord(apple, 0);
		gameManager.addWord("", 0);

		List<Point> checkpoint = gameManager.getLastAddedWord();
		for (int i = 0; i < 5; i++) {
			Point points = checkpoint.get(i);
			int x = (int) points.getX();
			int y = (int) points.getY();
			assertEquals(testboard[x][y], apple.charAt(i));
		}
		
		for (int i = 0; i < 4; i++) {
			Point points = checkpoint.get(i);
			int x0 = (int) points.getX();
			int y0 = (int) points.getY();
			
			Point compare= checkpoint.get(i+1);
			int x1 = (int) compare.getX();
			int y1 = (int) compare.getY();
			
			assertTrue(Math.abs(x1-x0)<2 && Math.abs(y1-y0)<2);
		}
		
		
	}
}
