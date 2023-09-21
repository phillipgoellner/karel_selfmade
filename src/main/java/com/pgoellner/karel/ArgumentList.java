package com.pgoellner.karel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ArgumentList {
    private ArgumentList() {}

    @SafeVarargs
    public static <E> List<E> of(E... elements) {
        return Stream.of(elements).collect(Collectors.toList());
    }
}
