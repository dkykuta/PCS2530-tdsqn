package com.aehooo.tdsqn.resources;

import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

/**
 * ImageAllocator named by Goroba
 * 
 * @author haruki
 */
public class ImageAlligator3000 {

	private static BaseGameActivity bga;
	private static BitmapTextureAtlas atlas = null;

	/* Textures declaration */
	public static Map<TextureName, TextureRegion> mapaTexturas = null;
	public static Map<TextureName, TiledTextureRegion> mapaTiledTexturas = null;

	public static void initialize(final BaseGameActivity bga) {
		ImageAlligator3000.bga = bga;
		ImageAlligator3000.mapaTexturas = new HashMap<TextureName, TextureRegion>();
		ImageAlligator3000.mapaTiledTexturas = new HashMap<TextureName, TiledTextureRegion>();
		ImageAlligator3000.loadTextures();
		// We're calling atlas.load() every time we create a new atlas.
		// But we must load the last one somewhere. And it's here. Because
		// bacon.
		atlas.load();
	}

	private static void loadTextures() {
		newAtlas(2048, 1024);
		{
			// map1 1400 x 960
			TextureRegion mapa1bg = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(atlas, bga, "gfx/staticSprites/map1.png",
							0, 0);
			mapaTexturas.put(TextureName.MAPA1_BG, mapa1bg);
		}
		{
			// barra_lateral 100 x 480
			TextureRegion barraLateral = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(atlas, bga,
							"gfx/staticSprites/barra_lateral.png", 1400, 0);
			mapaTexturas.put(TextureName.BARRA_LATERAL, barraLateral);
		}
		// newAtlas(1024, 1024);

		{
			TiledTextureRegion zombie = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(atlas, bga,
							"gfx/staticSprites/zombie.png", 1500, 0, 3, 4);
			mapaTiledTexturas.put(TextureName.ZOMBIE, zombie);
		}
		newAtlas(1024, 1024);
		{
			// barra_lateral 100 x 480
			TiledTextureRegion group = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(atlas, bga,
							"gfx/staticSprites/group.png", 0, 0, 3, 1);
			mapaTiledTexturas.put(TextureName.GROUP, group);
		}

		// house1 350 x 350
		// TextureRegion house1 =
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas, bga,
		// "gfx/staticSprites/icon-house1.gif", 0, 0);
		// mapaTexturas.put(ImageNameList.HOUSE1, house1);
	}

	public static void newAtlas(final int width, final int height) {
		if (atlas != null) {
			atlas.load();
		}
		ImageAlligator3000.atlas = new BitmapTextureAtlas(
				bga.getTextureManager(), width, height);
	}

	public static VertexBufferObjectManager getVertexBufferObjectManager() {
		return bga.getVertexBufferObjectManager();
	}

	public static TiledTextureRegion getTiledTexture(final TextureName zombie) {
		if (mapaTiledTexturas.containsKey(zombie)) {
			return mapaTiledTexturas.get(zombie);
		}
		Log.d("ImageAlligator3000",
				"Mapa de Texturas Tiled não tem a textura animada '" + zombie
						+ "'");
		return null;
	}

	public static TextureRegion getTexture(final TextureName key) {
		if (mapaTexturas.containsKey(key)) {
			return mapaTexturas.get(key);
		}
		Log.d("ImageAlligator3000", "Mapa de Texturas não tem a textura '"
				+ key + "'");
		return null;
	}
}