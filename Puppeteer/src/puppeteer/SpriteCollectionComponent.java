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
		setSpritesWithoutRepainting(sprites);
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
			
			// this next line
			// was previously g.drawImage(i, sprite.getX(), sprite.getY(), i.getWidth(), i.getHeight(), this.getBackground(), null)
			// removing this.getBackground() stopped gray boxes from interfering 
			g.drawImage(i, sprite.getX(), sprite.getY(), i.getWidth(), i.getHeight(), null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static class DisplayedSprite
	{
		protected BufferedImage image;
		protected int x, y;
		
		public DisplayedSprite()
		{
			super();
		}
		
		public DisplayedSprite(BufferedImage image, int x, int y)
		{
			this.image = image;
			this.x = x;
			this.y = y;
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
