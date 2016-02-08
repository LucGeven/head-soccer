package com.geven.headsoccer.handler;

public class Button {

    public static boolean isPressed(double x, double y, double width, double height, double touchX, double touchY){
        if (touchX >= x && touchX <= x + width &&
                touchY >= y && touchY <= y + height){
            return true;
        }
        else{
            return false;
        }
    }
}
