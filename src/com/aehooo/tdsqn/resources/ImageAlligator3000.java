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
		ImageAlligator3000.atlas.load();
	}

	private static void loadTextures() {
		// imagens

		// de

		// verdade

		//
		// IMAGENS DE VERDADE
		ImageAlligator3000.newAtlas(2048, 1024);
		{
			// background 1400 x 960
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/background.png", 0, 0);
			ImageAlligator3000.mapaTexturas.put(TextureName.MAPA1_BG, texture);
		}
		{
			// menu_superior 319 x 49
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/menu_superior1.png", 0, 960);
			ImageAlligator3000.mapaTexturas.put(TextureName.MENU_SUPERIOR,
					texture);
		}
		{
			// background 1400 x 960
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/menu_lateral_fundo1.png", 1400, 0);
			ImageAlligator3000.mapaTexturas.put(TextureName.BARRA_LATERAL,
					texture);
		}
		{
			// tela perdeu 400 x 200
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/tela_perdeu.png", 1400, 480);
			ImageAlligator3000.mapaTexturas.put(TextureName.LOST, texture);
		}
		{
			// tela ganhou 400 x 200
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/tela_ganhou.png", 1400, 680);
			ImageAlligator3000.mapaTexturas.put(TextureName.WIN, texture);
		}
		{
			// button 80 x 80
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/button.png", 1800, 480);
			ImageAlligator3000.mapaTexturas.put(TextureName.BUTTON, texture);
		}
		{
			TiledTextureRegion animacao = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/healer1.png", 1500, 0, 3, 4);
			ImageAlligator3000.mapaTiledTexturas.put(TextureName.HEALER,
					animacao);
		}
		{
			TiledTextureRegion animacao = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga, "gfx/newSprites/rei.png",
							1665, 0, 3, 4);
			ImageAlligator3000.mapaTiledTexturas.put(TextureName.REI, animacao);
		}
		{
			TiledTextureRegion animacao = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga, "gfx/newSprites/dps.png",
							1830, 0, 3, 4);
			ImageAlligator3000.mapaTiledTexturas.put(TextureName.DPS, animacao);
		}
		{
			TiledTextureRegion animacao = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga, "gfx/newSprites/tank.png",
							1500, 220, 3, 4);
			ImageAlligator3000.mapaTiledTexturas
					.put(TextureName.TANK, animacao);
		}
		{
			TiledTextureRegion animacao = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/support.png", 1665, 220, 3, 4);
			ImageAlligator3000.mapaTiledTexturas.put(TextureName.SUPPORT,
					animacao);
		}
		{
			// group 300x100
			TiledTextureRegion animacao = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga, "gfx/newSprites/group.png",
							1400, 880, 3, 1);
			ImageAlligator3000.mapaTiledTexturas.put(TextureName.GROUP,
					animacao);
		}
		{
			// tiro1 5 x 5
			TextureRegion tiro1 = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/staticSprites/tiro1.png", 1665, 440);
			ImageAlligator3000.mapaTexturas.put(TextureName.TIRO, tiro1);
		}

		//
		// TORRES
		//
		newAtlas(512, 256);
		{
			// TORRE 250 x 250
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/torre_banana.png", 0, 0);
			ImageAlligator3000.mapaTexturas.put(TextureName.TORRE_BANANA,
					texture);
		}
		{
			// TORRE 250 x 250
			TextureRegion texture = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(ImageAlligator3000.atlas,
							ImageAlligator3000.bga,
							"gfx/newSprites/torre_melancia.png", 250, 0);
			ImageAlligator3000.mapaTexturas.put(TextureName.TORRE_MELANCIA,
					texture);
		}

	}

	public static void newAtlas(final int width, final int height) {
		if (ImageAlligator3000.atlas != null) {
			ImageAlligator3000.atlas.load();
		}
		ImageAlligator3000.atlas = new BitmapTextureAtlas(
				ImageAlligator3000.bga.getTextureManager(), width, height);
	}

	public static VertexBufferObjectManager getVertexBufferObjectManager() {
		return ImageAlligator3000.bga.getVertexBufferObjectManager();
	}

	public static TiledTextureRegion getTiledTexture(final TextureName zombie) {
		if (ImageAlligator3000.mapaTiledTexturas.containsKey(zombie)) {
			return ImageAlligator3000.mapaTiledTexturas.get(zombie);
		}
		Log.d("ImageAlligator3000",
				"Mapa de Texturas Tiled não tem a textura animada '" + zombie
						+ "'");
		return null;
	}

	public static TextureRegion getTexture(final TextureName key) {
		if (ImageAlligator3000.mapaTexturas.containsKey(key)) {
			return ImageAlligator3000.mapaTexturas.get(key);
		}
		Log.d("ImageAlligator3000", "Mapa de Texturas não tem a textura '"
				+ key + "'");
		return null;
	}
}