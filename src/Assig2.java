import java.io.*;
import java.util.Scanner;

public class Assig2 {


    public static void main(String[] args) throws IOException{

        int roundNum = 0;
        int guessNum = 3;
        String wordInput;
        String realWord;
        String scramWord = "realWord was NULL possibly";
        StringBuilder wordCompare = new StringBuilder();
        int realWordLength = 0;
        String replay = "";
        String response = "";
        Scanner input = new Scanner(System.in); //create scanner object
        Results results = new Results("results.txt"); //creates instance of Results class "results"
        Scramble scramble = new Scramble("words.txt"); //create instance of Scramble class "scramble"

        //Intro
        System.out.println("\nWELCOME TO WORD SCRAMBLE!\n" +
                "-------------------------\n" +
                "Please enter your name: ");
        String playerName = input.nextLine();


        //Game
        realWord = scramble.getRealWord();      //initial get
        //if(realWord != null) fixme
        scramWord = scramble.getScrambledWord(realWord);

        while(realWord != null && !response.equals("no")) {
            guessNum = 3;

            while(guessNum != 0 && !response.equals("no")) {
                if (guessNum == 3) {
                    System.out.println("\n" + playerName + ", you have 3 tries to unscramble" +
                            " each scrambled word.\nGood luck!\n");
                }
                System.out.println("Scrambled word: " + scramWord);
                System.out.println("Your guess: ");
                wordInput = input.nextLine();
                while(wordInput.length() != realWord.length()){
                    System.out.println("The word you are unscrambling contains " + realWord.length()
                    + " letters.\nPlease input at least " + realWord.length() + " characters: ");
                    wordInput = input.nextLine();
                }
                while(!wordInput.matches("[a-zA-Z]+")){
                    System.out.println("Please input letters only: ");
                    wordInput = input.nextLine();
                }

                wordInput.toLowerCase();
            if (wordInput.equals(realWord)) {       // CORRECT GUESS
                results.won();      //update score and save
                results.save();
                System.out.println("That is correct! Nice job " + playerName + "!");
                realWord = scramble.getRealWord();      //get next word
                //if(realWord != null) fixme
                    scramWord = scramble.getScrambledWord(realWord);
                System.out.println("Would you like to play another round? (yes or no): ");
                response = input.nextLine().toLowerCase();
                guessNum = 3;       //reset guess counter
            }
            else {          //WRONG GUESS
                System.out.println("\nSorry " + playerName + ", that is not correct.");
                guessNum--;
                System.out.println("Here are the letters you got in the right places:");
                //todo: implement
                realWordLength = realWord.length();
                wordCompare.setLength(0); //clear wordCompare StringBuilder
                for(int i = 0; i < realWordLength; i++){
                    char x;
                    if(wordInput.charAt(i) == realWord.charAt(i)){
                        x = realWord.charAt(i);
                        wordCompare.append(x);
                    }
                    else
                        wordCompare.append(" _ ");
                }
                System.out.println(wordCompare);

                System.out.println("You have " + guessNum + " guesses remaining.\n");
            }

            }

            if(guessNum <= 0 && !response.equals("no")){
            results.lost();
            results.save();
            System.out.println("\nRound over! Better luck next time, " + playerName + ".");
            System.out.println("Would you like to play another round? (yes or no): ");
            response = input.nextLine().toLowerCase();
            }
            if(!response.equals("no")){
                realWord = scramble.getRealWord();      //get next word
                //if(realWord != null) fixme
                scramWord = scramble.getScrambledWord(realWord);
            }

        }


        if(scramble.getRealWord() == null)
            System.out.println("You've run out of words!");

            System.out.println("Thanks for playing, " + playerName + "!\n" +
                    "Here's the scoreboard:\n");

        //Print Results.toString
        System.out.println("=========================");
        System.out.println(playerName.toUpperCase() + results.toString());
        System.out.println("=========================");


    }


}
        /*
        results.won();
        results.lost();
        results.save();
        realWord = scramble.getRealWord();
        System.out.println(realWord);
        scramWord = scramble.getScrambledWord(realWord);
        System.out.println(scramWord);
        */

