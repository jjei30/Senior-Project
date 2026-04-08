package Spells;
import Effects.Effects;

public class SpellsList {
    public static Spells poisonMyst(){
        Spells poisonMyst = new Spells("Poison Myst", 5, 10, Effects.EffectType.POISON);
        return poisonMyst;
    }
    public static Spells healingBeam(){
        Spells healingBeam = new Spells("Healing Beam", 5, 20, Effects.EffectType.HEAL);
        return healingBeam;
    }
    public static Spells Flame(){
        Spells flame = new Spells("Flame", 8, 40, Effects.EffectType.BURN);
        return flame;
    }
    public static Spells Blast(){
        Spells blast = new Spells("Blast", 6, 10, Effects.EffectType.DMG);
        return blast;
    }
    //will add a freeze one later
}
