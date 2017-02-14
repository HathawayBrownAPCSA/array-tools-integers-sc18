/** The class IntegerArrayTools implements a variety of basic array tasks
  * using an array of integers.
  * 
  * The array has a fixed length MAX_NUMS but the actual number of elements
  * that are used can vary.
  */

import java.util.Scanner;
import java.io.*;

public class StringArrayTools
{
  
  private String [] arrayData;
  private int arrayCount;
  public final int MAX_STRINGS = 100;
 
  private String [] minMax = new String[2];
  private int[] minMaxIndex = new int[2];
  
  private String[] az = new String[2];
  private int[] azIndex = new int[2];
  
  private String[] adjective1;
  private String[] adjective2;
  private String[] noun;
  private final int NUM_WORDS = 30;
 
  
 
  // ------------------ CONSTRUCTOR ----------------------------
  /** The contsructor merely creates the array.
    * It does not fill it with any data.  That is left up to 
    * the fillRandom or fillKeyboard methods.
    */
  public StringArrayTools ()
  {
    arrayData = new String[MAX_STRINGS];
    arrayCount = 0;
    
    adjective1 = new String[NUM_WORDS];
    adjective2 = new String[NUM_WORDS];
    noun = new String[NUM_WORDS];
    
    readFile (adjective1, "shakespeare-1.txt");
    readFile (adjective2, "shakespeare-2.txt");
    readFile (noun, "shakespeare-3.txt");

  }
  
  private void readFile (String[] wordList, String fileName)
  {
    // open the file
    File file = new File(fileName);
    Scanner input = null;
    try
    {
        input = new Scanner(file);
    }
    catch (FileNotFoundException ex)
    {
        System.out.println("*** Cannot open " + fileName + " ***");
        System.exit(1);        // quit the program
    } 

    // read the words from the file, one per line
    for (int i = 0; i < NUM_WORDS; i++)
      wordList[i] = input.nextLine();
    input.close();
  }

  /**
   * Randomly picks a String from one of two adjective lists or a noun list. It picks from one of 30
   * @return String randomly chosen
   */
  public String getInsult()
  {
	int i = (int)(Math.random() * 2);
    String insult;
    if(i ==0)
    {
    i = (int)(Math.random() * NUM_WORDS);
    insult = adjective1[i];
    }
    else if( i == 1)
    {
    i = (int)(Math.random() * NUM_WORDS);
    insult = adjective2[i];
    }
    else
    {
    i = (int)(Math.random() * NUM_WORDS);
    insult = noun[i];
    }
    
    return insult;
  }
  
  /** Returns the number of objects in the array.
    * @return the count of items in the array. */
  public int getCount ()
  {
    return arrayCount;
  }
  
  /**
   * Finds the Strings closest to 'A' and 'Z' from an array and the index for each
   * Assigns the String to an array called az
   * Assigns the index to an array called azIndex
   */
  public void findAZWithIndex()
  {
	  az[0] = arrayData[0];
	  az[1] = arrayData[0];
	  azIndex[0] = 0;
	  azIndex[1] = 0;
	  for(int i = 0; i < getCount(); i++)
	  {
		 	 if(arrayData[i].compareToIgnoreCase(az[0]) > 0)
			 {
				 az[0] = arrayData[i];
				 azIndex[0] = i;
			 }
		 	 else if(arrayData[i].compareToIgnoreCase(az[1]) < 0)
		 	 {
		 		az[1] = arrayData[i];
				azIndex[1] = i;
		 	 }
	  }
  }
  
  /**
   * @return String closest to 'A' stored in az at 0
   */
  public String getToZ()
  {
	  return az[0];
  }
  
  /**
   * @return String closest to 'A' index stored in azIndex at 0
   */
  public int getToZIndex()
  {
	  return azIndex[0];
  }
  
  /**
   * @return String closest to 'Z' stored in az at 1
   */
  public String getToA()
  {
	  return az[1];
  }
  
  /**
   * @return String closest to 'Z' index stored in azIndex at 1
   */
  public int getToAIndex()
  {
	  return azIndex[1];
  }

  /**
   * Finds the longest and shortest Strings in an array
   * Assigns the String to minMax
   * Assigns the index to minMaxIndex
   */
  public void findMinMaxWithIndex()
  {
	  minMax[0] = arrayData[0];
	  minMax[1] = arrayData[0];
	  minMaxIndex[0] = 0;
	  minMaxIndex[0] = 0;
	  for(int i = 0; i < getCount(); i++)
	  {
		 	 if(arrayData[i].length() > minMax[0].length())
			 {
				 minMax[0] = arrayData[i];
				 minMaxIndex[0] = i;
			 }
		 	 else if(arrayData[i].length() < minMax[1].length())
		 	 {
		 		minMax[1] = arrayData[i];
				minMaxIndex[1] = i;
		 	 }
	  }
  }
 
  /**
   * Goes through an a String array from an index defined as start to the end. It sets pos to be equal to start
   * and runs through updated pos to be i if the String at i is closer to 'A' than the String at pos
   * @param int start (where in the array to begin searching)
   * @return int pos (of String closest to 'A' in the array)
   */
  private int throughMinIndex(int start)
  {
	  int pos = start;
	  {
		  for(int i = start; i < getCount(); i++)
		  {
			  if(arrayData[i].compareToIgnoreCase(arrayData[pos]) < 0)
				  {
				  	pos = i;
				  }
		  }
	  }
	  return pos;
  }
  
  /**
   * Loops through a String array from position to the end and calls throughMinIndex(position) to check what String is closest to 'A' from position to end, then moves that String to position. Position is updated at the end to check from the next String in the array.
   */
  public void sort()
  {
	  int position = 0;
	  while(position < getCount() - 1)
	  {
		 int min = throughMinIndex(position);
		 String temp = arrayData[min];
		 arrayData[min] = arrayData[position];
		 arrayData[position] = temp;
		 position ++;
	  }
  }
  
  /**
   * @return longest String stored in minMax at 0
   */
  public String getMax()
  {
	  return minMax[0];
  }
  
  /**
   * @return longest String index stored in minMaxIndex at 0
   */
  public int getMaxIndex()
  {
	  return minMaxIndex[0];
  }
  
  /**
   * @return shortest String stored in minMax at 1
   */
  public String getMin()
  {
	  return minMax[1];
  }
  
  /**
   * @return shortest String index stored in minMax at 1
   */
  public int getMinIndex()
  {
	  return minMaxIndex[1];
  }

  // ------------------- METHODS TO FILL THE ARRAY -------------------------
  /** Adds an element to the end of the array. 
    * @param s The element to add
    * @return whether the element was successfully added
    */
  public boolean addItem (String s)
  {
    if (arrayCount < MAX_STRINGS)
    {
      arrayData[arrayCount] = s;
      arrayCount++;
      return true;
    }
    else
      return false;
  }
  
    /** Fills the array arrayData with strings from 1 to count
    * @param count Upper bound for numbers with which to fill the array.
    */
  public void fillRandom (int count)
  {
    if (count > MAX_STRINGS)       // check that it's not too many items
      count = MAX_STRINGS;
    for (int i = 0; i < count; i++)
      addItem(getInsult());
  }
  
  /** Fills the array with Strings entered by the user
    */
  public void fillKeyboard ()
  {
    Scanner keyboard = new Scanner(System.in);
    System.out.println ("Please enter a word on one line, hit enter and type another to add more.");
    System.out.println ("Type nothing and hit enter to signal the end of the list.");
    
    arrayCount = 0;
    String s = keyboard.nextLine();
    while(!(s.length() == 0))                 // stop input when user enters -1
    {
      addItem(s);
      s = keyboard.nextLine();
    }
    keyboard.close();
  }
  
  // -------------------- METHODS TO WORK WITH THE ARRAY --------------------------
  /** Prints the array, each String on a new line.
    */
  public void printArray ()
  {
    for (int i = 0; i < arrayCount; i++)
      System.out.println (arrayData[i]);
    System.out.println();
  }
  
  /** Find the sum of all the characters of an integer array
    * @return the sum of the characters in the array
    */
  public int sumArray ()
  {
    int sum = 0;
    for (int i = 0; i < arrayCount; i++)
      sum = sum + arrayData[i].length();
    return sum;
  }
  
  
  /** Tests the various array tools */
  public static void main (String[] args)
  {
    StringArrayTools myArray = new StringArrayTools();
    myArray.fillRandom(100);
   // myArray.fillKeyboard ();
    myArray.printArray ();
    myArray.sort();
    myArray.printArray();
    System.out.println ("There are " + myArray.getCount() + " Strings in the array.");
    System.out.println ("There are " + myArray.sumArray() + " letters in the array.");
    
    // Un-comment these lines one at a time after you have written the appropriate code
   myArray.findMinMaxWithIndex();
   System.out.println ("The largest item is " + myArray.getMax() ); 
   System.out.println ("The largest item is at index " + myArray.getMaxIndex());

   System.out.println ("The smallest item is " + myArray.getMin() );
   System.out.println ("The smallest item is at index " + myArray.getMinIndex());
    
   myArray.findAZWithIndex();
   System.out.println ("The closest to Z is " + myArray.getToZ() ); 
   System.out.println ("The closest to Z is at index " + myArray.getToZIndex());

   System.out.println ("The closest to A is " + myArray.getToA() );
   System.out.println ("The closest to A is at index " + myArray.getToAIndex());
  
  }
}
    
    
  
  
         