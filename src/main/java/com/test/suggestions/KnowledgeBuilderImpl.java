package com.test.suggestions;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KnowledgeBuilderImpl implements KnowledgeBuilder {

    public KnowledgeBase buildKnowledge(String text) {
       final String cleanText = KnowledgeBuilder.cleanText( text );
        KnowledgeBase knowledgeBase = new KnowledgeBaseImpl();
        Set<String> allWords = KnowledgeBuilder.getAllWords(cleanText);
        allWords.remove( "" );// to avoid next word for ""
        allWords.stream().forEach( word -> knowledgeBase.addWord(addToKnowledge( word,cleanText )));
        return knowledgeBase;
    }

    private KnowledgeWord addToKnowledge(String word,String text){
        List<String> nextWords = KnowledgeBuilder.getNextWords(text, word);
        Map<String, Long> wordsCount = nextWords.stream().collect( Collectors.groupingBy( Function.identity(),Collectors.counting() ) );
        return new KnowledgeWord( word,wordsCount );

    }

}
