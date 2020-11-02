package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Rule;
import org.junit.rules.Timeout;

import assignment.*;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;


public class GameManagerSetSearchTacticTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	//check if setSearchTactic handles null
	@Test
	public void nulltest() throws IOException {
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
		gameManager.setSearchTactic(null);
		List<String> words = Arrays.asList(new String[] {"B", "BB", "BBB", "BBBB"});
		Collection<String> gameWords = gameManager.getAllWords();
		for(String s : words)
			assertTrue(gameWords.contains(s));

		for(String s : gameWords)
			assertTrue(words.contains(s));
		
		assertEquals(words.size(), gameWords.size());
	}
}
