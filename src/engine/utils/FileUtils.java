package engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class FileUtils {

    public static String loadAsString(String filePath){
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(FileUtils.class.getResourceAsStream(filePath))));
            String line = "";
            while((line = br.readLine()) != null){
                result.append(line).append("\n");
            }
        }catch(IOException e){
            System.err.println("Couldn't find the file at " + filePath);
        }
        return result.toString();
    }



}
