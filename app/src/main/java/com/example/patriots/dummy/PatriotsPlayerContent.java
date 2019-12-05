package com.example.patriots.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatriotsPlayerContent {

    /**
     * An array of patriots players.
     */
    public static final List<PatriotsPlayer> ITEMS = new ArrayList<PatriotsPlayer>();

    /**
     * A map of patriots players
     */
    public static final Map<String, PatriotsPlayer> ITEM_MAP = new HashMap<String, PatriotsPlayer>();

    private static void addItem(PatriotsPlayer item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    /**
     * A patriots player object
     */
    public static class PatriotsPlayer {
        private final String name;
        private final String number;
        private final String position;
        private final String age;
        private final String college;
        private final String displayInfo;

        public PatriotsPlayer(String name, String number, String position, String age, String college) {
            this.name = name;
            this.number = number;
            this.position = position;
            this.age = age;
            this.college = college;
            this.displayInfo = "NUM: " + number + " POS: " + position;

        }

        public String getName() {
            return name;
        }

        public String getNumber() {
            return number;
        }

        public String getPosition() {
            return position;
        }

        public String getAge() {
            return age;
        }

        public String getCollege() {
            return college;
        }

        public String getDisplayInfo() {
            return displayInfo;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
