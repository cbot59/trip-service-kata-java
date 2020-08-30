package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    // start refactoring with longest/deepest branch
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        validateUser();

        return user.isFriendsWith(getLoggedUser())
            ? getTripsOnDB(user)
            : new ArrayList<>();
    }

    private void validateUser() {
        if (null == getLoggedUser()) {
            throw new UserNotLoggedInException();
        }
    }

    protected List<Trip> getTripsOnDB(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
