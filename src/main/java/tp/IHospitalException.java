package tp;

public class IHospitalException extends Throwable{
    public IHospitalException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }

}
