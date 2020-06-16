package api.restful.restfull.model;

import java.io.Serializable;

public interface IIdentifier<T extends Number> extends Serializable {

    T getId();

}
