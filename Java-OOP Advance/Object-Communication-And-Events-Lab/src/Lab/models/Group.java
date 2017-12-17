package Lab.models;

import Lab.interfaces.AttackGroup;
import Lab.interfaces.Attacker;
import Lab.interfaces.Target;

import java.util.ArrayList;
import java.util.List;

public class Group implements AttackGroup{
    private List<Attacker> attackers;

    public Group() {
        this.attackers = new ArrayList<>();
    }

    @Override
    public void addMember(Attacker attacker) {
        this.attackers.add(attacker);
    }

    @Override
    public void groupTarget(Target target) {
        this.attackers.forEach(a -> a.setTarget(target));
    }

    @Override
    public void groupAttack() {
        this.attackers.forEach(Attacker::attack);
    }
}
