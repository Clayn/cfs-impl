/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.impl.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.impl.ClaynFileSystems;
import static net.bplaced.clayn.cfs.impl.util.FileSystemFactory.getTemporaryFile;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class SQLFileSystemSupplier implements Supplier<CFileSystem>
{
    private String url;
    
    private void createDB() throws IOException
    {
        File db = getTemporaryFile();
        url="jdbc:h2:" + db.toURI().toURL();
    }
    
    private Connection getCDB()
    {
        try
        {
            return DriverManager.getConnection(url, "sa", "");
        } catch (SQLException ex)
        {
            Logger.getLogger(SQLFileSystemSupplier.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }
    @Override
    public CFileSystem get()
    {
        try
        {
            createDB();
            return ClaynFileSystems.getDBFileSystem(this::getCDB);
        } catch (IOException|SQLException ex)
        {
            Logger.getLogger(SQLFileSystemSupplier.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }
    
}
