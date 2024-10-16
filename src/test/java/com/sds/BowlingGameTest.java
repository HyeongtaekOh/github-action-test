package com.sds;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @DisplayName("AC #1")
    @Test
    void testGutterGame() {
        BowlingGame g = new BowlingGame();
        for (int i = 0; i < 20; i++) {
            g.roll(0);
        }
        assertEquals(0, g.score());
    }

    @DisplayName("AC #2")
    @Test
    void testOnePin() {
        BowlingGame g = new BowlingGame();
        g.roll(1);
        for (int i = 0; i < 19; i++) {
            g.roll(0);
        }
        assertEquals(1, g.score());
    }

    @DisplayName("AC #3")
    @Test
    void testAccurateBowling() {
        BowlingGame g = new BowlingGame();
        // pin이 0~10 사이가 아닌 경우 IllegalArgumentException 발생
        assertThrows(IllegalArgumentException.class, () -> g.roll(-1));
        assertThrows(IllegalArgumentException.class, () -> g.roll(11));
    }

    @DisplayName("AC #4")
    @Test
    void testSpare() {
        BowlingGame g = new BowlingGame();
        g.roll(7);
        g.roll(3); // spare
        g.roll(1);
        for (int i = 0; i < 17; i++) {
            g.roll(0);
        }
        assertEquals(12, g.score());
    }

    @DisplayName("AC #5")
    @Test
    void testStrike() {
        BowlingGame g = new BowlingGame();
        g.roll(10); // strike
        g.roll(1);
        g.roll(1);
        for (int i = 0; i < 16; i++) {
            g.roll(0);
        }
        assertEquals(14, g.score());
    }

    @DisplayName("AC #6")
    @Test
    void testAccurateBowling2() {
        BowlingGame g = new BowlingGame();
        g.roll(4);
        assertThrows(IllegalStateException.class, () -> g.roll(7));
    }

    @DisplayName("AC #7")
    @Test
    void testPerfectGame() {
        BowlingGame g = new BowlingGame();
        for (int i = 0; i < 12; i++) {
            g.roll(10);
        }
        assertEquals(300, g.score());
    }

    @DisplayName("AC #8")
    @Test
    void testAccurateBowling3() {
        BowlingGame g = new BowlingGame();
        for (int i = 0; i < 18; i++) {
            g.roll(0);
        }
        g.roll(10);
        g.roll(3);
    }

    @DisplayName("AC #9")
    @Test
    void testRealGame() {
        BowlingGame g = new BowlingGame();
        g.roll(10); // 1st frame
        g.roll(9);  // 2nd frame
        g.roll(1);
        g.roll(7);  // 3rd frame
        g.roll(0);
        g.roll(9);  // 4th frame
        g.roll(1);
        g.roll(10); // 5th frame
        g.roll(10); // 6th frame
        g.roll(8);  // 7th frame
        g.roll(2);
        g.roll(10); // 8th frame
        g.roll(9);  // 9th frame
        g.roll(1);
        g.roll(9);  // 10th frame
        g.roll(1);
        g.roll(7);
        assertEquals(188, g.score());
    }

    @DisplayName("AC #10")
    @Test
    void testAccurateBowling4() {
        BowlingGame g = new BowlingGame();
        g.roll(10); // 1st frame
        g.roll(9);  // 2nd frame
        g.roll(1);
        g.roll(7);  // 3rd frame
        g.roll(0);
        g.roll(9);  // 4th frame
        g.roll(1);
        g.roll(10); // 5th frame
        g.roll(10); // 6th frame
        g.roll(8);  // 7th frame
        g.roll(2);
        g.roll(10); // 8th frame
        g.roll(9);  // 9th frame
        g.roll(1);
        g.roll(9);  // 10th frame
        g.roll(1);
        g.roll(7);
        assertThrows(IllegalStateException.class, () -> g.roll(3));
    }
}