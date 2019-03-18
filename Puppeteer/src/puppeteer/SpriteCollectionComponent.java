package puppeteer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SpriteCollectionComponent
extends Component
{
	private static final long serialVersionUID = 1L;
	
	protected Iterable<DisplayedSprite> sprites;
	
	public SpriteCollectionComponent()
	{
	}
	
	public SpriteCollectionComponent(Iterable<DisplayedSprite> sprites)
	{
		this.sprites = sprites;
	}

	public Iterable<DisplayedSprite> getSprites()
	{
		return sprites;
	}
	
	public void setSpritesWithoutRepainting(Iterable<DisplayedSprite> sprites)
	{
		this.sprites = sprites;
	}
	
	public void setSpritesAndRepaint(Iterable<DisplayedSprite> sprites)
	{
		setSpritesAndRepaint(sprites);
		repaint();
	}
	
	
	
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for (DisplayedSprite sprite : this.sprites)
		{
			BufferedImage i = sprite.getImage();
			g.drawImage(i, sprite.getX(), sprite.getY(), i.getWidth(), i.getHeight(), this.getBackground(), null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static class DisplayedSprite
	{
		protected int x, y;
		protected BufferedImage image;
		
		public DisplayedSprite()
		{
			super();
		}
		
		public DisplayedSprite(int x, int y, BufferedImage image)
		{
			this.x = x;
			this.y = y;
			this.image = image;
		}
		
		public int getX()
		{
			return x;
		}
		
		public void setX(int x)
		{
			this.x = x;
		}
		
		public int getY()
		{
			return y;
		}
		
		public void setY(int y)
		{
			this.y = y;
		}
		
		public BufferedImage getImage()
		{
			return image;
		}
		
		public void setImage(BufferedImage image)
		{
			this.image = image;
		}
	}	
}
