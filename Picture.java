import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }
    
    public void myCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        this.copy(flower1, 0, 100);
        int mirror = 98;
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        Pixel[][] pixels = this.getPixels2D();   
        
        for (int i = 0; i < 98; i++)
        {
          for (int j = 0; j < 88; j++)
          {
              rightPixel = pixels[i][j];      
              leftPixel = pixels[mirror - i + mirror][j];
              leftPixel.setColor(rightPixel.getColor());
          }
        }
        
        //sets all the green pixels of the water picture to 0
        Picture flower = new Picture("flower2.jpg");
        flower.zeroGreen();
        this.copy(flower, 300, 350);
        
        //inverts all the pixels
        Picture flower2 = new Picture("flower2.jpg");
        flower2.negate();
        this.copy(flower2, 100, 300);

        //mirrors the picture vertically
        Picture flower3 = new Picture("flower2.jpg");
        flower3.mirrorVerticalRightToLeft();
        this.copy(flower3, 200, 200);
    }
    
    public void newCopy(Picture fromPicture, int firstRow, int lastRow, int firstCol, int lastCol)
    {
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPicture.getPixels2D();
        Pixel fromPixel = null;
        Pixel toPixel = null;
        
        //Two different for - loops?
        for (int i = 0, j = firstRow; i < fromPixels.length && j < lastRow; i++, j++)
        {
            for (int k = 0, l = firstCol; k < fromPixels[0].length && l < lastCol; k++, l++)
            {
                fromPixel = fromPixels[i][k];
                toPixel = toPixels[j][l];
                toPixel.setColor(fromPixel.getColor());
            }
          } 
    }
    public void mirrorGull()
    {
        Pixel[][] pixels = this.getPixels2D();
        int pictureMirrored = 345;
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        
        for(int i = 235; i < 323; i++)
        {
            for(int j = 238; j < pictureMirrored; j++)
            {
                rightPixel = pixels[i][j];
                leftPixel = pixels[i][pictureMirrored - j + 115];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }
    
    public void mirrorArms()
    {
        Pixel[][] pixels = this.getPixels2D();
        int pictureMirrored = 193;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
    
        for (int i = 158; i < pictureMirrored; i++)
        {
          for (int j = 103; j < 170; j++)
          {
              topPixel = pixels[i][j];      
              bottomPixel = pixels[pictureMirrored - i + pictureMirrored][j];
              bottomPixel.setColor(topPixel.getColor());
          }
        }
        
        int pictureMirroredAgain = 198;
        Pixel otherTopPixel = null;
        Pixel otherBottomPixel = null;
        
        for (int i = 171; i < pictureMirroredAgain; i++)
        {
          for (int j = 239; j < 294; j++)
          {
              otherTopPixel = pixels[i][j];      
              otherBottomPixel = pixels[pictureMirroredAgain - i + pictureMirroredAgain][j];
              otherBottomPixel.setColor(otherTopPixel.getColor());
          }
        }
    }
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topRightPixel = null;
        Pixel bottomLeftPixel = null;
        int length;
        
        if (pixels.length < pixels[0].length) 
        { 
            length = pixels.length; 
        } else 
        {
            length = pixels[0].length;
        }
        
        for (int i = 0; i < length; i++)
        {
            for (int j = i; j < length; j++)
            {
                topRightPixel = pixels[i][j];
                bottomLeftPixel = pixels[j][i];
                bottomLeftPixel.setColor(topRightPixel.getColor());
            }
        }
    }
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[0].length; j++)
            {
                topPixel = pixels[i][j];
                bottomPixel = pixels[pixels[0].length - 1 - i][j];
                topPixel.setColor(topPixel.getColor());
            }
        }
    }
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[0].length / 2; j++)
            {
                topPixel = pixels[i][j];
                bottomPixel = pixels[pixels[0].length - 1 - i][j];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }
    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[0].length / 2; j++)
            {
                rightPixel = pixels[i][j];
                leftPixel = pixels[i][pixels[0].length - 1 - j];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }
    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[i].length; j++)
            {
                int average = (pixels[i][j].getRed() + pixels[i][j].getGreen() + pixels[i][j].getBlue()) / 3;
                pixels[i][j].setBlue(average);
                pixels[i][j].setRed(average);
                pixels[i][j].setGreen(average);
            }
        }
    }
    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[i].length; j++)
            {
                pixels[i][j].setRed(250 - pixels[i][j].getRed());
                pixels[i][j].setBlue(250 - pixels[i][j].getBlue());
                pixels[i][j].setGreen(250 - pixels[i][j].getGreen());
            }
        }
    }
    
    /** Method to set the blue to 0 */
    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for(int i = 0; i < pixels.length; i++)
        {
            for(int j = 0; j < pixels[i].length; j++)
            {
                pixels[i][j].setBlue(255);
            }
        }
    }
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    public void zeroRed()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
            }
        }
    }

    public void zeroGreen()
    {
        Pixel[][] pixels = this.getPixels2D();
        
        for (int i = 0; i < pixels.length; i++)
        {
            for (int j = 0; j < pixels[i].length; j++)
            {
                pixels[i][j].setGreen(0);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {

    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();
        for (int row = 27; row < 97; row++)
        {
            for (int col = 13; col < mirrorPoint; col++)
                {
                    leftPixel = pixels[row][col];
                    rightPixel = pixels[row]
                    [mirrorPoint - col + mirrorPoint];
                    rightPixel.setColor(leftPixel.getColor());
                    count++;
                }
        }
        
        System.out.println(count);
    }
    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic,int startRow, int startCol)
    {

    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {

    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
     {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length - 1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
        
                if (leftPixel.colorDistance(rightColor) > edgeDist)
                {
                    leftPixel.setColor(Color.BLACK);
                }
                else
                {
                    leftPixel.setColor(Color.WHITE);
                }
            }
        }
        
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Color bottomColor = null;
        
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[0].length - 1; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[row + 1 - row][col];
                bottomColor = bottomPixel.getColor();
        
                if (topPixel.colorDistance(bottomColor) > edgeDist)
                {
                    topPixel.setColor(Color.BLACK);
                }
                else
                {
                    topPixel.setColor(Color.WHITE);
                }
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture pix = new Picture("water-life-crop.jpg");
        pix.explore();
        pix.zeroBlue();
        pix.explore();
    }

} // this } is the end of class Picture, put all new methods before this
