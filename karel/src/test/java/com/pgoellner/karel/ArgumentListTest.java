package com.pgoellner.karel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgumentListTest {

    @Test
    void null_returns_empty_list() {
        assertEquals(new ArrayList<>(), ArgumentList.of(null));
    }

    @Test
    void several_elements_are_returned_as_list() {
        ArrayList<Integer> expected = new ArrayList<>();

        expected.add(1);
        expected.add(2);
        expected.add(3);

        assertEquals(expected, ArgumentList.of(1, 2, 3));
    }
}