package com.bouillennec.v.topquiz.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by valbo on 18/09/2017.
 */

public class QuestionBank {

    private final ArrayList<Reponse> mReponseList1;
    private final ArrayList<Reponse> mReponseList2;
    private final ArrayList<Reponse> mReponseList3;
    private final ArrayList<Reponse> mReponseList4;
    private final ArrayList<Reponse> mReponseList5;
    private final ArrayList<Reponse> mReponseList6;
    private final ArrayList<Reponse> mReponseList7;
    private final ArrayList<Reponse> mReponseList8;
    private final ArrayList<Reponse> mReponseList9;
    private final ArrayList<Reponse> mReponseList10;
    private final ArrayList<Reponse> mReponseList11;
    private final ArrayList<Reponse> mReponseList12;

    private ArrayList<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank() {
/*        // Shuffle the question list before storing it
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList);
*/
        mNextQuestionIndex = 0;
        mReponseList1 = new ArrayList<>();
        mReponseList1.add(new Reponse(1,"Emmanuel Macron"));
        mReponseList1.add(new Reponse(2,"François Hollande"));
        mReponseList1.add(new Reponse(3,"Nicolas Sarkozy"));
        mReponseList1.add(new Reponse(4,"Jacques Chirac"));
        mReponseList2 = new ArrayList<>();
        mReponseList2.add(new Reponse(1,"22"));
        mReponseList2.add(new Reponse(2,"24"));
        mReponseList2.add(new Reponse(3,"28"));
        mReponseList2.add(new Reponse(4,"34"));
        mReponseList3 = new ArrayList<>();
        mReponseList3.add(new Reponse(1,"Andy Rubin"));
        mReponseList3.add(new Reponse(2,"Steve Wozniak"));
        mReponseList3.add(new Reponse(3,"Paul Smith"));
        mReponseList3.add(new Reponse(4,"Mark Zukerberg"));
        mReponseList4 = new ArrayList<>();
        mReponseList4.add(new Reponse(1,"1958"));
        mReponseList4.add(new Reponse(2,"1962"));
        mReponseList4.add(new Reponse(3,"1967"));
        mReponseList4.add(new Reponse(4,"1969"));
        mReponseList5 = new ArrayList<>();
        mReponseList5.add(new Reponse(1,"Warsaw"));
        mReponseList5.add(new Reponse(2,"Bucarest"));
        mReponseList5.add(new Reponse(3,"Berlin"));
        mReponseList5.add(new Reponse(4,"Budapest"));
        mReponseList6 = new ArrayList<>();
        mReponseList6.add(new Reponse(1,"Michelange"));
        mReponseList6.add(new Reponse(2,"Leonard De Vinci"));
        mReponseList6.add(new Reponse(3,"Raphael"));
        mReponseList6.add(new Reponse(4,"Picasso"));
        mReponseList7 = new ArrayList<>();
        mReponseList7.add(new Reponse(1,"1"));
        mReponseList7.add(new Reponse(2,"3"));
        mReponseList7.add(new Reponse(3,"5"));
        mReponseList7.add(new Reponse(4,"7"));
        mReponseList8 = new ArrayList<>();
        mReponseList8.add(new Reponse(1,"Darkseid"));
        mReponseList8.add(new Reponse(2,"Thanos"));
        mReponseList8.add(new Reponse(3,"Le Bouffon Vert"));
        mReponseList8.add(new Reponse(4,"Le Joker"));
        mReponseList9 = new ArrayList<>();
        mReponseList9.add(new Reponse(1,"2011"));
        mReponseList9.add(new Reponse(2,"2012"));
        mReponseList9.add(new Reponse(3,"2013"));
        mReponseList9.add(new Reponse(4,"2014"));
        mReponseList10 = new ArrayList<>();
        mReponseList10.add(new Reponse(1,"Brad Pitt"));
        mReponseList10.add(new Reponse(2,"Tom Cruise"));
        mReponseList10.add(new Reponse(3,"Ben Affleck"));
        mReponseList10.add(new Reponse(4,"Tom Hanks"));
        mReponseList11 = new ArrayList<>();
        mReponseList11.add(new Reponse(1,"C3P0"));
        mReponseList11.add(new Reponse(2,"T800"));
        mReponseList11.add(new Reponse(3,"BB8"));
        mReponseList11.add(new Reponse(4,"R2D2"));
        mReponseList12 = new ArrayList<>();
        mReponseList12.add(new Reponse(1,"Booba"));
        mReponseList12.add(new Reponse(2,"Rohff"));
        mReponseList12.add(new Reponse(3,"La Fouine"));
        mReponseList12.add(new Reponse(4,"Orelsan"));

        mQuestionList = new ArrayList<>();
        mQuestionList.add(new Question(1,"Qui est le président actuel ?", mReponseList1,1));
        mQuestionList.add(new Question(2,"Combien de pays font parti de l'UE ?", mReponseList2, 3));
        mQuestionList.add(new Question(3,"Qui est le créateur d'Android ?", mReponseList3, 1));
        mQuestionList.add(new Question(4,"En quelle année l'Homme a t'il marché sur la lune ?", mReponseList4, 4));
        mQuestionList.add(new Question(5,"Quelle est la capitale de la Roumanie ?", mReponseList5, 2));
        mQuestionList.add(new Question(6,"Qui a peint la Joconde ?", mReponseList6, 2));
        mQuestionList.add(new Question(7,"Combien de saison de Game of Thrones sont sorti ?", mReponseList7, 4));
        mQuestionList.add(new Question(8,"Qui est le meilleur ennemi de Batman ?", mReponseList8, 4));
        mQuestionList.add(new Question(9,"En quelle année est sortie la PS4 ?", mReponseList9, 3));
        mQuestionList.add(new Question(10,"Qui est l'acteur principal du film 'Seul au monde' ?", mReponseList10, 4));
        mQuestionList.add(new Question(11,"Quel est le nom du petit robot dans Star Wars VII ?", mReponseList11, 3));
        mQuestionList.add(new Question(12,"Qui est le meilleur rappeur français ?", mReponseList12, 4));

        Collections.shuffle(mQuestionList);
    }

    public Question getQuestion() {
        // Loop over the questions and return a new one at each call
        if(mNextQuestionIndex == mQuestionList.size())
        {
            mNextQuestionIndex = 0;
        }
        return mQuestionList.get(mNextQuestionIndex++);
    }

}