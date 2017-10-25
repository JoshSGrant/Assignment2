import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Scramble {

    //todo: declare/move all "global" variables here
    String filename;
    Scanner wordFile;

    public Scramble(String words) throws FileNotFoundException {
        this.filename = words; //this - vars part of this class
        File wordList = new File(words);
        wordFile = new Scanner(wordList);
    }

    public String getWords(){
        return this.filename;
    }

        /*PrintWriter temp = new PrintWriter("words.txt");
        temp.println("swamp");
        temp.println("rodents");
        temp.println("insanity");
        temp.println("lightning");
        temp.println("princess");
        temp.println("dread");
        temp.println("pirate");
        temp.println("sportsmanlike");
        temp.println("mutton");
        temp.println("despair");
        temp.println("inconceivable");
        temp.println("blunders");
        temp.print("grandpa");
        temp.close();

        PrintWriter temp2 = new PrintWriter("results.txt");
        temp2.println("0");
        temp2.println("0");
        temp2.print("0");
        temp2.close();*/

        //todo: ?open words.txt file here, change methods below so that
        //todo: they can access Scanner and File classes?


    public String getRealWord() throws IOException{ //arg was "int roundNum"

        /*
        //Open results file to get round info and make new scanner for fil
        //fixme
        File resultsList = new File("results.txt");
        Scanner resultsFile = new Scanner(resultsList);
        //Read next line and assign round# to int roundNum
        resultsFile.close();
        */

        String nextRealWord = "x"; //error msg

        //Read next line in words.txt
        //for(int i = 0; i <= roundNum; i++)
        boolean check = wordFile.hasNext(); //checks to be sure there is more data in words.txt
        if(check)
            nextRealWord = wordFile.nextLine();
        else
            nextRealWord = null;

        //wordFile.close();
        return nextRealWord;

    }

    public String getScrambledWord(String realWord) throws IOException{

        /*
        //Open results file to get round info and make new scanner for fil
        File resultsList = new File("results.txt");
        Scanner resultsFile = new Scanner(resultsList);
        //Read next line and assign round# to int roundNum
        int roundNum = resultsFile.nextInt();
        resultsFile.close();
        */

        StringBuilder word = new StringBuilder();
        String scramWord;
        int wordLength = realWord.length();
        int num;
        StringBuilder wordNum = new StringBuilder(); //scrambled charAt#s to String for comparing

        Random ranNum = new Random();

        for (int i = 0; i < wordLength; i++) { //scrambling sequence
            num = ranNum.nextInt(wordLength);

            while(wordNum.toString().contains(Integer.toString(num))){
                num = ranNum.nextInt(wordLength);
            }
            wordNum.append(Integer.toString(num));
            word.append(realWord.charAt(num));
        }

        scramWord = word.toString();  //assign scrambled substring to string
        return scramWord;

    }

}
