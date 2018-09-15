package ru.innopolis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class RandomWords {
    private String[] words;
    private int probability;
    private Random random = new Random();

    public void genFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        if (probability == 0) throw new IllegalArgumentException("probability не должен быть равен нулю");
        this.words = words;
        this.probability = probability;
        for (int i = 0; i < n; i++) {
            File file = new File(path + "file" + i);
            FileOutputStream fos = new FileOutputStream(file);
            String text = makeFile(size);
            fos.write(text.getBytes());
            fos.flush();
            fos.close();
        }
    }

    private String makeFile(int size) {
        //size -количество предложений во всем файле
        int remain = size;
        StringBuilder builder = new StringBuilder();
        for (; remain > 0; ) {
            int sequenceCount = random.nextInt(remain) + 1;
            remain -= sequenceCount;
            builder.append(makeParagraph(sequenceCount));
        }
        return builder.toString();
    }

    private String makeParagraph(int length) {
        //количество предложений в данном абзаце
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(makeSentence());
        }
        builder.append("\r\n");
        return builder.toString();
    }

    private String makeSentence() {
        int length = random.nextInt(15) + 1; //количество слов в предложении
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= length; i++) {
            builder.append(makeWord());
            if (i != length) {
                builder.append(" ");
            }
        }
        builder.append(getSequenceSeparator())
                .setCharAt(0, (char) (builder.codePointAt(0) - 32));
        return builder.toString();
    }

    private String makeWord() {
        if (random.nextInt() % probability == 0) {
            return words[random.nextInt(words.length)];
        } else {
            int length = random.nextInt(14) + 1;
            return generateWord(length);
        }
    }

    private String generateWord(int length) {
        //length - количество букв в слове
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= length; i++) {
            builder.append(Character.toChars(this.random.nextInt(26) + 97));
        }
        return builder.toString();
    }


    private String getSequenceSeparator() {
        String separators = ".!?";
        return separators.toCharArray()[random.nextInt(separators.length())] + " ";
    }
}
