package com.sds;

public class BowlingGame {

    private final int[][] rolls = new int[10][2];
    private int bonus = 0;
    private boolean gameOver = false;
    private int currentFrame = 0;
    private int currentRollIndex = 0;

    public void roll(int pins) {

        if (gameOver) {
            throw new IllegalStateException("game is over");
        }

        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("pins must be between 0 and 10");
        }

        if (currentFrame < 9 && currentRollIndex == 1 && rolls[currentFrame][0] + pins > 10) {
            throw new IllegalStateException("sum of pins in a frame must be between 0 and 10");
        }

        if (currentFrame == 9 && currentRollIndex == 1 && rolls[currentFrame][0] != 10 && rolls[currentFrame][0] + pins > 10) {
            throw new IllegalStateException("sum of 2 rolls in 10th frame must be between 0 and 10");
        }

        if (currentFrame == 10 && rolls[9][1] != 10 && rolls[9][1] + pins > 10) {
            throw new IllegalStateException("sum of 2 rolls in 10th frame must be between 0 and 10");
        }

        if (currentFrame < 10) {
            rolls[currentFrame][currentRollIndex] = pins;
        } else {
            bonus += pins;
            gameOver = true;
            return;
        }

        if (currentFrame < 9) {
            if (pins == 10 || currentRollIndex == 1) {
                currentFrame++;
                currentRollIndex = 0;
            } else {
                currentRollIndex++;
            }
        } else if (currentRollIndex == 0) {
            currentRollIndex++;
        } else if (rolls[currentFrame][0] + rolls[currentFrame][1] < 10) {
            currentFrame++;
            gameOver = true;
        } else {
            currentFrame++;
        }
    }

    public int score() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Frame " + (i + 1) + ": " + rolls[i][0] + ", " + rolls[i][1]);
        }
        int score = 0;
        for (int i = 0; i < currentFrame; i++) {
            score += getRoll(i, 0) + getRoll(i, 1);
            if (i == 9) {
                System.out.println("score: " + score);
                break;
            }
            if (getRoll(i, 0) == 10) {
                score += getRoll(i + 1, 0) + getRoll(i + 1, 1);
                if (getRoll(i + 1, 1) == 0) {
                    score += getRoll(i + 2, 0);
                }
            } else if (getRoll(i, 0) + getRoll(i, 1) == 10) {
                score += getRoll(i + 1, 0);
            }
        }

        return score + bonus;
    }

    private int getRoll(int frame, int rollIndex) {
        if (frame < 0 || frame >= 10) {
            if (frame == 10 && rollIndex == 0) {
                return bonus;
            }
            return 0;
        }
        if (rollIndex < 0 || rollIndex >= 2) {
            return 0;
        }
        return rolls[frame][rollIndex];
    }
}