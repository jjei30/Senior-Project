package Spells;
import Effects.Effects;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SpellsList {
    static Spells poisonMyst = new Spells("Poison Myst", 5, 10, Effects.EffectType.POISON);
    static Spells healingBeam = new Spells("Healing Beam", 5, 20, Effects.EffectType.HEAL);
    static Spells flame = new Spells("Flame", 8, 40, Effects.EffectType.BURN);
    static Spells blast = new Spells("Blast", 6, 10, Effects.EffectType.DMG);
    //will add a freeze one later

    public static List<Spells> getSpells(){
        return new ArrayList<>(Arrays.asList(poisonMyst, healingBeam, flame, blast));
    }
}
