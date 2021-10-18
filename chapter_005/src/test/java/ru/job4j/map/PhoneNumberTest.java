package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PhoneNumberTest {

    @Test
    public void whenNotOverrideHashCodeThenGegNull() {
        Map<PhoneNumber, String> m = new HashMap<>();
        String s = "Jenny";
        m.put(new PhoneNumber(707, 867, 5309), s);
        assertThat(m.get(new PhoneNumber(707, 867, 5309)), is(s));

        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
    }

}