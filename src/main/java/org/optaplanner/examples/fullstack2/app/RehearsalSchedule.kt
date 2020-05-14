package org.optaplanner.examples.fullstack2.app

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.Solution
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore
import java.util.*

/**
 * Created by carl on 23/04/17.
 */
@PlanningSolution
class RehearsalSchedule : Solution<HardMediumSoftScore?> {
    @JvmField
    @ValueRangeProvider(id = "rehearsals")
    var rehearsals: HashSet<Rehearsal>? = null

    @JvmField
    var participants: HashSet<Participant>? = null

    @JvmField
    @PlanningEntityCollectionProperty
    var pieces: HashSet<Piece>? = null

    //    List<RehearsalPlan> plans = new ArrayList<RehearsalPlan>();
    private var _score: HardMediumSoftScore? = null


    override fun getProblemFacts(): Collection<*>? {
        return null
    }

    override fun toString(): String {
        var s = ""
        val piecesList: List<Piece> = ArrayList(pieces)
        Collections.sort(piecesList)
        for (piece in piecesList) {
            val rehs = piece.rehearsals
            s += """piece:${piece.name}     ${rehs[0]}, ${rehs[1]}, ${rehs[2]}, ${rehs[3]}
"""
        }
        return s
    }

    fun participantToString(): String {
        val parRehs = HashMap<Participant, ArrayList<PieceRehearsal>>()
        for (p in participants!!) parRehs[p] = ArrayList()
        for (piece in pieces!!) {
            for (reh in piece.rehearsals) {
                for (par in piece.participants) {
                    parRehs[par]!!.add(PieceRehearsal(piece, reh))
                }
            }
        }
        val str = StringBuilder()
        val participants = ArrayList(parRehs.keys)
        Collections.sort(participants)
        for (p in participants) {
            str.append("""
    ${p.name}
    
    """.trimIndent())
            val parReh = parRehs[p]!!
            Collections.sort(parReh)
            for (r in parReh) {
                str.append("   ")
                str.append(r.toString())
                str.append("\n")
            }
            str.append("\n")
        }
        return str.toString()
    }

    fun dateTimeScriptCsv(): String {
        val pieceRehs = ArrayList<PieceRehearsal>()
        for (piece in pieces!!) {
            for (reh in piece.rehearsals) {
                pieceRehs += PieceRehearsal(piece, reh)
            }
        }
        return pieceRehs.map { DateTimeScript(it.rehearsal.date, it.rehearsal.hour, it.piece.name) }
                .sortedBy {((it.date.month * 30 + it.date.day) * 100) + it.time }
                .map { "${it.date},${it.time}:00,${it.scriptName}" }
                .joinToString("\n")
    }

    private data class DateTimeScript(val date: Rehearsal.Date, val time: Int, val scriptName: String)

    internal class PieceRehearsal(var piece: Piece, var rehearsal: Rehearsal) : Comparable<PieceRehearsal> {
        override fun toString(): String {
            return rehearsal.toString() + " (" + piece.name + ")"
        }

        override fun compareTo(other: PieceRehearsal): Int {
            return rehearsal.compareTo(other.rehearsal)
        }

    }

    fun pieceParticipants(): String {
        val str = StringBuilder()
        for (piece in pieces!!) {
            str.append(piece.name + " has ")
            for (part in piece.participants) {
                str.append(part.name + ", ")
            }
            str.append("\n")
        }
        return str.toString()
    }

    fun unusedRehearsals(): String {
        val str = StringBuilder()
        val index = HashMap<Rehearsal, Piece?>()
        for (reh in rehearsals!!) {
            index[reh] = null
        }
        for (piece in pieces!!) {
            for (reh in piece.rehearsals) {
                index[reh] = piece
            }
        }
        val rehs = ArrayList(rehearsals)
        Collections.sort(rehs)
        for (reh in rehs) {
            val piece = index[reh]
            val pieceName = if (piece == null) "-" else piece.name
            str.append("$reh ($pieceName)\n")
        }
        return str.toString()
    }

    override fun setScore(p0: HardMediumSoftScore?) {
        _score = p0
    }

    override fun getScore(): HardMediumSoftScore? {
        return _score
    }
}