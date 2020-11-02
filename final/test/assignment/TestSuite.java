package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	GameManagerNewGameTest.class,
	GameManagerAddWordTest.class,
	GameManagerSetGameTest.class,
	GameManagerSetSearchTacticTest.class,
	GameManagerCubeFilesTest.class,
	GameManagerGetAllWordsTest.class,
	GameManagerGetLastAddedWordTest.class,
	GameDictionaryTest.class
})

public class TestSuite {
	
	
}
