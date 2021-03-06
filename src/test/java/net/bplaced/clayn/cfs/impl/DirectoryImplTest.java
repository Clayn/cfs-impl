/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.bplaced.clayn.cfs.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;
import net.bplaced.clayn.cfs.CFileSystem;
import net.bplaced.clayn.cfs.impl.util.LocalFileSystemSupplier;
import net.bplaced.clayn.cfs.impl.util.SQLFileSystemSupplier;
import net.bplaced.clayn.cfs.test.DirectoryTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 */
@RunWith(Parameterized.class)
public class DirectoryImplTest extends DirectoryTest
{

    public DirectoryImplTest()
    {
        runningTests.addAll(Arrays.asList(TEST_ALL));
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows"))
        {
            runningTests.remove(TEST_DELETE);
        }
    }

    @Parameterized.Parameter
    public Supplier<CFileSystem> cfsSupplier;

    @Parameterized.Parameters
    public static Collection<Object[]> suppliers()
    {
        return Arrays.asList(new Object[][]
        {
            {
                new LocalFileSystemSupplier()
            },
            {
                new SQLFileSystemSupplier()
            }
        });
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Override
    public CFileSystem getFileSystem() throws Exception
    {
        return cfsSupplier.get();
    }
}
