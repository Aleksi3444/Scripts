
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.listeners.SkillListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.event.types.SkillEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import java.awt.*;

@ScriptMeta(developer = "Aleksi", desc = "Barbarian Fishing", name = "Kalastus")
public class Kalastus extends Script implements RenderListener, SkillListener {

    private int EXPERIENCE_GAINED = 0;
    private long START_TIME = 0;

public void onStart(){

    START_TIME = System.currentTimeMillis();
}
    @Override
    public int loop() {


        if (shouldFish()){
            Time.sleep(3000,4000);
            Npcs.getNearest("Fishing spot").interact("Use-rod");
        Log.info("Starting to Fish");
        Time.sleepUntil(Players.getLocal()::isAnimating, 3000);
    }

        if (shouldDrop()) {
            Time.sleep(2000,4000);
            Inventory.accept(item -> item.getName().contains("Leaping"), item -> item.interact("Drop"));
             Log.info("Dropping Fish");

        }

        return 300;
    }

    private boolean shouldDrop() {

        // Validates method
        return Inventory.isFull() && !Players.getLocal().isAnimating() && !Players.getLocal().isMoving();
    }


    private boolean shouldFish(){
        return !Players.getLocal().isMoving() && !Players.getLocal().isAnimating() && !Inventory.isFull();

    }

    private String formatRuntime(long seconds) {
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%d:%02d:%02d", h,m,s);
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Graphics g = renderEvent.getSource();
        Graphics2D g2 = (Graphics2D)g;

        int y = 35;
        int x = 10;

        long runtime = System.currentTimeMillis() - START_TIME;

        g2.setColor(Color.PINK);
        g2.drawString("Run Time: " + formatRuntime(runtime / 1000), x, y += 20);
        g2.drawString("Experience gained: " + EXPERIENCE_GAINED, x, y += 20);
        g2.drawString("Experience per hour: " + (int)(EXPERIENCE_GAINED / ((double) runtime / 3600000D)), x,y+= 20);
    }

    @Override
    public void notify(SkillEvent skillEvent) {
        if(skillEvent.getType() == SkillEvent.TYPE_EXPERIENCE) {
            EXPERIENCE_GAINED += 50;
        }
    }
}