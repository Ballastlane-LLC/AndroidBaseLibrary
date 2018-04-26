package com.ballastlane.android.texasam.data.repositories;

import com.ballastlane.android.texasam.data.repositories.user.RepositoryUser;

/**
 * @author Daniela Perez danielaperez@kogimobile.com on 5/26/17.
 */

public class Repository {

    public static RepositoryUser getUserRepository() {
        return RepositoryUser.newInstance();
    }


}
