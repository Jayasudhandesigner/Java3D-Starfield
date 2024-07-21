package src;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferByte;
import javax.swing.JFrame;
/**
 * Represents a window that can be drawn in using a software renderer.
 */
public class Display extends Canvas
{
	// The window being used for display
	private final JFrame         m_frame;	
	// The bitmap representing the final image to display
	private final Bitmap         m_frameBuffer;	
	// Used to display the framebuffer in the window
	private final BufferedImage  m_displayImage;
	// The pixels of the display image, as an array of byte components
	private final byte[]         m_displayComponents;	
	// The buffers in the Canvas
	private final BufferStrategy m_bufferStrategy;	
	// A graphics object that can draw into the Canvas's buffers
	private final Graphics       m_graphics;
	// Basic getter for frame buffer
	public Bitmap GetFrameBuffer() { return m_frameBuffer; }
	/**
	 * Creates and initializes a new display.
	 *
	 * @param width  How wide the display is, in pixels.
	 * @param height How tall the display is, in pixels.
	 * @param title  The text displayed in the window's title bar.
	 */
	public Display(int width, int height, String title)
	{
		// Set the canvas's preferred, minimum, and maximum size to prevent unintentional resizing.
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		// Creates images used for display.
		m_frameBuffer = new Bitmap(width, height);
		m_displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		m_displayComponents = ((DataBufferByte)m_displayImage.getRaster().getDataBuffer()).getData();
		// Clear the frame buffer and draw a pixel at (100, 100)
		m_frameBuffer.Clear((byte)0x80);
		m_frameBuffer.DrawPixel(100, 100, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFF);
		// Create a JFrame designed specifically to show this Display.
		m_frame = new JFrame();
		m_frame.add(this);
		m_frame.pack();
		m_frame.setResizable(false);
		m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_frame.setLocationRelativeTo(null);
		m_frame.setTitle(title);
		m_frame.setVisible(true);
		// Allocate 1 display buffer, and get access to it via the buffer strategy and a graphics object for drawing into it.
		createBufferStrategy(1);
		m_bufferStrategy = getBufferStrategy();
		m_graphics = m_bufferStrategy.getDrawGraphics();
	}
	/**
	 * Displays in the window.
	 */
	public void SwapBuffers()
	{
		// Copy the frameBuffer into the displayComponents array
		m_frameBuffer.CopyToByteArray(m_displayComponents);		
		// Draw the displayImage on the screen
		m_graphics.drawImage(m_displayImage, 0, 0, m_frameBuffer.GetWidth(), m_frameBuffer.GetHeight(), null);		
		// Show the buffer strategy
		m_bufferStrategy.show();
	}
}