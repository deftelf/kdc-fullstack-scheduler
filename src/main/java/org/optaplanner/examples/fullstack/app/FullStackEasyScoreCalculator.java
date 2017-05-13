package org.optaplanner.examples.fullstack.app;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by carl on 23/04/17.
 */
public class FullStackEasyScoreCalculator implements EasyScoreCalculator<RehearsalSchedule> {
    @Override
    public Score calculateScore(RehearsalSchedule sch) {
        int hardScore = 0;
        int softScore = 0;

//        if (sch.plans.size() == 0)
//            hardScore = -1;
//
//        for (RehearsalPlan plan : sch.plans) {
//
//            List<Participant> participants = plan.getPiece().participants;
//
//            // Check participants aren't in any other rehearsals
//            participantSearch:
//            for (RehearsalPlan otherPlan : sch.plans) {
//                if (otherPlan != plan) {
//                    if (otherPlan.getRehearsal().time == plan.getRehearsal().time) {
//                        for (Participant participantInOtherReh : otherPlan.getPiece().participants) {
//                            if (plan.getPiece().participants.contains(participantInOtherReh)) {
//                                hardScore = -1;
//                                break participantSearch;
//                            }
//                        }
//                    }
//                }
//            }
//
//        }

        Map<Piece, Integer> rehCount = new HashMap<Piece, Integer>();

        for (Rehearsal plan : sch.rehearsals) {

            if (plan.getPiece() == null) {
                continue;
            }

            List<Participant> participants = plan.getPiece().participants;

            // Check participants aren't in any other rehearsals
            participantSearch:
            for (Rehearsal otherPlan : sch.rehearsals) {
                if (otherPlan != plan) {
                    if (otherPlan.getTime() == plan.getTime()) {
                        if (otherPlan.getPiece() == null) {
                            hardScore = -1;
                            break participantSearch;
                        }
                        for (Participant participantInOtherReh : otherPlan.getPiece().participants) {
                            if (plan.getPiece().participants.contains(participantInOtherReh)) {
                                hardScore = -1;
                                break participantSearch;
                            }
                        }
                    }
                }
            }

            if (plan.getPiece().id != Piece.NO_PIECE) {
                Integer rehs = rehCount.getOrDefault(plan.getPiece(), 0);
                rehs++;
                rehCount.put(plan.getPiece(), rehs);
                hardScore = -Math.abs(hardScore -= 3);
            }
        }


        return HardSoftScore.valueOf(hardScore, softScore);
    }
}
