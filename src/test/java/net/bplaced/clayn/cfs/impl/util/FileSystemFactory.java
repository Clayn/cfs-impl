/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.impl.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class FileSystemFactory
{

    static String DB_URL = "";

    static
    {
        try
        {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(FileSystemFactory.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    static File getTemporaryFile() throws IOException
    {
        TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        return folder.newFile("testdb");
    }

    public static File getTemporaryDir() throws IOException
    {
        TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        return folder.newFolder();
    }

//    public static Connection getConnection()
//    {
//        
//        try
//        {
//            
//            System.out.println(url);
//            //url="jdbc:h2:/Users/lpazderski/h2/cfs/sql";
//            return DriverManager.getConnection(url,"sa","");
//        } catch (IOException | SQLException ex)
//        {
//            Logger.getLogger(FileSystemFactory.class.getName()).log(Level.SEVERE,
//                    null, ex);
//            throw new RuntimeException(ex);
//        }
//    }
}
