package com.reddy.university.repository;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by deven on 9/23/2016.
 */
public class DatabaseTest {

    @Test
    public void whenTwoDistinctClassRecords_ReturnsTwoClasses(){
        Database db = new Database("D:\\git\\university\\input.csv");
        Assert.assertEquals(db.getClasses().size(),11);
    }
}
