package com.pranav.quizoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrueFalseQuestionBank {
    public static List<QuestionTrueFalse> getShuffledQuestions(int count) {
        List<QuestionTrueFalse> all = new ArrayList<>();

        all.add(new QuestionTrueFalse("The Great Wall of China is visible from space.", false));
        all.add(new QuestionTrueFalse("India gained independence in 1947.", true));
        all.add(new QuestionTrueFalse("Mahatma Gandhi was a lawyer by profession.", true));
        all.add(new QuestionTrueFalse("Water boils at 80°C at sea level.", false));
        all.add(new QuestionTrueFalse("Humans have 206 bones.", true));
        all.add(new QuestionTrueFalse("The currency of Japan is the Yen.", true));
        all.add(new QuestionTrueFalse("The Earth is the fourth planet from the Sun.", false));
        all.add(new QuestionTrueFalse("Python is only a snake, not a programming language.", false));
        all.add(new QuestionTrueFalse("Mount Everest is in India.", false));
        all.add(new QuestionTrueFalse("The Taj Mahal is made of white marble.", true));
        all.add(new QuestionTrueFalse("The Eiffel Tower is located in Berlin.", false));
        all.add(new QuestionTrueFalse("Shakespeare wrote 'Romeo and Juliet'.", true));
        all.add(new QuestionTrueFalse("The human heart has four chambers.", true));
        all.add(new QuestionTrueFalse("Oxygen is a metal.", false));
        all.add(new QuestionTrueFalse("The Amazon is the longest river in the world.", false)); // Nile is longer
        all.add(new QuestionTrueFalse("Photosynthesis occurs in plant leaves.", true));
        all.add(new QuestionTrueFalse("The capital of Australia is Sydney.", false)); // Canberra
        all.add(new QuestionTrueFalse("Albert Einstein developed the theory of relativity.", true));
        all.add(new QuestionTrueFalse("The Moon is a planet.", false));
        all.add(new QuestionTrueFalse("Asia is the largest continent.", true));
        all.add(new QuestionTrueFalse("Water freezes at 0°C.", true));
        all.add(new QuestionTrueFalse("There are 12 months in a year.", true));
        all.add(new QuestionTrueFalse("Venus is the closest planet to the Sun.", false)); // Mercury
        all.add(new QuestionTrueFalse("India is a continent.", false));
        all.add(new QuestionTrueFalse("Humans breathe in oxygen and exhale carbon dioxide.", true));
        all.add(new QuestionTrueFalse("Earth is the only planet that supports life.", true));
        all.add(new QuestionTrueFalse("The rainbow has six colors.", false)); // 7
        all.add(new QuestionTrueFalse("Sound travels faster than light.", false));
        all.add(new QuestionTrueFalse("A triangle has three sides.", true));
        all.add(new QuestionTrueFalse("Bats are blind.", false));
        all.add(new QuestionTrueFalse("Mango is the national fruit of India.", true));
        all.add(new QuestionTrueFalse("The human body has two kidneys.", true));
        all.add(new QuestionTrueFalse("The currency of the USA is the Dollar.", true));
        all.add(new QuestionTrueFalse("The Pyramids of Giza are in Mexico.", false)); // Egypt
        all.add(new QuestionTrueFalse("Neptune is the farthest planet from the Sun.", true));
        all.add(new QuestionTrueFalse("Plants produce carbon dioxide during the day.", false));
        all.add(new QuestionTrueFalse("An octopus has 6 legs.", false)); // 8
        all.add(new QuestionTrueFalse("Chocolate is toxic to dogs.", true));
        all.add(new QuestionTrueFalse("Jupiter is the largest planet in the solar system.", true));
        all.add(new QuestionTrueFalse("Diamonds are made of carbon.", true));
        all.add(new QuestionTrueFalse("There are 365 days in a leap year.", false)); // 366
        all.add(new QuestionTrueFalse("Lightning is hotter than the surface of the Sun.", true));
        all.add(new QuestionTrueFalse("A spider has 10 legs.", false)); // 8
        all.add(new QuestionTrueFalse("Gold is heavier than silver.", true));
        all.add(new QuestionTrueFalse("India is the world's most populous country.", false)); // As of now, China
        all.add(new QuestionTrueFalse("Sound cannot travel through a vacuum.", true));
        all.add(new QuestionTrueFalse("The human skeleton renews itself every 10 years.", true));
        all.add(new QuestionTrueFalse("The first computer virus was created in the 2000s.", false));
        all.add(new QuestionTrueFalse("Bananas grow on trees.", false)); // Technically, it's a herb
        all.add(new QuestionTrueFalse("The brain is the heaviest organ in the human body.", false)); // Liver is heavier

        Collections.shuffle(all);
        return all.subList(0, Math.min(count, all.size()));
    }
}

