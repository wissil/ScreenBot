package com.util.ai.screenbot.input.sikuli;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class TestSikuli {

	@Test
    public void test() {
        Screen s = new Screen();
        try{
                s.click("sikuli/g1.png");
//                s.wait("sikuli/g1.png");
//                s.click();
                s.write("hello world#ENTER.");
        }
        catch(FindFailed e){
                e.printStackTrace();
        }
}
}
