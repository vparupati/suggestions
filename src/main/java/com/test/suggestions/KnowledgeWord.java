package com.test.suggestions;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class KnowledgeWord {

    private String word;

    private Map<String,Long> suggestions;

    public KnowledgeWord(String word, Map<String, Long> suggestions) {
        this.word = word;
        if(suggestions == null){
            suggestions = Collections.emptyMap();
        }
        this.suggestions = suggestions;
    }

    public String getWord() {
        return word;
    }

    /**
     * returns top suggestion based on number of occurrence
     * in case of all words occurred same number of times then it returns first element of map
     * which is not appropriate, can be improved based on order of occurrence
     * @return
     */
    public Optional<String> topSuggestion() {
        if (suggestions.isEmpty()) {
            return Optional.empty();
        } else {
            Optional<Map.Entry<String, Long>> maxEntry =
                    suggestions.entrySet().stream().max( Comparator.comparing( Map.Entry::getValue ) );
            return Optional.ofNullable( maxEntry.get().getKey() );
        }
    }

}
