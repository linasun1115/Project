package byog.Core;

//import byog.TileEngine.TERenderer;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.io.Serializable;
import java.util.Random;

public class Gamemap implements Serializable {
    TETile[][] world;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private long SEED;
    private Random RANDOM;
    private int max;
    int totalfloors = 0;
    int[][] store = new int[2400][2];

    public Gamemap(long seed, TETile[][] w) {
        SEED = seed;
        this.RANDOM = new Random(SEED);
        max = 2000;
        world = w;
        int x1 = RANDOM.nextInt(WIDTH - 10) + 5;
        int y1 = RANDOM.nextInt(HEIGHT - 10) + 5;
        floor(x1, y1);
    }

    public void floor(int x, int y) {
        world[x][y] = Tileset.FLOOR;
        int px = x;
        int py = y;
        int i = max;

        while (i > 0) {
            int choice = RANDOM.nextInt(4);
            if (choice == 0) {
                int k = check(px - 1, py);
                if (k == 2 || k == 1) {
                    world[px - 1][py] = Tileset.FLOOR;
                    px -= 1;
                    i -= 1;
                    store[totalfloors][0] = px;
                    store[totalfloors][1] = py;
                    totalfloors += 1;
                } else if (k == 3) {
                    int point = RANDOM.nextInt(totalfloors + 1);
                    px = store[point][0];
                    py = store[point][1];
                }

            } else if (choice == 1) {
                int k = check(px + 1, py);
                if (k == 2 || k == 1) {
                    world[px + 1][py] = Tileset.FLOOR;
                    px += 1;
                    i -= 1;
                    store[totalfloors][0] = px;
                    store[totalfloors][1] = py;
                    totalfloors += 1;
                } else if (k == 3) {
                    int point = RANDOM.nextInt(totalfloors + 1);
                    px = store[point][0];
                    py = store[point][1];
                }

            } else if (choice == 2) {
                int k = check(px, py + 1);
                if (k == 2 || k == 1) {
                    world[px][py + 1] = Tileset.FLOOR;
                    py += 1;
                    i -= 1;
                    store[totalfloors][0] = px;
                    store[totalfloors][1] = py;
                    totalfloors += 1;
                } else if (k == 3) {
                    int point = RANDOM.nextInt(totalfloors + 1);
                    px = store[point][0];
                    py = store[point][1];
                }
            } else {
                int k = check(px, py - 1);
                if (k == 2 || k == 1) {
                    world[px][py - 1] = Tileset.FLOOR;
                    py -= 1;
                    i -= 1;
                    store[totalfloors][0] = px;
                    store[totalfloors][1] = py;
                    totalfloors += 1;
                } else if (k == 3) {
                    int point = RANDOM.nextInt(totalfloors + 1);
                    px = store[point][0];
                    py = store[point][1];
                }
            }
        }
        wall(totalfloors);
    }


    public void wall(int totalpoint) {
        for (int i = 0; i < totalpoint; i++) {
            int x = store[i][0];
            int y = store[i][1];

            if (world[x + 1][y] != Tileset.FLOOR) {
                world[x + 1][y] = Tileset.WALL;
            }

            if (world[x - 1][y] != Tileset.FLOOR) {
                world[x - 1][y] = Tileset.WALL;
            }

            if (world[x][y - 1] != Tileset.FLOOR) {
                world[x][y - 1] = Tileset.WALL;
            }

            if (world[x][y + 1] != Tileset.FLOOR) {
                world[x][y + 1] = Tileset.WALL;
            }

            if (world[x - 1][y - 1] != Tileset.FLOOR) {
                world[x - 1][y - 1] = Tileset.WALL;
            }

            if (world[x + 1][y + 1] != Tileset.FLOOR) {
                world[x + 1][y + 1] = Tileset.WALL;
            }

            if (world[x + 1][y - 1] != Tileset.FLOOR) {
                world[x + 1][y - 1] = Tileset.WALL;
            }

            if (world[x - 1][y + 1] != Tileset.FLOOR) {
                world[x - 1][y + 1] = Tileset.WALL;
            }
        }


        chara(totalpoint);
    }


    public int check(int x, int y) {
        if (x >= WIDTH - 2 || y >= HEIGHT - 2 || x < 3 || y < 3) {
            return 3;
        }
        if (world[x][y] == Tileset.FLOOR) {
            return 1;
        }

        if ((world[x][y] != Tileset.FLOOR) && (x < WIDTH) && (y < HEIGHT)) {
            return 2;
        }
        return 0;
    }


    public void chara(int floorpoint) {
        int eatsand = RANDOM.nextInt(10) + 5;
        int xp;
        int yp;
        while (eatsand > 0) {
            int point = RANDOM.nextInt(floorpoint + 1);
            xp = store[point][0];
            yp = store[point][1];
            if (world[xp][yp] != Tileset.FLOWER || world[xp][yp] != Tileset.SAND) {
                world[xp][yp] = Tileset.SAND;
                eatsand -= 1;
            }
        }
    }
}
