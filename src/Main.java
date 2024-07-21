package src;
/**
 * The sole purpose of this class is to hold the main method.
 *
 * Any other use should be placed in a separate class
 */
public class Main
{
	public static void main(String[] args)
	{
		// Initialize the display with width 800, height 600, and title "Software Rendering"
		Display display = new Display(800, 600, "Software Rendering");
		// Get the framebuffer from the display
		Bitmap target = display.GetFrameBuffer();
		// Initialize the 3D star field with 4096 stars, spread of 64.0f, and speed of 20.0f
		Stars3D stars = new Stars3D(4096, 64.0f, 20.0f);
		// Record the previous time in nanoseconds
		long previousTime = System.nanoTime();		
		// Main rendering loop
		while(true)
		{
			// Get the current time in nanoseconds
			long currentTime = System.nanoTime();			
			// Calculate the time delta in seconds
			float delta = (float)((currentTime - previousTime)/1000000000.0);			
			// Update the previous time to the current time
			previousTime = currentTime;
			// Update and render the stars on the target bitmap
			stars.UpdateAndRender(target, delta);			
			// Swap the display buffers to show the updated frame
			display.SwapBuffers();
		}
	}
}
