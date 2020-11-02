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
import java.awt.Point;
import java.util.*;


public class GameManagerCubeFilesTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	@Test
	public void randomcharcube() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "randomchar.txt", gameDict);
		
	}
	@Test
	public void lowercasecube() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "lowercase.txt", gameDict);
		
		
	}
	@Test
	public void longcube() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "longercube.txt", gameDict);
		
	}
	@Test
	public void shortcube() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("words.txt");

		int boardsize = 4;
		gameManager.newGame(boardsize, 2, "shortercube.txt", gameDict);

	}
}
