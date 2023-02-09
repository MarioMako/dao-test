package db;

public class DbException extends RuntimeException{
    public static final long SerialVersionUID = 1L;

    public DbException(String e){
        super(e);
    }
}
