package pe.bigprime.verifycades.models;

public class Logger {
    
    private static Boolean Err;
    private static String Message;

    public static Boolean isErr() {
        return Err;
    }

    public static void setErr(Boolean Err) {
        Logger.Err = Err;
    }

    public static String getMessage() {
        return Message;
    }

    public static void setMessage(String Message) {
        Logger.Message = Message;
    }
    
}
