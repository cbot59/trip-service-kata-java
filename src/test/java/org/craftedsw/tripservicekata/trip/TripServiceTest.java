package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TripServiceTest {

    // start testing with short branch
    private static final User ANOTHER_USER = new User();
    private static final User REGISTERED_USER = new User();
    private static final Trip TO_JAKARTA = new Trip();
    private TripService tripService;
    private User loggedUser;

    @Test(expected = UserNotLoggedInException.class)
    public void whenUserNotLoggedInShouldThrowException() {
        tripService = new TestableService();
        tripService.getTripsByUser(loggedUser);
    }

    @Test
    public void whenUserLoggedInAndNotFriendsWithShouldReturnNoTrips() {
        loggedUser = REGISTERED_USER;
        tripService = new TestableService();

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addTrip(TO_JAKARTA);
        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(0));
    }

    @Test
    public void whenUserLoggedInAndFriendsWithShouldReturnTrips() {
        loggedUser = REGISTERED_USER;
        tripService = new TestableService();

        User friend = new User();
        friend.addFriend(ANOTHER_USER);
        friend.addFriend(REGISTERED_USER);
        friend.addTrip(TO_JAKARTA);
        List<Trip> trips = tripService.getTripsByUser(friend);

        assertThat(trips.size(), is(1));
    }

    private class TestableService extends TripService {
        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> getTripsOnDB(User user) {
            return user.trips();
        }
    }
}
