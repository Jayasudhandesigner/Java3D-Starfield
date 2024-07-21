package src;
/**
 * Represents a 3D Star field that can be rendered into an image.
 */
public class Stars3D {
    // How much the stars are spread out in 3D space, on average.
    private final float m_spread;    
    // How quickly the stars move towards the camera.
    private final float m_speed;
    // The star positions on the X axis.
    private final float m_starX[];    
    // The star positions on the Y axis.
    private final float m_starY[];    
    // The star positions on the Z axis.
    private final float m_starZ[];
    /**
     * Creates a new 3D star field in a usable state.
     *
     * @param numStars The number of stars in the star field.
     * @param spread   How much the stars spread out, on average.
     * @param speed    How quickly the stars move towards the camera.
     */
    public Stars3D(int numStars, float spread, float speed) {
        // Initialize spread and speed.
        m_spread = spread;
        m_speed = speed;
        // Initialize the star position arrays.
        m_starX = new float[numStars];
        m_starY = new float[numStars];
        m_starZ = new float[numStars];
        // Randomly initialize each star's position within the spread.
        for (int i = 0; i < numStars; i++) {
            m_starX[i] = 2 * ((float) Math.random() - 0.5f) * spread;
            m_starY[i] = 2 * ((float) Math.random() - 0.5f) * spread;
            m_starZ[i] = ((float) Math.random() + 0.00001f) * spread;
        }
    }
    /**
     * Updates and renders the star field.
     *
     * @param target The bitmap where the stars will be rendered.
     * @param delta  The time elapsed since the last update.
     */
    public void UpdateAndRender(Bitmap target, float delta) {
        // Clear the target bitmap with a shade of grey.
        target.Clear((byte) 0x00);
        // Get the width and height of the target bitmap.
        int width = target.GetWidth();
        int height = target.GetHeight();
        // Calculate the half-width and half-height.
        float halfWidth = width / 2.0f;
        float halfHeight = height / 2.0f;
        // Calculate the field of view (FOV) coefficient.
        float tanHalfFOV = (float) Math.tan(Math.toRadians(70.0 / 2.0));
        // Iterate over each star to update and render it.
        for (int i = 0; i < m_starX.length; i++) {
            // Update the Z position of the star based on the speed and delta time.
            m_starZ[i] -= delta * m_speed;
            // If the star is behind the camera, reset its position.
            if (m_starZ[i] <= 0) {
                m_starZ[i] += m_spread;
            }
            // If the star is too far from the camera, reset its position.
            if (m_starZ[i] > m_spread) {
                m_starZ[i] -= m_spread;
            }
            // Project the 3D star position to 2D screen coordinates.
            float x = (m_starX[i] / (m_starZ[i] * tanHalfFOV)) * halfWidth + halfWidth;
            float y = (m_starY[i] / (m_starZ[i] * tanHalfFOV)) * halfHeight + halfHeight;
            // If the star is within the screen boundaries, draw it.
            if (x >= 0 && x < width && y >= 0 && y < height) {
                target.DrawPixel((int) x, (int) y, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff);
            }
        }
    }
}
