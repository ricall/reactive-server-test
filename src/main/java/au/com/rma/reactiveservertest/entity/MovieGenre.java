package au.com.rma.reactiveservertest.entity;

import java.util.Random;

public enum MovieGenre {

    HORROR,
    SCI_FI,
    ACTION,
    COMEDY,
    ROMANCE,
    DRAMA;

    public static MovieGenre random() {
        return values()[new Random().nextInt(values().length)];
    }

}
