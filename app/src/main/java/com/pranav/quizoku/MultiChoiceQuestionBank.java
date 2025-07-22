package com.pranav.quizoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiChoiceQuestionBank {
    public static List<QuestionMultiChoice> getShuffledQuestions(int count) {
        List<QuestionMultiChoice> all = new ArrayList<>();

        all.add(new QuestionMultiChoice("Who won the 2011 Cricket World Cup?",
                new String[]{"India", "Australia", "England", "South Africa"}, 0));
        all.add(new QuestionMultiChoice("How many players are in a soccer team on the field?",
                new String[]{"9", "10", "11", "12"}, 2));
        all.add(new QuestionMultiChoice("Which country hosts the Wimbledon tournament?",
                new String[]{"USA", "England", "Australia", "France"}, 1));
        all.add(new QuestionMultiChoice("What is the full form of IPL?",
                new String[]{"Indian Premier League", "International Pro League", "India Players League", "Indian Power League"}, 0));
        all.add(new QuestionMultiChoice("Who is known as the 'God of Cricket'?",
                new String[]{"Virat Kohli", "MS Dhoni", "Sachin Tendulkar", "Brian Lara"}, 2));
        all.add(new QuestionMultiChoice("How many rings are there in the Olympic logo?",
                new String[]{"4", "5", "6", "7"}, 1));
        all.add(new QuestionMultiChoice("Which sport uses a shuttlecock?",
                new String[]{"Tennis", "Squash", "Badminton", "Golf"}, 2));
        all.add(new QuestionMultiChoice("Who won the 2022 FIFA World Cup?",
                new String[]{"France", "Argentina", "Brazil", "Germany"}, 1));
        all.add(new QuestionMultiChoice("What sport is Serena Williams known for?",
                new String[]{"Golf", "Basketball", "Tennis", "Swimming"}, 2));
        all.add(new QuestionMultiChoice("Which country invented table tennis?",
                new String[]{"Japan", "USA", "India", "England"}, 3));

        all.add(new QuestionMultiChoice("Which athlete has won the most Olympic medals?",
                new String[]{"Usain Bolt", "Michael Phelps", "Carl Lewis", "Simone Biles"}, 1));
        all.add(new QuestionMultiChoice("Which sport uses the term 'checkmate'?",
                new String[]{"Cricket", "Tennis", "Chess", "Boxing"}, 2));
        all.add(new QuestionMultiChoice("Which country has hosted the Olympics most times?",
                new String[]{"USA", "Japan", "Germany", "France"}, 0));
        all.add(new QuestionMultiChoice("What is the highest score possible in 10-pin bowling?",
                new String[]{"250", "275", "300", "350"}, 2));
        all.add(new QuestionMultiChoice("How long is a marathon?",
                new String[]{"26.2 miles", "21.1 km", "13 miles", "42.5 km"}, 0));
        all.add(new QuestionMultiChoice("What color is the jersey worn by the leader in the Tour de France?",
                new String[]{"Red", "Blue", "Green", "Yellow"}, 3));
        all.add(new QuestionMultiChoice("Which sport is played with a bat, ball, and wickets?",
                new String[]{"Baseball", "Cricket", "Rugby", "Golf"}, 1));
        all.add(new QuestionMultiChoice("In which sport do you perform a slam dunk?",
                new String[]{"Volleyball", "Football", "Basketball", "Wrestling"}, 2));
        all.add(new QuestionMultiChoice("Who holds the record for fastest 100m sprint?",
                new String[]{"Yohan Blake", "Tyson Gay", "Usain Bolt", "Justin Gatlin"}, 2));
        all.add(new QuestionMultiChoice("Which sport is known as the 'King of Sports'?",
                new String[]{"Basketball", "Soccer", "Tennis", "Cricket"}, 1));

        all.add(new QuestionMultiChoice("Which country has won the most Cricket World Cups?",
                new String[]{"India", "England", "Australia", "Pakistan"}, 2));
        all.add(new QuestionMultiChoice("How many minutes are there in a football match?",
                new String[]{"80", "90", "100", "120"}, 1));
        all.add(new QuestionMultiChoice("In which game would you use a puck?",
                new String[]{"Baseball", "Hockey", "Golf", "Basketball"}, 1));
        all.add(new QuestionMultiChoice("What is the national sport of Canada?",
                new String[]{"Ice Hockey", "Baseball", "Basketball", "Rugby"}, 0));
        all.add(new QuestionMultiChoice("Who is the only tennis player to win 23 Grand Slam singles titles?",
                new String[]{"Steffi Graf", "Serena Williams", "Maria Sharapova", "Martina Navratilova"}, 1));
        all.add(new QuestionMultiChoice("Which country is famous for sumo wrestling?",
                new String[]{"China", "Korea", "Japan", "Thailand"}, 2));
        all.add(new QuestionMultiChoice("Which cricket player is known as the 'Wall'?",
                new String[]{"Virat Kohli", "Rahul Dravid", "MS Dhoni", "Rohit Sharma"}, 1));
        all.add(new QuestionMultiChoice("What is the name of the NBA team from Chicago?",
                new String[]{"Lakers", "Bulls", "Warriors", "Rockets"}, 1));
        all.add(new QuestionMultiChoice("Who is Lionel Messi?",
                new String[]{"Golfer", "Tennis Player", "Footballer", "Swimmer"}, 2));
        all.add(new QuestionMultiChoice("Which sport is associated with Wimbledon?",
                new String[]{"Golf", "Football", "Tennis", "Rugby"}, 2));

        all.add(new QuestionMultiChoice("Which cricketer is nicknamed 'Boom Boom'?",
                new String[]{"Shahid Afridi", "Chris Gayle", "Brett Lee", "MS Dhoni"}, 0));
        all.add(new QuestionMultiChoice("Which country has won the most Olympic gold medals?",
                new String[]{"China", "Russia", "USA", "Germany"}, 2));
        all.add(new QuestionMultiChoice("What sport did Michael Jordan play?",
                new String[]{"Tennis", "Baseball", "Basketball", "Golf"}, 2));
        all.add(new QuestionMultiChoice("How many players are in a volleyball team on court?",
                new String[]{"5", "6", "7", "8"}, 1));
        all.add(new QuestionMultiChoice("Where was the first modern Olympics held?",
                new String[]{"Paris", "Athens", "Rome", "Berlin"}, 1));
        all.add(new QuestionMultiChoice("Which Indian cricketer is known for finishing with sixes?",
                new String[]{"Yuvraj Singh", "Kapil Dev", "MS Dhoni", "Suresh Raina"}, 2));
        all.add(new QuestionMultiChoice("What sport is Tiger Woods famous for?",
                new String[]{"Basketball", "Golf", "Tennis", "Baseball"}, 1));
        all.add(new QuestionMultiChoice("Who is known as 'The Hitman' in Indian cricket?",
                new String[]{"Virat Kohli", "KL Rahul", "Rohit Sharma", "Hardik Pandya"}, 2));
        all.add(new QuestionMultiChoice("What is the term for 3 goals by a player in a match?",
                new String[]{"Triple", "Trick", "Hat-trick", "Three-pointer"}, 2));
        all.add(new QuestionMultiChoice("Which sport has 'love' as a score?",
                new String[]{"Badminton", "Cricket", "Tennis", "Football"}, 2));

        all.add(new QuestionMultiChoice("Who won the Ballon d'Or in 2022?",
                new String[]{"Cristiano Ronaldo", "Luka Modrić", "Lionel Messi", "Karim Benzema"}, 3));
        all.add(new QuestionMultiChoice("What is the height of a basketball hoop?",
                new String[]{"10 ft", "12 ft", "8 ft", "11 ft"}, 0));
        all.add(new QuestionMultiChoice("Which Indian state is famous for producing hockey players?",
                new String[]{"Punjab", "Kerala", "Gujarat", "West Bengal"}, 0));
        all.add(new QuestionMultiChoice("Which female gymnast is the most decorated ever?",
                new String[]{"Nadia Comăneci", "Mary Lou Retton", "Simone Biles", "Sunisa Lee"}, 2));
        all.add(new QuestionMultiChoice("Which country hosted the 2016 Summer Olympics?",
                new String[]{"China", "Brazil", "Russia", "Greece"}, 1));
        all.add(new QuestionMultiChoice("Which game is known as the 'gentleman’s game'?",
                new String[]{"Football", "Golf", "Cricket", "Polo"}, 2));
        all.add(new QuestionMultiChoice("In which sport is the Davis Cup awarded?",
                new String[]{"Tennis", "Badminton", "Football", "Golf"}, 0));
        all.add(new QuestionMultiChoice("What surface is used in French Open tennis?",
                new String[]{"Grass", "Clay", "Hard", "Sand"}, 1));
        all.add(new QuestionMultiChoice("Which Indian athlete won a gold medal at the Tokyo Olympics 2021?",
                new String[]{"PV Sindhu", "Bajrang Punia", "Neeraj Chopra", "Ravi Dahiya"}, 2));

        Collections.shuffle(all);
        return all.subList(0, Math.min(count, all.size()));
    }
}
