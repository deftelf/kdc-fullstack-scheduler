package org.optaplanner.examples.fullstack.app;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 * Created by carl on 23/04/17.
 */
//@PlanningEntity
//public class RehearsalPlan {
//
//    private Piece piece;
//    private Rehearsal rehearsal;
//
//    @PlanningVariable(valueRangeProviderRefs = "pieces")
//    public Piece getPiece() {
//        return piece;
//    }
//
//    public void setPiece(Piece piece) {
//        this.piece = piece;
//    }
//
//    @PlanningVariable(valueRangeProviderRefs = "rehearsals")
//    public Rehearsal getRehearsal() {
//        return rehearsal;
//    }
//
//    public void setRehearsal(Rehearsal rehearsal) {
//        this.rehearsal = rehearsal;
//    }
//
//
//    @Override
//    public String toString() {
//        return "Piece: " + getPiece().id + " has rehearsal " + rehearsal.id + " at "+ rehearsal.time;
//    }
//}
