package bo;

import bo.custom.impl.ProgrammeBOImpl;
import bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        if (boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOFactory.BoTypes types){
        switch (types){
            case STUDENT:
                return new StudentBOImpl();
            case PROGRAMME:
                return new ProgrammeBOImpl();
            default:
                return null;
        }

    }

    public enum BoTypes{
        STUDENT,PROGRAMME
    }

}
