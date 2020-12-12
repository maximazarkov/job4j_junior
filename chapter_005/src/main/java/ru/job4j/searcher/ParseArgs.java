package ru.job4j.searcher;

import java.io.File;

public class ParseArgs {

    private String[] args;

    private static String sourceDir;
    private static String fileNameMask;
    private static String resultFile;
    private static String typeMask;

    private static final String MFULL = "full";
    private static final String MMASK = "mask";
    private static final String MREGULAR = "regular";
    private static final String FORMATCOMMAND = "format: java -jar find.jar -d sourceDir -n mask -m|-f|-r -o resultFile"
            + System.lineSeparator()
            + "параметры:"
            + System.lineSeparator()
            + "-d          -ключ для указания директории (sourceDir), в которой будет производиться поиск"
            + System.lineSeparator()
            + "-m          -ключ для указания маски (mask), по которой будет производиться поиск"
            + System.lineSeparator()
            + "-m|-f|-r    -ключ для указания типа маски. искать по маске, либо -f - полное совпадение имени. -r регулярное выражение"
            + System.lineSeparator()
            + "-o          -ключ для указания имени log-файла (resultFile)";

    public ParseArgs(String[] args) {
        this.args = args;
    }

    public void parseArgs(String[] args) {

        int countNormalKeys = 0;
        if (args.length >= 7) {
            int indexArgs = 0;
            while (indexArgs < args.length) {
                switch (args[indexArgs]) {
                    case "-d" :
                        sourceDir = args[++indexArgs];
                        File file = new File(sourceDir);
                        if(!file.exists()) {
                            System.out.println(sourceDir + " папка не существует");
                        }
                        countNormalKeys++;
                        break;
                    case "-n" :
                        fileNameMask = args[++indexArgs];
                        countNormalKeys++;
                        break;
                    case "-m":
                        typeMask = MMASK;
                        countNormalKeys++;
                        break;
                    case "-f":
                        typeMask = MFULL;
                        countNormalKeys++;
                        break;
                    case "-r":
                        typeMask = MREGULAR;
                        countNormalKeys++;
                        break;
                    case "-o":
                        resultFile = args[++indexArgs];
                        countNormalKeys++;
                        break;
                    default:
                        System.out.println("Вы ввели недопустимый параметр. Формат комманды:");
                        System.out.println(FORMATCOMMAND);
                }
                indexArgs++;
            }

        } else {
            System.out.println("Вы ввели недостаточной число параметров. Формат комманды:");
            System.out.println(FORMATCOMMAND);
            if (countNormalKeys != 4) {
                System.out.println(FORMATCOMMAND);
            }
        }
    }

    public String sourceDir() {
        return sourceDir;
    }

    public String fileNameMask() {
        return fileNameMask;
    }

    public String resultFile() {
        return resultFile;
    }

    public String typeMask() {
        return typeMask;
    }
}
