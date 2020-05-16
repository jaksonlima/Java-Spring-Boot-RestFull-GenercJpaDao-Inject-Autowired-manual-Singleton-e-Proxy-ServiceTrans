package api.restful.restfull.context;

public interface IProxy {

    default IProxy getInstance() {
        try {
            return this.getClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
