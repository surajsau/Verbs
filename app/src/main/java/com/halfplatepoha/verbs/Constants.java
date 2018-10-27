package com.halfplatepoha.verbs;

public interface Constants {

    int VERB_DB_VERSION = 1;
    int APP_DB_VERSION = 1;
    
    interface Schema {
        
        interface Verb {
            String FIRST_FORM = "firstForm";
            String SECOND_FORM = "secondForm";
            String READING = "reading";
            String ROMAJI = "romaji";
            String FIRST_VERB = "firstVerb";
            String FIRST_VERB_READING = "firstVerbReading";
            String FIRST_VERB_ROMAJI = "firstVerbRomaji";
            String FIRST_VERB_MASU = "firstVerbMasu";
            String FIRST_VERB_MASU_READING = "firstVerbMasuReading";
            String FIRST_VERB_MASU_ROMAJI = "firstVerbMasuRomaji";
            String SECOND_VERB = "secondVerb";
            String SECOND_VERB_READING = "secondVerbReading";
            String SECOND_VERB_ROMAJI = "secondVerbRomaji";
            String TRANSITIVE = "transitive";
            String USAGE_PATTERN = "usagePattern";
            String MEANINGS = "meanings";
            String SEE_ALSO = "seeAlso";
            String PRONOUNCE = "pronounce";
            String NOUN = "noun";
            String SYNONYM = "synonymn";
            String ANYTONYM = "antonym";
            String REMARKS = "remarks";
            String NLB_LINK = "nlbLink";
        }
        
    }
}
