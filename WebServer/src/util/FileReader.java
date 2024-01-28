/*
 * Created by Bawb
 * 
 * 
 */
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author MeanC
 */
public class FileReader {
    
    public void readFile(String fileName) {

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
            }
            scanner.close();
        }
        
        catch (FileNotFoundException e) {
        }

    }    
    
    public ArrayList<String> getLines(String fileName) {
        
        ArrayList<String> lines = new ArrayList<>();
        
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                lines.add(data);
            }
            scanner.close();
        }
        
        catch (FileNotFoundException e) {
        }
        
        return lines;
        
    }      
    
}
