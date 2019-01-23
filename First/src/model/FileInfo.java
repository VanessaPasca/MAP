package model;

import java.io.BufferedReader;

public class FileInfo {
    private String fileName;
    private BufferedReader buff;

    public FileInfo(String fileName, BufferedReader buff) {
        this.fileName = fileName;
        this.buff = buff;
    }

    public String getFileName() {
        return fileName;
    }

    public BufferedReader getBuff() {
        return buff;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
