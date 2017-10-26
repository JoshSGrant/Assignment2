import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Scramble {

    //todo: declare/move all "global" variables here
    private String filestring;
    private Scanner wordFile;
    String nextRealWord;
    int test;
    int roundNum = 0;

    public int test(){ //todo delete

        return test;
    }

    public int roundNum(){

        return roundNum;
    }

    public Scramble(String filename) throws FileNotFoundException {
        //opens file "filename" and creates scanner for it
        this.filestring = filename;
        File wordList = new File(filename);
        wordFile = new Scanner(wordList); //open  file
        //todo DELETE
        test ++;
    }

    public void close() throws IOException {
        wordFile.close();
    }

    public void open() throws IOException {
        File wordList = new File("words.txt");
        wordFile = new Scanner(wordList);
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



    public String getRealWord() throws IOException{ //arg was "int roundNum"

            for (int i = 0; i <= roundNum; i++) {
                if (wordFile.hasNext())
                    nextRealWord = wordFile.nextLine();
                else
                    nextRealWord = null;
            }

        return nextRealWord;

    }

    public String getScrambledWord() throws IOException {

        /*
        //Open results file to get round info and make new scanner for fil
        File resultsList = new File("results.txt");
        Scanner resultsFile = new Scanner(resultsList);
        //Read next line and assign round# to int roundNum
        int roundNum = resultsFile.nextInt();
        resultsFile.close();
        */


        String realWord = nextRealWord;
        String nah = null;
        if (realWord != null) {
            String realWordH1 = realWord.substring(0, 5);
            String realWordH2 = realWord.substring(5);
            int num;
            StringBuilder word = new StringBuilder();
            StringBuilder wordNum = new StringBuilder();
            wordNum.setLength(0);
            String scramWord="";


            Random ranNum = new Random();

            if (realWord.length() > 9) {
                for (int i = 0; i < realWordH1.length(); i++) { //half 1
                    num = ranNum.nextInt(realWordH1.length()); //get random #

                    while (wordNum.toString().contains(Integer.toString(num))) { //ensure ran# unique
                        num = ranNum.nextInt(realWordH1.length());
                    }
                    wordNum.append(Integer.toString(num));  //appends unique ran# to SB
                    word.append(realWordH1.charAt(num)); //appends charAt(unique ran#) to SB word
                }
                wordNum.setLength(0);
                for (int i = 0; i < realWordH2.length(); i++) { //half 2
                    num = ranNum.nextInt(realWordH2.length()); //get random #

                    while (wordNum.toString().contains(Integer.toString(num))) { //ensure ran# unique
                        num = ranNum.nextInt(realWordH2.length());
                    }
                    wordNum.append(Integer.toString(num));  //appends unique ran# to SB
                    word.append(realWordH2.charAt(num)); //appends charAt(unique ran#) to SB word

                    scramWord = word.toString(); //casts SB word to String realWord
                }
            } else {
                for (int i = 0; i < realWord.length(); i++) { //whole word (unsplit)
                    num = ranNum.nextInt(realWord.length()); //get random #

                    while (wordNum.toString().contains(Integer.toString(num))) { //ensure ran# unique
                        num = ranNum.nextInt(realWord.length());
                    }
                    wordNum.append(Integer.toString(num));  //appends unique ran# to SB
                    word.append(realWord.charAt(num)); //appends charAt(unique ran#) to SB word
                }

                scramWord = word.toString(); //casts SB word to String realWord
            }
            return scramWord;

        }
        else

        return nah;
    }

}
