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
        Participant ben = addParticipant("Ben", sch.participants,
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6,10),
                new Rehearsal.Date(6, 15),
                new Rehearsal.Date(6, 16),
                new Rehearsal.Date(6, 19),
                new Rehearsal.Date(6, 22),
                new Rehearsal.Date(6, 26),
                new Rehearsal.Date(6, 29),
                new Rehearsal.Date(6, 30));
        Participant govind = addParticipant("Govind", sch.participants,
                new Rehearsal.Date(5, 29));
        Participant henry = addParticipant("Henry", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 26),
                new Rehearsal.Date(6, 29),
                new Rehearsal.Date(6, 30),
                new Rehearsal.Date(7, 1));
        Participant holly = addParticipant("Holly", sch.participants,
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 12),
                new Rehearsal.Date(6, 19),
                new Rehearsal.Date(6, 26),
                new Rehearsal.Date(7, 3));
        Participant jamila = addParticipant("Jamila", sch.participants,
                new Rehearsal.Date(5, 26));
        Participant owen = addParticipant("Owen", sch.participants,
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 9));
        Participant paul = addParticipant("Paul", sch.participants,
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 12));
        Participant rhydian = addParticipant("Rhydian", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 23),
                new Rehearsal.Date(6, 30));
        Participant sarahB = addParticipant("Sarah B", sch.participants,
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 10),
                new Rehearsal.Date(6, 12),
                new Rehearsal.Date(6, 16),
                new Rehearsal.Date(6, 17),
                new Rehearsal.Date(6, 19),
                new Rehearsal.Date(6, 22),
                new Rehearsal.Date(6, 23),
                new Rehearsal.Date(6, 24),
                new Rehearsal.Date(6, 26),
                new Rehearsal.Date(7, 1),
                new Rehearsal.Date(7, 3));
        Participant sarahD = addParticipant("Sarah D", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 22),
                new Rehearsal.Date(6, 23),
                new Rehearsal.Date(6, 26),
                new Rehearsal.Date(6, 30));
        Participant tom = addParticipant("Tom", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 5),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 22),
                new Rehearsal.Date(6, 23),
                new Rehearsal.Date(6, 26),
                new Rehearsal.Date(6, 30)
                );

        Participant directorLucy = addParticipant("directorLucy", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 29)
        );
        Participant directorSaskia = addParticipant("directorSaskia", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 15),
                new Rehearsal.Date(6, 16),
                new Rehearsal.Date(6, 17)
        );
        Participant directorLloyd = addParticipant("directorLloyd", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 15),
                new Rehearsal.Date(6, 16),
                new Rehearsal.Date(6, 17)
        );
        Participant directorVicky = addParticipant("directorVicky", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2));
        Participant directorAneirin = addParticipant("directorAnierin", sch.participants,
                new Rehearsal.Date(5, 26),
                new Rehearsal.Date(5, 27),
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 15));
        Participant directorAnna = addParticipant("directorAnna", sch.participants,
                new Rehearsal.Date(5, 29),
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 10));
        Participant directorClaire = addParticipant("directorClaire", sch.participants,
                new Rehearsal.Date(6, 1),
                new Rehearsal.Date(6, 2),
                new Rehearsal.Date(6, 3),
                new Rehearsal.Date(6, 8),
                new Rehearsal.Date(6, 9),
                new Rehearsal.Date(6, 10));

        sch.rehearsals = new HashSet<Rehearsal>();

        sch.rehearsals.add(new Rehearsal("deli", 5, 26, 12));
        sch.rehearsals.add(new Rehearsal("deli", 5, 26, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 27, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 27, 20));
        sch.rehearsals.add(new Rehearsal("deli", 5, 27, 19));
        sch.rehearsals.add(new Rehearsal("deli", 5, 27, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 29, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 29, 20));
        sch.rehearsals.add(new Rehearsal("deli", 5, 29, 19));
        sch.rehearsals.add(new Rehearsal("deli", 5, 29, 20));

        sch.rehearsals.add(new Rehearsal("deli", 6, 1, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 1, 15));

        sch.rehearsals.add(new Rehearsal("deli", 6, 2, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 2, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 5, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 5, 20));
        sch.rehearsals.add(new Rehearsal("deli", 6, 5, 19));
        sch.rehearsals.add(new Rehearsal("deli", 6, 5, 20));

        sch.rehearsals.add(new Rehearsal("deli", 6, 8, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 8, 15));

        sch.rehearsals.add(new Rehearsal("deli", 6, 9, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 9, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 12, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 12, 20));
        sch.rehearsals.add(new Rehearsal("deli", 6, 12, 19));
        sch.rehearsals.add(new Rehearsal("deli", 6, 12, 20));

        sch.rehearsals.add(new Rehearsal("deli", 6, 15, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 15, 15));

        sch.rehearsals.add(new Rehearsal("deli", 6, 16, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 16, 15));

        // Exclude 17th

        sch.rehearsals.add(new Rehearsal("hoop", 6, 19, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 19, 20));
        sch.rehearsals.add(new Rehearsal("deli", 6, 19, 19));
        sch.rehearsals.add(new Rehearsal("deli", 6, 19, 20));

        sch.rehearsals.add(new Rehearsal("deli", 6, 22, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 22, 15));

        sch.rehearsals.add(new Rehearsal("deli", 6, 23, 12));
        sch.rehearsals.add(new Rehearsal("deli", 6, 23, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 24, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 24, 20));

        sch.rehearsals.add(new Rehearsal("deli", 6, 26, 19));
        sch.rehearsals.add(new Rehearsal("deli", 6, 26, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 29, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 29, 20));
        sch.rehearsals.add(new Rehearsal("deli", 6, 29, 19));
        sch.rehearsals.add(new Rehearsal("deli", 6, 29, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 30, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 30, 20));
        sch.rehearsals.add(new Rehearsal("deli", 6, 30, 19));
        sch.rehearsals.add(new Rehearsal("deli", 6, 30, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 7, 1, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 7, 1, 20));
        sch.rehearsals.add(new Rehearsal("deli", 7, 1, 19));
        sch.rehearsals.add(new Rehearsal("deli", 7, 1, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 7, 3, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 7, 3, 20));
        sch.rehearsals.add(new Rehearsal("deli", 7, 3, 19));
        sch.rehearsals.add(new Rehearsal("deli", 7, 3, 20));





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
        sch.pieces.add(new Piece("Goat", directorVicky, henry, owen, tom));
        sch.pieces.add(new Piece("Princess Leila", directorVicky, ben));
        sch.pieces.add(new Piece("The New Woman", directorAnna, ben, henry, holly));
        sch.pieces.add(new Piece("Jessica", directorAnna, govind));
        sch.pieces.add(new Piece("High Score", directorClaire, holly, rhydian));
        sch.pieces.add(new Piece("They're Coming to Take Me Away", directorSaskia, sarahD, ben, owen));
        sch.pieces.add(new Piece("One Lucky Country", directorSaskia, sarahB, govind));
        sch.pieces.add(new Piece("Aubergine", directorAneirin, paul, jamila));
        sch.pieces.add(new Piece("Fairies", directorLucy, rhydian));
        sch.pieces.add(new Piece("The Date", directorLucy, govind, henry, tom));
        sch.pieces.add(new Piece("Alice in Therapy", directorLucy, jamila, ben));
        sch.pieces.add(new Piece("Vortex", directorLloyd, sarahB, owen));
        sch.pieces.add(new Piece("Necessary Sacrifices", directorLloyd, jamila, sarahD, sarahB));
        sch.pieces.add(new Piece("Merits", directorLloyd, holly, rhydian, sarahD));
        sch.pieces.add(new Piece("Death of a Mine", directorSaskia, tom, paul));



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
