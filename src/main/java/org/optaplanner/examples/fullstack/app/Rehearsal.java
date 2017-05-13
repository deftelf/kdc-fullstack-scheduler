package org.optaplanner.examples.fullstack.app;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 * Created by carl on 23/04/17.
 */
@PlanningEntity
public class Rehearsal {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private int id;
    private long time;

    private Piece piece;

    public Rehearsal(int id, long time) {
        this.id = id;
        this.time = time;
    }

    public Rehearsal() {
    }

    @PlanningVariable(valueRangeProviderRefs = "pieces")
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Rehearsal"+id+" is for piece" + piece + " at time"+ time;
    }
}
