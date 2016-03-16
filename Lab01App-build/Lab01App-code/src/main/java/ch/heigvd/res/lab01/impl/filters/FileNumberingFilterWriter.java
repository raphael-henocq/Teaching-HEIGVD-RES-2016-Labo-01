package ch.heigvd.res.lab01.impl.filters;

import static ch.heigvd.res.lab01.impl.Utils.getIndexEndOfLine;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int counter = 1;
  private int previous;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    int index ;
    String tmp;
    String useful = str.substring(off, off + len);  //take only the useful part of the String
    
    index = getIndexEndOfLine(useful);
    //Ecriture de la première ligne
    if (counter == 1 )
    {
       super.write("1\t",0,2);
       counter++;
    }
    
    
    while (index !=-1)
    {
       super.write(useful.substring(0, index+1),0,index+1);
       super.write(counter++ +"\t",0,2);
       useful = useful.substring(index+1);
       index = getIndexEndOfLine(useful);
    }

    if (!useful.isEmpty())
    {
     super.write(useful,0,useful.length());
    }
    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException { 
    char tmp[];
    int size;
    
    if (c != '\r' )   
    {
       if (previous == '\r')
       {
         tmp = new char[2];
         tmp[0]=(char)previous;
         tmp[1]=(char)c;
         size = 2;
       } else
       {
          tmp = new char[1];
          tmp[0]=(char)c;
          size = 1;
       }
      this.write(new String(tmp),0,size);
    }
    previous = c;
  }

}
