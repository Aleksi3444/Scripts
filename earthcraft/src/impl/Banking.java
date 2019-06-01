package impl;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;


public class Banking extends Task {





    @Override
    public boolean validate() {




        return Inventory.containsOnly("Earth rune") && Traverse.BANK_AREA.contains(Players.getLocal());
    }

    @Override
    public int execute() {
        if (!Inventory.contains("Pure essence") && !Bank.isOpen()) {
            Bank.open();
            Time.sleepUntil(() -> Bank.isOpen(), Random.nextInt(3000, 6000));
        }else if(Bank.isOpen()) {
            Bank.depositAll("Earth rune");
            Time.sleepUntil(() -> Inventory.isEmpty(), Random.nextInt(3000, 6000));
            Bank.withdrawAll("Pure essence");
            Time.sleepUntil(() -> Inventory.contains("Pure essence"), Random.nextInt(3000, 6000));
        }





        return 0;
    }
}
