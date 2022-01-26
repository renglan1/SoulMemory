package com.soulmemory.main;
/* *************************************************************************
Program: DSDeathCounter.java
by: Rob England #3624767
Program Date: October 29th, 2020
****************************************************************************
Purpose: Tracks the deaths and key accomplishments of a given character.
         Created for Dark Souls but applies to most games of similar nature.
************************************************************************* */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  

public class SoulMemoryDriver
{
   public static void main(String[] args)
   {
      String playerName, game, tag, currArea, currBoss = "NOT PROVIDED", choice, line = "";
      int numDeaths = 0, bossDeaths = 0, sevDeaths = 0;
      boolean done = false;
      File deathRecord;
      Scanner userInput = new Scanner(System.in);
      
      System.out.println("> Enter the name of the player you would like to track deaths for:");
      playerName = userInput.nextLine().toUpperCase();
      System.out.println("> What game would you like to track deaths for?");
      game = userInput.nextLine().toUpperCase();
      System.out.println("> Enter the tag, if any, associated with this file:");
      tag = userInput.nextLine().toUpperCase();
      
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
      
      try
      {
         // Creates the counter file if it doesn't exist, the writer, and the scanner
         if (tag.equals(""))
         {
            deathRecord = new File(System.getProperty("user.dir") + game + "_" + playerName + ".txt");
         }
         else
         {
            deathRecord = new File(System.getProperty("user.dir") + game + "_" + playerName + "_" + tag + ".txt");
         }
         
         if (deathRecord.createNewFile())
         {
            if (tag.equals(""))
            {
               System.out.println("> File created for " + playerName + " with no tag");
            }
            else
            {
               System.out.println("> File created for " + playerName + " with the tag: " + tag);
            }
         }
         else
         {
            if (tag.equals(""))
            {
               System.out.println("> File for " + playerName + " in " + game + " already exists");
            }
            else
            {
               System.out.println("> File for " + playerName + " in " + game + " with the tag " + tag + " already exists");
            }
         }
         
         Scanner deathReader = new Scanner(deathRecord);
         while (deathReader.hasNextLine())
         {  
            line = deathReader.nextLine();
            if (line.contains("Death"))
            {
               numDeaths++;
            }
         }
         // Prints the number of deaths
         System.out.println("> " + playerName + " has died " + numDeaths + " times to date\n");
         
         System.out.println("> Enter the name of the AREA currently being fought through:");
         currArea = userInput.nextLine().toUpperCase();
         System.out.println("> Enter the name of the AREA BOSS currently being challenged:");
         currBoss = userInput.nextLine().toUpperCase();
         
         Scanner bossReader = new Scanner(deathRecord);
         while (bossReader.hasNextLine())
         {  
            line = bossReader.nextLine();
            if (line.contains("y " + currBoss))
            {
               bossDeaths++;
            }
         }
         // Prints the number of deaths
         System.out.println("> " + currBoss + " has killed " + playerName + " " + bossDeaths + " times to date");
         
         while (!done)
         {
            System.out.println("\n> Currently tracking deaths in the area " + currArea + " with the associated boss " + currBoss);
            System.out.println("> To view the options menu, enter 'M'");
            choice = userInput.nextLine().toUpperCase();
            
            if (choice.equals("N"))
            {
               FileWriter deathScribe = new FileWriter(deathRecord, true);
               numDeaths++;
               deathScribe.write("Death no. " + numDeaths + " occured on " + dtf.format(LocalDateTime.now()) + " in the area " + currArea + "\n");
               deathScribe.close();
               System.out.println("> Recorded one death in " + currArea); 
               System.out.println("> " + playerName + " has died " + numDeaths + " times to date");
            }
            else if (choice.equals("B"))
            {
               FileWriter bossScribe = new FileWriter(deathRecord, true);
               numDeaths++;
               bossDeaths++;
               bossScribe.write("Death no. " + numDeaths + " occured on " + dtf.format(LocalDateTime.now()) + " and was caused by " + currBoss + "\n");
               bossScribe.close();
               System.out.println("> Recorded one death to " + currBoss + "\n> " + playerName + " has died " + bossDeaths + " times to this boss");
               System.out.println("> " + playerName + " has died " + numDeaths + " times to date");
            }
            else if (choice.equals("K"))
            {
               FileWriter killScribe = new FileWriter(deathRecord, true);
               killScribe.write("\n> Vanquished " + currBoss + " on " + dtf.format(LocalDateTime.now()) + " after " + (bossDeaths+1) + " attempt(s)\n\n");
               killScribe.close();
               System.out.printf("> Recorded boss kill on: %s%n", currBoss); 
               currBoss = "NOT PROVIDED";
               bossDeaths = 0;
            }
            else if (choice.equals("U"))
            {
               System.out.println("> Would you like to update the AREA or the BOSS?");
               choice = userInput.nextLine().toUpperCase();
               
               while (!"AREA".equals(choice) && !"BOSS".equals(choice))
               {
                  System.out.println("> Invalid entry, please input either 'AREA' or 'BOSS'");
                  choice = userInput.nextLine().toUpperCase();
               }
               
               if (choice.equals("AREA"))
               {
                  System.out.println("> Enter the name of the AREA currently being fought through:");
                  currArea = userInput.nextLine().toUpperCase();
               }
               else
               {
                  System.out.println("> Enter the name of the AREA BOSS currently being challenged:");
                  currBoss = userInput.nextLine().toUpperCase();
                  bossDeaths = 0;
                  
                  Scanner newBoss = new Scanner(deathRecord);
                  while (newBoss.hasNextLine())
                  {  
                     line = newBoss.nextLine();
                     if (line.contains("y " + currBoss))
                     {
                        bossDeaths++;
                     }
                  }
                  // Prints the number of deaths
                  System.out.println("> " + currBoss + " has killed " + playerName + " " + bossDeaths + " times to date");
               }
            }
            else if (choice.equals("S"))
            {
               System.out.println("Would you like to record these deaths for the AREA or BOSS?");
               choice = userInput.nextLine().toUpperCase();
               System.out.println("How many deaths would you like to record?");
               sevDeaths = userInput.nextInt();
               userInput.nextLine();
               
               while (!"AREA".equals(choice) && !"BOSS".equals(choice))
               {
                  System.out.println("> Invalid entry, please input either 'AREA' or 'BOSS'");
                  choice = userInput.nextLine().toUpperCase();
               }
               
               if (choice.equals("AREA"))
               {
                  FileWriter massacreScribe = new FileWriter(deathRecord, true);
                  for (int i = 0; i < sevDeaths; i++)
                  {
                     numDeaths++;
                     massacreScribe.write("Death no. " + numDeaths + " occured on " + java.time.LocalDate.now() + " in the area " + currArea + "\n"); 
                  }
                  System.out.println("> Recorded " + sevDeaths + " deaths in the area " + currArea);
                  System.out.println("> " + playerName + " has died " + numDeaths + " to date");
                  massacreScribe.close(); 
               }
               else
               {
                  FileWriter slaughterScribe = new FileWriter(deathRecord, true);
                  for (int i = 0; i < sevDeaths; i++)
                  {
                     numDeaths++;
                     bossDeaths++;
                     slaughterScribe.write("Death no. " + numDeaths + " occured on " + java.time.LocalDate.now() + " and was caused by " + currBoss + "\n"); 
                  }
                  System.out.println("> Recorded " + sevDeaths + " deaths to " + currBoss);
                  System.out.println("> " + playerName + " has died " + numDeaths + " to date");
                  slaughterScribe.close();    
               }
            }
            else if (choice.equals("M"))
            {
               System.out.println("> To record a death to a normal enemy, enter 'N'\n> To record a death to the current boss, enter 'B'\n> To record a boss kill, enter 'K'\n> To update the BOSS or AREA, enter 'U'\n> To record several deaths, enter 'S'\n> To exit the program, enter 'E'");
            }
            else if (choice.equals("E"))
            {
               done = true;
            }
            else
            {
               System.out.println("Invalid entry");
            }
         }
      }
      catch (Exception e)
      {
         System.out.println("Error creating/reading file.");
         e.getStackTrace();
      } 
   }
}