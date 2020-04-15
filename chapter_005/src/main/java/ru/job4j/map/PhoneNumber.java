package ru.job4j.map;

/*
* Данный пример взят из книги Эффективное программирование, Дж.Блох, в качестве примера.
*/

import java.util.Objects;

public final class PhoneNumber {
    /*в примере поля объявленны как final, но по какой-то ошибке фиксируется ошибка
    * Разные трансформации конструктора к положительному результату не привели, в конечном итоге
    * у полей убрал объявления final, а конструктор оставил в прежнем виде*/
    private short areaCode;
    private short prefix;
    private short lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name){
        if (arg < 0 || arg > max){
            throw new IllegalArgumentException(name + ": " + arg);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber pn = (PhoneNumber) o;
        return pn.areaCode == areaCode && pn.lineNumber == lineNumber && pn.prefix == prefix;
    }

    /* Если не переопределить метод hashCode(), то при одинаковых объектах по equals() из-за
    * непереопределенного hashCode, данные объекты скорее всего будут иметь разный хэш-код,
    * т.к. метод hashCode класса Object по сути сравнивает ссылки на память. И соотвественно
    * одинаковые обьекты по содержимому, но ссозданные с помощью оператора new, будут
    * расположены в разных участках памяти, а соотвественно будут иметь разные хэши
    *
    * Для устарнения данной проблемы необходимо создать обратное условие. конда возможны
    * совпадения по хэшкодам, а по equals совпадения не обязательны*/

    // Создадим для тестирования самую плохую реализаицю метода hashCode()
    /*
    * При такой реализации все элементы будут привязаны только к одному сегменту хэш-таблицы
    * и наша хэш-таблица превратиться в связный список, а соовтественно время доступа
    * до элементов увеличится с линейного до квадратичного. И соотвественно для любых
    * сравнений объектов, не зависимо, одниковое у них содержимое или нет, будет вызыватсья
    * метод equals(), что негативно сказывается на производительность.*/
//    @Override
//    public int hashCode(){
//        return 42;   // ОООЧЕНЬ ПЛОХО!!!! НИКОГДА ТАК НЕ ДЕЛАЙТЕ!!!
//    }

    /*
    * Согласно разным реализациям JVM методы вычисления хэш-кодов меняются, по этому
    * достаточно понимать, что основная задача метода заключается в том,
    * чтобы все важные поля, перевести в примитивы, затем примитивы привести к int,
    * затем распределить значение как можно в более широком диапазоне int. Если вычисляются
    * хэш-коды объектов или массивов, то необходимо применить рекурсия по вычислению их
    * equals() и hashCode().
    *
    * Главное правило, метода hashCode должен быть как можно легде, но при этом возврщать
    * как можно более уникальые хэш-коды. А уже equals() повеить всю тяжелую работу.
    *
    * Есть один важный нюанс. Это умножение результата в методе hashCode() на 31. Зделано
    * это для равномерного распредения хэш-кодов, но простое число 31 еще выбрано по тому, что
    * его можно получить простым сдвигом, что является легкой операцией для процессора
    * 31 * i == (i << 5) - i.
    * */

    /*ПРАВИЛЬНАЯ, НО НЕ ЛУЧШАЯ РЕАЛИЗАЦИЯ*/
//    @Override
//    public int hashCode() {
//        int result = 17;  // число 17 выбрано произвольно. обычно выбирают число 1.
//        result = 31 * result + areaCode;
//        result = 31 * result + prefix;
//        result = 31 * result + lineNumber;
//        return result;
//    }

    /*ИЛИ начиная с Java 7 можно переписать так...*/
    @Override
    public int hashCode() {
        return Objects.hash(areaCode, prefix, lineNumber);
    }

    /*
    * Для сокращения расходов вычислений на расчет хэш-таблиц, можно выполнять расчет
    * либо при создании объекта, либо с помощью ленивой инициализации поля. содержащего
    * хэш-код, высчитовать его при первом вызове и сохранять в теле обхекта до момента,
    * когда объект потребуется именить*/

    /*ЛУЧШАЯ РЕАЛИЗАЦИЯ - РАЗРАБОТКА ОПТИМИЗИРОВАННЫХ ХЭШ-ФУНКЦИЯ, НО ДОСТИГАЕТСЯ ТОЛЬКО
    * ПОСТОЯННЫМИ ИССЛЕДОВАНИЯМИ.*/
}
