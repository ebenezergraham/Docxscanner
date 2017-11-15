/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docxscanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;import java.util.Scanner;
;

/**
 *
 * @author EbenezerG
 */
public class Docxscanner {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader reader;
        String line;
        String find;
        String deliverables = "Deliverables";
        String filename="Filename";
        Boolean yes;
        String doc;
        int offset;
        int counter1=0,counter2=0,errors=0;
        int noDirFiles=new File("C:\\Users\\EbenezerG\\Documents\\test").list().length;
        System.out.println(filename+","+deliverables);
        
        Scanner input=new Scanner(System.in);
        System.out.print("Find: ");
        find=input.nextLine();
        
        System.out.print("Does the files naming have an offset?: ");
        String response=input.nextLine();
        
        if(response.equalsIgnoreCase("yes")){
        System.out.print("Define offset number: ");
        offset=input.nextInt();
        }else{
            offset=0;
        }
        
        for(int quantity=0+offset; quantity<noDirFiles+offset; quantity++){
        try {
            String number = setNumber(quantity);
            reader = new BufferedReader( new FileReader("C:\\Users\\EbenezerG\\Documents\\test\\CC-06.04.1-"+number+".doc"));
            ++counter1;
            while( (line = reader.readLine()) != null){
                if (line.contains(find)) {
                    yes=true;
                    if(yes){
                        reader = new BufferedReader( new FileReader("C:\\Users\\EbenezerG\\Documents\\test\\CC-06.04.1-"+number+".doc"));
                        while( (deliverables = reader.readLine()) != null){
                             if (deliverables.contains("Deliverables")) {
                                doc=deliverables.substring(49, 70);
                                filename=deliverables.substring(49, 64);
                                System.out.println(filename.trim()+","+doc.trim());
                                ++counter2;
                    }
                }     
            }
                    ;
        }
      }
        } catch (FileNotFoundException e) {
            errors++;
            System.out.println(filename+" has an error");
        } catch (IOException e) {
            errors++;
            System.out.println(filename+" has an error");
        }
        }
        System.out.println("\nOverview and Sanity Check:");
        System.out.println("But in total,"+counter1+" files got read out of "+noDirFiles+"\n"+counter2+" had \""+find+"\" in it!"); 
        System.out.println(errors+" files had errors");
    }
    public static String setNumber(int x){
        String y;
        if(x<100){
             y = "0"+Integer.toString(x);
        }else{
            return Integer.toString(x);
        }
        return y;
    }  
}
