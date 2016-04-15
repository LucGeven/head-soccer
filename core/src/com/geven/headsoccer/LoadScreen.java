package com.geven.headsoccer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geven.headsoccer.game.HeadSoccer;
import com.geven.headsoccer.handler.AssetLoader;

public class LoadScreen implements Screen {
    private HeadSoccer headsoccer;

    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Texture load;

    public LoadScreen(HeadSoccer headsoccer){
        this.headsoccer = headsoccer;

        cam = new OrthographicCamera();
        cam.setToOrtho(false,1360,2040);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        load = new Texture(Gdx.files.internal("texture/loading.png"));
    }
    @Override
    public void show() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        AssetLoader.load(headsoccer);
                    }
                });
            }
        }).start();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.app.log("ASSETLOADER","HAPPEN");
        batch.begin();
        batch.disableBlending();
        batch.draw(load, 0, 0, 1360, 2040);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        load.dispose();
    }
}
