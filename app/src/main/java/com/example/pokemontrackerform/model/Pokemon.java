package com.example.pokemontrackerform.model;

import java.util.logging.Level;

public class Pokemon {
    int number;
    String name;
    String species;
    double height;
    double weight;
    int level;
    int hp;
    int attack;
    int defense;

    public Pokemon(int number, double height, String species, String name, double weight, int level,
                   int hp, int attack, int defense) {
        this.number = number;
        this.height = height;
        this.species = species;
        this.name = name;
        this.weight = weight;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getSpecies() {
        return species;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
