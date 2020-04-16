package dev.skyit.pao.utility;

import java.util.Objects;

public class Pair {
    private final Integer first;
    private final Integer second;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return first.equals(pair.first) &&
                second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }
}
