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
                    System.out.println("\nSolved it with score " + newBestSolution.getScore().toString() + "\n"
                            + newBestSolution.toString());

                    System.out.println("\n\nParticipant schedule\n" + newBestSolution.participantToString());
                    System.out.println("\n\nPieces participants\n" + newBestSolution.pieceParticipants());
                    System.out.println("\n\nUnused rehearsals\n" + newBestSolution.unusedRehearsals());
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
        Participant sarah = addParticipant("Sarah", sch.participants,
                new Rehearsal.Date(5, 7),
                new Rehearsal.Date(5, 9),
                new Rehearsal.Date(5, 14),
                new Rehearsal.Date(5, 23),
                new Rehearsal.Date(6,3),
                new Rehearsal.Date(6, 4));
        Participant carl = addParticipant("Carl", sch.participants,
                new Rehearsal.Date(5, 16),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 4));
        Participant lucy = addParticipant("Lucy", sch.participants,
                new Rehearsal.Date(5, 6),
                new Rehearsal.Date(5, 7));
        Participant alex = addParticipant("Alex", sch.participants);
        Participant lil = addParticipant("Lil", sch.participants);
        Participant annabel = addParticipant("Annabel", sch.participants,
                new Rehearsal.Date(5, 4),
                new Rehearsal.Date(5, 5),
                new Rehearsal.Date(5, 6),
                new Rehearsal.Date(5, 7),
                new Rehearsal.Date(5, 8),
                new Rehearsal.Date(5, 9),
                new Rehearsal.Date(5, 10),
                new Rehearsal.Date(5, 11),
                new Rehearsal.Date(5, 12),
                new Rehearsal.Date(5, 13),
                new Rehearsal.Date(5, 16));
        Participant ynes = addParticipant("Ynes", sch.participants);
        Participant callum = addParticipant("Callum", sch.participants,
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 4),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 6),
                new Rehearsal.Date(6, 7),
                new Rehearsal.Date(6, 8));
        Participant tekle = addParticipant("Tekle", sch.participants,
                new Rehearsal.Date(5, 7),
                new Rehearsal.Date(5, 8));
        Participant gemma = addParticipant("Gemma", sch.participants,
                new Rehearsal.Date(5, 25),
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 28),
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(5, 30),
                new Rehearsal.Date(5, 31),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 4),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 6),
                new Rehearsal.Date(6, 7),
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 9));

        Participant directorSarah = addParticipant("directorSarah", sch.participants,
                new Rehearsal.Date(5, 6),
                new Rehearsal.Date(5, 7),
                new Rehearsal.Date(5, 9),
                new Rehearsal.Date(5, 13),
                new Rehearsal.Date(5, 14),
                new Rehearsal.Date(5, 27));
        Participant directorAneirin = addParticipant("directorAneirin", sch.participants
                );
        Participant directorGrace = addParticipant("directorGrace", sch.participants,
                new Rehearsal.Date(5, 13),
                new Rehearsal.Date(5, 14),
                new Rehearsal.Date(5, 20),
                new Rehearsal.Date(5, 21),
                new Rehearsal.Date(5, 23),
                new Rehearsal.Date(5, 30),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 4)
        );
        Participant directorDanielle = addParticipant("directorDanielle", sch.participants,
                new Rehearsal.Date(6, 6));
        Participant directorGovind = addParticipant("directorGovind", sch.participants);
        Participant directorSteph = addParticipant("directorSteph", sch.participants,
                new Rehearsal.Date(5, 7),
                new Rehearsal.Date(5, 16),
                new Rehearsal.Date(5, 20),
                new Rehearsal.Date(5, 21),
                new Rehearsal.Date(5, 22),
                new Rehearsal.Date(6, 6));

        sch.rehearsals = new HashSet<Rehearsal>();

        sch.rehearsals.add(new Rehearsal("hoop", 5, 9, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 9, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 13, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 13, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 13, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 13, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 13, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 13, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 13, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 13, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 14, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 14, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 14, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 14, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 16, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 16, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 20, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 20, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 20, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 20, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 20, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 20, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 20, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 20, 15));

        sch.rehearsals.add(new Rehearsal("calder bookshop", 5, 21, 19));
        sch.rehearsals.add(new Rehearsal("calder bookshop", 5, 21, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 22, 19)); // Tuesday
        sch.rehearsals.add(new Rehearsal("hoop", 5, 22, 20));

        sch.rehearsals.add(new Rehearsal("calder bookshop", 5, 23, 19));
        sch.rehearsals.add(new Rehearsal("calder bookshop", 5, 23, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 30, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 30, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 4, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 4, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 4, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 4, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 6, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 6, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 11, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 11, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 11, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 11, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 13, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 13, 20));

        System.out.print("Participant unavailability:\n");
        for (Participant p : sch.participants) {
            System.out.print(p.name + ": ");
            for (Rehearsal.Date d : p.notAvailableDate) {
                System.out.print(d.toString() + ", ");
            }
            System.out.println();
        }

        Map<Rehearsal.Date, Integer> allDates = new HashMap<Rehearsal.Date, Integer>();
        for (int i=1; i <= 24; i++) {
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

        // Speculative

//        sch.rehearsals.add(new Rehearsal("?", 6, 4, 12));
//        sch.rehearsals.add(new Rehearsal("?", 6, 4, 13));
//        sch.rehearsals.add(new Rehearsal("?", 6, 4, 14));
//        sch.rehearsals.add(new Rehearsal("?", 6, 4, 15));
//
//        sch.rehearsals.add(new Rehearsal("?", 6, 18, 12));
//        sch.rehearsals.add(new Rehearsal("?", 6, 18, 13));
//        sch.rehearsals.add(new Rehearsal("?", 6, 18, 14));
//        sch.rehearsals.add(new Rehearsal("?", 6, 18, 15));



        sch.pieces = new HashSet<Piece>();
        sch.pieces.add(new Piece("What's App-ening", directorGrace, lucy, lil));
        sch.pieces.add(new Piece("Taking Sun Lumps", directorGrace, sarah, lil));
        sch.pieces.add(new Piece("Welcome to Future Imperfect", directorAneirin, sarah, callum,
                tekle // small
                ));
        sch.pieces.add(new Piece("Ill Comes Forth", directorAneirin, alex));
        sch.pieces.add(new Piece("The cream Coloured Clock", directorDanielle, gemma));
        sch.pieces.add(new Piece("Moving Day", directorDanielle, carl,
                alex // small
        ));
        sch.pieces.add(new Piece("Pseudo Human Resources", directorSteph, sarah, ynes, gemma, tekle));
        sch.pieces.add(new Piece("Not A Lot Party", directorSteph, carl, ynes));
        sch.pieces.add(new Piece("Raven 2.0", directorGovind, annabel, alex));
        sch.pieces.add(new Piece("Lucy", directorGovind, lucy, tekle));
        sch.pieces.add(new Piece("Dancercourse", directorSarah, lucy, callum));
        sch.pieces.add(new Piece("Crime Sympathy Unit", directorSarah, carl, alex));
        sch.pieces.add(new Piece("Wednesday", directorSarah, annabel, ynes));


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
