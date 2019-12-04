package Services;

public class ValidateService {

    public static boolean isAgeValid(String str){

        if(!str.matches("[-+]?\\d+"))
            return false;
        else
            if (Integer.parseInt(str) < 18 || Integer.parseInt(str) > 65)
                return false;

        return true;
    }


    public static boolean isAmountValid(String str){

        if(!str.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+"))
            return false;
        else
        if (Double.parseDouble(str) < 1.0 || Double.parseDouble(str) > 999999.0)
            return false;

        return true;
    }

    public static boolean isUserNameValid(String str){

        if(str.matches("^[A-Za-zА-Яа-я\\s]{1,}[\\.]{0,1}[A-Za-zА-Яа-я\\s]{0,}$"))
            return true;

        return false;
    }


    public static boolean isPasswordValid(String str){
        if(str.length() < 4 || str.length() > 30)
            return false;

        return true;
    }

    public static boolean isLoginValid(String str){
        if(str.matches("^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$"))
            return true;

        return false;
    }


}
