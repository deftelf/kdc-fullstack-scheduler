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
public class Piece {

    public String name;
    public Set<Participant> participants;

    Rehearsal rehearsals0;
    Rehearsal rehearsals1;
    Rehearsal rehearsals2;

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

    public ArrayList<Rehearsal> getRehearsals() {
        ArrayList<Rehearsal> rehs = Lists.newArrayList(rehearsals0, rehearsals1, rehearsals2);
        if (rehearsals0!=null && rehearsals1!= null && rehearsals2!= null) {
            rehs.sort(new Comparator<Rehearsal>() {
                @Override
                public int compare(Rehearsal t0, Rehearsal t1) {
                    return (int) (t0.time - t1.time);
                }
            });
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

}
