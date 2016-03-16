package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.*;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    int index;
    String result[] = new String[2];
    
    index = getIndexEndOfLine(lines);
     
    if (index!=-1)
    {
      result[0]= lines.substring(0,index+1);
      result[1]=lines.substring(index+1);
    } else
    {      
       result[0]="";
       result[1]=lines;
    }
          
    return result;
   }
  
  //This function return the Index of the first end of line caractere.
  //It work for Windows, Mac and Linux
  public static int getIndexEndOfLine(String lines)
  {
    int index;
    index = lines.indexOf("\r\n");  //For windows
    if (index == -1)
    {
       index = lines.indexOf('\n');   //For unix
    } else
    {
       index++;  //Because there is 2 characters
    }
    
    if (index == -1)
    {
       index = lines.indexOf('\r');    //For MAC
    }
    
    return index;
  }

}
