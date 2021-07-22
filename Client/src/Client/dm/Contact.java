package Client.dm;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private long phone_number;

    public Contact(String name, long phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "Model.dm.Contact{" +
                "name='" + name + '\'' +
                ", phone_number=" + phone_number +
                '}';
    }
}
