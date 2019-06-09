
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Magic;
import org.rspeer.runetek.api.component.tab.Spell;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;



@ScriptMeta(developer = "BESTAKKE", desc = "MAGEMIES", name = "CURSE")
public class MagicBOT extends Script {





    @Override
    public int loop() {

        if (!Players.getLocal().isAnimating())
            Time.sleep(700,1100);
            Magic.cast(Spell.Modern.CURSE, Npcs.getNearest("Monk of Zamorak"));
            Time.sleep(500,1400);


        return 250;
    }
}