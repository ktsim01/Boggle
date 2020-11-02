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

public class GameManagerNewGameTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	//Invalid file testing
	@Test (expected = IOException.class)
	public void invalidFileTest() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		String filename = "thisdoesntexist.txt";
		gameManager.newGame(4, 2, filename, gameDict);
	}
	
	
	//Correct Board testing
	@Test
	public void correctBoard1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int boardsize=4;
		gameManager.newGame(boardsize, 2, "testcubes1.txt", gameDict);
		char[][] testboard= gameManager.getBoard();
		int[] cubeinputs= new int[26];
		
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				cubeinputs[Character.toUpperCase(testboard[i][j])-'A']++;
			}
		}
		String shouldsee="ABCDEFGHIJKLMNOP";
		
		for(int i=0; i<cubeinputs.length; i++) {
			if(shouldsee.indexOf((char)(i+'A'))!=-1) {
				assertEquals(cubeinputs[i],1);
			}
			else assertEquals(cubeinputs[i],0);
			
		}
	}
	@Test
	public void correctBoard2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int boardsize=4;
		gameManager.newGame(boardsize, 2, "testcubes2.txt", gameDict);
		char[][] testboard= gameManager.getBoard();
		int[] cubeinputs= new int[26];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				cubeinputs[Character.toUpperCase(testboard[i][j])-'A']++;
			}
		}
		String shouldsee="ABCDEF";
		
		for(int i=0; i<cubeinputs.length; i++) {
			if(shouldsee.indexOf((char)(i+'A'))==-1) {
				assertEquals(cubeinputs[i],0);
			}
			
		}
	}
	@Test
	public void correctBoard3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int boardsize=6;
		gameManager.newGame(boardsize, 2, "testcubes3.txt", gameDict);
		char[][] testboard= gameManager.getBoard();
		int[] cubeinputs= new int[26];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				cubeinputs[Character.toUpperCase(testboard[i][j])-'A']++;
			}
		}
		String shouldsee="ABCDEF";
		
		for(int i=0; i<cubeinputs.length; i++) {
			if(shouldsee.indexOf((char)(i+'A'))==-1) {
				assertEquals(cubeinputs[i],0);
			}
		}
	}
	@Test
	public void correctBoard4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int boardsize=6;
		gameManager.newGame(boardsize, 2, "testcubes4.txt", gameDict);
		char[][] testboard= gameManager.getBoard();
		int[] cubeinputs= new int[26];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				cubeinputs[Character.toUpperCase(testboard[i][j])-'A']++;
			}
		}
		String shouldsee="ABCDEF";
		
		for(int i=0; i<cubeinputs.length; i++) {
			if(shouldsee.indexOf((char)(i+'A'))!=-1) {
				assertEquals(cubeinputs[i],6);
			}
			else assertEquals(cubeinputs[i],0);
			
		}
	}
	@Test
	public void correctBoard5() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int boardsize=10;
		gameManager.newGame(boardsize, 2, "testcubes5.txt", gameDict);
		char[][] testboard= gameManager.getBoard();
		int[] cubeinputs= new int[26];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				cubeinputs[Character.toUpperCase(testboard[i][j])-'A']++;
			}
		}
		String shouldsee="B";
		
		for(int i=0; i<cubeinputs.length; i++) {
			if(shouldsee.indexOf((char)(i+'A'))==-1) 
				assertEquals(cubeinputs[i],0);
			
		}
	}
	
	
	
	//Various size testing
	@Test
	public void invalidSizeTest() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(-1000, 2, "cubes.txt", gameDict);
	}
	
	@Test
	public void zeroSizeTest() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(0, 2, "cubes.txt", gameDict);
	}
	@Test
	public void validSizeTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(10, 2, "testcubes5.txt", gameDict);
	}
	@Test
	public void validSizeTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(300, 2, "testcubes5.txt", gameDict);
	}
	@Test
	public void validSizeTest3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(1, 2, "cubes.txt", gameDict);
	}
	@Test
	public void validSizeTest4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(2, 2, "cubes.txt", gameDict);
	}
	@Test
	public void validSizeTest5() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(3, 2, "cubes.txt", gameDict);
	}
	@Test
	public void validSizeTest6() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
	}
	
	
	
	
	//Various number of players testing
	@Test
	public void invalidNumPlayerTest() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int numplayers=-100;
		gameManager.newGame(4, numplayers, "cubes.txt", gameDict);
	}
	
	
	@Test
	public void validNumPlayerTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int numPlayers = 1;
		gameManager.newGame(4, numPlayers, "cubes.txt", gameDict);
		assertEquals(gameManager.getScores().length, numPlayers);
		assertArrayEquals(gameManager.getScores(), new int[numPlayers]);
	}

	@Test
	public void validNumPlayerTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int numPlayers = 2;
		gameManager.newGame(4, numPlayers, "cubes.txt", gameDict);
		assertEquals(gameManager.getScores().length, numPlayers);
		assertArrayEquals(gameManager.getScores(), new int[numPlayers]);
	}
	@Test
	public void validNumPlayerTest3() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int numPlayers = 0;
		gameManager.newGame(4, numPlayers, "cubes.txt", gameDict);
		assertEquals(gameManager.getScores().length, numPlayers);
		assertArrayEquals(gameManager.getScores(), new int[numPlayers]);
	}
	@Test
	public void validNumPlayerTest4() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int numPlayers = 1000;
		gameManager.newGame(4, numPlayers, "cubes.txt", gameDict);
		assertEquals(gameManager.getScores().length, numPlayers);
		assertArrayEquals(gameManager.getScores(), new int[numPlayers]);
	}
}
