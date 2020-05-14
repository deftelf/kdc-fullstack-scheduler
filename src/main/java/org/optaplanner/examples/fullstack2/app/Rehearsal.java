package org.optaplanner.examples.fullstack2.app;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

/**
 * Created by carl on 23/04/17.
 */
public class Rehearsal implements Comparable<Rehearsal> {


    public String place;
    public Date date;
    public int hour;

//    public Rehearsal(String place, int month, int day) {
//        this(place, month, day, -1);
//    }

    public Rehearsal(String place, int month, int day, int hour) {
        this.place = place;
        this.date = new Date(month, day);
        this.hour = hour;
    }

    public Rehearsal() {
    }

    @Override
    public String toString() {
        return place + " " + date + " " + hour + ":00";
    }

    @Override
    public int compareTo(Rehearsal other) {
        int sorter = date.compareTo(other.date);
        if (sorter != 0)
            return sorter;
        return hour - other.hour; // they're on the same day
    }

    static class Date implements Comparable<Date> {

        public int month, day;

        public Date(int month, int day) {
            this.day = day;
            this.month = month;
        }

        @Override
        public String toString() {
            return day + "/" + month;
        }

        @Override
        public int hashCode() {
             return month * 10 + day;
        }

        @Override
        public boolean equals(Object o) {
            Date other = ((Date)o);
            return other.month == month && other.day == day;
        }

        @Override
        public int compareTo(Date date) {
            return (month * 30 + day) - (date.month * 30 + date.day);
        }
    }
}
