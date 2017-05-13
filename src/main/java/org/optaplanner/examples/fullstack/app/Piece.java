package org.optaplanner.examples.fullstack.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by carl on 23/04/17.
 */
public class Piece {

    public static final long NO_PIECE = -100;

    final long id;
    final List<Participant> participants;
//    final List<Rehearsal> rehearsals = new ArrayList<Rehearsal>();

    public Piece(long id, List<Participant> participants) {
        this.id = id;
        this.participants = participants;
    }

    @Override
    public String toString() {
        return id + "";
    }

}
