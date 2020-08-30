package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserTest {
    private static final User BOB = new User();

    @Test
    public void user_isFriendsWith() {
        User user = new User();
        user.addFriend(BOB);

        assertThat(user.isFriendsWith(BOB), is(true));
    }

    @Test
    public void user_isNotFriendsWith() {
        User user = new User();

        assertThat(user.isFriendsWith(BOB), is(false));
    }
}
