package heap.exception;

/**
 * 访问空的容器时, 抛出此异常
 */
public class UnderflowException extends RuntimeException {
    public UnderflowException() {
        super();
    }
}
