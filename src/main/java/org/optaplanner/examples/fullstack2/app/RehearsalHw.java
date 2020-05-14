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

package org.optaplanner.examples.fullstack2.app;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

import java.util.*;

public class RehearsalHw {

    public static void main(String[] args) {

        final SolverEventListener<RehearsalSchedule> listener = new SolverEventListener<RehearsalSchedule>() {
            @Override
            public void bestSolutionChanged(BestSolutionChangedEvent<RehearsalSchedule> bestSolutionChangedEvent) {

                // Display the result
                RehearsalSchedule newBestSolution = bestSolutionChangedEvent.getNewBestSolution();
                if (newBestSolution.getScore().getHardScore() >= 0) {
                    System.out.println("\n################## Solved it with score " + newBestSolution.getScore().toString() + " #################\n"
                            + newBestSolution.toString());

                    System.out.println("\n\nParticipant schedule\n" + newBestSolution.participantToString());
                    System.out.println("\n\nPieces participants\n" + newBestSolution.pieceParticipants());
                    System.out.println("\n\nUnused rehearsals\n" + newBestSolution.unusedRehearsals());
                    System.out.println("\n\nDate/Time/Script CSV\n" + newBestSolution.dateTimeScriptCsv());
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(1, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(2, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(3, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(4, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(5, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(6, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(7, listener);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(8, listener);
            }
        }).start();
    }

    public static void startSolver(long seed, SolverEventListener<RehearsalSchedule> listener) {
        // Build the Solver
        SolverFactory<RehearsalSchedule> solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/fullstack2/fullstackSolverConfig.xml");
//        solverFactory.getSolverConfig().setTerminationConfig(new TerminationConfig());
//        solverFactory.getSolverConfig().getTerminationConfig().setsco
        solverFactory.getSolverConfig().setRandomSeed(seed);
        Solver<RehearsalSchedule> solver = solverFactory
                .buildSolver();

        solver.addEventListener(listener);

        RehearsalSchedule unsolvedCloudBalance = createUnsolved();

        // Solve the problem
        RehearsalSchedule solvedCloudBalance = solver.solve(unsolvedCloudBalance);


//        System.exit(0);
    }

    private static RehearsalSchedule createUnsolved() {
        RehearsalSchedule sch = new RehearsalSchedule();
        sch.participants = new HashSet<Participant>();
        //new Rehearsal.Date(, )
        Participant directorSaskia = addParticipant("directorSaskia", sch.participants,
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 20),
                new Rehearsal.Date(6, 21)
                );
        Participant directorMatthew = addParticipant("directorMatthew", sch.participants,
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 17),
                new Rehearsal.Date(6, 24),
                new Rehearsal.Date(7, 1));
        Participant directorSimonne = addParticipant("directorSimonne", sch.participants,
                new Rehearsal.Date(5, 24),
                new Rehearsal.Date(5, 31),
                new Rehearsal.Date(6, 7),
                new Rehearsal.Date(6, 14),
                new Rehearsal.Date(6, 21),
                new Rehearsal.Date(6, 28));
        Participant directorAnna = addParticipant("directorAnna", sch.participants,
                new Rehearsal.Date(5, 24),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 31),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 14),
                new Rehearsal.Date(6, 27),
                new Rehearsal.Date(6, 28)
                );
        Participant directorMillie = addParticipant("directorMillie", sch.participants,
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 20),
                new Rehearsal.Date(6, 21),
                new Rehearsal.Date(6, 22),
                new Rehearsal.Date(6, 27),
                new Rehearsal.Date(6, 28));
        Participant directorSarah = addParticipant("directorSarah", sch.participants,
                new Rehearsal.Date(5, 24),
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 14),
                new Rehearsal.Date(6, 17),
                new Rehearsal.Date(6, 24),
                new Rehearsal.Date(7, 1));
        Participant anie = addParticipant("Anie", sch.participants,
                new Rehearsal.Date(6, 21));
        Participant anjali = addParticipant("Anjali", sch.participants,
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 17),
                new Rehearsal.Date(6, 24),
                new Rehearsal.Date(7, 6)
                );
        Participant caroline = addParticipant("Caroline", sch.participants,
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 15),
                new Rehearsal.Date(6, 17),
                new Rehearsal.Date(6, 22),
                new Rehearsal.Date(6, 29),
                new Rehearsal.Date(7, 1)
                );
        Participant doug = addParticipant("Doug", sch.participants);
        Participant jamila = addParticipant("Jamila", sch.participants,
                new Rehearsal.Date(5, 25),
                new Rehearsal.Date(6, 2)
                );
        Participant kateOr = addParticipant("Kate OR", sch.participants,
                new Rehearsal.Date(5, 23)
        );
        Participant katieM = addParticipant("Katie M", sch.participants
        );
        Participant maddie = addParticipant("Maddie", sch.participants,
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 20)
        );
        Participant mattT = addParticipant("Matt T", sch.participants,
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 30),
                new Rehearsal.Date(5, 31));
        Participant matthewA = addParticipant("Matthew A", sch.participants,
                new Rehearsal.Date(5, 25));
        Participant rahul = addParticipant("Rahul", sch.participants);
        Participant tom = addParticipant("Tom", sch.participants);


        sch.rehearsals = new HashSet<Rehearsal>();

        sch.rehearsals.add(new Rehearsal("", 5, 23, 10));
        sch.rehearsals.add(new Rehearsal("", 5, 23, 12));
        sch.rehearsals.add(new Rehearsal("", 5, 23, 14));

        sch.rehearsals.add(new Rehearsal("", 5, 24, 10));
        sch.rehearsals.add(new Rehearsal("", 5, 24, 12));
        sch.rehearsals.add(new Rehearsal("", 5, 24, 14));
        sch.rehearsals.add(new Rehearsal("", 5, 24, 16));

        sch.rehearsals.add(new Rehearsal("", 5, 25, 18));
        sch.rehearsals.add(new Rehearsal("", 5, 25, 20));

        sch.rehearsals.add(new Rehearsal("", 5, 26, 18));
        sch.rehearsals.add(new Rehearsal("", 5, 26, 20));

        sch.rehearsals.add(new Rehearsal("", 5, 27, 18));
        sch.rehearsals.add(new Rehearsal("", 5, 28, 20));

        sch.rehearsals.add(new Rehearsal("", 5, 30, 10));
        sch.rehearsals.add(new Rehearsal("", 5, 30, 12));
        sch.rehearsals.add(new Rehearsal("", 5, 30, 14));
        sch.rehearsals.add(new Rehearsal("", 5, 30, 16));

        sch.rehearsals.add(new Rehearsal("", 5, 31, 10));
        sch.rehearsals.add(new Rehearsal("", 5, 31, 12));
        sch.rehearsals.add(new Rehearsal("", 5, 31, 14));
        sch.rehearsals.add(new Rehearsal("", 5, 31, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 1, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 1, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 2, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 2, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 3, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 4, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 6, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 6, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 6, 14));
        sch.rehearsals.add(new Rehearsal("", 6, 6, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 7, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 7, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 7, 14));
        sch.rehearsals.add(new Rehearsal("", 6, 7, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 8, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 8, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 9, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 9, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 10, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 10, 20));

//        sch.rehearsals.add(new Rehearsal("", 6, 13, 14));
//        sch.rehearsals.add(new Rehearsal("", 6, 13, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 14, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 14, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 14, 14));
        sch.rehearsals.add(new Rehearsal("", 6, 14, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 15, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 15, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 16, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 16, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 17, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 17, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 20, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 20, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 20, 14));
        sch.rehearsals.add(new Rehearsal("", 6, 20, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 21, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 21, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 21, 14));
        sch.rehearsals.add(new Rehearsal("", 6, 21, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 22, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 22, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 23, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 23, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 24, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 24, 20));

        sch.rehearsals.add(new Rehearsal("", 6, 27, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 27, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 27,14));
        sch.rehearsals.add(new Rehearsal("", 6, 27, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 28, 10));
        sch.rehearsals.add(new Rehearsal("", 6, 28, 12));
        sch.rehearsals.add(new Rehearsal("", 6, 28, 14));
        sch.rehearsals.add(new Rehearsal("", 6, 28, 16));

        sch.rehearsals.add(new Rehearsal("", 6, 29, 18));
        sch.rehearsals.add(new Rehearsal("", 6, 29, 20));

//        sch.rehearsals.add(new Rehearsal("", 7, 4, 18));
//        sch.rehearsals.add(new Rehearsal("", 7, 4, 20));

//        sch.rehearsals.add(new Rehearsal("", 7, 7, 18));
//        sch.rehearsals.add(new Rehearsal("", 7, 7, 20));


        System.out.print("Participant unavailability:\n");
        for (Participant p : sch.participants) {
            System.out.print(p.name + ": ");
            for (Rehearsal.Date d : p.notAvailableDate) {
                System.out.print(d.toString() + ", ");
            }
            System.out.println();
        }

        Map<Rehearsal.Date, Integer> allDates = new HashMap<Rehearsal.Date, Integer>();
        for (int i=1; i <= 29; i++) {
            allDates.put(new Rehearsal.Date(6, i), 0);
        }
        for (int i=24; i <= 31; i++) {
            allDates.put(new Rehearsal.Date(5, i), 0);
        }
        for (Participant p : sch.participants) {
            for (Rehearsal.Date d : p.notAvailableDate) {
                Integer count = allDates.get(d);
                if (count != null)
                    allDates.put(d, count + 1);
            }
        }
        System.out.println("Date unavail counts:");
        for (Rehearsal.Date d : allDates.keySet())
            System.out.println(d.toString() + " " + allDates.get(d));




        sch.pieces = new HashSet<Piece>();
        sch.pieces.add(new Piece("Best Before", directorMatthew, caroline, maddie));
        sch.pieces.add(new Piece("Paper Girl", directorMatthew, maddie, mattT));
        sch.pieces.add(new Piece("Box of Joy", directorMatthew, anjali, tom));
        sch.pieces.add(new Piece("It Just Is", directorMatthew, caroline));
        sch.pieces.add(new Piece("100 Ways", directorSimonne, anie, jamila, matthewA));
        sch.pieces.add(new Piece("You Haven't Changed a Bit", directorSimonne, kateOr, rahul));
        sch.pieces.add(new Piece("Don't Cross the Line", directorSarah, anie, jamila));
        sch.pieces.add(new Piece("Like Frogs", directorSarah, doug, mattT));
        sch.pieces.add(new Piece("That Place", directorSarah, doug, kateOr, maddie, rahul));
        sch.pieces.add(new Piece("Hunting", directorAnna, kateOr, mattT));
        sch.pieces.add(new Piece("The Poe-ster", directorAnna, doug));
        sch.pieces.add(new Piece("Lost Judgement", directorAnna, anjali, katieM, matthewA, rahul, tom));
        sch.pieces.add(new Piece("Filling Time", directorMillie, katieM, tom));
        sch.pieces.add(new Piece("In Full Bloom", directorMillie, jamila, kateOr));



        return sch;
    }

//    private static void addUnavail(HashSet<String> unavailablePartimes, Participant participant, int... encodedTimes) {
//        for (int encodedTime : encodedTimes) {
//            for (int i=encodedTime; i < encodedTime + 10; i++)
//                unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(participant, i));
//        }
//    }


    private static Participant addParticipant(String actor, HashSet<Participant> participants, Rehearsal.Date... notAvailable) {
        Participant p = new Participant(actor);
        participants.add(p);
        for (Rehearsal.Date d : notAvailable)
            p.notAvailableDate.add(d);
        return p;
    }


}
