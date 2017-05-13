package org.optaplanner.examples.fullstack.app;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by carl on 23/04/17.
 */
@PlanningSolution
public class RehearsalSchedule implements Solution<HardSoftScore> {

    @ValueRangeProvider(id="rehearsals")
    @PlanningEntityCollectionProperty
    List<Rehearsal> rehearsals;
    List<Participant> participants;

    @ValueRangeProvider(id="pieces")
    List<Piece> pieces;


//    List<RehearsalPlan> plans = new ArrayList<RehearsalPlan>();

    HardSoftScore score;

    @Override
    public HardSoftScore getScore() {
        return score;
    }

    @Override
    public void setScore(HardSoftScore hardSoftScore) {
        this.score = hardSoftScore;
    }

    @Override
    public Collection<?> getProblemFacts() {
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (Rehearsal plan : rehearsals) {
            s += plan.toString() + "\n";
        }
        return s;
    }
}
