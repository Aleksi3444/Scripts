package impl;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Altar extends Task {


    @Override
    public boolean validate() {

        Log.info("Interact " + Interact());
        Log.info("Entering Altar " + EnterAltar());
        Log.info("Exit Altar "+ ExitAltar());


        return Interact() || EnterAltar() || ExitAltar();

    }

    @Override
    public int execute() {

        if (EnterAltar()) {
            SceneObject ruins = SceneObjects.getNearest("Mysterious ruins");
            ruins.interact("Enter");
            Time.sleep(1000, 2000);



        } else if(Interact()){

                SceneObject altar = SceneObjects.getNearest("Altar");
                altar.interact("Craft-rune");
                Time.sleepUntil(() -> Inventory.contains("Pure essence"), () -> Players.getLocal().isAnimating(), 2000);


        } else {
            SceneObject portal = SceneObjects.getNearest("Portal");
            portal.interact("Use");
            Time.sleep(2000,4000);


        }







            return 300;
        }

        private boolean Interact() {

            return Traverse.ALTAR_INSIDE.contains(Players.getLocal()) && Inventory.contains("Pure essence");

        }

        private boolean EnterAltar() {

            return !Traverse.ALTAR_INSIDE.contains(Players.getLocal()) && Inventory.contains("Pure essence");
        }


        private boolean ExitAltar(){

        return Traverse.ALTAR_INSIDE.contains(Players.getLocal()) && !Inventory.contains("Pure essence");
        }



    }
