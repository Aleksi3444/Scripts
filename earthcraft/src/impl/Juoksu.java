package impl;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Juoksu extends Task {



    @Override
    public boolean validate() {




        return liikkuminen();
    }

    @Override
    public int execute() {
        Movement.toggleRun(true);

        return 300;
    }


private boolean liikkuminen(){

    return Movement.isRunEnabled() && Movement.getRunEnergy() > 35 && Players.getLocal().isMoving();
}



}
