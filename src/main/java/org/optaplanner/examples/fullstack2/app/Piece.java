package org.optaplanner.examples.fullstack2.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.*;

/**
 * Created by carl on 23/04/17.
 */
@PlanningEntity
public class Piece implements Comparable<Piece> {

    public String name;
    public Set<Participant> participants;

    Rehearsal rehearsals0;
    Rehearsal rehearsals1;
    Rehearsal rehearsals2;
    Rehearsal rehearsals3;

    @PlanningVariable(valueRangeProviderRefs="rehearsals")
    public Rehearsal getRehearsals0() {
        return rehearsals0;
    }

    public void setRehearsals0(Rehearsal rehearsals0) {
        this.rehearsals0 = rehearsals0;
    }

    @PlanningVariable(valueRangeProviderRefs="rehearsals")
    public Rehearsal getRehearsals1() {
        return rehearsals1;
    }

    public void setRehearsals1(Rehearsal rehearsals1) {
        this.rehearsals1 = rehearsals1;
    }

    @PlanningVariable(valueRangeProviderRefs="rehearsals")
    public Rehearsal getRehearsals2() {
        return rehearsals2;
    }

    public void setRehearsals2(Rehearsal rehearsals2) {
        this.rehearsals2 = rehearsals2;
    }

    @PlanningVariable(valueRangeProviderRefs="rehearsals")
    public Rehearsal getRehearsals3() {
        return rehearsals3;
    }

    public void setRehearsals3(Rehearsal rehearsals3) {
        this.rehearsals3 = rehearsals3;
    }

    public ArrayList<Rehearsal> getRehearsals() {
        ArrayList<Rehearsal> rehs = Lists.newArrayList(rehearsals0, rehearsals1, rehearsals2, rehearsals3);
        if (rehearsals0!=null && rehearsals1!= null && rehearsals2!= null && rehearsals3!= null) {
            Collections.sort(rehs);
        }
        return rehs;
    }

    public Piece(String name, Participant... participants) {
        this.name = name;
        this.participants = new HashSet<Participant>(Arrays.asList(participants));
    }

    public Piece() {

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Piece other) {
        return name.compareTo(other.name);
    }
}
