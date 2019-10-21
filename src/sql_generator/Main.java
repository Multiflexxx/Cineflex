package sql_generator;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * CreateSeatsSQL
 * <p>
 * Copyright by @author Marcel Mertens
 * Website: https://mertens-web.ddns.net
 * <p>
 * Date: 21.10.2019
 */
public class Main
{
    static String fileName = "SQL.txt";

    public static int getRandomIntegerBetweenRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 3; i <= 112; i++) {
            int reihe = getRandomIntegerBetweenRange(6, 15);
            int spalte = getRandomIntegerBetweenRange(10, 20);
            generiereSitze(i, reihe, spalte);
        }
    }

    public static void generiereSitze(int saal, int reihen, int spalten) throws Exception {
        String sql1 = "INSERT INTO Sitz(`SitzplanID`, `Reihe`, `Nummer`, `Sitzklasse`) VALUES";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        writer.append(sql1);

        int generiereReihen = reihen;
        int generiereSpalten = spalten;

        int saalID = saal;
        char reihe = 'A';
        char sitzklasse = 'P';
        int counter = 0;

        for(int i = 0; i < generiereReihen+1; i++) {
            counter = 0;
            for (int j = 1; j < generiereSpalten+1; j++) {
                //"(3, 1, 'A', 'B')"
                switch(i) {
                    case 0: reihe = 'A';
                        break;
                    case 1: reihe = 'B';
                        break;
                    case 2: reihe = 'C';
                        break;
                    case 3: reihe = 'D';
                        break;
                    case 4: reihe = 'E';
                        break;
                    case 5: reihe = 'F';
                        break;
                    case 6: reihe = 'G';
                        break;
                    case 7:
                        reihe = 'H';
                        break;
                    case 8:
                        reihe = 'I';
                        break;
                    case 9:
                        reihe = 'J';
                        break;
                    case 10:
                        reihe = 'K';
                        break;
                    case 11:
                        reihe = 'L';
                        break;
                    case 12:
                        reihe = 'M';
                        break;
                    case 13:
                        reihe = 'N';
                        break;
                    case 14:
                        reihe = 'O';
                        break;
                    case 15:
                        reihe = 'P';
                        break;
                }

                if ((j == 1 || j == (generiereSpalten)) && i == 0) {
                    sitzklasse = 'B';
                } else if (reihe == 'H' || reihe == 'I' || reihe == 'J' || reihe == 'K' || reihe == 'L' || reihe == 'M' || reihe == 'N' || reihe == 'O' || reihe == 'P') {
                    sitzklasse = 'L';
                } else {
                    sitzklasse = 'P';
                }
                counter++;

                if (j == generiereSpalten && i == generiereReihen) {
                    writer.append("(" + saalID + ", '" + reihe + "'," + counter + " , '" + sitzklasse + "');");
                } else {
                    writer.append("(" + saalID + ", '" + reihe + "'," + counter + " , '" + sitzklasse + "'), \n");
                }
            }

        }


        writer.close();
    }

    /*
    public static void main(String[] args) throws  Exception{
        String sql1 = "INSERT INTO Sitzplan(`SitzplanID`, `SaalID`) VALUES";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        writer.append(sql1);

        int generiereReihen = 112;

        int saalID = 3;

        for(int i = saalID; i < generiereReihen+1; i++)
        {
            if(i < (generiereReihen))
                {
                    writer.append("("+i+", "+i +"), \n");
                }

                else
                {
                    writer.append("("+i+", "+i +");");
                }


        }

        writer.close();
    }
    */
}
