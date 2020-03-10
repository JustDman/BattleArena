package our.battlearena;

import our.battlearena.fighters.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Team t1 = new Team(new Dragon("Gustav")).add(new Dwarf("Günter"));
        Team t2 = new Team(new Dragon("Bob")).add(new Dwarf("Howard"));

        new Battle(t1, t2).startBattle();
    }
}