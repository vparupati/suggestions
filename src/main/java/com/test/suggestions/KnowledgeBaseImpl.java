package com.test.suggestions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KnowledgeBaseImpl implements KnowledgeBase {

    private Map<String,KnowledgeWord> knowledge;

    public KnowledgeBaseImpl() {
        knowledge = new HashMap<>();
    }

    public Optional<KnowledgeWord> find(String word) {
        return Optional.ofNullable( knowledge.get( word.toLowerCase() ) );
    }

    @Override
    public void addWord(KnowledgeWord knowledgeWord) {
        knowledge.put( knowledgeWord.getWord().toLowerCase(),knowledgeWord );
    }
}
