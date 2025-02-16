import java.util.Random;

public class Hero {
    private String name;
    private int hitpoints;

    public Hero(String name) {
        this.name = name;
        this.hitpoints = 100;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitpoints;
    }

    public String attack(Hero opp) {
        if (new Random().nextDouble() < 0.5) {
            opp.hitpoints -= 10;
            return this.name + "attacked" + opp.name + "!";
        } else {
            this.hitpoints -= 10;
            return opp.name + "attacked" + this.name + "!";
        }
    }

    public void senzuBean() {
        this.hitpoints = 100;
    }

    private void fightUntilTheDeathHelper(Hero opp) {
        while (this.hitpoints > 0 && opp.hitpoints > 0) {
            attack(opp);
        }
    }

    public String fightUntilTheDeath(Hero opp) {
        fightUntilTheDeathHelper(opp);
        return "Hero{name='" + opp.name + "', hitPoints=" + opp.hitpoints + "}\n" +
                "Hero{name='" + this.name + "', hitPoints=" + this.hitpoints + "}";
    }

    private int[] nFightsToTheDeathHelper(Hero opp, int n) {
        int[] wins = new int[2];
        for (int i = 0; i < n; i++) {
            fightUntilTheDeathHelper(opp);
            if (opp.hitpoints == 0) {
                wins[0]++;
            } else {
                wins[1]++;
            }
            opp.senzuBean();
            this.senzuBean();
        }
        return wins;
    }

    public String nFightsToTheDeath(Hero opp, int n) {
        int[] endResult = nFightsToTheDeathHelper(opp, n);
        if (endResult[0] == endResult[1]) {
            return opp.name + ":" + endResult[1] + "wins\n" + name + ": " + endResult[0] + " wins\n" + "draw";
        } else if (endResult[0] > endResult[1]) {
            return opp.name + ": " + endResult[1] + " wins\n" + name + ": " + endResult[0] + " wins\n" + name + " won";
        }
        return opp.name + ": " + endResult[1] + " wins\n" + name + ": " + endResult[0] + " wins\n" + opp.name + " won";
    }

    public String dramaticFightToTheDeath(Hero opp) {
        while (hitpoints > 0 && opp.hitpoints > 0) {
            attack(opp);
            System.out.println(this.getName() + ": " + this.getHitPoints() + "\t" +
                    opp.getName() + ": " + opp.getHitPoints());
        }

        if (opp.hitpoints > 0) {
            opp.attack(this);
            System.out.println(this.getName() + ": " + this.getHitPoints() + "\t" +
                    opp.getName() + ": " + opp.getHitPoints());
        }
        if (hitpoints > 0) {
            return this.getName() + " wins!";
        } else {
            return opp.getName() + " wins!";
        }
    }

    public String toString() {
        return "Hero{name='" + name + "', hitPoints=" + hitpoints + "}";
    }
}

