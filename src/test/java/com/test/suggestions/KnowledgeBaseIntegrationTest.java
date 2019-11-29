package com.test.suggestions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class KnowledgeBaseIntegrationTest {

    String testString = "Mary had a little lamb,\n" +
            "It's fleece was white as snow;\n" +
            "And everywhere that Mary went\n" +
            "The lamb was sure to go.\n" +
            "He followed her to school one day\n" +
            "Which was against the rule;\n" +
            "It made the children laugh and play,\n" +
            "To see a lamb at school.\n" +
            "And so the teacher turned him out,\n" +
            "But still he lingered near;\n" +
            "And waited patiently about\n" +
            "Till Mary did appear\n" +
            "\"What makes the lamb love Mary so?\"\n" +
            "The eager chldren cry;\n" +
            "\"Why, Mary loves the lamb, you know,\"\n" +
            "The teacher did reply.";

   private KnowledgeBase knowledgeBase;

   @BeforeEach
   public void before(){
       KnowledgeBuilder knowledgeBuilder = new KnowledgeBuilderImpl();
       knowledgeBase = knowledgeBuilder.buildKnowledge( testString );
   }

    @Test
    /**
     * in this test printing cause it is hard to predict maps order.
     */
    public void testSuggestions() {
        Optional<KnowledgeWord> knowledgeWord = knowledgeBase.find( "Mary" );
        Assertions.assertTrue( knowledgeWord.isPresent() );
        System.out.println( knowledgeWord.get().topSuggestion() );

        knowledgeWord = knowledgeBase.find( "mary" );
        Assertions.assertTrue( knowledgeWord.isPresent() );
        System.out.println( knowledgeWord.get().topSuggestion() );

        knowledgeWord = knowledgeBase.find( "appear" );
        Assertions.assertTrue( knowledgeWord.isPresent() );
        Assertions.assertEquals("What", knowledgeWord.get().topSuggestion().get() );

        knowledgeWord = knowledgeBase.find( "made" );
        Assertions.assertTrue( knowledgeWord.isPresent() );
        Assertions.assertEquals("the", knowledgeWord.get().topSuggestion().get() );

        knowledgeWord = knowledgeBase.find( "reply" );
        Assertions.assertTrue( knowledgeWord.isPresent() );
        Assertions.assertFalse( knowledgeWord.get().topSuggestion().isPresent() );


    }



}