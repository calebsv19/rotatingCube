import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MultiKeyListener implements KeyListener {
    // Set of currently pressed keys
    private final Set<Integer> pressedKeys = new HashSet<>();
    private Handler handler;

    private static final int ADD = 10;
    private static final double ROTATION = .02;

    public MultiKeyListener(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        Point offset = new Point();
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();){
                switch (it.next()) {
                    case KeyEvent.VK_W:
                        handler.xRotate(ROTATION);
                        break;
                    case KeyEvent.VK_UP:
                        handler.addY(-ADD);
                        break;
                    case KeyEvent.VK_A:
                        handler.yRotate(ROTATION);
                        break;
                    case KeyEvent.VK_LEFT:
                        handler.addX(-ADD);
                        break;
                    case KeyEvent.VK_S:
                        handler.xRotate(-ROTATION);
                        break;
                    case KeyEvent.VK_DOWN:
                        handler.addY(ADD);
                        break;
                    case KeyEvent.VK_D:
                        handler.yRotate(-ROTATION);
                        break;
                    case KeyEvent.VK_RIGHT:
                        handler.addY(ADD);
                        break;
                }
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
