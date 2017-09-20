/*
* Author: Shawn Bash
*
* This class contains test pertaining to the MobyDickTxt class.
*
* */

package org.wordcount;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class MobyDickTxtTests {

    MobyDickTxt mobyDick = new MobyDickTxt();
    File file;

    @Before
    public void setUp() {
        file = new File(mobyDick.getFileName());
    }

    @Test
    public void testObjExists() {
        assertNotNull(mobyDick);
    }

    @Test
    public void testMobyDickFileExists() {
        assertEquals(true, file.exists());
    }

    @Test
    public void testMobyDickTxtNotEmpty() {
        assertNotEquals(0, file.length());
    }
}
