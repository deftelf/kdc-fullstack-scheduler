package org.optaplanner.examples.fullstack2.app;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningEntityProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.*;

/**
 * Created by carl on 23/04/17.
 */
@PlanningSolution
public class RehearsalSchedule implements Solution<HardSoftScore> {

    @ValueRangeProvider(id="rehearsals")
    HashSet<Rehearsal> rehearsals;
    HashSet<Participant> participants;
    HashSet<String> unavailablePartimes;

    @PlanningEntityCollectionProperty
    HashSet<Piece> pieces;



//    List<RehearsalPlan> plans = new ArrayList<RehearsalPlan>();

    HardSoftScore score;;

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
        for (Piece piece : pieces) {
            ArrayList<Rehearsal> rehs = piece.getRehearsals();
            s += "piece:" + piece.name + "     " + rehs.get(0) + ", " + rehs.get(1) + ", " + rehs.get(2) + "\n";
        }
        return s;
    }

    public String participantToString() {
        HashMap<Participant, ArrayList<Rehearsal>> parRehs = new HashMap<Participant, ArrayList<Rehearsal>>();
        for (Participant p : participants)
            parRehs.put(p, new ArrayList<Rehearsal>());
        for (Piece piece : pieces) {
            for (Rehearsal reh : piece.getRehearsals()) {
                for (Participant par : piece.participants) {
                    parRehs.get(par).add(reh);
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (Participant p : parRehs.keySet()) {
            str.append(p.name);
            str.append("   ");
            ArrayList<Rehearsal> parReh = parRehs.get(p);
            parReh.sort(new Comparator<Rehearsal>() {
                @Override
                public int compare(Rehearsal t0, Rehearsal t1) {
                    return (int) (t0.time - t1.time);
                }
            });
            for (Rehearsal r : parReh) {
                str.append(r.name);
                str.append(", ");
            }
            str.append("\n");
        }

        return str.toString();
    }

    public String pieceParticipants() {
        StringBuilder str = new StringBuilder();
        for (Piece piece : pieces) {
            str.append(piece.name + " has ");
            for (Participant part : piece.participants) {
                str.append(part.name + ", ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public String unusedRehearsals() {
        StringBuilder str = new StringBuilder();
        HashSet<Rehearsal> remaining = new HashSet<Rehearsal>(rehearsals);
        for (Piece piece : pieces) {
            for (Rehearsal reh : piece.getRehearsals()) {
                remaining.remove(reh);
            }
        }
        ArrayList<Rehearsal> rehs = new ArrayList<Rehearsal>(rehearsals);
        Collections.sort(rehs, new Comparator<Rehearsal>() {
            @Override
            public int compare(Rehearsal rehearsal, Rehearsal t1) {
                return (int) (rehearsal.time - t1.time);
            }
        });
        for (Rehearsal reh : rehs) {
            str.append(reh.name + (remaining.contains(reh) ? " *" : "") + "\n");
        }
        return str.toString();
    }
}
