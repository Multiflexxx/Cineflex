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

    public static void main(String[] args) throws  Exception{
        String sql1 = "INSERT INTO Sitz(`SitzplanID`, `Reihe`, `Nummer`, `Sitzklasse`) VALUES";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

        writer.append(sql1);

        int generiereReihen = 6;
        int generiereSpalten = 10;

        int saalID = 3;
        char reihe = 'A';
        char sitzklasse = 'P';
        int counter = 0;

        for(int i = 0; i < generiereReihen+1; i++)
        {
            for (int j = 1; j < generiereSpalten+1; j++)
            {
                //"(3, 1, 'A', 'B')"
                switch(i)
                {
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
                }

                if((j == 1 || j == (generiereReihen+1)) && i == 0)
                {
                    sitzklasse = 'B';
                }

                else if(reihe == 'E' && reihe == 'F')
                {
                    sitzklasse = 'L';
                }

                else
                {
                    sitzklasse = 'P';
                }
                counter++;

                if(counter < ((generiereReihen+1)*generiereSpalten))
                {
                    writer.append("("+saalID+", "+counter+", '"+reihe+"', " + sitzklasse + "), \n");
                }

                else
                {
                    writer.append("("+saalID+", "+counter+", '"+reihe+"', " + sitzklasse + ");");
                }
            }

        }

       writer.close();
    }
}
