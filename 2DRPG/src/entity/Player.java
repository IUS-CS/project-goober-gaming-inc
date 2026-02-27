import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import java.awt.Color;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 7;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up = ImageIO.read(new File("C:\\Users\\S\\Documents\\Goober\\project-goober-gaming-inc\\2DRPG\\res\\player\\up.png"));
            down = ImageIO.read(new File("C:\\Users\\S\\Documents\\Goober\\project-goober-gaming-inc\\2DRPG\\res\\player\\down.png"));
            left = ImageIO.read(new File("C:\\Users\\S\\Documents\\Goober\\project-goober-gaming-inc\\2DRPG\\res\\player\\left.png"));
            right = ImageIO.read(new File("C:\\Users\\S\\Documents\\Goober\\project-goober-gaming-inc\\2DRPG\\res\\player\\right.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    public void update(){

        if(keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }

        else if(keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        }

    }
    public void draw(Graphics2D g2) {

         BufferedImage image = null;

         switch (direction) {
            case "up":
                image = up;  
                break;
            case "down":
                image = down;  
                break;
            case "left":
                image = left;  
                break;
            case "right":
                image = right;  
                break;
         }

         g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);


    }

}
