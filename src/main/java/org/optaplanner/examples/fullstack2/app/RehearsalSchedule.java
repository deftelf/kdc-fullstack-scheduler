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

    @PlanningEntityCollectionProperty
    HashSet<Piece> pieces;



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
        for (Piece piece : pieces) {
            ArrayList<Rehearsal> rehs = piece.getRehearsals();
            s += "piece:" + piece.name + "     " + rehs.get(0) + ", " + rehs.get(1) + ", " + rehs.get(2) + "\n";
        }
        return s;
    }

    public String participantToString() {
        HashMap<Participant, ArrayList<PieceRehearsal>> parRehs = new HashMap<Participant, ArrayList<PieceRehearsal>>();
        for (Participant p : participants)
            parRehs.put(p, new ArrayList<PieceRehearsal>());
        for (Piece piece : pieces) {
            for (Rehearsal reh : piece.getRehearsals()) {
                for (Participant par : piece.participants) {
                    parRehs.get(par).add(new PieceRehearsal(piece, reh));
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (Participant p : parRehs.keySet()) {
            str.append(p.name+"\n");
            ArrayList<PieceRehearsal> parReh = parRehs.get(p);
            parReh.sort(new Comparator<PieceRehearsal>() {
                @Override
                public int compare(PieceRehearsal t0, PieceRehearsal t1) {
                    return t0.rehearsal.date.compareTo(t1.rehearsal.date);
                }
            });
            for (PieceRehearsal r : parReh) {
                str.append("   ");
                str.append(r.toString());
                str.append("\n");
            }
            str.append("\n");
        }

        return str.toString();
    }


    static class PieceRehearsal {
        Piece piece;
        Rehearsal rehearsal;

        PieceRehearsal(Piece piece, Rehearsal rehearsal) {
            this.piece = piece;
            this.rehearsal = rehearsal;
        }

        @Override
        public String toString() {
            return rehearsal.toString() + " (" + piece.name + ")";
        }
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
        HashMap<Rehearsal, Piece> index = new HashMap<Rehearsal, Piece>()
                ;
        for (Rehearsal reh: rehearsals) {
            index.put(reh, null);
        }
        for (Piece piece : pieces) {
            for (Rehearsal reh : piece.getRehearsals()) {
                index.put(reh, piece);
            }
        }
        ArrayList<Rehearsal> rehs = new ArrayList<Rehearsal>(rehearsals);
        Collections.sort(rehs, new Comparator<Rehearsal>() {
            @Override
            public int compare(Rehearsal rehearsal, Rehearsal t1) {
                return rehearsal.date.compareTo(t1.date);
            }
        });
        for (Rehearsal reh : rehs) {
            Piece piece = index.get(reh);
            String pieceName = piece == null ? "-" : piece.name;
            str.append(reh.toString() + " (" + pieceName + ")\n");
        }
        return str.toString();
    }
}
