import java.util.*;
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

        NLGElement s = nlgFactory.createSentence("my dog is happy");
        String output = realiser.realiseSentence(s);
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
        
        NPPhraseSpec place = nlgFactory.createNounPhrase("park");
        place.setDeterminer("the");
        PPPhraseSpec pp = nlgFactory.createPrepositionPhrase();
        pp.addComplement(place);
        pp.setPreposition("in");
        place.addPreModifier("leafy");
        p.addComplement(pp);
        
        String output2 = realiser.realiseSentence(p); // Realiser created earlier.
        System.out.println(output2);
        
        SPhraseSpec q = nlgFactory.createClause("I", "be", "happy");
        SPhraseSpec r = nlgFactory.createClause("I", "eat", "fish");
        
        r.setFeature(Feature.COMPLEMENTISER, "because");
        r.setFeature(Feature.TENSE, Tense.PAST);
        q.addComplement(r);
                
        String output3 = realiser.realiseSentence(q);  //Realiser created earlier
        System.out.println(output3);
        
        SPhraseSpec p1 = nlgFactory.createClause("Mary", "chase", "the monkey");
        SPhraseSpec p2 = nlgFactory.createClause("The monkey", "fight back"); 
        SPhraseSpec p3 = nlgFactory.createClause("Mary", "be", "nervous");
        
        DocumentElement s1 = nlgFactory.createSentence(p1);
        DocumentElement s2 = nlgFactory.createSentence(p2);
        DocumentElement s3 = nlgFactory.createSentence(p3);
        
        DocumentElement par1 = nlgFactory.createParagraph(Arrays.asList(s1, s2, s3));
        String output4 = realiser.realise(par1).getRealisation();
        System.out.println(output4);
        
        DocumentElement section = nlgFactory.createSection("The Trials and Tribulations of Mary and the Monkey");
        section.addComponent(par1);
        String output5 = realiser.realise(section).getRealisation();
        System.out.println(output5);
        
}
}