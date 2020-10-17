package ru.job4j.io;
/**
 *     Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу
 *     из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит слово «стоп», при
 *     этом он может продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
 *     программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу. Запись
 *     диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class ConsoleChat {

    private static boolean speakBot;
    private final String path;
    private static List<String> values = new ArrayList<>();

    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String EXIT = "закончить";
    private static final String HELP = "помощь";

    public ConsoleChat(String path) {
        //Механизм получения ссылки на файл через рефлексию. В переменной path, полученной
        //как параметр конструктора, получаем имя файла, который лежит в папке src/main/resources
        //после компиляции файл появится в папке target, соответственно будет выполнятся и через
        //psvm и через тесты, и через тревис
        this.path = Objects.requireNonNull(Config.class.getClassLoader().getResource(path)).getFile();;
    }

    /**
     * метод для вывод помощи
     */
    private static void help() {
        System.out.println("[BOT] > Если хочешь, чтоб я замолчал, набери \"" + STOP + "\"");
        System.out.println("[BOT] > Чтоб я заговорил, набери \"" + CONTINUE + "\"");
        System.out.println("[BOT] > Для выхода, набери \"" + EXIT + "\"");
        System.out.println("[BOT] > Для вызова этой справки, набери \"" + HELP + "\"");
    }

    /**
     * метод загружает словарь фраз для бота
     */
    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = read.readLine()) != null) {
                values.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkCommand(String phrase, String command, String message) {
        if (phrase.equals(command)) {
            System.out.println(message);
            speakBot = false;
        }
    }

    public static void main(String[] args) {
        ConsoleChat textBot = new ConsoleChat("consoleChatBot.txt");
        textBot.load();

        Scanner console = new Scanner(System.in);
        String phrase;
        speakBot = true;
        System.out.println("[BOT] > Привет. Я Bot по имени BOT");
        help();

        do {

            phrase = console.nextLine();
            checkCommand(phrase, STOP, "[BOT] > Захочешь поговорить, напиши \"" + CONTINUE + "\"");
            checkCommand(phrase, CONTINUE, "[BOT] > Привет! Я рад, что ты со мной захотел поговорить. Напиши мне что-нибудь.");

            if (phrase.equals(HELP)) {
                help();
            }

            if (speakBot) {
                System.out.println("[BOT] > " + values.get(new Random().nextInt(values.size())));
            }


        } while (!phrase.equals(EXIT));

        System.out.println("[BOT] > Заходи еще!");

    }
}
