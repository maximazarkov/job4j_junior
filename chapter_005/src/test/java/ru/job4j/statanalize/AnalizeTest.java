package ru.job4j.statanalize;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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

        assertThat(new Analize()
                .diff(listIn, listOut)
                .added,
                is(1));
    }

    @Test
    public void whenAdded3UsersThenAdded3() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listOut.add(new Analize.User(0, "Ivan"));
        listOut.add(new Analize.User(1, "Dima"));
        listOut.add(new Analize.User(2, "Oksana"));

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .added,
                is(3));
    }

    @Test
    public void whenChengedUserThenChengedOne() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        listOut.add(new Analize.User(0, "Ivan"));
        listOut.add(new Analize.User(1, "Oksana"));

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .changed,
                is(1));
    }

    @Test
    public void whenDeletedUserThenDeletedOne() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        listOut.add(new Analize.User(0, "Ivan"));

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .deleted,
                is(1));
    }

    @Test
    public void whenDeletedAllUserThenDeleted2() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

        listIn.add(new Analize.User(0, "Ivan"));
        listIn.add(new Analize.User(1, "Dima"));

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .deleted,
                is(2));
    }

    @Test
    public void when3Add2Deleted1ChangeThen321() {
        List<Analize.User> listIn = new ArrayList<>();
        List<Analize.User> listOut = new ArrayList<>();

//        listIn.add(new Analize.User(0, "A"));
        listIn.add(new Analize.User(1, "A"));   //0
        listIn.add(new Analize.User(2, "C"));   //1
        listIn.add(new Analize.User(3, "D"));   //2
//        listIn.add(new Analize.User(4, "E"));
        listIn.add(new Analize.User(5, "F"));   //3
        listIn.add(new Analize.User(6, "G"));   //4
//        listIn.add(new Analize.User(7, "H"));

        listOut.add(new Analize.User(0, "A"));  //0 - added
        listOut.add(new Analize.User(1, "B"));  //1 - changed
//        listOut.add(new Analize.User(2, "C"));            //deleted
        listOut.add(new Analize.User(3, "D"));  //2 - no action
        listOut.add(new Analize.User(4, "E"));  //3 - added
        listOut.add(new Analize.User(5, "F"));  //4 - no action
//        listOut.add(new Analize.User(6, "G"));            //deleted
        listOut.add(new Analize.User(7, "H"));  //5 - added

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .added,
                is(3));

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .deleted,
                is(2));

        assertThat(new Analize()
                        .diff(listIn, listOut)
                        .changed,
                is(1));
    }

}