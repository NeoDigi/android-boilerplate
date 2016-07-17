package be.neodigi.androidboilerplate.data.model;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.UserRealmProxy;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = { UserRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { User.class })
public class User extends RealmObject {

    @PrimaryKey
    public int id;
    public String name;
    public String username;
    public String email;
    public String phone;
    public String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}

