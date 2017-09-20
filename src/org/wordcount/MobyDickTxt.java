/*
* Author: Shawn Bash
*
* This class handles the mobydick.txt file.
* The main reason for this class is to validate
* the file was copied to the project src folder.
*
* */

package org.wordcount;

import java.io.File;

public class MobyDickTxt {

    final static String fileName = "mobydick.txt";
    File file = new File(fileName);

    public boolean fileExists() {
        return this.file.exists();
    }

    public String getFileName() {
        return fileName;
    }
}

