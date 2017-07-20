/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import javachains.CoreRun;

/**
 *
 * @author Serafim
 */
public class CompicatedUI {

    private CoreRun runningThing;

    public CompicatedUI(CoreRun r) {
        this.runningThing = r;
    }

    public void run() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        screen.startScreen();
        screen.clear();

        screen.setCharacter(10, 10, new TextCharacter('*'));
        screen.refresh();

        KeyStroke k = screen.readInput();
       //  screen.stopScreen();
        boolean stillrunning = false;
        while (stillrunning) {
            
        }
    }
}
