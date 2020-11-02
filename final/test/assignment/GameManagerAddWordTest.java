package test;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Rule;
import org.junit.rules.Timeout;

import assignment.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

public class GameManagerAddWordTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	//check if add word handles capitalization well
	@Test
	public void capsCheck1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(4, 2, "testcubes6.txt", gameDict);
		gameDict.loadDictionary("words.txt");
		int score = gameManager.addWord("coco",1);
		assertNotEquals(score, 0);
		assertEquals(gameManager.getScores()[1], score);
	}
	@Test
	public void capsCheck2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(4, 2, "testcubes6.txt", gameDict);
		gameDict.loadDictionary("words.txt");
		int score = gameManager.addWord("COCO",1);
		assertNotEquals(score, 0);
		assertEquals(gameManager.getScores()[1], score);
	}
	@Test
	public void capsCheck3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(4, 2, "testcubes6.txt", gameDict);
		gameDict.loadDictionary("words.txt");
		int score = gameManager.addWord("CoCo",1);
		assertNotEquals(score, 0);
		assertEquals(gameManager.getScores()[1], score);
	}
	//Handles player# out of bounds well
	@Test
	public void invalidNumPlayers() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(4, 2, "testcubes6.txt", gameDict);
		gameDict.loadDictionary("words.txt");
		gameManager.addWord("COCO",6);
	}
	//Doesn't add repeated words
	@Test
	public void repeatGuesses() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(4, 2, "testcubes6.txt", gameDict);
		gameDict.loadDictionary("words.txt");
		gameManager.addWord("COCO",1);
		assertEquals(gameManager.addWord("COCO",1), 0);
		assertNotEquals(gameManager.addWord("COCO",0), 0);
	}
	//check if the points are calculated properly
	@Test
	public void pointCheck1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(10, 2, "testcubes5.txt", gameDict);
		gameDict.loadDictionary("testDic1.txt");
		String s = "";
		for(int i = 1; i <= 100; i++) {
			s += "B";
			assertEquals(s, gameManager.addWord(s,1), Math.max(i-3, 0));
		}
	}
	
	//Check if the points get added to the right player
	@Test
	public void playerCheck1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(10, 100, "testcubes5.txt", gameDict);
		gameDict.loadDictionary("testDic1.txt");
		
		gameManager.addWord("BBBBB", 0);
		int[] scores = gameManager.getScores();
		for(int i = 0; i < 100; i++) {
			if(i == 0) assertEquals(scores[i], 2);
			else assertEquals(scores[i], 0);
		}
	}
	@Test
	public void playerCheck2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		gameManager.newGame(10, 100, "testcubes5.txt", gameDict);
		
		for(int i = 0; i < 100; i++)
			if(i%2 == 0) gameManager.addWord("BBBBB", i);
		
		int[] scores = gameManager.getScores();
		for(int i = 0; i < 100; i++) {
			if(i%2 == 0) assertEquals(scores[i], 2);
			else assertEquals(scores[i], 0);
		}
	}
}
