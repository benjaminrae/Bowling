package com.codurance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Frame {

    List<Integer> currentFrame = new ArrayList<>();
    public void addRoll(int roll) {
       currentFrame.add(roll);
    }

    public boolean isSpare() {
        if (currentFrame.size() != 2) {
            return false;
        }

        return calculateFrameTotal() == 10;
    }

    public boolean isStrike() {
        return currentFrame.size() == 1 && calculateFrameTotal() == 10;
    }

    public int calculateFrameTotal() {
        AtomicInteger total = new AtomicInteger();

        currentFrame.forEach(total::addAndGet);
        return total.intValue();
    }

    public void addBonus(int bonus) {
        currentFrame.add(bonus);
    }

    public boolean isComplete() {
        return isStrike() || currentFrame.size() >= 2;
    }

    public int getFirstRoll() {
        return currentFrame.get(0);
    }
}
