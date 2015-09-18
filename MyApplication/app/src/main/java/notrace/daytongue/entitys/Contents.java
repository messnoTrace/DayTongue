package notrace.daytongue.entitys;

import java.io.Serializable;
import java.util.List;

/**
 * Created by notrace on 2015/9/16.
 */
public class Contents implements Serializable{

    public List<Photo> getItem() {
        return item;
    }

    public void setItem(List<Photo> item) {
        this.item = item;
    }

    private List<Photo>item;
}
