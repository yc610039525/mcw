package cn.cd.caoyeung.webapp.utils.exception;


public class TaskException extends Exception{

	private static final long serialVersionUID = 1L;

	public TaskException(Throwable cause) {
        super(cause);
    }
	
	public TaskException(String msg) {
        super(msg);
    }
	
	public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
