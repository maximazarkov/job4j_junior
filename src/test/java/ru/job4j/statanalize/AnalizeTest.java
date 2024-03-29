package ru.job4j.statanalize;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class AnalizeTest {
    @Test
    public void whenAddedUserThenAddedOne() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        listOut.add(new Analize.User(0, "Ivan"));
        listOut.add(new Analize.User(1, "Dima"));
        listOut.add(new Analize.User(2, "Oksana"));

        assertThat(new Analize().diff(listIn, listOut).added).isEqualTo(1);
    }

    @Test
    public void whenAdded3UsersThenAdded3() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listOut.add(new Analize.User(0, "Ivan"));
        listOut.add(new Analize.User(1, "Dima"));
        listOut.add(new Analize.User(2, "Oksana"));

        assertThat(new Analize().diff(listIn, listOut).added).isEqualTo(3);
    }

    @Test
    public void whenChengedUserThenChengedOne() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        listOut.add(new Analize.User(0, "Ivan"));
        listOut.add(new Analize.User(1, "Oksana"));

        assertThat(new Analize().diff(listIn, listOut).changed).isEqualTo(1);
    }

    @Test
    public void whenDeletedUserThenDeletedOne() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        listOut.add(new Analize.User(0, "Ivan"));

        assertThat(new Analize().diff(listIn, listOut).deleted).isEqualTo(1);
    }

    @Test
    public void whenDeletedAllUserThenDeleted2() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        assertThat(new Analize().diff(listIn, listOut).deleted).isEqualTo(2);
    }

    @Test
    public void when3Add2Deleted1ChangeThen321() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

/**        listIn.add(new Analize.User(0, "A")); */
        listIn.add(new Analize.User(1, "A"));   /** 0 */
        listIn.add(new Analize.User(2, "C"));   /** 1 */
        listIn.add(new Analize.User(3, "D"));   /** 2 */
/**         listIn.add(new Analize.User(4, "E")); */
        listIn.add(new Analize.User(5, "F"));   /** 3 */
        listIn.add(new Analize.User(6, "G"));   /** 4 */
/**        listIn.add(new Analize.User(7, "H")); */
                                                         /**(add del chng) */
        listOut.add(new Analize.User(0, "A"));  /** 0 - added     (1 0 0) */
        listOut.add(new Analize.User(1, "B"));  /** 1 - changed   (1 0 1) */
/**        listOut.add(new Analize.User(2, "C"));        /**  deleted      (1 1 1) */
        listOut.add(new Analize.User(3, "D"));  /** 2 - no action (1 1 1) */
        listOut.add(new Analize.User(4, "E"));  /** 3 - added     (2 1 1) */
        listOut.add(new Analize.User(5, "F"));  /** 4 - no action (2 1 1) */
/**        listOut.add(new Analize.User(6, "G"));        /** deleted       (2 2 1) */
        listOut.add(new Analize.User(7, "H"));  /** 5 - added     (3 2 1) */

        assertThat(new Analize().diff(listIn, listOut).added).isEqualTo(3);
        assertThat(new Analize().diff(listIn, listOut).deleted).isEqualTo(2);
        assertThat(new Analize().diff(listIn, listOut).changed).isEqualTo(1);
    }
}