package view;

import controller.Main;

import javax.swing.*;
import java.awt.*;

// process of CPU drawing on screen is slow, so we use GPU with special 'graphic memory' for displaying
// we make 'double buffer' in memory (2 memory: m1 and m2)
// while GPU renders m1 on screen, CPU is buffering m2

public class MyCanvas extends JPanel {

    // want these readily avbl as unknown until render on screen
    public int width;
    public int height;

    public void render() { // will draw all game data on canvas
        width = getSize().width;
        height = getSize().height;

        // off-screen double buffer image
        Image doubleBufferImage = createImage(width, height);
        if (doubleBufferImage == null) {
            System.out.println("Critical error: doubleBufferImage is null");
            System.exit(1);
        }

        // off-screen rendering: CPU writes next screen data onto this double buffer image
        Graphics2D g2OffScreen = (Graphics2D) doubleBufferImage.getGraphics();
        if (g2OffScreen == null) {
            System.out.println("Critical error: g2OffScreen is null");
            System.exit(1);
        }

        // initialize the image buffer (off-screen double buffer image is ready)
        g2OffScreen.setBackground(Color.BLACK);
        g2OffScreen.clearRect(0, 0, width, height);

        // render all game data here!!******************** (buffer is initialized: GPU renders on screen)
        for (var fig: Main.gameData.fixedObject) {
            fig.render(g2OffScreen);
        }
        for (var fig: Main.gameData.friendObject) {
            fig.render(g2OffScreen);
        }
        for (var fig: Main.gameData.enemyObject) {
            fig.render(g2OffScreen);
        }

        // use active rendering to put the buffer image on screen
        Graphics gOnScreen;
        gOnScreen = this.getGraphics(); // this: from current active (canvas/JPanel) screen
        if (gOnScreen != null) {
            // copy offScreen image to onScreen
            gOnScreen.drawImage(doubleBufferImage, 0, 0, null);
        }
        Toolkit.getDefaultToolkit().sync(); // sync the display on some systems
        if (gOnScreen != null) {
            gOnScreen.dispose();
        }

    }
}

