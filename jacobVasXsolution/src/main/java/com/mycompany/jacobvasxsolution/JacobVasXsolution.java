/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.jacobvasxsolution;

// this is not a standard java package
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.IntSummaryStatistics;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 *
 * @author Makro
 */
public class JacobVasXsolution {
    
    private static String csvSeparator = ",";
    private static String outputFileName = null;
  
    
    
    private void validateCall(String[] callArgs)
    {
        System.out.println("-------------------------------------------");
        System.out.println("Welcome to the csv file processor.");
        System.out.println("You can give three inputs");
        System.out.println("The first argument is the location of the csv you want to be processed.");
        System.out.println("The second argument is the position you want to store the processed csv.");
        System.out.println("The third is the seperator you want your columns to be seperated as.");
        System.out.println("Eg. program_name inputfilename outputfilename csvseperator");
        System.out.println("-------------------------------------------");
        
        if(callArgs.length < 1)
        {
            System.out.println("*************************************");
            System.out.println("Too few arguments, run as follows: pgmname inputfilename outputfilename csvseperator\n"
                    + "eg JacobVasXsolution <path>/myinput.csv <path>/myoutput.csv ,");
            System.out.println("*************************************");
            System.exit(0);
        }
        else if(callArgs.length == 1)
        {
            System.out.println("You omitted an output file and seperator. Please specify the location and name of the output file.");
            System.out.println("A default output file will created in the same folder as you input file");
            System.out.println("Input file is " + callArgs[0]);
            String arg = callArgs[0];
            String csvName = arg.substring(arg .length() - 12, arg .length() );
            if(!csvName.equals("employee.csv") )
            {
                System.out.println(csvName);
                System.out.println("The file employee.csv is not found in this folder.");
                System.exit(0);
            }
            String path = arg.substring(0, arg .length() - 12);
            outputFileName = path+"default.csv";
            
        }
        else if(callArgs.length == 2)
        {
            String arg = callArgs[0];
            String csvName = arg.substring(arg .length() - 12, arg .length() );
            if(!csvName.equals("employee.csv") )
            {
                System.out.println("The file employee.csv is not found in this folder.");
                System.exit(0);
            }
            
            System.out.println("You omitted a seperator.");
            System.out.println("Default file created in your directory.");
            System.out.println("Input file is " + callArgs[0]);
            outputFileName = callArgs[1];
        }
        
        else
        {
            System.out.println("Input file is " + callArgs[0]);
            System.out.println("Output file to be generated is " + callArgs[1]);
            System.out.println("CSV separator is " +  callArgs[2]);
            outputFileName = callArgs[1];
            csvSeparator = callArgs[2];
        }
    }
    
    public List<FileHeaders> readInput(String fileName)
    {   
        Path path = Paths.get(fileName);
        List<FileHeaders> fileHeaders = new ArrayList<FileHeaders>();
        try(BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8")))
        {
 
            String currentLine = null;
     
            String[] lineInArray = null; 
            while((currentLine = reader.readLine()) != null)
            {
                lineInArray = currentLine.split(csvSeparator);
                fileHeaders.add(new FileHeaders(lineInArray));
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace(); 
        }
        return fileHeaders;
    }
    
    
    
    public void calcAndPrintSummaries(List<FileHeaders> fileHeaders)
    {
        String csvSeparator = ",";
         Map<String, IntSummaryStatistics> groupInpByDept = fileHeaders.stream().
                collect(Collectors.groupingBy(FileHeaders::getOccupation
                        ,Collectors.summarizingInt(FileHeaders::getSalary))); 
                        
        System.out.println("----------Printing the summaries individually----------");
        groupInpByDept.forEach((key,value) ->System.out.println("Department:"+key + "Employees:" + value.getCount() 
                + ",MinSalary:" + value.getMin() 
                + ", Average:"+ value.getAverage()
                + ",MaxSalary:"+value.getMax()
                + ",TotalSalary:"+value.getSum()));
        
         IntSummaryStatistics groupTotal = fileHeaders.stream().
                collect(Collectors.summarizingInt(FileHeaders::getSalary));
         System.out.println("Totals: Employees="+groupTotal.getCount() 
                 + ",MinSalary=" + groupTotal.getMin()
                 + ",Average=" + groupTotal.getAverage()
                 + ",MaxSalary=" + groupTotal.getMax()
                 + ",TotalSalary=" + groupTotal.getSum()  );
    }
    
   public static List<FileHeaders> readFileCSV(String inputFileName)
   {
        String csvSeparator = ",";
        Path path = Paths.get(inputFileName);
        
        List<FileHeaders> fileHeaders = new ArrayList<FileHeaders>();
        File file = new File(inputFileName);
        boolean exists = file.exists();
        
        if(!exists)
        {
            System.out.println("File does not exist");
            System.exit(0);
        }
        FileReader input = null;
        
        try(BufferedReader br = new BufferedReader(new FileReader(inputFileName)))
        {
            String currentLine = null;
     
            String[] lineInArray = null; 
            boolean firstReadFlag = false;
            br.readLine(); //Reading headers
                
           
            while((currentLine = br.readLine()) != null){//while there is content on the current line
                lineInArray = currentLine.split(csvSeparator);
                fileHeaders.add(new FileHeaders(lineInArray));
            }
            
            
        }catch(Exception ex){
            System.out.println("CSV removed: "+ex.getMessage());
            
    }
        return fileHeaders;
       
       
       
   }
    
   public static void writeToCSV(List<FileHeaders> fileObject_List, String outputfileName, String csvSeparator)
   {
       
       File file = new File(outputfileName);
       Map<String, IntSummaryStatistics> groupInpByDept = fileObject_List.stream().
        collect(Collectors.groupingBy(FileHeaders::getOccupation
                ,Collectors.summarizingInt(FileHeaders::getSalary))); 
       
       IntSummaryStatistics groupTotal = fileObject_List.stream().
        collect(Collectors.summarizingInt(FileHeaders::getSalary));
       
       String data;
       try{
            FileWriter outputfile = new FileWriter(file);
            String header =  "Department"+ csvSeparator
                +"Employees"+ csvSeparator
                + "MinSalary"+ csvSeparator
                +"AvgSalary"+ csvSeparator
                +"MaxSalary"
                + csvSeparator+"TotalSalary" ;
            outputfile.write(header);
            outputfile.write("\n");
            
            List<Entry<String, IntSummaryStatistics>> list = new ArrayList<>(groupInpByDept.entrySet());
            list.sort(Entry.comparingByKey());
            
            for (Map.Entry<String, IntSummaryStatistics> entry : list)
            {
                outputfile.write(entry.getKey() + csvSeparator
                    + entry.getValue().getCount()+ csvSeparator
                    + entry.getValue().getMin()+ csvSeparator
                    + entry.getValue().getAverage()+ csvSeparator
                    + entry.getValue().getMax()+ csvSeparator
                    + entry.getValue().getSum());
                outputfile.write("\n");            
            }
                outputfile.write("Totals"+csvSeparator
                +groupTotal.getCount()+csvSeparator
                +groupTotal.getMin()+csvSeparator
                +String.format("%.2f", groupTotal.getAverage())+csvSeparator
                +groupTotal.getMax()+csvSeparator
                +groupTotal.getSum());
                outputfile.close();    
        }
        catch (IOException e) {
            e.printStackTrace();
        }
   }
   
   
   
    public static void main(String[] args)  {
        
        JacobVasXsolution soln = new JacobVasXsolution();
        soln.validateCall(args);
        List<FileHeaders> fileHeaders = new ArrayList<FileHeaders>(); 
        
        fileHeaders = readFileCSV(args[0]);
        writeToCSV(fileHeaders,outputFileName,csvSeparator);
    }
 
        
}

