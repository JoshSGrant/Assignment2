import java.io.*;
import java.util.Scanner;

public class Assig2 {


    public static void main(String[] args) throws IOException{

        String user="";
        int roundNum = 0;
        int guessNum;
        String wordInput;
        String realWord;
        String scramWord = "";
        StringBuilder wordCompare = new StringBuilder();
        int realWordLength = 0;
        String replay = "";
        String response = "";
        Scanner input = new Scanner(System.in); //create scanner object

        //Intro
        System.out.println("\nWELCOME TO WORD SCRAMBLE!\n" +
                "-------------------------\n" +
                "Please enter your User Name: ");
        String playerName = input.nextLine();
        user = playerName;
        Results results = new Results(user + " Results.txt"); //creates instance of Results class "results"
        Scramble scramble = new Scramble("words.txt"); //create instance of Scramble class "scramble"
        if(!results.resultsfile.exists()) {
            PrintWriter outputFile = new PrintWriter(user + "results.txt");//initialize results.txt
            outputFile.println(0);
            outputFile.println(0);
            outputFile.println(0);
            outputFile.close();
        }

        if (!results.resultsfile.exists()){
            System.out.println("Welcome New User!\n\nYour game data will be saved and you can" +
                    "\n continue where you left off with this username: "+ user);
        }
        if (results.resultsfile.exists()){
            System.out.println("Welcome back " + user + "!\n\n"+
            "Your game data has been saved from last time!\n"
            + results.toString());
        }



        //Game

        realWord = scramble.getRealWord();      //initial get
        scramble.close(); //todo: CHECK
        scramble.roundNum++; //round# incrememnt
        if(realWord != null)
            scramWord = scramble.getScrambledWord();

        System.out.println(realWord + " " + scramWord + scramble.test()); //todo: delete

        while(realWord != null && !response.equals("no")) {
            guessNum = 3;

            while(guessNum != 0 && !response.equals("no") && scramWord != null){
                if (guessNum == 3) {
                    System.out.println("\n" + playerName + ", you have 3 tries to unscramble" +
                            " each scrambled word.\nGood luck!\n");
                }
                //fixme
                System.out.println(realWord + " " + scramWord + scramble.test()); //todo: delete
                System.out.println("Scrambled word: " + scramWord);
                System.out.println("Your guess: ");
                wordInput = input.nextLine().toLowerCase();
                while(wordInput.length() != realWord.length()){
                    System.out.println("The word you are unscrambling contains " + realWord.length()
                    + " letters.\nPlease input at least " + realWord.length() + " characters: ");
                    wordInput = input.nextLine().toLowerCase();
                }
                while(!wordInput.matches("[a-zA-Z]+")){
                    System.out.println("Please input letters only: ");
                    wordInput = input.nextLine().toLowerCase();
                }

            if (wordInput.equals(realWord)) {       // CORRECT GUESS
                results.won();      //update score and save
                results.save();
                System.out.println("That is correct! Nice job, " + playerName + "!");
                scramble.open(); //todo: OPEN
                realWord = scramble.getRealWord();      //get next word
                    scramWord = scramble.getScrambledWord();
                    scramble.close(); //todo: CLOSE
                scramble.roundNum++; //round# incrememnt
                    //fixme
                System.out.println("Would you like to play another round? (yes or no): ");
                response = input.nextLine().toLowerCase();
                guessNum = 3;       //reset guess counter
            }
            else {          //WRONG GUESS
                System.out.println("\nSorry " + playerName + ", that is not correct.");
                guessNum--;
                System.out.println("Here are the letters you got in the right places:");

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
            System.out.println("\nRound over! The word was '" + realWord +
                    "'. Better luck next time, " + playerName + ".");

            System.out.println("Would you like to play another round? (yes or no): ");
            response = input.nextLine().toLowerCase();
            }
            if(!response.equals("no")){
                scramble.open(); //todo: OPEN
                realWord = scramble.getRealWord();      //get next word
                scramble.close(); //todo: CHECK
                scramble.roundNum++; //round# incrememnt

                scramWord = scramble.getScrambledWord();
            }

        }


        if(realWord == null || scramWord == null)
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

