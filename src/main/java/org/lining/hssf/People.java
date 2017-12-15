package org.lining.hssf;

/**
 * description:
 * date 2017/12/12
 *
 * @author lining1
 * @version 1.0.0
 */
public class People {

    private String id;
    private String name;

    public People() {}

    public People(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "姓名 : " + name + " , 工号 : " + id;
    }
}
