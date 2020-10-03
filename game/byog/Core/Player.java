package byog.Core;

//import byog.TileEngine.TERenderer;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;


public class Player implements Serializable {
    TETile[][] world;
    Gamemap gm;
    Random RANDOM;
    int px;
    int py;
    int score;
    int move;


    public Player(long seed, Gamemap gamemap, TETile[][] w) {
        this.RANDOM = new Random(seed);
        world = w;
        gm = gamemap;
        int[] x = new int[1];
        int[] y = new int[1];
        putplayer(x, y);
        px = x[0];
        py = y[0];
        score = 0;
    }


    public void putplayer(int[] ppx, int[] ppy) {
        int charaPint = RANDOM.nextInt(gm.totalfloors + 1);
        int cx = gm.store[charaPint][0];
        int cy = gm.store[charaPint][1];
        gm.world[cx][cy] = Tileset.FLOWER;
        ppx[0] = cx;
        ppy[0] = cy;

    }


    public void wup() {
        move = testwall(px, py + 1);
        if (move != 1) {
            world[px][py] = Tileset.FLOOR;
            world[px][py + 1] = Tileset.FLOWER;
            py = py + 1;

        } else if (move == 3) {
            score += 1;
        }
    }


    public void sdown() {
        move = testwall(px, py - 1);
        if (move != 1) {
            world[px][py] = Tileset.FLOOR;
            world[px][py - 1] = Tileset.FLOWER;
            py = py - 1;
        } else if (move == 3) {
            score += 1;
        }
    }

    public void aleft() {
        move = testwall(px - 1, py);
        if (move != 1) {
            world[px][py] = Tileset.FLOOR;
            world[px - 1][py] = Tileset.FLOWER;
            px = px - 1;
        } else if (move == 3) {
            score += 1;
        }
    }

    public void dright() {
        move = testwall(px + 1, py);
        if (move != 1) {
            world[px][py] = Tileset.FLOOR;
            world[px + 1][py] = Tileset.FLOWER;
            px = px + 1;
        } else if (move == 3) {
            score += 1;
        }
    }


    public int testwall(int x, int y) {
        if (world[x][y] == Tileset.WALL) {
            return 1;
        }
        if (world[x][y] == Tileset.SAND) {
            return 3;
        } else {
            return 2;
        }
    }

}
