/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author samtu
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static String Welcome;
    public static String RoundInfo; 
    public static void main(String[] args) throws FileNotFoundException {
        //set up varaibles
        WordGame.initialize();
        Scanner input = new Scanner(System.in);
        int currentRound = 0;
        boolean run = true;
        boolean guessing;
        boolean containsUnderscore = true;
        boolean correctGuess = false;
        boolean alreadyGuessed = false;
        
        
        Welcome = "Welcome to WordGame! (title is a work-in-progress)";
        System.out.println(Welcome);

        //code that runs the game
        while(run) {
            //set up variables
            currentRound +=1;
            guessing = true;
            String word = WordGame.generateWord();
            int length = word.length();
            LinkedList<Character> wrongGuesses = new LinkedList<>();

            //creates a LinkedList of underscores for each letter in the word
            LinkedList<Character> wordStatus = new LinkedList<>();
            for(int i=0; i<length; i++) {
                wordStatus.add('_');
            }
            
            //displays round information for the user
            RoundInfo = "Current round: Round " + currentRound + 
                "\nThe word to guess is " + wordStatus.size() + " letters: " + WordGame.listToString(wordStatus);
            System.out.println(RoundInfo);
            
            //The main program for prompting and recording player guesses.
            //Uses the LinkedList to replace underscores with correct guesses.
            while(guessing) {
                correctGuess = false;
                alreadyGuessed = false;
                System.out.println("\nPlease guess a letter:");
                
                //saves the player's guess as a character
                char guess = input.next().charAt(0);
                for(int i=0; i<length; i++) {
                    if(guess == word.charAt(i)) { //if player guesses correctly
                        wordStatus.set(i, guess);
                        correctGuess = true;
                    }else { //check if the letter has already been guessed
                        for(char letter : wrongGuesses) {
                            if(guess == letter) {
                            alreadyGuessed = true;
                            }
                        }
                    }
                }
                if(!correctGuess && !alreadyGuessed) {
                    wrongGuesses.add(guess);
                }
                System.out.println("\n" + WordGame.listToString(wordStatus));
                System.out.println("Wrong guesses: " + WordGame.listToString(wrongGuesses));
                //if the player gets 5 wrong guesses, the game has been lost :(
                if(wrongGuesses.size() >= 5) {
                    guessing = false;
                    //print the results
                    WordGame.loseText(word, wrongGuesses, currentRound);
                    run = WordGame.askIfContinue(input, run, currentRound);
                    currentRound = 0;
                    break;
                }
                //checks to see if any letters have not been guessed yet
                containsUnderscore = false;
                for(int i=0; i<length; i++) {
                    if(wordStatus.get(i).equals('_')) {
                        containsUnderscore = true;
                    }
                }
                //if all letters have been guessed, the game has been won!
                if(!containsUnderscore) {
                    guessing = false;
                    //print the results
                    WordGame.winText(word, wrongGuesses);
                    run = WordGame.askIfContinue(input, run, currentRound);
                }
            }
        }
    }

    
    
}
