package net.bplaced.clayn.cfs.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.impl.local.ClaynFileSystem;
import net.bplaced.clayn.cfs.impl.sql.SQLCFileSystem;

/**
 * This class collects all official implementions for the filesystem. All
 * classes are dependencies. That can be used if you want to have all
 * implementations while using just one dependency that includes all others.
 *
 * @author Clayn
 * @since 0.1
 */
public class ClaynFileSystems
{

    /**
     * Creates a new CFileSystem that uses the given database for performing
     * it's operations. Depending on the connection speed some operations such
     * as creation and writing files or directories take some time. However the
     * implementation may cache informations to reduce the needed data exchange.
     * Keep this in mind since that could bring the file system in an
     * inconsitence state when other applications access the database and change
     * the data from the filesystem.
     *
     * @param dbAccess the access to the database that should always create a
     * new connection.
     * @return a new CFilesystem working in a database
     * @throws SQLException if the connection couldnt be established or the
     * tables couldnt be created.
     * @since 0.1
     */
    public static CFileSystem getDBFileSystem(Supplier<Connection> dbAccess) throws SQLException
    {
        return new SQLCFileSystem(dbAccess);
    }

    /**
     * Creates a new CFileSystem that operates on the local file system using
     * the given directory as root directory.
     *
     * @param base the root for the CFileSystem
     * @return a new CFileSystem with the given file as root
     * @throws IOException if an I/O Exception occures
     * @since 0.1
     * @see #getDBFileSystem(java.util.function.Supplier)
     *
     */
    public static CFileSystem getLocalFileSystem(File base) throws IOException
    {
        return new ClaynFileSystem(base);
    }
}
