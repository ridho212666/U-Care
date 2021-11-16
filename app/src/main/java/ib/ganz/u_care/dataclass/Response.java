package ib.ganz.u_care.dataclass;

public class Response<T> {
    private T data;
    private boolean success;
    private String message;
    private String role;
    private OrtuData ortu;
    private NakesData nakes;

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    public OrtuData getOrtu() {
        return ortu;
    }

    public NakesData getNakes() {
        return nakes;
    }
}
