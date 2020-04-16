package dev.skyit.pao.audit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Auditor {

    private final String logFileName;

    public Auditor(String logFileName) {
        this.logFileName = logFileName;
    }

    public void logQuery(String query) {
        try {
            Writer output = new BufferedWriter(new FileWriter(logFileName, true));
            output.write(query + ", " + getTimeStamp() + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }
}
