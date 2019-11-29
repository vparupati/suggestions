package com.test.suggestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface KnowledgeBuilder {
    static List<String> getNextWords(String text, String word) {
        List<String> nextWords = new ArrayList<>();
        Pattern p = Pattern.compile( word + "\\W+(\\w+)(.+?)" );
        Matcher m = p.matcher( text );
        while (m.find()) {
            String[] matched = m.group().split( "\\W" );
            nextWords.add( matched[matched.length - 1] );
        }
        return nextWords;
    }

    static Set<String> getAllWords(String text) {
        return Arrays.stream( text.split( "\\W" ) ).collect( Collectors.toSet() );
    }

    static String cleanText(String text) {
        Pattern pt = Pattern.compile( "[^a-zA-Z0-9'\\s\n]" );
        Matcher match = pt.matcher( text );
        text = match.replaceAll( "" );
        return text;
    }


    KnowledgeBase buildKnowledge(String test);
}
