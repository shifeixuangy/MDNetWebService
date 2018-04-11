package dataDAOTests;

import data.proj.FileNameHolder;
import dataDAO.proj.impl.FileNameQuery;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liudongdong on 2015/12/25.
 */
public class FileNameQueryTest extends TestCase {
    @Test
    public void testGetFileName()
    {
        FileNameQuery fq=new FileNameQuery();
        List<FileNameHolder>holders= fq.query(1,1, Timestamp.valueOf("2013-01-01 00:00:00"),Timestamp.valueOf("2016-01-01 00:00:00"));
        String str="";
    }
}
