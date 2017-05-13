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

        HashSet<String> participantHasRehearsalAt = new HashSet();

        // Check rehearsal only has one piece
        HashSet<Rehearsal> rehearsalHasPiece = new HashSet<Rehearsal>();
        for (Piece p : sch.pieces) {
            for (Rehearsal r : p.getRehearsals()) {
                if (rehearsalHasPiece.contains(r)) {
                    hardScore--;
                }
                rehearsalHasPiece.add(r);
            }
        }

        for (Piece p : sch.pieces) {
            hardScore += (p.rehearsals0 == null ? -1 : 0);
            hardScore += (p.rehearsals1 == null ? -1 : 0);
            hardScore += (p.rehearsals2 == null ? -1 : 0);

            hardScore += p.rehearsals0 == p.rehearsals1 ? -1 : 0;
            hardScore += p.rehearsals1 == p.rehearsals2 ? -1 : 0;
            hardScore += p.rehearsals0 == p.rehearsals2 ? -1 : 0;


            // Check participants don't have conflicts
            for (Participant par : p.participants) {
                if (p.rehearsals0 != null) {
                    String idTime = createParTime(par, p.rehearsals0.time);
                    if (participantHasRehearsalAt.contains(idTime))
                        hardScore--;
                    else
                        participantHasRehearsalAt.add(idTime);
                    if (sch.unavailablePartimes.contains(idTime))
                        hardScore--;
                }
                if (p.rehearsals1 != null) {
                    String idTime = createParTime(par, p.rehearsals1.time);
                    if (participantHasRehearsalAt.contains(idTime))
                        hardScore--;
                    else
                        participantHasRehearsalAt.add(idTime);
                    if (sch.unavailablePartimes.contains(idTime))
                        hardScore--;
                }
                if (p.rehearsals2 != null) {
                    String idTime = createParTime(par, p.rehearsals2.time);
                    if (participantHasRehearsalAt.contains(idTime))
                        hardScore--;
                    else
                        participantHasRehearsalAt.add(idTime);
                    if (sch.unavailablePartimes.contains(idTime))
                        hardScore--;
                }
            }
        }

        if (hardScore >= 0) {
            for (Piece p : sch.pieces) {
                ArrayList<Rehearsal> rehearsals = p.getRehearsals();
                if (rehearsals.get(1).time - rehearsals.get(0).time >= 10) {
                    softScore++;
                }
                if (rehearsals.get(2).time - rehearsals.get(1).time >= 10) {
                    softScore++;
                }
            }
        }

        return HardSoftScore.valueOf(hardScore, softScore);
    }

    public static String createParTime(Participant par, long rehTime) {
        return par.name + ":" + rehTime;
    }
}
