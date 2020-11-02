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


public class GameManagerSetGameTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);
	@Test
	//check if current board is set to the board we passed
	// in setGame() as a parameter
	public void boardCorrectSet() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		int boardsize=4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		gameManager.setGame(testboard);
		assertArrayEquals(gameManager.getBoard(),testboard);
		
	}
	//Test if scores all reset to 0
	@Test
	public void scoreReset() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		int boardsize=4;
		gameManager.newGame(boardsize, 5, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		gameManager.setGame(testboard);
		for(int i = 0; i < 5; i++)
			gameManager.addWord("BBBB", i);
		
		int[] testscore=gameManager.getScores();
		for(int scores: testscore) {
			assertNotEquals(scores,0);
		}
		gameManager.setGame(testboard);
		testscore=gameManager.getScores();
		for(int scores: testscore) {
			assertEquals(scores,0);
		}
	}
	
	//checks if guessed words get reset after calling set game
	@Test
	public void guessedWordsReset() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		gameDict.loadDictionary("testDic1.txt");
		int boardsize=4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		gameManager.setGame(testboard);
		assertEquals(gameManager.addWord("BBBB", 0), 1);
		
		gameManager.setGame(testboard);
		assertNotEquals(gameManager.addWord("BBBB", 0), 0);
	}
	
	//check if there any underlying pointer issues with setting the game board
	@Test
	public void pointerTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		
		int boardsize=4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		
		gameManager.setGame(testboard);
		
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='A';
			}
		}
		
		char[][] gameBoard = gameManager.getBoard();
		for(int i=0; i<boardsize; i++)
			for(int j=0; j<boardsize; j++)
				assertEquals(gameBoard[i][j], 'B');
	}
	@Test
	public void pointerTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		
		int boardsize=4;
		gameManager.newGame(boardsize, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		
		gameManager.setGame(testboard);

		char[][] gameBoard = gameManager.getBoard();
		for(int i=0; i<boardsize; i++)
			for(int j=0; j<boardsize; j++)
				gameBoard[i][j]='A';
		
		gameBoard = gameManager.getBoard();
		for(int i=0; i<boardsize; i++)
			for(int j=0; j<boardsize; j++)
				assertEquals(gameBoard[i][j], 'B');
	}
	//tests whether setGame handles varying sized boards
	@Test
	public void smallBoardTest() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		
		int boardsize=2;
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		
		gameManager.setGame(testboard);
		
		char[][] gameBoard = gameManager.getBoard();
		assertEquals(gameBoard.length, boardsize);
		assertEquals(gameBoard[0].length, boardsize);
		
		for(int i=0; i<boardsize; i++)
			for(int j=0; j<boardsize; j++)
				assertEquals(gameBoard[i][j], 'B');
	}
	@Test
	public void bigBoardTest() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		
		int boardsize=10;
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[boardsize][boardsize];
		for(int i=0; i<boardsize; i++) {
			for(int j=0; j<boardsize; j++) {
				testboard[i][j]='B';
			}
		}
		
		gameManager.setGame(testboard);
		
		char[][] gameBoard = gameManager.getBoard();
		assertEquals(gameBoard.length, boardsize);
		assertEquals(gameBoard[0].length, boardsize);
		
		for(int i=0; i<boardsize; i++)
			for(int j=0; j<boardsize; j++)
				assertEquals(gameBoard[i][j], 'B');
	}
	@Test
	public void nonSquareTest1() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[5][10];
		for(int i=0; i<5; i++) {
			for(int j=0; j<10; j++) {
				testboard[i][j]='B';
			}
		}
		
		gameManager.setGame(testboard);
		
		char[][] gameBoard = gameManager.getBoard();
		assertEquals(gameBoard.length, 5);
		assertEquals(gameBoard[0].length, 10);
		
		for(int i=0; i<5; i++)
			for(int j=0; j<10; j++)
				assertEquals(gameBoard[i][j], 'B');
	}
	@Test
	public void nonSquareTest2() throws IOException {
		BoggleGame gameManager = new GameManager();
		BoggleDictionary gameDict = new GameDictionary();
		
		gameManager.newGame(4, 2, "cubes.txt", gameDict);
		char[][] testboard=new char[10][5];
		for(int i=0; i<10; i++) {
			for(int j=0; j<5; j++) {
				testboard[i][j]='B';
			}
		}
		
		gameManager.setGame(testboard);
		
		char[][] gameBoard = gameManager.getBoard();
		assertEquals(gameBoard.length, 10);
		assertEquals(gameBoard[0].length, 5);
		
		for(int i=0; i<10; i++)
			for(int j=0; j<5; j++)
				assertEquals(gameBoard[i][j], 'B');
	}
}
