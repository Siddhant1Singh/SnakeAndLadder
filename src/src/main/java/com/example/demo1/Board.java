package com.example.demo1;

public class Board {
    final int noOfSnakes = 10;
    final int noOfLadders = 10;
    private final int[][] snakes;    // ((H,T), (H,T),...)
    private final int[][] ladders;    // ((T,H), (T,H),...)

    Board() {
        snakes = new int[noOfSnakes][2];
        ladders = new int[noOfLadders][2];
        setSnakes();
        setLadders();
    }

    void setSnakes() {
        int row = 0, col = 0;
        snakes[row][col++] = 24; snakes[row++][col--] = 18;
        snakes[row][col++] = 26; snakes[row++][col--] = 16;
        snakes[row][col++] = 28; snakes[row++][col--] = 14;
        snakes[row][col++] = 55; snakes[row++][col--] = 34;
        snakes[row][col++] = 57; snakes[row++][col--] = 36;
        snakes[row][col++] = 59; snakes[row++][col--] = 38;
        snakes[row][col++] = 91; snakes[row++][col--] = 50;
        snakes[row][col++] = 95; snakes[row++][col--] = 74;
        snakes[row][col++] = 97; snakes[row++][col--] = 76;
        snakes[row][col++] = 99; snakes[row][col] = 78;

    }

    void setLadders() {
        int row = 0, col = 0;
        ladders[row][col++] = 5; ladders[row++][col--] = 17;
        ladders[row][col++] = 7; ladders[row++][col--] = 15;
        ladders[row][col++] = 9; ladders[row++][col--] = 13;
        ladders[row][col++] = 33; ladders[row++][col--] = 47;
        ladders[row][col++] = 35; ladders[row++][col--] = 45;
        ladders[row][col++] = 37; ladders[row++][col--] = 43;
        ladders[row][col++] = 40; ladders[row++][col--] = 81;
        ladders[row][col++] = 64; ladders[row++][col--] = 83;
        ladders[row][col++] = 66; ladders[row++][col--] = 85;
        ladders[row][col++] = 68; ladders[row][col] = 87;
    }

    public int[][] getSnakes() {
        return this.snakes;
    }

    public int[][] getLadders() {
        return this.ladders;
    }

    public int getSnakesTail(int pos) {

        for (int[] snake : snakes) {
            if (pos == snake[0])
                return snake[1];
        }

        return -1;
    }

    public int getLaddersHead(int pos) {

        for (int[] ladder : ladders) {
            if (pos == ladder[0])
                return ladder[1];
        }

        return -1;
    }

    public int[] getCoordinate(int counter) {
        int[] pos = new int[2];
        int end = 10;
        for(int i=9; i>=0; i--, end +=10) {
            if (counter <= end) {
                pos[1] = i;     // row
                break;
            }
        }
        //int temp = counter;
        if (pos[1]%2 == 0) {
            counter %= 10;
            counter = 10-counter;
            pos[0] = counter;
            if (counter >9)
                pos[0] = 0;
        }
        else {
            counter %= 10;
            counter -= 1;
            pos[0] = counter;
            if (counter <0)
                pos[0] = 9;
        }

        return pos;
    }
}

