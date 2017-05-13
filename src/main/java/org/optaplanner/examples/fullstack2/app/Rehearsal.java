package org.optaplanner.examples.fullstack2.app;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 * Created by carl on 23/04/17.
 */
public class Rehearsal {


    public String name;
    public long time;


    public Rehearsal(String name, long time) {
        this.name = name;
        this.time = time;
    }

    public Rehearsal() {
    }

    @Override
    public String toString() {
        return name;
    }
}
