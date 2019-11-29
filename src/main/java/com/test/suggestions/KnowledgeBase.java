package com.test.suggestions;

import java.util.Optional;

public interface KnowledgeBase {
    Optional<KnowledgeWord> find(String test);
    void addWord(KnowledgeWord knowledgeWord);
}
