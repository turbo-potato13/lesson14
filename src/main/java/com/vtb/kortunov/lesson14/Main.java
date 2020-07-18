package com.vtb.kortunov.lesson14;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        //Task 1
        File file1 = new File("file.txt");
        char[] chars = {'q', 'w', 'e'};
        System.out.println(countWord(file1, chars));

        //Task 2
        File file2 = new File("task2");
        mergingFiles(file2);

        //Task 3
        File file3 = new File("file.txt");
        deleteDirectories(file3);
    }

    //Task 1
    public static int countWord(File file, char[] chars) {
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int symbol = bufferedReader.read();
            while (symbol != -1) {
                for (int i = 0; i < chars.length; i++) {
                    if (symbol != chars[i]) {
                        break;
                    } else if ((chars.length - 1) == i) {
                        count++;
                    }
                    symbol = bufferedReader.read();
                }
                symbol = bufferedReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    //Task 2
    public static void mergingFiles(File directory) {
        File mergeFile = new File(directory.getPath() + "\\merge.txt");
        try (BufferedWriter mergeWriter = new BufferedWriter(new FileWriter(mergeFile))) {
            mergeFile.createNewFile();
            for (final File fileEntry : Objects.requireNonNull(directory.listFiles())) {
                if (!fileEntry.isDirectory()) {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileEntry))) {
                        String str;
                        while ((str = bufferedReader.readLine()) != null) {
                            mergeWriter.append(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Task 2
    public static void deleteDirectories(File directory)  {
        if (!directory.isDirectory()){
            throw new RuntimeException();
        }
        for (final File fileEntry : Objects.requireNonNull(directory.listFiles())){
            if (fileEntry.isDirectory()) {
                deleteDirectories(fileEntry);
            }
            fileEntry.delete();
        }
    }

}
