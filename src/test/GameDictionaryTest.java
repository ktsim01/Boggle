package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Rule;
import org.junit.rules.Timeout;

import assignment.*;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class GameDictionaryTest {
    @Rule
    public Timeout globalTimeout= new Timeout(1000);

	//Tests if all prefixes and words are correctly recognized for each dictionary file
	//Also checks that the iterator goes over every word
	@Test
	public void loadDictionaryTest1() throws IOException {
		String filename = "words.txt";
		GameDictionary dict = new GameDictionary();
		dict.loadDictionary(filename);
		
		BufferedReader f = new BufferedReader(new FileReader(filename));
		HashSet<String> fileWords = new HashSet<>();
		while(true) {
			String s = f.readLine();
			if(s == null) break;
			
			assertTrue(dict.contains(s));
			for(int i = 0; i <= s.length(); i++)
				assertTrue(s.substring(0,i), dict.isPrefix(s.substring(0,i)));
			fileWords.add(s);
		}

		HashSet<String> dictWords = new HashSet<>();
		for(String s : dict)
			dictWords.add(s);
		
		assertEquals(fileWords, dictWords);
	}
	@Test
	public void loadDictionaryTest2() throws IOException {
		String filename = "testDic1.txt";
		GameDictionary dict = new GameDictionary();
		dict.loadDictionary(filename);
		
		BufferedReader f = new BufferedReader(new FileReader(filename));
		HashSet<String> fileWords = new HashSet<>();
		while(true) {
			String s = f.readLine();
			if(s == null) break;
			
			assertTrue(dict.contains(s));
			for(int i = 0; i <= s.length(); i++)
				assertTrue(s.substring(0,i), dict.isPrefix(s.substring(0,i)));
			fileWords.add(s);
		}

		HashSet<String> dictWords = new HashSet<>();
		for(String s : dict)
			dictWords.add(s);
		
		assertEquals(fileWords, dictWords);
		
		for(char c = 'A'; c <= 'Z'; c++)
			if(c != 'B')
				assertFalse(dict.isPrefix(c+""));
	} 
	@Test
	public void loadDictionaryTest3() throws IOException {
		String filename = "testDic2.txt";
		GameDictionary dict = new GameDictionary();
		dict.loadDictionary(filename);
		
		BufferedReader f = new BufferedReader(new FileReader(filename));
		HashSet<String> fileWords = new HashSet<>();
		while(true) {
			String s = f.readLine();
			if(s == null) break;
			
			assertTrue(dict.contains(s));
			for(int i = 0; i <= s.length(); i++)
				assertTrue(s.substring(0,i), dict.isPrefix(s.substring(0,i)));
			fileWords.add(s);
		}

		HashSet<String> dictWords = new HashSet<>();
		for(String s : dict)
			dictWords.add(s);
		
		assertEquals(fileWords, dictWords);
	} 
	//testDic3 contains 1 word, repeated
	@Test
	public void repeatWordsTest() throws IOException {
		String filename = "testDic3.txt";
		GameDictionary dict = new GameDictionary();
		dict.loadDictionary(filename);
		
		BufferedReader f = new BufferedReader(new FileReader(filename));
		HashSet<String> fileWords = new HashSet<>();
		while(true) {
			String s = f.readLine();
			if(s == null) break;
			
			assertTrue(dict.contains(s));
			for(int i = 0; i <= s.length(); i++)
				assertTrue(s.substring(0,i), dict.isPrefix(s.substring(0,i)));
			fileWords.add(s);
		}

		HashSet<String> dictWords = new HashSet<>();
		for(String s : dict)
			dictWords.add(s);
		
		assertEquals(fileWords, dictWords);
	} 
	//testDic4 contains nonstandard characters - uppercase letters, digits, special chars, etc.
	@Test
	public void specialCharsTest() throws IOException {
		String filename = "testDic4.txt";
		GameDictionary dict = new GameDictionary();
		dict.loadDictionary(filename);
		
		BufferedReader f = new BufferedReader(new FileReader(filename));
		HashSet<String> fileWords = new HashSet<>();
		while(true) {
			String s = f.readLine();
			if(s == null) break;
			
			assertTrue(dict.contains(s));
			for(int i = 0; i <= s.length(); i++)
				assertTrue(s.substring(0,i), dict.isPrefix(s.substring(0,i)));
			fileWords.add(s);
		}

		HashSet<String> dictWords = new HashSet<>();
		for(String s : dict)
			dictWords.add(s);
		assertEquals(fileWords, dictWords);
	}
	//testDic5 is an all uppercase version of words.txt, but we lowercase all strings to see if they are considered the same
	@Test
	public void caseSensitiveTest() throws IOException {
		String filename = "testDic5.txt";
		GameDictionary dict = new GameDictionary();
		dict.loadDictionary(filename);
		
		BufferedReader f = new BufferedReader(new FileReader(filename));
		HashSet<String> fileWords = new HashSet<>();
		while(true) {
			String s = f.readLine();
			if(s == null) break;
			
			assertTrue(dict.contains(s.toLowerCase()));
			for(int i = 0; i <= s.length(); i++)
				assertTrue(s.substring(0,i).toLowerCase(), dict.isPrefix(s.substring(0,i)));
			fileWords.add(s);
		}

		HashSet<String> dictWords = new HashSet<>();
		for(String s : dict)
			dictWords.add(s);
		
		assertEquals(fileWords, dictWords);		
	}
}
