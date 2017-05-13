/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.fullstack.app;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.cloudbalancing.domain.CloudBalance;
import org.optaplanner.examples.cloudbalancing.domain.CloudComputer;
import org.optaplanner.examples.cloudbalancing.domain.CloudProcess;
import org.optaplanner.examples.cloudbalancing.persistence.CloudBalancingGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RehearsalHw {

    public static void main(String[] args) {
        // Build the Solver
        SolverFactory<RehearsalSchedule> solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/fullstack/fullstackSolverConfig.xml");
        Solver<RehearsalSchedule> solver = solverFactory.buildSolver();

        // Load a problem with 400 computers and 1200 processes
        RehearsalSchedule unsolvedCloudBalance = createUnsolved();

        // Solve the problem
        RehearsalSchedule solvedCloudBalance = solver.solve(unsolvedCloudBalance);

        // Display the result
        System.out.println("\nSolved it!\n"
                + solvedCloudBalance.toString());
    }

    private static RehearsalSchedule createUnsolved() {
        RehearsalSchedule sch = new RehearsalSchedule();
        sch.participants = new ArrayList<Participant>();
        sch.participants.add(new Participant(0));
        sch.participants.add(new Participant(1));
        sch.participants.add(new Participant(2));
        sch.participants.add(new Participant(3));
        sch.participants.add(new Participant(4));
        sch.participants.add(new Participant(5));

        sch.rehearsals = new ArrayList<Rehearsal>();
        sch.rehearsals.add(new Rehearsal(1, 1));
        sch.rehearsals.add(new Rehearsal(2, 1));
        sch.rehearsals.add(new Rehearsal(3, 2));
        sch.rehearsals.add(new Rehearsal(4, 2));
        sch.rehearsals.add(new Rehearsal(5, 5));
        sch.rehearsals.add(new Rehearsal(6, 6));
        sch.rehearsals.add(new Rehearsal(7, 7));
        sch.rehearsals.add(new Rehearsal(8, 7));
        sch.rehearsals.add(new Rehearsal(9, 8));

        List<Participant> p1p = new ArrayList<Participant>();
        p1p.add(sch.participants.get(0));
        p1p.add(sch.participants.get(1));
        p1p.add(sch.participants.get(2));

        List<Participant> p2p = new ArrayList<Participant>();
//        p2p.add(sch.participants.get(2));
        p2p.add(sch.participants.get(3));
        p2p.add(sch.participants.get(4));

        Piece p1 = new Piece(1, p1p);
        Piece p2 = new Piece(2, p2p);
        sch.pieces = new ArrayList<Piece>();
        sch.pieces.add(p1);
        sch.pieces.add(p2);
        sch.pieces.add(new Piece(Piece.NO_PIECE, new ArrayList<Participant>(0)));


//        RehearsalPlan pl1;
//        pl1 = new RehearsalPlan();
//        pl1.setPiece(p1);
//        sch.plans.add(pl1);
//        pl1 = new RehearsalPlan();
//        pl1.setPiece(p1);
//        sch.plans.add(pl1);
//        pl1 = new RehearsalPlan();
//        pl1.setPiece(p1);
//        sch.plans.add(pl1);
//        pl1 = new RehearsalPlan();
//        pl1.setPiece(p2);
//        sch.plans.add(pl1);
//        pl1 = new RehearsalPlan();
//        pl1.setPiece(p2);
//        sch.plans.add(pl1);
//        pl1 = new RehearsalPlan();
//        pl1.setPiece(p2);
//        sch.plans.add(pl1);

        return sch;
    }


}
