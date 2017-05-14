package org.optaplanner.examples.fullstack2.app;

import java.util.HashSet;

/**
 * Created by carl on 23/04/17.
 */
public class Participant {

    final String name;
    public HashSet<Rehearsal.Date> notAvailableDate = new HashSet<Rehearsal.Date>();

    public Participant(String name) {
        this.name = name;
    }

    public boolean canRehearseAt(Rehearsal r) {
        return !notAvailableDate.contains(r.date);
    }
}
