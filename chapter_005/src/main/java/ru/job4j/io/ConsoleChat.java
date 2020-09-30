package ru.job4j.io;

import java.util.Scanner;

public class ConsoleChat {
//    Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу
//    из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит слово «стоп», при
//    этом он может продолжать отправлять сообщения в чат. Если пользователь вводит слово «продолжить»,
//    программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу. Запись
//    диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.

    private static void help() {
        System.out.println("[BOT] > Если хочешь, чтоб я замолчал, набери \"стоп\"");
        System.out.println("[BOT] > Чтоб я заговорил, набери \"продолдить\"");
        System.out.println("[BOT] > Для выхода, набери \"закончить\"");
        System.out.println("[BOT] > Для вызова этой справки, набери \"помощь\"");
    }

    private static void checkStop() {

    }

    public static void main(String[] args) {
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
                speakBot = false;
            }
            if (phrase.equals("помощь")) {
                help();
            }

//            System.out.println(phrase);
        } while (!phrase.equals("закончить"));



    }
}
