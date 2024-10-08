package org.example;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Можно изменять по своему усмотрению, не нарушая правила приоритезации очереди
 */
public class Ticket implements Comparable<Ticket>{

    private static int idSeq;

    /**
     * Автогенерация id
     */
    int id = ++idSeq;

    /**
     * Приоритеты типов:
     * 1) pension
     * 2) любые другие
     */
    @NotNull
    Type type;

    /**
     * Приоритет для ранней регистрации
     */
    OffsetDateTime registerTime = OffsetDateTime.now();

    public Ticket(@NotNull Type type) {
        this.type = Objects.requireNonNull(type, "type is null");
    }

    @Override
    public int compareTo(@NotNull Ticket o) {
        Objects.requireNonNull(o, "o is null");

        if(this.type == Type.PENSION && o.type != Type.PENSION){
            return 1;
        }
        return registerTime.compareTo(o.registerTime) * (-1);
    }

    public enum Type{
        PENSION,
        OTHER
    }
}
