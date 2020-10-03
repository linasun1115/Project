package byog.Core;

//import byog.TileEngine.TERenderer;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Game implements Serializable {
    //    TERenderer ter = new TERenderer();
    private TETile[][] save;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    Player play;


    /**
     *
     */
    public void playWithKeyboard() {
        TETile[][] finalWorldFrame = null;
        long seed = 0;
        String input = "";
        String nextinput = "";
        String showimput = "";
        Game sw;
        start();
        while (!StdDraw.hasNextKeyTyped()) {
            int bab = 1;
        }
        input += StdDraw.nextKeyTyped();
        if (input.equals("N") || input.equals("n")) {
            StdDraw.clear(Color.black);
            StdDraw.text(20, 20, "Hi, please enter your seed, press s to start Game");
            StdDraw.show();
            StdDraw.clear(Color.black);
            while (!nextinput.equals("s") && !nextinput.equals("S")) {
                while (!StdDraw.hasNextKeyTyped()) {
                    int riri = 1;
                }
                nextinput = StdDraw.nextKeyTyped() + "";
                showimput += nextinput;
                StdDraw.clear(Color.black);
                StdDraw.text(20, 20, showimput);
                StdDraw.show();
                input += nextinput;
            }
            String[] keyinput = input.split("");
            if (checkinput(keyinput) == -1) {
                StdDraw.clear(Color.black);
                StdDraw.text(20, 20, "Wrong input");
                StdDraw.show();
            } else {
                seed = checkinput(keyinput);
                gamebegin(seed);
            }
        }
        if (input.equals("l") || input.equals("L")) {
            sw = (Game) loadWorld();
            while (true) {
                while (!StdDraw.hasNextKeyTyped()) {
                    int popo = 1;
                }
                while (StdDraw.hasNextKeyTyped()) {
                    char key = StdDraw.nextKeyTyped();
                    if (key == 'w' || key == 'W') {
                        sw.play.wup();
                    }
                    if (key == 's' || key == 'S') {
                        sw.play.sdown();
                    }
                    if (key == 'a' || key == 'A') {
                        sw.play.aleft();
                    }
                    if (key == 'd' || key == 'D') {
                        sw.play.dright();
                    }
                    if (key == ':') {
                        while (!StdDraw.hasNextKeyTyped()) {
                            int pk = 1;
                        }
                        String saveexit = StdDraw.nextKeyTyped() + "";
                        if (saveexit.equals("Q") || saveexit.equals("q")) {
                            saveWorld(this);
                            System.exit(0);
                        }
                    }
                    save = finalWorldFrame;
                }
            }
        }
    }

    private Game loadWorld() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        Game x = null;
        try {
            fis = new FileInputStream("try1.txt");
            in = new ObjectInputStream(fis);
            x = (Game) in.readObject();
//            ter.renderFrame(x.save);
            in.close();
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return x;
    }


    private void saveWorld(Game sw) {
        String filename = "try1.txt";

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(sw);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }


    public void start() {
        StdDraw.setCanvasSize(40 * 16, 40 * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, 40);
        StdDraw.setYscale(0, 40);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.white);
        StdDraw.text(20, 30, "CS61B:  THE  GAME");

        font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(20, 20, "New Game (N)");
        StdDraw.text(20, 15, "Load Game (L)");
        StdDraw.text(20, 10, "Quit (Q)");

        StdDraw.show();
    }


    private void gamebegin(long seed) {
        TETile[][] finalWorldFrame = null;
//            ter = new TERenderer();
//            ter.initialize(WIDTH, HEIGHT);
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }
        Gamemap g = new Gamemap(seed, finalWorldFrame);
        play = new Player(seed, g, finalWorldFrame);
//            ter.renderFrame(finalWorldFrame);
        while (true) {
            while (!StdDraw.hasNextKeyTyped()) {
                int ha = 2;
            }
            while (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'w' || key == 'W') {
                    play.wup();
//                        ter.renderFrame(finalWorldFrame);
                }
                if (key == 's' || key == 'S') {
                    play.sdown();
//                        ter.renderFrame(finalWorldFrame);
                }
                if (key == 'a' || key == 'A') {
                    play.aleft();
//                        ter.renderFrame(finalWorldFrame);
                }
                if (key == 'd' || key == 'D') {
                    play.dright();
//                        ter.renderFrame(finalWorldFrame);
                }

                if (key == ':') {
                    while (!StdDraw.hasNextKeyTyped()) {
                        int haha = 1;
                    }
                    String saveexit = StdDraw.nextKeyTyped() + "";
                    if (saveexit.equals("Q") || saveexit.equals("q")) {
                        saveWorld(this);
                        System.exit(0);
                    }

                }
                save = finalWorldFrame;
            }
        }
    }


    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @return the 2D TETile[][] representing the state of the world
     * @paraminput the input string to feed to your program
     */


    public TETile[][] playWithInputString(String input) {
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        String[] userinput = input.split("");
        TETile[][] finalWorldFrame = null;
        finalWorldFrame = new TETile[WIDTH][HEIGHT];
        long seed = 0;
        Game sw;


        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        if (userinput[0].equals("l") || userinput[0].equals("L")) {

            sw = (Game) loadWorld();
            save = stringmove(userinput, sw.play.gm.world, sw.play, 1);
            finalWorldFrame = save;
//            ter.renderFrame(finalWorldFrame);
            return finalWorldFrame;
        }


        if (checkinput(userinput) == -1) {
            System.out.print("Wrong input");
        } else {
//                ter.initialize(WIDTH, HEIGHT);
            seed = checkinput(userinput);
            Gamemap g = new Gamemap(seed, finalWorldFrame);
            String s = seed + "";
            int i = s.length() + 2;
            play = new Player(seed, g, finalWorldFrame);
            finalWorldFrame = stringmove(userinput, finalWorldFrame, play, i);
//                play = new Player(seed, g, finalWorldFrame);
//                gamebegin(seed);
//                ter.renderFrame(finalWorldFrame);
        }


        if (savequit(userinput)) {
            saveWorld(this);
        }
//            ter.renderFrame(finalWorldFrame);


        return finalWorldFrame;
    }


    public long checkinput(String[] input) {
        long seednumber = 0;
        int checknumber = 0;
        int haves = 0;

        for (int p = 0; p < input.length; p++) {
            if (input[p].equals("s") || input[p].equals("S")) {
                haves = p;
                break;
            }
        }

        if (haves < 2) {
            return -1;
        }

        int powtime = haves - 1;
        if (input.length > 2) {
            if (input[0].equals("N") || input[0].equals("n")) {
                for (int k = 1; k < haves; k++) {
                    if (isnumber(input[k])) {
                        checknumber += 1;
                    } else {
                        return -1;
                    }
                }
                if (checknumber == powtime) {
                    for (int i = 1; i < haves; i++) {
                        seednumber += Integer.parseInt(input[i])
                                * ((int) Math.pow(10, powtime - 1));
                        powtime -= 1;
                    }
                    return seednumber;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }


    public TETile[][] stringmove(String[] control, TETile[][] world, Player P, int sp) {
        for (int i = sp; i < control.length; i++) {
            if (i < control.length - 1) {
                if (control[i].equals(":") && (control[i + 1].equals("q")
                        || control[i + 1].equals("Q"))) {
                    saveWorld(this);
                }
            }
            switch (control[i]) {
                case ("a"):
                    P.aleft();
                    break;
                case ("A"):
                    P.aleft();
                    break;
                case ("w"):
                    P.wup();
                    break;
                case ("W"):
                    P.wup();
                    break;
                case ("d"):
                    P.dright();
                    break;
                case ("D"):
                    P.dright();
                    break;
                case ("s"):
                    P.sdown();
                    break;
                case ("S"):
                    P.sdown();
                    break;
                default:
                    break;


            }
            save = world;


        }
        return world;

    }


    private boolean savequit(String[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if ((input[i].equals(":") && input[i + 1].equals("Q"))
                    || (input[i].equals(":") && input[i + 1].equals("q"))) {
                return true;
            }
        }
        return false;
    }


    public boolean isnumber(String input) {
        if ((!input.equals("0")) && (!input.equals("1"))
                && (!input.equals("2")) && (!input.equals("3"))
                && (!input.equals("4")) && (!input.equals("5"))
                && (!input.equals("6")) && (!input.equals("7"))
                && (!input.equals("8")) && (!input.equals("9"))) {
            return false;
        }
        return true;
    }

//    public static void main (String[]args){
//        Game g = new Game();
//           String[] a = new String[]{"n", "5", "2", "0", "s", "8"};
//           g.playWithKeyboard();
//        g.playWithInputString("lwadas");
//    }


}
