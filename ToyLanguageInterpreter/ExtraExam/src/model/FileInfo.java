package model;

import java.io.BufferedReader;

public class FileInfo {
    private String fileName;
    private BufferedReader reader;

    public FileInfo(String fileName, BufferedReader reader) {
        this.fileName = fileName;
        this.reader = reader;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public String getFileName() {
        return fileName;
    }

    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        return this.fileName;
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof FileInfo)){
            return false;
        }
        return ((FileInfo)other).getFileName().equals(fileName);

    }
}
