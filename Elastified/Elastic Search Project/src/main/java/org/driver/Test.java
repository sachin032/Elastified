package org.driver;

import java.io.*;

/**
 * Created by @Shaun on 02 Nov 2016 .
 */
public class Test {

     public static void main(String[] args) throws IOException {
          String line = null;
          String[] doc = null;
          FileReader fReader = new FileReader("C:\\Users\\smishra\\Downloads\\Dummy Data\\SampleTextJson.json");
          BufferedReader reader = new BufferedReader(fReader);
          FileWriter writer = new FileWriter("C:\\Users\\smishra\\Downloads\\Dummy Data\\SampleTextJson.json");

          while ((line = reader.readLine()) != null) {
               writer.write("{ \"index\" : { \"_index\" : \"person\", \"_type\" : \"employee\", \"_id\" : \"1\" } }");
               doc = line.split("\n");
          }
          System.out.println(doc[0]);
          writer.close();
          fReader.close();
     }
}
