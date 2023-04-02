package ru.job4j.map;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumberTest {

    @Test
    public void whenNotOverrideHashCodeThenGegNull() {
        Map<PhoneNumber, String> m = new HashMap<>();
        String s = "Jenny";
        m.put(new PhoneNumber(707, 867, 5309), s);
        assertThat(m.get(new PhoneNumber(707, 867, 5309))).isEqualTo(s);
        System.out.println(m.get(new PhoneNumber(707, 867, 5309)));
    }
}