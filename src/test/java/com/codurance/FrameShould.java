package com.codurance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FrameShould {
    @Test
    void isFrameSpare(){
        Frame frame = new Frame();
        frame.addRoll(5);
        frame.addRoll(5);

        assertTrue(frame.isSpare());
    }

    @Test
    void isFrameStrike() {
        Frame frame = new Frame();
        frame.addRoll(10);

        assertTrue(frame.isStrike());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,4,7"
    })
    void calculateFrameTotal(int firstRoll, int secondRoll, int expectedTotal) {
        Frame frame = new Frame();
        frame.addRoll(firstRoll);
        frame.addRoll(secondRoll);

        assertEquals(expectedTotal, frame.calculateFrameTotal());
    }

    @Test
    void calculateTotalWithBonus() {
        Frame frame = new Frame();
        frame.addRoll(5);
        frame.addRoll(5);
        frame.addBonus(2);

        assertEquals(12, frame.calculateFrameTotal());
    }

    @Test
    void beCompleteAfterStrike() {
        Frame frame = new Frame();
        frame.addRoll(10);

        assertTrue(frame.isComplete());
    }

    @Test
    void beCompleteAfter2Rolls() {
        Frame frame = new Frame();
        frame.addRoll(5);
        frame.addRoll(2);

        assertTrue(frame.isComplete());
    }

    @Test
    void getFirstRoll() {
        Frame frame = new Frame();
        frame.addRoll(5);
        frame.addRoll(3);

        assertEquals(5, frame.getFirstRoll());
    }

}