package notrace.daytongue.entitys;

/**
 * Created by notrace on 2015/9/22.
 */
public class GridEntity {
    private int icon;
    private String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GridEntity(int id,String name)
    {
        this.icon=id;
        this.name=name;
    }
    public GridEntity(){}
}
