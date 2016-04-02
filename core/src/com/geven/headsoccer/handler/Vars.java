package com.geven.headsoccer.handler;

public class Vars {
    public static final float PPM = 100;
    public static final float TIMESTEP = 1 / 60f;
    public static final int VELOCITYITERATIONS = 8;
    public static final int POSITIONITERATIONS = 3;

    //public static final int JUMPFORCE = -85000000;  //-75
    public static final int JUMPFORCE = -90000000;

    //Collision filtering
    public static final short COLLISION = 1;
    public static final short SPRITE_HOME = 2;
    public static final short SPRITE_OUT  = 4;
}
