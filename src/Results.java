import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Results {

    File resultsfile;
    PrintWriter printResults;
    int wins;
    int loses;

    public Results(String filename) throws IOException {
        this.resultsfile = new File(filename);
        File results = new File(filename);
        printResults = new PrintWriter(results);
    }


    public void won(){
        wins++;
    }

    public void lost(){
        loses++;
    }

    public void save() throws IOException{
        printResults = new PrintWriter("results.txt");
        printResults.println(wins+loses);
        printResults.println(wins);
        printResults.println(loses);
        printResults.close();
    }

    public String toString(){


        String results = "'s RESULTS\n" +
                "-------------------------" +
                "\n| Total Rounds:        " + (wins + loses) + "|" +
                "\n| Total Rounds Won:    " + wins + "|" +
                "\n| Total Rounds Lost:   " + loses + "|";
        return results;
    }

}
