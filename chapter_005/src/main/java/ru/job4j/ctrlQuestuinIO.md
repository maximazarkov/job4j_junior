# Контрольные вопросы часть 002. IO.
Ответы на повросы созданы для личного использования и за использование Вами данного материала я ответсвенности не несу.

### Оглавление проекта
**Список вопросов**

[1. Что такое поток ввода-вывода?](#Что-такое-поток-ввода-вывода) <br />
[2. Что такое Java IO?](#Что-такое-Java-IO) <br />
[3. Что такое Java NIO?](#Что-такое-Java-NIO) <br />
[4. Что такое Scanner?](#Что-такое-Scanner) <br />
[5. Как работает Scanner внутри?](#Как-работает-Scanner-внутри) <br />
[6. Какие базовые методы существуют в Scanner?](#Какие-базовые-методы-существуют-в-Scanner) <br />
[7. Что такое байтовый поток? Как он реализован внутри?](#Что-такое-байтовый-поток-и-как-он-реализован-внутри) <br />
[8. Что такое символьный поток? Как он реализован внутри?](#Что-такое-символьный-поток-и-как-он-реализован-внутри) <br />
[9. Что такое буферизированный поток?](#Что-такое-буферизированный-поток) <br />
[10. Что такое форматированный вывод? Какие механизмы позволяют осуществить форматированный вызов?](#Что-такое-форматированный-вывод-и-какие-механизмы-позволяют-осуществить-форматированный-вызов) <br />
[11. Как осуществляется ввод и вывод из командной строки?](#Как-осуществляется-ввод-и-вывод-из-командной-строки) <br />
[12. Что такое класс Console? Расскажите его АПИ.](#Что-такое-класс-Console-и-какой-его-АПИ) <br />
[13. Что такое поток данных? Data stream.](#Что-такое-поток-данных-Data-stream) <br />
[14. Что такое поток объектов, Object stream.](#Что-такое-поток-объектов-Object-stream) <br />
[15. Что такое Path? Как он реализуется на разных ОС?](#Что-такое-Path-и-как-он-реализуется-на-разных-ОС) <br />
[16. Как получить список файлов?](#Как-получить-список-файлов) <br />
[17. Как проверить что файловая сущность является файлом или папкой?](#Как-проверить-что-файловая-сущность-является-файлом-или-папкой)  <br /> 
[18. Как удалить файл?](#Как-удалить-файл)  <br />
[19. Как переместить файл?](#Как-переместить-файл)  <br />
[20. Как управлять аттрибутами файла?](#Как-управлять-аттрибутами-файла)  <br />
[21. Как создать файл?](#Как-создать-файл)  <br />
[22. Как создать директорию?](#Как-создать-директорию)  <br />
[23. Как записать в файл?](#Как-записать-в-файл)  <br />
[24. Как прочитать данные из файла?](#Как-прочитать-данные-из-файла) <br />

[Потоки ввода-вывода от Oracle.com](https://docs.oracle.com/javase/tutorial/essential/io/index.html)

***
+ [Контакты](#Контакты)
***

## Ответы на вопросы
### Что такое поток ввода-вывода?
Поток ввода-вывода проедставляет собой входной источник или выходной приемник (пункт назначения, адресат). Поток может представлять множество 
различных типов источников и приемников, включая дисковые файлы, устройства и другие программы и массивы памяти.

Потоки поддерживаю множество различных типов данных, включая простые байтв, примитивные типы данных, локализованные символы и объекты.
Некоторые потоки просто передают данные; другие манипулируют и преобразуют данные полезным способами.

Незаивисимо от того, как они устроены внутри, внешне они пердоставляют одну и ту же модель для прогрмм, которые их используют: поток - это
последовательность данных. 

Источником данных и местом назначения данных, изображенными выше, может быть все, что содержит, генерирует или потребляет данные. Очевидно, 
что это включает в себя дисковые файлы, но источником или местом назначения также может быть другая программа, периферийное устройство, 
сетевой сокет или массив.

 [к оглавлению](#Оглавление-проекта)
### Что такое Java IO
Потоковая система ввода-вывода, входящая в пакет java.io, является составной частью Java, начиная с первого выпуска и широко
применяется до сих пор. Предоставляет готовые классы для работы с:
+ потоками байтов
+ потоками символов
+ буферизированными потоками
+ потоками ввода-вывода командной строки
+ сериализацией объектов (их сохранение и восставнование)
+ файлами (класс File)

 [к оглавлению](#Оглавление-проекта)
### Что такое Java NIO
Потоковая система ввода-вывода, входящая в пакет java.nio, является составной частью Java, включена с версии 1.4. Система NIO (что
первоначально означало NewI/O, т.е. новый ввод-вывод). В этой системе поддерживается канальный подход к операциям ввода-вывода, ориентированный
на применение буферов. А в версии JDK 7 система NIO была существенно расширена, и теперь она оказывает улучшенную поддержку стредств обработки 
файлов и файловых систем.

Система ввода-вывода NIO построена на двух основопологающих элементахЖ буферах и каналах. В буфере хранятся данные, а канал предоставляет открытое
соединение с сустройством ввода вывода, например файлом или сокетом. В общем, для применение системы ввода-вывода NIO требуется получить канал 
для устройства ввода-вывода и буфер для хранения данных. После этого можно обращаться с буфером, вводя или выводя данные по мере необходимости.


 [к оглавлению](#Оглавление-проекта)
### Что такое Scanner
Класс java.util.Scanner является классом, который может анализировать примитивные типы и строки с помощью регулярных выражений.

Объект типа Scanner развивает его входные данные на токены с помощью шаблона разделителя, которые по умолчанию соотвествует пробелу.
В результате чего позже токены могут быть преобразованы в значения различных типов.
Например, этот код позволяет пользователю считывать число из System.in:

```java
Scanner sc = new Scanner(System.in);
int i = sc.nextInt();
```

Данный клас предоставляет и иные готовые решения по обработке строковых потоков данных и их обработку.

[источник](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)

[к оглавлению](#Оглавление-проекта)
### Как работает Scanner внутри
Класс Scanner служит дополнением класса Formatter. Он читает отформатированные вводимые данные из объектов классов String, InputStream, File
или объекта любого другого класса, реализующего интерфейс Readable либо ReadableByteChanel и преобразовывает их в двоичную форму.
Класс Scanner.

Как только объект Scanner будет создан, его очень просто использовать для чтения отформатированных вводимых данных. В общем, объект типа Scanner 
читает лексемы из некоторого базового источника, указываемого при создании объекта. С точки зрения класса Scanner лексема - порция вводимых данных, 
разграничеваемая рядом разделителей, которым по умолчанию являются пробельные символы. Лексема читается по совпадению с конкретным регулярным 
выражением, задающим формат данных. Класс сканнер имеет встроенные шаблоны регулярных выражений, что чащще всего бывает достаточно для обработки
вводных данных. Так же можно хадать свое регулярное выражение.

Основый принцип работы заключается в следующих шагах:
+ Определяем доступность конкретного типа вводимых данных методом hasNextX(), где Х - требуемый тип данных;
+ Если вводимые данные доступны, читаем оджним из методов nextX();
+ Повторить пп. 1 и 2 вплоть до исчерпывания вводимых данных;
+ Закрываем объект типа Scanner, вызвав метод close().

Есть интересная статья, как работает метод next() внутри и о времени выполнения. Можно почитать здесь: 
[Интересная статья про скорость выполнения метода next() класса Scanner](https://habr.com/ru/post/348056/) 

[источник](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)

[к оглавлению](#Оглавление-проекта)

### Какие базовые методы существуют в Scanner
В классе сканнер имеется два основных комлекта методов, а именно hasNextX() и nextX(), где Х - требуемый тип данных.

Некоторые методы, например, hasNext(), могут принимать шаблоны в виде объекта Pattern или String.

При этом много приватных методов, в которых описаны готовые шаблоны регулярных выражений. Полезно посмотреть в 
[источнике](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)

[к оглавлению](#Оглавление-проекта)

### Что такое байтовый поток и как он реализован внутри
Битовый поток - поток байтов для выполнения ввода и вывода 8-битных байтов. Все классы байтовых потоков проиходят от 
абстрактных классов InputStream и OutputStream.

Состоит он из массива типа byte[], по которому можно перемещаться с помощью специальных методов и маркера.

[к оглавлению](#Оглавление-проекта)

### Что такое символьный поток и как он реализован внутри
Символьный поток - поток байтов для выполнения ввода и вывода 16-битных элементов типа char[]. Все классы байтовых потоков проиходят от 
абстрактных классов Reader и Writer.

Состоит он из массива типа char[], по которому можно перемещаться с помощью специальных методов и маркера.

[к оглавлению](#Оглавление-проекта)

### Что такое буферизированный поток
Каждое обращение к источнику или ардесату потока - достаточно дорогостоящее действие, т.к. оно выполняется для каждого элемента. 
Для сокращения количества обращений к источнику или адресату потока, можно выполнить обертывание байтовых или символьных потоков 
в буфферизированный поток. Он будет заполнятся/освобождаться сразу блоками. Так же можно недожидаться заполнения на отправку 
данных из буфферезированного потока, вызвав метод flush(). При вызове методов println или format буфер автоматически сомоочищается 
(autoflush)

Существует четыре класса буферизованных потоков, используемых для обертывания небуферизованных потоков: BufferedInputStream и еще 
BufferedOutputStream создавайте буферизованные байтовые потоки, в то время как BufferedReader и еще BufferedWriter создайте 
буферизованные потоки символов

[к оглавлению](#Оглавление-проекта)

### Что такое форматированный вывод и какие механизмы позволяют осуществить форматированный вызов
Если требуется сформировать поток формтированных данных, то можно воспользоваться набором методов классов PrintWriter или PrintStream.

Как и все объекты потока байтов и сомволов, экземпляры PrintStream и PrintWriter реализуют стандартный набор write-методов для простого
вывода байтов и символов. Кроме того, оба класса реализуют один и тот же набор методов преобразования внутренних данных в форматированный
вывод. Предусмотренно два уровня форматирования:
+ print и println - форматировать отдельные значения стандартным образом, комбинацией данных методов;
+ format - форматирует практически любое количество значений на основе строки формата, с большим количеством опций для точного форматрирования.
Строка формата состоит из статического текста, встроенные в спецификаторов формата; за исключением спецификаторов формата, строка формата 
выводится без изменений. Форматирование строк поддерживает множество  функций. 
Полное описание [format string syntax](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax).
Самые часто используетмые литералы формата: 
* %d - форматирует целое число как десятичное
* %f - форматирует значение с плавающей запятой как десятичное значение
* %s - String
* %n - специфичный для платформы перенос строки. (Внимание! \n не применять! никогда, либо %n, либо line separator)

Спецификатор может быть более сложной конструкции, например: %1$+020.10f, где
* % - признак спецификатора
* 1$ - порядковый индекс/номер спецификатора при выводе, можно указать знак <, для указания предыдущего спецификатора
* +0 - флаги
* 20 - ширина
* .10 - точность

[к оглавлению](#Оглавление-проекта)

### Как осуществляется ввод и вывод из командной строки
Программа часто запускается из командной строки и взаимодействует с пользователем в среде командной строки. 
Платформа Java поддерживает этот вид взаимодействия двумя способами: через стандартные потоки и через консоль

стандартыне потоки вывода - System.out и System.err. - байтовые потоки, которые ведут себя как символьные потоки
System.in - бистый байтовый поток. чтобы преварать его в символьный, необходимо его обернуть в InputStreamReader()

Более совершенной альтернативой стандартным потокам является Console. Это один заранее определенный объект типа Console 
имеет большинство функций, предоставляемых стандартными потоками, и другие, кроме того. Консоль особенно полезна для 
безопасного ввода пароля. Объект консоли также предоставляет входные и выходные потоки, которые являются истинными символьными 
потоками, через свои readerметоды и writerметоды.
Прежде чем программа сможет использовать консоль, она должна попытаться получить объект консоли путем вызова System.console(). 
Если объект консоли доступен, этот метод возвращает его. Если System.console возвращает NULL, то консольные операции не разрешены 
либо потому, что ОС их не поддерживает, либо потому, что программа была запущена в неинтерактивной среде.
Объект консоли поддерживает безопасный ввод пароля с помощью своего метода readPassword(). Этот метод помогает защитить ввод 
пароля двумя способами. Во-первых, он подавляет эхо (echo), поэтому пароль не виден на экране пользователя. Во-вторых, 
readPassword() возвращает массив символов, а не a String, поэтому пароль можно перезаписать, удалив его из памяти, как только он больше не нужен.

Password пример-прототип программы для изменения пароля пользователя. Он демонстрирует несколько Console методов.
 
```java
import java.io.Console;
import java.util.Arrays;
import java.io.IOException;

public class Password {
 
 public static void main (String args[]) throws IOException {

    Console c = System.console();
    if (c == null) {
        System.err.println("No console.");
        System.exit(1);
    }

    String login = c.readLine("Enter your login: ");
    char [] oldPassword = c.readPassword("Enter your old password: ");

    if (verify(login, oldPassword)) {
        boolean noMatch;
        do {
            char [] newPassword1 = c.readPassword("Enter your new password: ");
            char [] newPassword2 = c.readPassword("Enter new password again: ");
            noMatch = ! Arrays.equals(newPassword1, newPassword2);
            if (noMatch) {
                c.format("Passwords don't match. Try again.%n");
            } else {
                change(login, newPassword1);
                c.format("Password for %s changed.%n", login);
            }
            Arrays.fill(newPassword1, ' ');
            Arrays.fill(newPassword2, ' ');
        } while (noMatch);
    }

    Arrays.fill(oldPassword, ' ');
 }
 
 // Dummy change method.
 static boolean verify(String login, char[] password) {
 // This method always returns
 // true in this example.
 // Modify this method to verify
 // password according to your rules.
    return true;
 }

 // Dummy change method.
 static void change(String login, char[] password) {
 // Modify this method to change
 // password according to your rules.
 }
}
```

Класс Password выполняет следующие действия:

Попытайтесь получить объект консоли. Если объект недоступен, прерывает работу.
Взывать Console.readLine чтобы запросить и прочитать имя пользователя для входа в систему.
Взывать Console.readPassword чтобы запросить и прочитать существующий пароль пользователя.
Взывать verify чтобы подтвердить, что пользователь имеет право изменить пароль. (В этом примере, verify это фиктивный метод, 
который всегда возвращает true.)
Повторяйте следующие действия до тех пор, пока пользователь не введет один и тот же пароль дважды:
Взывать Console.readPassword дважды запрашивать и читать новый пароль.
Если пользователь ввел один и тот же пароль оба раза, вызовите change изменить его. (Снова, change это фиктивный метод.)
Перепишите оба пароля с пробелами - удаляем пароль.
Перепишите старый пароль пробелами  - удаляем пароль.

[к оглавлению](#Оглавление-проекта)

### Что такое класс Console и какой его АПИ
java.io.Console - для удобного взаимодейтсвия пользователя в среде командной строки с приложением на Java.
Данный клас работает с символьными потоками
Апи класса небольшой, но достаточный для работы с потоками коммандной строки:
* void flush()
* Console format(String fmt, Object ...args)
* Console printf(String format, Object ... args)
* Reader reader()
* String readLine()
* String readLine(String fmt, Object ... args) 
* char[] readPassword()
* char[] readPassword(String fmt, Object ... args)
* PrintWriter writer()

[к оглавлению](#Оглавление-проекта)

### Что такое поток данных Data stream
Потод данных - поток, в котором передаются примитивы и String. Если в поток передаются данные разных типов, 
то и получать их нужно именно в этой последовательности, т.к. в потоке данных не передается информация о типе данных.
для работы с числами с плавающей запятой, лучше использовать не double и float, а класс java.math.BigDecimal.

[к оглавлению](#Оглавление-проекта)

### Что такое поток объектов Object stream
Поток объектов - поток в котором передаются объекты. Большинство стандартных классов поддерживают сериализацию 
своих объектов, но не все. Те, которые реализуют интерфейс маркера Serializable.

Классы потока объектов являются ObjectInputStream и еще ObjectOutputStream. Эти классы реализуют ObjectInput и 
еще ObjectOutput, которые являются подинтерфейсами DataInputи DataOutput. Это означает, что все примитивные методы 
ввода-вывода данных, описанные в потоках данных, также реализуются в потоках объектов. Таким образом, поток объектов 
может содержать смесь примитивных и объектных значений. Пример ObjectStreams иллюстрирует это. ObjectStreams 
создает то же самое приложение, что и DataStreams с парой изменений. Во-первых, для предоставления дробных значений 
лучше применять объекты BigDecimal. Во-вторых, объект Calendar записывается в файл данных с указанием последней даты.

Если readObject() не возвращает ожидаемый тип объекта, попытка привести его к правильному типу может привести к 
ClassNotFoundException. В этом простом примере этого не может произойти, поэтому мы не пытаемся поймать исключение. 
Вместо этого мы уведомляем компилятор о том, что нам известно об этой проблеме, добавляя main throws ClassNotFoundException 
предложение метода.

Методы writeObject и readObject просты в использовании, но они содержат очень сложную логику управления объектами. 
Это не важно для такого класса, как Calendar, который просто инкапсулирует примитивные значения. Но многие объекты 
содержат ссылки на другие объекты. Чтобы readObject воссоздать объект из потока, он должен быть способен воссоздать все 
объекты, на которые ссылается исходный объект. Эти дополнительные объекты могут иметь свои собственные ссылки и т. д. 
В этом случае writeObject выполняется обход всей сети ссылок на объекты и запись всех объектов в этой сети в поток. 
Таким образом, один вызов writeObject может привести к тому, что в поток будет записано большое количество объектов.

Поток может содержать только одну копию объекта, хотя он может содержать любое количество ссылок на него. Таким 
образом, если вы явно пишете объект в поток дважды, то на самом деле вы пишете только ссылку дважды. Например, 
если следующий код ob дважды записывает объект в поток:

```java
Object ob = new Object();
out.writeObject(ob);
out.writeObject(ob);
```
Каждый writeObject из них должен быть сопоставлен с a readObject, поэтому код, считывающий поток обратно, будет 
выглядеть примерно так:
```java
Object ob1 = in.readObject();
Object ob2 = in.readObject();
```

Это приводит к двум переменным ob1 и ob2, которые являются ссылками на один объект.

Однако если один объект записывается в два разных потока, он фактически дублируется — одна программа, считывающая 
оба потока обратно, увидит два разных объекта.

[к оглавлению](#Оглавление-проекта)

### Что такое Path и как он реализуется на разных ОС
То Path класс, представленный в выпуске Java SE 7, является одной из основных точек входа в систему. java.nio.file
пакет.

Как следует из названия, Path-класс является программным представлением пути в файловой системе. Path-Объект 
содержит имя файла и список каталогов, используемых для построения пути, а также используется для проверки, 
поиска и управления файлами.

Path-Экземпляр отражает базовую платформу. В ОС Solaris a Path использует синтаксис Solaris (/home/joe/foo), а в 
Microsoft Windows a Path-использует синтаксис Windows (C:\home\joe\foo). Path не является независимым от системы. 
Невозможно сравнить a Path из файловой системы Solaris и ожидать, что он будет соответствовать a Path из файловой 
системы Windows, даже если структура каталогов идентична и оба экземпляра находят один и тот же относительный файл.

Соответствующий файл или каталог Path-может не существовать. Можно создать Path-экземпляр и управлять им 
различными способами: можно добавлять к нему, извлекать его фрагменты, сравнивать его с другим путем. 
Можно использовать методы, описанные в Files класс, чтобы проверить существование файла, соответствующего
Path, создать файл, открыть его, удалить его, изменить его разрешения и так далее.

[к оглавлению](#Оглавление-проекта)

### Как получить список файлов

Самым простым способом метод listFiles класса File. В цикле. Можно добавть проверку на директории, 
чтобы исключить их из списка 

```java
File folder = new File("c:/path/");
for (File file : folder.listFiles())
{
 System.out.println(file.getName());
}
```

[к оглавлению](#Оглавление-проекта)

### Как проверить что файловая сущность является файлом или папкой

Для проверки, яявляется ли сущность файлом или директорией, необходимо применить методы isFile() 
или isDirectory() класса File.

Files.isRegularFile(Path.of("c:\\readme.txt")); <br />
Files.isDirectory(Path.of("c:\\test"));<br />
Нестандарный метод:<br />
Files.exists(Path.of("c:\\test\\1\\2\\3"));<br />
Files.size(Path.of("c:\\readme.txt"));<br />

[к оглавлению](#Оглавление-проекта)

### Как удалить файл

Для удаления файла, каталога или ссылки. При использовании символических ссылок ссылка 
удаляется, а не становится целью ссылки. В случае с каталогами каталог должен быть пустым, 
иначе удаление завершится неудачей.

FilesКласс предоставляет два метода удаления.

То delete(Path) метод удаляет файл или создает исключение, если удаление завершается неудачно. 
Например, если файл не существуетNoSuchFileException, то выбрасывается исключение. 
лучше ловить исключение, чтобы определить, почему удаление не удалось следующим образом:

```java
try {
 Files.delete(path);
} catch (NoSuchFileException x) {
 System.err.format("%s: no such" + " file or directory%n", path);
} catch (DirectoryNotEmptyException x) {
 System.err.format("%s not empty%n", path);
} catch (IOException x) {
 // File permission problems are caught here.
 System.err.println(x);
}
```

То deleteIfExists(Path) метод также удаляет файл, но если файл не существует, исключение не 
создается. Молчаливая ошибка полезна, когда у вас есть несколько потоков, удаляющих файлы, и 
вы не хотите создавать исключение только потому, что один поток сделал это первым.

[к оглавлению](#Оглавление-проекта)

### Как переместить файл

Переместить файл или каталог можно с помощью метода move(Path, Path, CopyOption...). Перемещение 
завершается неудачей, если целевой файл существует, если только не указан параметр REPLACE_EXISTING.

Пустые каталоги можно перемещать. Если каталог не пуст, перемещение разрешено, когда каталог можно 
перемещать без перемещения содержимого этого каталога. В системах UNIX перемещение каталога в 
пределах одного раздела обычно состоит из переименования каталога. В этом случае этот метод работает 
даже тогда, когда каталог содержит файлы.

Этот метод принимает аргумент varargs – поддерживаются следующие StandardCopyOption перечисления:

REPLACE_EXISTING - Выполняет перемещение, даже если целевой файл уже существует. Если целью 
является символьная ссылка, то она заменяется, но на что она указывает, это не влияет.
ATOMIC_MOVE – Выполняет перемещение как операцию атомарного файла. Если файловая система 
не поддерживает атомарное перемещение, создается исключение. С помощью an ATOMIC_MOVEвы можете 
переместить файл в каталог и быть уверенным, что любой процесс, наблюдающий за каталогом, 
получит доступ к полному файлу.
Ниже показано, как использовать метод move:

```java
import static java.nio.file.StandardCopyOption.*;
...
Files.move(source, target, REPLACE_EXISTING);
```

Можно реализовать метод move в одном каталоге, как показано на рисунке, этот метод чаще всего 
используется с механизмом рекурсии дерева файлов. Например, реализовав интерфейс FileVisitor.

[к оглавлению](#Оглавление-проекта)

### Как управлять аттрибутами файла

Очень обширный раздел. рекомендуется прочесть его [здесь](https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html)
Но если кратко, то для управления атрибутами файла нужно воспользоваться классом Files

[к оглавлению](#Оглавление-проекта)

### Как создать файл

Ответ может быть обширным. Все зависит от формата сущности, который будет сохранятся файл. 
Это может быть файтовый поток, текстовый поток, буферизированный поток и т.д. Каждый тип потока 
подбирается по необходимости выполнения задачи. Так же можно просто создать файл методами 
File.createNewFile() или File.createTempFile().
Files.createFile(Path.of("c:\\readme.txt"));

[к оглавлению](#Оглавление-проекта)

### Как создать директорию

Воспользовавшись методами File.mkdir() и File.mkdirs.
Files.createDirectory(Path.of("c:\\test"));
Files.createDirectories(Path.of("c:\\test\\1\\2\\3"));

[к оглавлению](#Оглавление-проекта)

### Как записать в файл
Например через статические методы java.nio.file.Files
Path write(Path path, byte[])
Path writeString(Path path, String str)


[к оглавлению](#Оглавление-проекта)

### Как прочитать данные из файла
byte[] readAllBytes(Path path)
String readString(Path path)
List<String> readAllLines(Path path)

[к оглавлению](#Оглавление-проекта)

### Контакты
Если Вас что-то заинтересовало и у Вас есть вопросы по данному проекту, обращайтесь:
+ Email: aza-maxim@yandex.ru
+ Telegram: @azamaxim

[к оглавлению](#Оглавление-проекта)
