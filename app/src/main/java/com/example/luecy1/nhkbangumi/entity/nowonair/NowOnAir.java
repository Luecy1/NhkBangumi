package com.example.luecy1.nhkbangumi.entity.nowonair;

import com.example.luecy1.nhkbangumi.entity.common.Program;

/**
 * Created by you on 2018/01/08.
 */

public class NowOnAir {
    private Program previous;
    private Program present;
    private Program following;

    public Program getPrevious() {
        return previous;
    }

    public void setPrevious(Program previous) {
        this.previous = previous;
    }

    public Program getPresent() {
        return present;
    }

    public void setPresent(Program present) {
        this.present = present;
    }

    public Program getFollowing() {
        return following;
    }

    public void setFollowing(Program following) {
        this.following = following;
    }
}
