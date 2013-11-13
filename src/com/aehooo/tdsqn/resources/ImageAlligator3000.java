package com.aehooo.tdsqn.resources;

import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

/**
 * ImageAllocator named by Goroba
 * @author haruki
 *
 */
public class ImageAlligator3000 {

	private static BaseGameActivity bga;
	private static BitmapTextureAtlas atlas = null;

	/* Textures declaration */
	public static Map<String, TextureRegion> mapaTexturas = null;



	public static void initialize(final BaseGameActivity bga) {
		ImageAlligator3000.bga = bga;
		ImageAlligator3000.mapaTexturas = new HashMap<String, TextureRegion>();
		ImageAlligator3000.loadTextures();
		// We're calling atlas.load() every time we create a new atlas.
		// But we must load the last one somewhere. And it's here. Because bacon.
		atlas.load();
	}

	private static void loadTextures() {
		newAtlas(2048, 1024);
		{
		TextureRegion mapa1bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, bga, "gfx/staticSprites/map1.png", 0, 0);
		mapaTexturas.put(TextureName.MAPA1_BG, mapa1bg);
		}
//		newAtlas(1024, 1024);

		// house1 350 x 350
//		TextureRegion house1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, bga, "gfx/staticSprites/icon-house1.gif", 0, 0);
//		mapaTexturas.put(ImageNameList.HOUSE1, house1);
	}

	public static void newAtlas(final int width, final int height) {
		if (atlas != null) {
			atlas.load();
		}
		ImageAlligator3000.atlas = new BitmapTextureAtlas(bga.getTextureManager(), width, height);
	}

	public static VertexBufferObjectManager getVertexBufferObjectManager() {
		return bga.getVertexBufferObjectManager();
	}

	public static TextureRegion getTexture(final String key) {
		if (mapaTexturas.containsKey(key)) {
			return mapaTexturas.get(key);
		}
		Log.d("ImageAlligator3000", "Mapa de Texturas não tem a textura '" + key + "'");
		return null;
	}
}