package by.bstu.pnv.education.laba_3_translate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 22.02.2018.
 */

public class WorkWithFille {
    public static List<String> reader(File path){
        List<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String s;
            while((s=br.readLine())!=null){
                list.add(s);
            }
        }
        catch(IOException ex) {

            System.out.println(ex.getMessage());
        }
        return list;
    }

    public static void write(File path, String text){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true)))
        {
            bw.write(text + "\n");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
