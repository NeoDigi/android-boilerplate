package be.neodigi.androidboilerplate.data.remote;

import java.util.List;

import be.neodigi.androidboilerplate.data.model.User;
import retrofit2.http.GET;
import rx.Observable;

public interface RestService {

    @GET("users")
    Observable<List<User>> getUsers();
}
