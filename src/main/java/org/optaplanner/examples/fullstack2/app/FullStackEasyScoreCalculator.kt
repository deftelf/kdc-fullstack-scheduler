package org.optaplanner.examples.fullstack2.app

import org.optaplanner.core.api.score.Score
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator
import java.util.*

/**
 * Created by carl on 23/04/17.
 */
class FullStackEasyScoreCalculator : EasyScoreCalculator<RehearsalSchedule> {
    override fun calculateScore(sch: RehearsalSchedule): Score<*> {
        var hardScore = 0
        var mediumScore = 0
        val participantHasRehearsalAt = HashSet<String>()

        // Check rehearsal only has one piece
        val rehearsalHasPiece = HashSet<Rehearsal>()
        for (p in sch.pieces) {
            for (r in p.rehearsals) {
                if (rehearsalHasPiece.contains(r)) {
                    hardScore -= 100
                }
                rehearsalHasPiece.add(r)
            }
        }
        for (p in sch.pieces) {
            hardScore += if (p.rehearsals0 == null) -1000 else 0
            hardScore += if (p.rehearsals1 == null) -1000 else 0
            hardScore += if (p.rehearsals2 == null) -1000 else 0
            hardScore += if (p.rehearsals3 == null) -1000 else 0
            hardScore += if (p.rehearsals0 === p.rehearsals1) -1000 else 0
            hardScore += if (p.rehearsals1 === p.rehearsals2) -1000 else 0
            hardScore += if (p.rehearsals0 === p.rehearsals2) -1000 else 0
            hardScore += if (p.rehearsals0 === p.rehearsals3) -1000 else 0
            hardScore += if (p.rehearsals1 === p.rehearsals3) -1000 else 0
            hardScore += if (p.rehearsals2 === p.rehearsals3) -1000 else 0


            // Check participants don't have conflicts
            for (par in p.participants) {
                for (r in p.rehearsals) {
                    if (r != null) {
                        val idTime = createParTime(par, r)
                        if (participantHasRehearsalAt.contains(idTime)) hardScore-- else participantHasRehearsalAt.add(idTime)
                        if (!par.canRehearseAt(r)) hardScore -= 10
                    }
                }
            }
        }
        if (hardScore >= 0) {
            for (p in sch.pieces) {
                val rehSet: MutableSet<Rehearsal.Date> = HashSet()
                for (reh in p.rehearsals) {
                    rehSet.add(reh.date)
                }
                mediumScore -= p.rehearsals.size - rehSet.size
            }
        }
        if (mediumScore >= 0) {
        }
        return HardMediumSoftScore.valueOf(hardScore, mediumScore, 0)
    }

    companion object {
        fun createParTime(par: Participant, reh: Rehearsal): String {
            return par.name + ":" + reh.date + "," + reh.hour
        }
    }
}