package src;

import java.util.Arrays;

/**
 * Stores a set of pixels in a component-based format.
 * The component-based format stores colors as follows:
 * Byte 0: Alpha
 * Byte 1: Blue 
 * Byte 2: Green
 * Byte 3: Red
 */
public class Bitmap
{
	// The width, in pixels, of the image
	private final int  m_width;
	
	// The height, in pixels, of the image
	private final int  m_height;
	
	// Every pixel component in the image
	private final byte m_components[];

	// Basic getter for width
	public int GetWidth() { return m_width; }
	
	// Basic getter for height
	public int GetHeight() { return m_height; }

	/**
	 * Creates and initializes a Bitmap.
	 *
	 * @param width The width, in pixels, of the image.
	 * @param height The height, in pixels, of the image.
	 */
	public Bitmap(int width, int height)
	{
		// Initialize width and height
		m_width      = width;
		m_height     = height;
		
		// Initialize the components array to store pixel data
		m_components = new byte[m_width * m_height * 4];
	}

	/**
	 * Sets every pixel in the bitmap to a specific shade of grey.
	 */
	public void Clear(byte shade)
	{
		// Fill the components array with the specified shade
		Arrays.fill(m_components, shade);
	}

	/**
	 * Sets the pixel at (x, y) to the color specified by (a,b,g,r).
	 */
	public void DrawPixel(int x, int y, byte a, byte b, byte g, byte r)
	{
		// Calculate the index in the components array for the pixel at (x, y)
		int index = (x + y * m_width) * 4;
		
		// Set the color components for the pixel
		m_components[index    ] = a;
		m_components[index + 1] = b;
		m_components[index + 2] = g;
		m_components[index + 3] = r;
	}

	/**
	 * Copies the Bitmap into a BGR byte array.
	 */
	public void CopyToByteArray(byte[] dest)
	{
		// Copy each pixel's BGR components into the destination array
		for(int i = 0; i < m_width * m_height; i++)
		{
			dest[i * 3    ] = m_components[i * 4 + 1];
			dest[i * 3 + 1] = m_components[i * 4 + 2];
			dest[i * 3 + 2] = m_components[i * 4 + 3];
		}
	}
}
