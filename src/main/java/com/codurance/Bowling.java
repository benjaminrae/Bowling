package com.codurance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Bowling {
    List<Frame> game = new ArrayList<>(10);
    int currentFrameIndex = 0;

    public Bowling() {
        for (int i = 0; i < 10; i++) {
            game.add(new Frame());
        }
    }

    public int Score() {
        AtomicInteger score = new AtomicInteger();

        for (int i = 0; i < game.size(); i++) {
            Frame frame = game.get(i);

            if (frame.isStrike()) {
                Frame nextFrame = game.get(i+1);

                if (nextFrame.isStrike()) {

                }

                frame.addBonus(nextFrame.calculateFrameTotal());
            }

            if (frame.isSpare()) {
                Frame nextFrame = game.get(i+1);

                frame.addBonus(nextFrame.getFirstRoll());
            }

            score.addAndGet(frame.calculateFrameTotal());
        }

        return score.get();
    }

    public void roll(int roll) {
        Frame currentFrame = game.get(currentFrameIndex);

        currentFrame.addRoll(roll);

        if (currentFrame.isComplete()) {
            currentFrameIndex++;
        }


    }
}
