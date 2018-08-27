package com.ballastlane.android.texasam.app.ui.init.emailValidation.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.ballastlane.android.texasam.data.repositories.Repository;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 4/20/18.
 */
public class ViewModelEmailValidation extends ViewModel {

    public Single<Response<ResponseBody>> checkEmail(String username) {
       return Repository.getUserRepository().checkEmail(username);
    }
}
