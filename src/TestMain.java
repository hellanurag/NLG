import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class TestMain {
    public static void main(String[] args) {
        Lexicon lexicon = Lexicon.getDefaultLexicon();
        NLGFactory nlgFactory = new NLGFactory(lexicon);
        Realiser realiser = new Realiser(lexicon);

        NLGElement s1 = nlgFactory.createSentence("my dog is happy");
        String output = realiser.realiseSentence(s1);
        System.out.println(output);
        
        SPhraseSpec p = nlgFactory.createClause();
        p.setSubject("Mary");
        p.setVerb("chase");
        p.setObject("the monkey");
        p.setFeature(Feature.TENSE, Tense.FUTURE);
        p.setFeature(Feature.NEGATED, true);
        p.addComplement("very quickly"); // Adverb phrase, passed as a string
        p.addComplement("because she is exhausted"); // Prepositional phrase, string
        
        //Alternative way to do things. Might be better.
        NPPhraseSpec subject1 = nlgFactory.createNounPhrase("Mary");
        NPPhraseSpec subject2 = nlgFactory.createNounPhrase("her", "dog");

        CoordinatedPhraseElement subj = nlgFactory.createCoordinatedPhrase(subject1, subject2); 
        // may revert to nlgFactory.createCoordinatedPhrase( subject1, subject2 ) ;
        p.setSubject(subj);
        
        NPPhraseSpec object1 = nlgFactory.createNounPhrase("the monkey");
        NPPhraseSpec object2 = nlgFactory.createNounPhrase("George");

        CoordinatedPhraseElement obj = nlgFactory.createCoordinatedPhrase(object1, object2); 
        obj.addCoordinate("Martha");
        p.setObject(obj);
        obj.setFeature(Feature.CONJUNCTION, "or");
        
        String output2 = realiser.realiseSentence(p); // Realiser created earlier.
        System.out.println(output2);
}
}