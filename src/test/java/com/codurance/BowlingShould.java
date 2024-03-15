package com.codurance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* - roll
* - frame
* - many frames
* - exceptions - end of game (extra rolls)
* */
public class BowlingShould {

    Bowling bowling = new Bowling();

    @BeforeEach
    void setup(){
        this.bowling = new Bowling();
    }

    @ParameterizedTest
    @ValueSource(ints = {9, 8})
    void scoreSingleRoll(int roll){
        bowling.roll(roll);

        assertEquals(roll, bowling.Score());
    }

    @ParameterizedTest
    @CsvSource({"1,0,1","6,4,10"})
    void scoreSingleFrame(int firstRoll, int secondRoll, int expectedScore){
        rollFrame(new int[]{firstRoll, secondRoll});

        assertEquals(expectedScore,bowling.Score());
    }

    @Test
    void scoreMultipleFramesWithSpare(){
        rollFrame(new int[]{5, 5});
        rollFrame(new int[]{2, 3});

        assertEquals(17,bowling.Score());
    }

    @Test
    void scoreMultipleFramesWithStrike(){
        rollFrame(new int[]{10});
        rollFrame(new int[]{2, 3});

        assertEquals(20,bowling.Score());
    }

    @Test
    void scoreMultipleFramesWithRepeatingStrikes(){
        rollFrame(new int[]{10});
        rollFrame(new int[]{10});
        rollFrame(new int[]{2, 3});

        assertEquals(42,bowling.Score());
    }

    @Test
    void scoreGameWithNoStrikes() {
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});
        rollFrame(new int[]{0, 9});

        assertEquals(90, bowling.Score());
    }

    private void rollFrame(int[] rolls) {
        for (int roll: rolls) {
            bowling.roll(roll);
        }
    }
}


