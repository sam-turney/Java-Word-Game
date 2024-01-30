/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author samtu
 */
public class WordGame {
    
    static ArrayList<String> wordList = new ArrayList<>();
    
    //reads a file containing English words and adds each word to a list of words wordList
    //uses the USA English (61,000 words) dictionary from http://www.gwicks.net/dictionaries.htm
    public static void initialize() throws FileNotFoundException {
        File text = new File("C:/Java Projects/WordGame/usa.txt");
        try (Scanner fileReader = new Scanner(text)) {
            while(fileReader.hasNext() == true) {
                wordList.add(fileReader.next());
            }
        }
    }
    
    //pull a random word from an ArrayList containing a list possible words
    public static String generateWord() {
        Random rand = new Random();
        int i = rand.nextInt(wordList.size());
        String word = wordList.get(i);
        return word;
    }
    
    //converts a list of characters to a formatted string
    public static String listToString(LinkedList<Character> list) {
        String string = "";
        for(int i=0; i<list.size(); i++) {
            string += list.get(i) + " ";
        }
        return string;
    }
    
    //continues or ends the game based on player input
    public static boolean askIfContinue(Scanner input, boolean run, int currentRound) {
        System.out.println("Do you wish to continue? \"Y\"/\"N\"");
        String answer = input.next().toUpperCase();
        if(answer.equals("N")) {
            System.out.println("Thanks for playing, you made it to round " + currentRound);
            run = false;
        }else {
            System.out.println("Good luck...");
            }
        return run;
    }
    
    //displays general end of game results
    public static void gameEndText(String word, LinkedList<Character> wrongGuesses) {
        System.out.println("The word was: " + word);
        System.out.println("Total wrong guesses: " + wrongGuesses.size());
    }
    
    //displays results for a lose screeen
    public static void loseText(String word, LinkedList<Character> wrongGuesses, int currentRound) {
        gameEndText(word, wrongGuesses);
        System.out.println("\nYou failed on round " + currentRound + ". Looks like you haven't memorized the English dictionary yet!");
    }
    
    //displays results for a win screen
    public static void winText(String word, LinkedList<Character> wrongGuesses) {
        gameEndText(word, wrongGuesses);
        System.out.println("\nCongratulaions! You know one English word!");
    }
}
