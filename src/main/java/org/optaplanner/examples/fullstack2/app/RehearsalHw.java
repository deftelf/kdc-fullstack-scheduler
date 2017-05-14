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
import org.optaplanner.core.config.solver.termination.TerminationConfig;
import org.optaplanner.examples.cloudbalancing.domain.CloudBalance;
import org.optaplanner.examples.cloudbalancing.domain.CloudComputer;
import org.optaplanner.examples.cloudbalancing.domain.CloudProcess;
import org.optaplanner.examples.cloudbalancing.persistence.CloudBalancingGenerator;

import java.util.*;

public class RehearsalHw {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                startSolver(1);
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(2);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(3);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(4);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(5);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(6);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(7);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                startSolver(8);
//            }
//        }).start();
    }

    public static void startSolver(long seed) {
        // Build the Solver
        SolverFactory<RehearsalSchedule> solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/fullstack2/fullstackSolverConfig.xml");
//        solverFactory.getSolverConfig().setTerminationConfig(new TerminationConfig());
//        solverFactory.getSolverConfig().getTerminationConfig().setsco
        solverFactory.getSolverConfig().setRandomSeed(seed);
        Solver<RehearsalSchedule> solver = solverFactory
                .buildSolver();

        solver.addEventListener(new SolverEventListener<RehearsalSchedule>() {
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
        });

        // Load a problem with 400 computers and 1200 processes
        RehearsalSchedule unsolvedCloudBalance = createUnsolved();

        // Solve the problem
        RehearsalSchedule solvedCloudBalance = solver.solve(unsolvedCloudBalance);


//        System.exit(0);
    }

    private static RehearsalSchedule createUnsolved() {
        RehearsalSchedule sch = new RehearsalSchedule();
        sch.participants = new HashSet<Participant>();
        Participant rachel = addParticipant("rachel", sch.participants);
        Participant grace = addParticipant("grace", sch.participants);
        Participant georgina = addParticipant("georgina", sch.participants);
        Participant james = addParticipant("james", sch.participants);
        Participant govind = addParticipant("govind", sch.participants);
        Participant lee = addParticipant("lee", sch.participants);
        Participant spencer = addParticipant("spencer", sch.participants);
        Participant matt = addParticipant("matt", sch.participants);
        Participant jess = addParticipant("jess", sch.participants);
        Participant yasmin = addParticipant("yasmin", sch.participants);
        Participant kat = addParticipant("kat", sch.participants);
        Participant danielle = addParticipant("danielle", sch.participants);
        Participant michaela = addParticipant("michaela", sch.participants);
        Participant directorAsma = addParticipant("Asma", sch.participants);
        Participant directorSarahB = addParticipant("directorSarahB", sch.participants);
        Participant directorSarahD = addParticipant("directorSarahD", sch.participants);
        Participant directorDave = addParticipant("directorDave", sch.participants);
        Participant directorVicky = addParticipant("directorVicky", sch.participants);

        sch.rehearsals = new HashSet<Rehearsal>();
        sch.rehearsals.add(new Rehearsal("hoop", 5, 21, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 21, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 21, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 21, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 21, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 21, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 21, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 21, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 22, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 22, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 22, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 5, 22, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 24, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 24, 20));
        sch.rehearsals.add(new Rehearsal("foundry", 5, 24, 19));
        sch.rehearsals.add(new Rehearsal("foundry", 5, 24, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 5, 31, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 5, 31, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 3, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 3, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 5, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 5, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 5, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 5, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 7, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 7, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 10, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 10, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 12, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 12, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 12, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 12, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 14, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 14, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 17, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 17, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 17, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 17, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 17, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 17, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 17, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 17, 15));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 19, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 19, 20));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 19, 19));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 19, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 21, 19));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 21, 20));

        sch.rehearsals.add(new Rehearsal("hoop", 6, 24, 12));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 24, 13));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 24, 14));
        sch.rehearsals.add(new Rehearsal("hoop", 6, 24, 15));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 24, 12));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 24, 13));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 24, 14));
        sch.rehearsals.add(new Rehearsal("hoop2", 6, 24, 15));


        // Speculative
//        sch.rehearsals.add(new Rehearsal("sun18 7a", 187));
//        sch.rehearsals.add(new Rehearsal("sun18 8a", 188));
//        sch.rehearsals.add(new Rehearsal("sun18 5a", 185));
//        sch.rehearsals.add(new Rehearsal("sun18 6a", 186));
//        sch.rehearsals.add(new Rehearsal("sun18 1a", 181));
//        sch.rehearsals.add(new Rehearsal("sun18 2a", 182));
//        sch.rehearsals.add(new Rehearsal("sun18 3a", 183));
//        sch.rehearsals.add(new Rehearsal("sun18 4a", 184));



//        sch.rehearsals.add(new Rehearsal("wed24 1a", -241));
//        sch.rehearsals.add(new Rehearsal("wed24 2a", -242));
//        sch.rehearsals.add(new Rehearsal("wed24 1b", -241));
//        sch.rehearsals.add(new Rehearsal("wed24 2b", -242));
//
//
//        sch.rehearsals.add(new Rehearsal("sun21 1a", -211));
//        sch.rehearsals.add(new Rehearsal("sun21 2a", -212));
//        sch.rehearsals.add(new Rehearsal("sun21 3a", -213));
//        sch.rehearsals.add(new Rehearsal("sun21 4a", -214));
//        sch.rehearsals.add(new Rehearsal("sun21 1b", -211));
//        sch.rehearsals.add(new Rehearsal("sun21 2b", -212));
//        sch.rehearsals.add(new Rehearsal("sun21 3b", -213));
//        sch.rehearsals.add(new Rehearsal("sun21 4b", -214));


        sch.pieces = new HashSet<Piece>();
        sch.pieces.add(new Piece("Bathyscaphe", georgina, danielle, kat, directorSarahB));
        sch.pieces.add(new Piece("Wisdom", rachel, grace, michaela, directorSarahB));
//        sch.pieces.add(new Piece("Plato's Cave", actor5, actor6, directorAsma));
        sch.pieces.add(new Piece("Bed Air", govind, danielle, directorSarahD));
        sch.pieces.add(new Piece("Insomnia", lee, michaela, directorVicky));
        sch.pieces.add(new Piece("The End", spencer, matt, rachel, grace, directorVicky));
        sch.pieces.add(new Piece("TLTKMUAN", jess, yasmin, lee, directorDave));
        sch.pieces.add(new Piece("Code 0113/Plato's Cave", james, govind, kat, directorAsma));
        sch.pieces.add(new Piece("The Train", jess, matt, directorAsma));
        sch.pieces.add(new Piece("Amnesia", james, georgina, directorSarahD));
        sch.pieces.add(new Piece("Tag/Grown Attached", kat, spencer, yasmin, directorDave));

//        sch.unavailablePartimes = new HashSet<String>();
//        addUnavail(sch.unavailablePartimes, directorSarahD, 290, 60, 80, 130, 210, 220, 230, 240, 250);
//        addUnavail(sch.unavailablePartimes, directorAsma, 290, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120);
//        addUnavail(sch.unavailablePartimes, jess, 30, 60, 70, 100, 110, 240);
//        addUnavail(sch.unavailablePartimes, georgina, 80, 90, 100, 110);
//        addUnavail(sch.unavailablePartimes, michaela, 10, 80, 140, 150, 210, 220);
//        addUnavail(sch.unavailablePartimes, kat, 160, 170, 290);
//        addUnavail(sch.unavailablePartimes, rachel, 300, 30, 60, 70, 80, 90, 100, 110, 120, 130, 170, 200, 240);
//        addUnavail(sch.unavailablePartimes, matt, 100, 110, 120, 130, 140, 150, 160, 170);
//        addUnavail(sch.unavailablePartimes, directorSarahB, 30, 100, 160, 170, 240);
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 291));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 301));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 311));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 51));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 81));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 91));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 101));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 121));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 131));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 141));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 151));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 201));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 211));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorSarahB, 231));






//        addUnavail(sch.unavailablePartimes, );
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorAsma, 1L));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorAsma, 2L));
//        for (int i=5; i <= 18; i++)
//            sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorAsma, i));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorAsma, 4L));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorAsma, 30L));
//        sch.unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(directorAsma, 32L));


        return sch;
    }

//    private static void addUnavail(HashSet<String> unavailablePartimes, Participant participant, int... encodedTimes) {
//        for (int encodedTime : encodedTimes) {
//            for (int i=encodedTime; i < encodedTime + 10; i++)
//                unavailablePartimes.add(FullStackEasyScoreCalculator.createParTime(participant, i));
//        }
//    }


    private static Participant addParticipant(String actor, HashSet<Participant> participants) {
        Participant p = new Participant(actor);
        participants.add(p);
        return p;
    }


}
