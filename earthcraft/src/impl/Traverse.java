package impl;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Traverse extends Task {

    public static final Area BANK_AREA = Area.rectangular(3250, 3423, 3257, 3419);
    public static final Area ALTAR_AREA = Area.rectangular(3301,3478,3311,3471);
    public static final Area ALTAR_INSIDE = Area.rectangular(2650,4824,2668,4846);

    @Override
    public boolean validate() {
        Log.info("BANK " + traverseBank());
        Log.info("ALTAR " + traverseAltar());
        return traverseBank() || traverseAltar();

    }


    @Override
    public int execute() {

        Movement.walkTo(traverseBank() ? Traverse.BANK_AREA.getCenter() : Traverse.ALTAR_AREA.getCenter());
        Time.sleep(2000,3000);



        return 300;
    }


    private boolean traverseBank() {

     return !Inventory.contains("Pure essence") && !Traverse.ALTAR_INSIDE.contains(Players.getLocal());

    }

    private boolean traverseAltar() {


     return Inventory.contains("Pure essence") && !Traverse.ALTAR_AREA.contains(Players.getLocal()) && Inventory.isFull() && !Traverse.ALTAR_INSIDE.contains(Players.getLocal());
    }
    }





