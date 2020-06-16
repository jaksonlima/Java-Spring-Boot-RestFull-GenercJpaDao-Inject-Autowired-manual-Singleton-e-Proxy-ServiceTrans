package api.restful.restfull.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class AbstractEntity<T extends Number> implements IIdentifier<T>, Serializable {

}
