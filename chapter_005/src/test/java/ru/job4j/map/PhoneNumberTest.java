package ru.job4j.map;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PhoneNumberTest {

    /* Если hashCode не переопределять
    * как мы видим, если не переопределить метод hashCode, то при создании двух
    * одинаковых объектов методом new, эти объекты будут хранится в разных областях памяти
    * или как в данном случае - идет речь о другом сегменте хэш-таблицы,
    * соотвественно будут иметь разные хэш-коды
    *
    * В следствии нарушения контракта по hashCode. метод equals() скорее всего никогда
    * не будет запущен.
    * */
    @Test
//    @Ignore  // применять только в случае, если трубуется пройти тест без переопределенного hashCode()
    public void whenNotOverrideHashCodeThenGegNull() {
        Map<PhoneNumber, String> m = new HashMap<>();
        String s = "Jenny";
        m.put(new PhoneNumber(707, 867, 5309), s);
        assertThat(m.get(new PhoneNumber(707, 867, 5309)), is(s));

        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
    }

}