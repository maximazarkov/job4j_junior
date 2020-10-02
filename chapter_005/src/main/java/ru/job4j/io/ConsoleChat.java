package ru.job4j.io;
/**
 *     Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу
 *     из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит слово «стоп», при
 *     этом он может продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
 *     программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу. Запись
 *     диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ConsoleChat {

    private final String path;
    private static List<String> values = new ArrayList<>();

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
        System.out.println("[BOT] > Если хочешь, чтоб я замолчал, набери \"стоп\"");
        System.out.println("[BOT] > Чтоб я заговорил, набери \"продолжить\"");
        System.out.println("[BOT] > Для выхода, набери \"закончить\"");
        System.out.println("[BOT] > Для вызова этой справки, набери \"помощь\"");
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

    public static void main(String[] args) {
        ConsoleChat textBot = new ConsoleChat("consoleChatBot.txt");
        textBot.load();

        Scanner console = new Scanner(System.in);
        String phrase;
        boolean speakBot = true;
        System.out.println("[BOT] > Привет. Я Bot по имени BOT");
        help();

        do {

            phrase = console.nextLine();
            if (phrase.equals("стоп")) {
                System.out.println("[BOT] > Захочешь поговорить, напиши \"продолжить\"");
                speakBot = false;
            }
            if (phrase.equals("продолжить")) {
                System.out.println("[BOT] > Привет! Я рад, что ты со мной захотел поговорить. Напиши мне что-нибудь.");
                speakBot = true;
            }
            if (phrase.equals("помощь")) {
                help();
            }

            if (speakBot) {
                System.out.println("[BOT] > " + values.get(new Random().nextInt(values.size())));
            }


        } while (!phrase.equals("закончить"));

        System.out.println("[BOT] > Заходи еще!");

    }
}
