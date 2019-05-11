package org.optaplanner.examples.fullstack2.app;

import com.google.common.collect.Sets;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.*;

/**
 * Created by carl on 23/04/17.
 */
public class FullStackEasyScoreCalculator implements EasyScoreCalculator<RehearsalSchedule> {
    @Override
    public Score calculateScore(RehearsalSchedule sch) {
        int hardScore = 0;
        int softScore = 0;

        HashSet<String> participantHasRehearsalAt = new HashSet<String>();

        // Check rehearsal only has one piece
        HashSet<Rehearsal> rehearsalHasPiece = new HashSet<Rehearsal>();
        for (Piece p : sch.pieces) {
            for (Rehearsal r : p.getRehearsals()) {
                if (rehearsalHasPiece.contains(r)) {
                    hardScore -= 100;
                }
                rehearsalHasPiece.add(r);
            }
        }

        for (Piece p : sch.pieces) {
            hardScore += (p.rehearsals0 == null ? -1000 : 0);
            hardScore += (p.rehearsals1 == null ? -1000 : 0);
            hardScore += (p.rehearsals2 == null ? -1000 : 0);

            hardScore += p.rehearsals0 == p.rehearsals1 ? -1000 : 0;
            hardScore += p.rehearsals1 == p.rehearsals2 ? -1000 : 0;
            hardScore += p.rehearsals0 == p.rehearsals2 ? -1000 : 0;


            // Check participants don't have conflicts
            for (Participant par : p.participants) {
                for (Rehearsal r : p.getRehearsals()) {
                    if (r != null) {
                        String idTime = createParTime(par, r);
                        if (participantHasRehearsalAt.contains(idTime))
                            hardScore--;
                        else
                            participantHasRehearsalAt.add(idTime);
                        if (!par.canRehearseAt(r))
                            hardScore -= 10;
                    }
                }

            }
        }

        if (hardScore >= 0) {
            for (Piece p : sch.pieces) {
                ArrayList<Rehearsal> rehearsals = p.getRehearsals();
                if (rehearsals.get(1).date.equals(rehearsals.get(0).date)) {
                    softScore--;
                }
                if (rehearsals.get(2).date.equals(rehearsals.get(1).date)) {
                    softScore--;
                }
            }
        }

//        if (hardScore == -10) {
//            calculateScore(sch);
//        }

        return HardSoftScore.valueOf(hardScore, softScore);
    }

    public static String createParTime(Participant par, Rehearsal reh) {
        return par.name + ":" + reh.date + "," + reh.hour;
    }
}
