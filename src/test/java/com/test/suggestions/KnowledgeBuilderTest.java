package com.test.suggestions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;


/**
 * @author vparupati
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KnowledgeBuilderTest {

    KnowledgeBuilder knowledgeBuilder;

    @BeforeAll
    public void before(){
        knowledgeBuilder = new KnowledgeBuilderImpl();
    }


    @Test
    void test_returns_empty_when_unknown_word() {
        KnowledgeBase knowledgeBase = knowledgeBuilder.buildKnowledge( "Test" );
        Optional<KnowledgeWord> knowledgeWord = knowledgeBase.find( "bla" );
        Assertions.assertFalse(knowledgeWord.isPresent());

    }

    @Test
    void test_returns_answer_when_unknown_word() {
        KnowledgeBase knowledgeBase = knowledgeBuilder.buildKnowledge( "Test bla" );

        Optional<KnowledgeWord> knowledgeWord = knowledgeBase.find( "bla" );
        Assertions.assertTrue(knowledgeWord.isPresent());
        Assertions.assertFalse(knowledgeWord.get().topSuggestion().isPresent());

        knowledgeWord = knowledgeBase.find( "Test" );
        Assertions.assertTrue(knowledgeWord.isPresent());
        Assertions.assertTrue(knowledgeWord.get().topSuggestion().isPresent());

        knowledgeWord = knowledgeBase.find( "test" );
        Assertions.assertTrue(knowledgeWord.isPresent());
        Assertions.assertTrue(knowledgeWord.get().topSuggestion().isPresent());

    }

    @Test
    void test_returns_answer_when_known_word() {
        KnowledgeBase knowledgeBase = knowledgeBuilder.buildKnowledge( "Test bla" );

        Optional<KnowledgeWord> knowledgeWord =  knowledgeBase.find( "Test" );
        Assertions.assertTrue(knowledgeWord.isPresent());
        Assertions.assertTrue(knowledgeWord.get().topSuggestion().isPresent());

        knowledgeWord = knowledgeBase.find( "test" );
        Assertions.assertTrue(knowledgeWord.isPresent());
        Assertions.assertTrue(knowledgeWord.get().topSuggestion().isPresent());

    }

    @Test
    void testGetNextWords(){
       List nextWords = KnowledgeBuilder.getNextWords( "bla test's asdb sdkj test's\n sdkn test", "test's");
       Assertions.assertEquals( Arrays.asList( "asdb", "sdkn" ),nextWords );
    }

    @Test
    void testGetNextWords_mary(){
       List nextWords = KnowledgeBuilder.getNextWords( "And everywhere that Mary went\n", "Mary");
       Assertions.assertEquals( Arrays.asList( "went" ),nextWords );
    }

    @Test
    void testAllWords(){
        Set<String> allWordsExpected = new HashSet<>(Arrays.asList( "word1", "word2", "word3" ));
        Assertions.assertEquals(allWordsExpected,KnowledgeBuilder.getAllWords("word1 word2 word3 word1 word2 word3 word1 word2 word3"  ));
    }

    @Test
    void testCleanText() {
        String testString = " balaa \"sdad\"\nfsadlk abc's \nldfasdl";
        String expected = " balaa sdad\nfsadlk abc's \nldfasdl";

        String actual = KnowledgeBuilder.cleanText( testString );
        Assertions.assertTrue( actual.contains( "'" ) );
        Assertions.assertTrue( !actual.contains( "\"" ) );

        Assertions.assertEquals( expected,actual );
    }

}
