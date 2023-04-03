package ru.job4j.io;
/**
 *     Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу
 *     из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит слово «стоп», при
 *     этом он может продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
 *     программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу. Запись
 *     диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 */
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {

    private static boolean speakBot;
    private final String path;
    private final String pathLogChat;
    private static List<String> values = new ArrayList<>();
    private static List<String> chatLog = new LinkedList<>();

    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String EXIT = "закончить";
    private static final String HELP = "помощь";

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * конструктор
     * @param path - путь к словарю бота
     * @param pathLogChat - путь к log файлу диалога с ботом
     * @throws IOException
     */
    public ConsoleChat(String path, String pathLogChat) throws IOException {
        /**Механизм получения ссылки на файл через рефлексию. В переменной path, полученной
        * как параметр конструктора, получаем имя файла, который лежит в папке src/main/resources
        * после компиляции файл появится в папке target, соответственно будет выполняться и через
        * psvm и через тесты, и через тревис
         */
        this.path = Objects.requireNonNull(Config.class.getClassLoader().getResource(path)).getFile();
            try (FileWriter file =
                         new FileWriter(
                                 (Config.class.getClassLoader().getResource("")).getPath()
                                         + pathLogChat)
            ) {
                file.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.pathLogChat = Objects.requireNonNull(Config.class.getClassLoader().getResource(pathLogChat)).getFile();
    }

    /**
     * метод для вывода помощи
     */
    private static void help() {
        System.out.println("[BOT] > Если хочешь чтоб я замолчал, набери \"" + STOP + "\"");
        System.out.println("[BOT] > Чтоб я заговорил, набери \"" + CONTINUE + "\"");
        System.out.println("[BOT] > Для выхода, набери \"" + EXIT + "\"");
        System.out.println("[BOT] > Для вызова этой справки, набери \"" + HELP + "\"");
    }

    /**
     * метод загружает словарь фраз для бота
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = read.readLine()) != null) {
                values.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверки команды на приостановку или возобновления ответов бота
     * @param phrase - ответ пользователя или возможная команда боту, например, "стоп", "продолжить" и т.д.
     * @param command - текст командны для сравнения
     * @param sb - параметр запрещающий или разрешающий ответ бота
     * @param message - сообщение бота, которое должно высветится в случае совпадения ответа пользователя и команды
     */
    private void checkCommand(String phrase, String command, boolean sb, String message) {
        if (phrase.equals(command)) {
            chatLog.add(message);
            System.out.println(chatLog.get(chatLog.size() - 1));
            speakBot = sb;
        }
    }

    /**
     * Метод выполняющий запись данных в файл. Дублирующее сообщение выводится в консоль.
     * @param line - строка для вывода информации в консоль и записи в файл.
     * @deprecated
     */
    private void writeLogChat(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathLogChat, UTF_8, true))) {
            System.out.println(line);
            writer.write(line);
            writer.write(System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLogChat(List<String> chatLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathLogChat, UTF_8))) {
            Iterator<String> it = chatLog.iterator();
            while (it.hasNext()) {
                writer.write(it.next());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для чтения словаря бота. В итоге формируется коллекция ответов бота
     */
    private void readPhrase() {
        String line = null;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {

            while ((line = read.readLine()) != null) {
                values.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() {
        load();

        readPhrase();

        Scanner console = new Scanner(System.in);
        String phrase;
        speakBot = true;
        chatLog.add("[BOT] > Привет. Я Bot по имени BOT");
        System.out.println(chatLog.get(chatLog.size() - 1));
        help();

        do {
            phrase = console.nextLine();

            chatLog.add("[HUMAN] > " + phrase);
            System.out.println(chatLog.get(chatLog.size() - 1));

            checkCommand(phrase, STOP, false, "[BOT] > Захочешь поговорить, напиши \"" + CONTINUE + "\"");
            checkCommand(phrase, CONTINUE, true, "[BOT] > Привет! Я рад, что ты со мной захотел поговорить. Напиши мне что-нибудь.");

            if (phrase.equals(HELP)) {
                help();
            }

            if (speakBot) {
                chatLog.add("[BOT] > " + values.get(new Random().nextInt(values.size())));
                System.out.println(chatLog.get(chatLog.size() - 1));
            }
        } while (!phrase.equals(EXIT));

        chatLog.add("[BOT] > Заходи еще!");
        System.out.println(chatLog.get(chatLog.size() - 1));

        writeLogChat(chatLog);
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat textBot = new ConsoleChat("consoleChatBot.txt", "consoleChatBot.log");
        textBot.run();
    }
}
