package ru.job4j.io;
/**
 *     Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу
 *     из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит слово «стоп», при
 *     этом он может продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
 *     программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу. Запись
 *     диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 */
import java.io.*;
import java.util.*;

public class ConsoleChat {

    private static boolean speakBot;
    private final String path;
    private final String pathLogChat;
    private static List<String> values = new ArrayList<>();

    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String EXIT = "закончить";
    private static final String HELP = "помощь";

    /**
     * конструктор
     * @param path - путь к словарю бота
     * @param pathLogChat - путь к логфайлу диалога с ботом
     * @throws IOException
     */
    public ConsoleChat(String path, String pathLogChat) throws IOException {
        //Механизм получения ссылки на файл через рефлексию. В переменной path, полученной
        //как параметр конструктора, получаем имя файла, который лежит в папке src/main/resources
        //после компиляции файл появится в папке target, соответственно будет выполнятся и через
        //psvm и через тесты, и через тревис
        this.path = Objects.requireNonNull(Config.class.getClassLoader().getResource(path)).getFile();
            try (FileWriter file =
                         new FileWriter (
                                 (Config.class.getClassLoader().getResource("")).getPath()
                                         + pathLogChat)
            ) {
                file.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
        this.pathLogChat = Objects.requireNonNull(Config.class.getClassLoader().getResource(pathLogChat)).getFile();

    }

    /**
     * метод для вывод помощи
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
     * Метод проверки комманды на приостановку или возобновления ответов бота
     * @param phrase - ответ пользователя или возможная команда боту, например, "стоп", "продолжить" и т.д.
     * @param command - текст коммадны для сранения
     * @param sb - параметр запрещающий или разрешающий ответ бота
     * @param message - сообщение бота, которое должно высветится в случае совпадения ответа пользователя и комманды
     * @param textBot - объект, который имеет метод для записи ответов бота или пользователя в файл
     */
    private static void checkCommand(String phrase, String command, boolean sb, String message, ConsoleChat textBot) {
        if (phrase.equals(command)) {
            textBot.writeLogChat(message);
            speakBot = sb;
        }
    }

    /**
     * метод выполняющий запись данных в файл. Дублирующее сообщение выводится в консоль.
     * @param line - строка для вывода инфомрации в консоль и записи в файл.
     */
    private void writeLogChat(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.pathLogChat, true))) {
            System.out.println(line);
            writer.write(line);
            writer.write(System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * метод для чтения словаря бота. В итоге формируется коллекция ответов бота
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

    public static void main(String[] args) throws IOException {
        ConsoleChat textBot = new ConsoleChat("consoleChatBot.txt", "consoleChatBot.log");
        textBot.load();

        textBot.readPhrase();

        Scanner console = new Scanner(System.in);
        String phrase;
        speakBot = true;
        textBot.writeLogChat("[BOT] > Привет. Я Bot по имени BOT");
        help();

        do {
            phrase = console.nextLine();
            textBot.writeLogChat("[HUMAN] > " + phrase);

            checkCommand(phrase, STOP, false, "[BOT] > Захочешь поговорить, напиши \"" + CONTINUE + "\"", textBot);
            checkCommand(phrase, CONTINUE, true, "[BOT] > Привет! Я рад, что ты со мной захотел поговорить. Напиши мне что-нибудь.", textBot);

            if (phrase.equals(HELP)) {
                help();
            }

            if (speakBot) {
                textBot.writeLogChat("[BOT] > " + values.get(new Random().nextInt(values.size())));
            }
        } while (!phrase.equals(EXIT));

        textBot.writeLogChat("[BOT] > Заходи еще!");

    }
}
