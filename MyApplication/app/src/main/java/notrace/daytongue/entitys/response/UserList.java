package notrace.daytongue.entitys.response;

import java.util.List;

import notrace.daytongue.entitys.Users;

/**
 * Created by notrace on 2015/9/22.
 */
public class UserList {

    private List<Users>item;

    public List<Users> getItem() {
        return item;
    }

    public void setItem(List<Users> item) {
        this.item = item;
    }
}
