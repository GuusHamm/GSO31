import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Effectenbeurs implements IEffectenbeurs, Serializable
{
    private ArrayList<IFonds> fondsen;

    @Override
    public ArrayList<IFonds> getKoersen() {
        return fondsen;
    }
}