package be.neodigi.androidboilerplate.ui.main;

import java.util.List;

import be.neodigi.androidboilerplate.data.model.User;
import be.neodigi.androidboilerplate.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showUsers(List<User> users);

    void showUsersEmpty();

    void showError();

}
