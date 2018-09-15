package ru.innopolis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] words = readSourse();
        RandomWords randomWords = new RandomWords();

        randomWords.genFiles("/home/sa/", 2, 15, words, 5);
    }

    static String[] readSourse() throws IOException {
        String projectHome = System.getenv().get("PWD");
        FileInputStream fileInputStream = new FileInputStream(projectHome + "/sourse");
        Scanner scanner = new Scanner(fileInputStream);

        ArrayList<String> arrayList = new ArrayList<>();

        for (; scanner.hasNext(); ) {
            arrayList.add(scanner.next());
        }
        fileInputStream.close();
        return arrayList.toArray(new String[0]);
    }
}
