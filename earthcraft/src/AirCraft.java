
import impl.Altar;
import impl.Banking;
import impl.Juoksu;
import impl.Traverse;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "Script Name",  desc = "Script description", developer = "Developer's Name")
public class AirCraft extends TaskScript {




private static final Task[] TASKS = {new Banking(),new Traverse(), new Altar(),new Juoksu()};




    @Override
    public void onStart() {

    submit (TASKS);
    }





@Override
    public void onStop(){



}


}

