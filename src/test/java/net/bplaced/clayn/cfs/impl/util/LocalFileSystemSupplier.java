/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.impl.util;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.impl.ClaynFileSystems;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
public class LocalFileSystemSupplier implements Supplier<CFileSystem>
{

    public static final TemporaryFolder FOLDER = new TemporaryFolder();
    public static final BooleanProperty CREATED = new SimpleBooleanProperty(
            false);
    
    @Override
    public CFileSystem get()
    {
        
        try
        {
            if(!CREATED.get())
        {
            FOLDER.create();
            CREATED.set(true);
        }
            return ClaynFileSystems.getLocalFileSystem(FOLDER.newFolder());
        } catch (IOException ex)
        {
            Logger.getLogger(LocalFileSystemSupplier.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }
    
}
