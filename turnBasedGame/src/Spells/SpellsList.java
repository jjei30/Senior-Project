package Spells;
import Effects.Effects;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SpellsList {

    public static List<Spells> getSpells(){
        return new ArrayList<>(Arrays.asList(
            new Spells("Poison Myst", 5, 10, Effects.EffectType.POISON),
            new Spells("Healing Beam", 5, 20, Effects.EffectType.HEAL),
            new Spells("Flame", 8, 40, Effects.EffectType.BURN),
            new Spells("Blast", 6, 10, Effects.EffectType.DMG)
        )
        );
    }
}
