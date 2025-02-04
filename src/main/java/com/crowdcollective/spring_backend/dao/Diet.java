package com.crowdcollective.spring_backend.dao;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

public enum Diet {
    VEGAN("vegan", "Vegan"),
    VEGETARIAN("vegetarian", "Vegetarian"),
    MEAT("meat", "Meat"),
    FISH("fish", "Fish"),
    CHICKEN("chicken", "Chicken");

    private String value;
    private String prettyName;

    private Diet(String name, String prettyName) {
        this.value = name;
        this.prettyName = prettyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String name) {
        this.value = name;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public static Diet getDiet(String value) {
        for (Diet diet : Diet.values()) {
            if (value.equalsIgnoreCase(diet.getValue())) {
                return diet;
            }
        }
        return null;
    }
    public static Set<Diet> getDiets(Collection<String> values) {
        Set<Diet> diets = new HashSet<>();
        for (String value : values) {
            Diet diet = getDiet(value);
            if (diet != null) {
                diets.add(diet);
            }
        }
        return diets;
    }
}
