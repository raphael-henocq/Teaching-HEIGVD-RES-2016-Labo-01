package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    File fileNames[] = rootDirectory.listFiles();
    vistor.visit(rootDirectory);
    
    if (fileNames!=null)
    {
      //Traitement of files
      for (File f : fileNames)
      {
         if (f.isFile())
         {
            vistor.visit(f);
         }
      }

      //Traitment of directories
      for (File f : fileNames)
      {
         if (f.isDirectory())
         {
            explore(f,vistor);
         }
      }
    }
    
    
  }

}
