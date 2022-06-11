package com.bowlingGame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class GameTest {

    private final Game game = new Game();
    @Test
    void worstGame(){

        rollZeros(20);

        int score = game.score();

        assertThat(score).isEqualTo(0);
    }

    @Test
    void returnOne_forOnePinDown(){
        roll(1);
        rollZeros(19);

        int score = game.score();

        assertThat(score).isEqualTo(1);
    }

    @Test
    void throwsForTooLargeRoll(){
        assertThatThrownBy(() -> game.roll(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void throwsForNegativeRoll(){
        assertThatThrownBy(() -> game.roll(12))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void spare(){
        roll(5,5,1);
        rollZeros(17);

        int score = game.score();

        assertThat(score).isEqualTo(11 + 1);
    }

    @Test
    void nonSpare(){
        roll(0,5,5,1);
        rollZeros(16);

        int score = game.score();

        assertThat(score).isEqualTo(11);
    }

    @Test
    void twoSpare(){
        roll(5,5,5,5,1);
        rollZeros(15);

        int score = game.score();

        assertThat(score).isEqualTo(15 + 11 + 1);
    }



    private void roll(int ...pinsArray){
        for (int pins : pinsArray) {
            game.roll(pins);
        }
    }

    private void rollZeros(int numberOfRolls) {
        for (int i = 0; i < numberOfRolls; i++) {
            game.roll(0);
        }
    }


}
