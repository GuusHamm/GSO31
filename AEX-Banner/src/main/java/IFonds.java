import java.io.Serializable;

public interface IFonds extends Serializable
{

	String getNaam();

	double getKoers();

    void setKoers(double modifier);

}