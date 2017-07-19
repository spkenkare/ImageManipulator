package assignment;
/**
 *
 * CS314H Programming Assignment 1 - Java image processing
 *
 * Included is the Invert effect from the assignment.  Use this as an
 * example when writing the rest of your transformations.  For
 * convenience, you should place all of your transformations in this file.
 *
 * You can compile everything that is needed with
 * javac -d bin src/assignment/*.java
 *
 * You can run the program with
 * java -cp bin assignment.JIP
 *
 * Please note that the above commands assume that you are in the prog1
 * directory.
 */

import java.util.ArrayList;
import java.util.Collections;


class Invert extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x] = ~pixels[y][x];
            }
        }
        return pixels;
    }
}


class Dummy extends ImageEffect {

    public Dummy() {
        super();
        params = new ArrayList<ImageEffectParam>();
        params.add(new ImageEffectParam("ParamName",
                                           "Description of param.",
                                           10, 0, 1000));
    }

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
       
    	
        return pixels;
        
    }
}

class NoRed extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x]=makePixel(0, getGreen(pixels[y][x]), getBlue(pixels[y][x]));
            }
        }
        return pixels;
    }
}

class NoGreen extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x]=makePixel(getRed(pixels[y][x]), 0, getBlue(pixels[y][x]));
            }
        }
        return pixels;
    }
}

class NoBlue extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x]=makePixel(getRed(pixels[y][x]), getGreen(pixels[y][x]), 0);
            }
        }
        return pixels;
    }
}

class RedOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x]=makePixel(getRed(pixels[y][x]), 0, 0);
            }
        }
        return pixels;
    }
}

class GreenOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x]=makePixel(0, getGreen(pixels[y][x]), 0);
            }
        }
        return pixels;
    }
}

class BlueOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	pixels[y][x]=makePixel(0, 0, getBlue(pixels[y][x]));
            }
        }
        return pixels;
    }
}

class BlackAndWhite extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	int r = getRed(pixels[y][x]);
            	int g = getGreen(pixels[y][x]);
            	int b = getBlue(pixels[y][x]);
            	int avg = (r+g+b)/3;
                pixels[y][x]=makePixel(avg, avg, avg); 
                //grayscale can be done with many weights for the R, G, and B values 
                //I chose to have each have an equal weight
            }
        }
        return pixels;
    }
}

class VerticalReflect extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width/2; x++) {
            for (int y = 0; y < height; y++) {
            	int temp = pixels[y][x];
            	pixels[y][x]=pixels[y][width-1-x];
            	pixels[y][width-1-x]=temp;
            	//swapping the column values
            }
        }
        return pixels;
    }
}

class HorizontalReflect extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height/2; y++) {
            	//swapping the row values
            	int temp = pixels[y][x];
            	pixels[y][x]=pixels[height-1-y][x];
            	pixels[height-1-y][x]=temp;
            }
        }
        return pixels;
    }
}

class Grow extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height*2][width*2];

        for (int x = 0; x < width*2-1; x++) {
            for (int y = 0; y < height*2-1; y++) {
            	array[y][x]=pixels[y/2][x/2];
            	array[y+1][x]=pixels[y/2][x/2];
            	array[y][x+1]=pixels[y/2][x/2];
            	array[y+1][x+1]=pixels[y/2][x/2];
            }
        }
        return array;
    }
}

class Shrink extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;

        int[][] array = new int[height/2][width/2];

        for (int x = 0; x < width-1; x+=2) {
            for (int y = 0; y < height-1; y+=2) {
            	int r1 = getRed(pixels[y][x]);
            	int r2 = getRed(pixels[y][x+1]);
            	int r3 = getRed(pixels[y+1][x]);
            	int r4 = getRed(pixels[y+1][x+1]);
            	int redAvg = (r1+r2+r3+r4)/4;
            	
            	int g1 = getGreen(pixels[y][x]);
            	int g2 = getGreen(pixels[y][x+1]);
            	int g3 = getGreen(pixels[y+1][x]);
            	int g4 = getGreen(pixels[y+1][x+1]);
            	int greenAvg = (g1+g2+g3+g4)/4;
            	
            	int b1 = getBlue(pixels[y][x]);
            	int b2 = getBlue(pixels[y][x+1]);
            	int b3 = getBlue(pixels[y+1][x]);
            	int b4 = getBlue(pixels[y+1][x+1]);
            	int blueAvg = (b1+b2+b3+b4)/4;
            	array[y/2][x/2]=makePixel(redAvg, greenAvg, blueAvg);
            }
        }
        return array;
        
    }
}

class Threshold extends ImageEffect {

    public Threshold() {
        super();
        params = new ArrayList<ImageEffectParam>();
        params.add(new ImageEffectParam("ParamName",
                                           "Description of param.",
                                           127, 0, 255));
    }

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        
    	int threshold = params.get(0).getValue();
    	int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	if(getRed(pixels[y][x])<=threshold&&getGreen(pixels[y][x])<=threshold&&getBlue(pixels[y][x])<=threshold)
            		pixels[y][x]=makePixel(0, 0, 0);
            	else
            		pixels[y][x]=makePixel(255, 255, 255);
            }
        }
        return pixels;
        
    }
}

class Smooth extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	
            	int redSum=0;
            	int greenSum=0;
            	int blueSum=0;
            	int numEdges=0; //keeps track of number of valid neighbors
            	for(int i=-1; i<=1; i++)
            	{
            		for(int j=-1; j<=1; j++)
            		{
            			if(isValid(y+i, x+j, width, height)) //calls method to avoid exception error
            			{
            				numEdges++;
            				redSum+=getRed(pixels[y+i][x+j]);
            				greenSum+=getGreen(pixels[y+i][x+j]);
            				blueSum+=getBlue(pixels[y+i][x+j]);
            			}
            		}
            	}
            	
            	int redAvg = redSum/numEdges;
            	int greenAvg = greenSum/numEdges;
            	int blueAvg = blueSum/numEdges;
            	array[y][x]=makePixel(redAvg, greenAvg, blueAvg);
            	
            }
        }
        
        return array;
    }
}

class Erode extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];
        //created new array so that pixels will be preserved so early pixel changes won't affect later ones
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	
            	int pixelMin=Integer.MAX_VALUE; 
            	
            	for(int i=-1; i<=1; i++)
            	{
            		for(int j=-1; j<=1; j++)
            		{
            			if(isValid(y+i, x+j, width, height))
            			{
            			
            				if(pixelMin>pixels[y+i][x+j])
            					pixelMin = pixels[y+i][x+j];
            			}
            		}
            	}
            	
            	
            	array[y][x]=pixelMin;
     
            }
        }
        return array;
    }
}

class Dilate extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	
            	int pixelMax=Integer.MIN_VALUE;
            	
            	for(int i=-1; i<=1; i++)
            	{
            		for(int j=-1; j<=1; j++)
            		{
            			if(isValid(y+i, x+j, width, height))
            			{
            				if(pixelMax<pixels[y+i][x+j])
            					pixelMax = pixels[y+i][x+j];
            			}
            		}
            	}
            	
            	
            	array[y][x]=pixelMax;
            	
            }
        }
        return array;
    }
}

class DeNoise extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];

        for (int x = 1; x < width-1; x++) {
            for (int y = 1; y < height-1; y++) {
            	
            	ArrayList<Integer> pixelList = new ArrayList<Integer>();
            	for(int i=-1; i<=1; i++)
            	{
            		for(int j=-1; j<=1; j++)
            		{
            			if(isValid(y+i, x+j, width, height))
            			{
            				pixelList.add(pixels[y+i][x+j]);		
            			}
            		}
            	}
            	Collections.sort(pixelList); //to find median, need a sorted list
            	int medPixel = 0;
            	if(pixelList.size()%2==1)
            		medPixel = pixelList.get(pixelList.size()/2);
            	else //average the middle values
            		medPixel = (pixelList.get(pixelList.size()/2)+pixelList.get(pixelList.size()/2-1))/2;
            	array[y][x]=medPixel;
            	
            }
        }
        return array;
    }
}

class Thick extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	
            	array[y][x]=pixels[y][x];
            	//if the pixel is relatively dark
            	if(getRed(pixels[y][x])<=75&&getGreen(pixels[y][x])<=75&&getBlue(pixels[y][x])<=75)
            	{
            		
            		for(int i=-2; i<=2; i++)            	
            		{
            			for(int j=-2; j<=2; j++)
            			{
            				if(isValid(y+i, x+j, width, height))
            				{
            					//change surrounding neighborhood to same value to make dark areas bigger
            					array[y+i][x+j]=array[y][x];
            				}
            				
            			}
            		}
            	}
            	
            	
            }
        }
        return array;
    }
}



class Speckle extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	            	
            		int darkCount=0;
            		
            		for(int i=-1; i<=1; i++)
            		{
            			for(int j=-1; j<=1; j++)
            			{
            				if(isValid(y+i, x+j, width, height)&&pixels[y+i][x+j]<pixels[y][x])
            				{
            					darkCount++;
            				}
            				
            			}
            		}
            		if(darkCount>=3)
            		{
            			
            			int r = getRed(pixels[y][x])+25;
            			int g = getGreen(pixels[y][x])+25;
            			int b = getBlue(pixels[y][x])+25;
            			if(r>255)
            				r=255;
            			if(g>255)
            				g=255;
            			if(b>255)
            				b=255;
            			array[y][x]=makePixel(r,g,b);
            			
            		}
            		
            	
            	
            }
        }
        return array;
    }
}

class GrayscaleRipple extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];
        
        for (int x = 1; x < width-1; x++) {
            for (int y = 1; y < height-1; y++) {
            	            	
            		array[y][x]=pixels[y][x];
            		//if there is more than a difference of 30
            		if((pixels[y+1][x]-pixels[y-1][x])>30)
            			array[y][x]-=30;
            		if(array[y][x]<=0)
            			array[y][x]=-1;
            		if((pixels[y+1][x]-pixels[y-1][x])<30)
            			array[y][x]+=30;
            		if(array[y][x]>255)
            			array[y][x]=255;
            	
            	
            }
        }
        return array;
    }
}



class Ghost extends ImageEffect {
	
	public boolean isValid(int y, int x, int width, int height)
	{
		if(y>=0&&x>=0&&y<height&&x<width)
			return true;
		return false;
	}
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
    	int width = pixels[0].length;
        int height = pixels.length;
        
        int[][] array = new int[height][width];
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            
            	if(getRed(pixels[y][x])>=200&&getGreen(pixels[y][x])>=200&&getBlue(pixels[y][x])>=200) //light pixel
            	{	
            		
            		int darkCount=0;
            		for(int i=-1; i<=1; i++)
            		{
            			for(int j=-1; j<=1; j++)
            			{
            				if(isValid(y+i, x+j, width, height)&&getRed(pixels[y+i][x+j])<=getRed(pixels[y][x])&&getGreen(pixels[y+i][x+j])<=getGreen(pixels[y][x])&&getBlue(pixels[y+i][x+j])<=getBlue(pixels[y][x]))
            				{
            					
            					darkCount++;
            				}

            			}
            		}
            		if(darkCount>=1)
            		{
            			
            			int r = getRed(pixels[y][x])+25;
            			int g = getGreen(pixels[y][x])+25;
            			int b = getBlue(pixels[y][x])+25;
            			if(r>255)
            				r=255;
            			if(g>255)
            				g=255;
            			if(b>255)
            				b=255;
            			array[y][x]=makePixel(r,g,b);
            			
            		}
            	}
            	else if(getRed(pixels[y][x])<=55&&getGreen(pixels[y][x])<=55&&getBlue(pixels[y][x])<=55) //dark pixel
            	{
            		
            		int lightCount=0;
            		for(int i=-1; i<=1; i++)
            		{
            			for(int j=-1; j<=1; j++)
            			{
            				if(isValid(y+i, x+j, width, height)&&getRed(pixels[y+i][x+j])>=getRed(pixels[y][x])&&getGreen(pixels[y+i][x+j])>=getGreen(pixels[y][x])&&getBlue(pixels[y+i][x+j])>=getBlue(pixels[y][x]))
            				{
            					lightCount++;
            				}
            				
            			}
            		}
            		if(lightCount>=1)
            		{        			
            			//darken the pixel to increase contrast
            			int r = getRed(pixels[y][x])-25;
            			int g = getGreen(pixels[y][x])-25;
            			int b = getBlue(pixels[y][x])-25;
            			if(r<0)
            				r=-1;
            			if(g<0)
            				g=-1;
            			if(b<0)
            				b=-1;
            			array[y][x]=makePixel(r,g,b);
            		}
            	
            	}
            	
            	
            	
            	
            }
        }
       
        return array;
    }
}

