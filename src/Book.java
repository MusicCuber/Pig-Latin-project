import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Scanner;
import java.net.URL;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Book {
    private int totalWords;
    private String bookText = "";
    public Book(String link) {
        totalWords = 0;
        
        readBook(link);
    }
    

  public void writeToFile(String text) {
    try {
      FileWriter myWriter = new FileWriter("filename.txt");
      myWriter.write(text);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

    private void readBook(String link) {
        try {
            URL url = new URL(link);
            Scanner s = new Scanner(url.openStream());
            boolean toTranslate = false;
            while (s.hasNext()) {
                
                String text = s.nextLine();
                if (text.contains("START OF THE PROJECT GUTENBERG EBOOK")) {
                    toTranslate = true;
                    continue;
                }
                else if (text.contains("END OF THE PROJECT GUTENBERG EBOOK")) {
                    break;
                }
                if (toTranslate) {
                    bookText += text;

                    String[] words = text.split(" ");
                    totalWords += words.length;
                }
                
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
  private static final String vowels = "aeiou";
  private static final String capitals = "QWERTYUIOPASDFGHJKLZXCVBNM";
  private static final String PUNCTUATION = "!@#$%^&*()-<>?:,./[]`~";
  public String returnBook() {
    return bookText;
  }
  public String pigLatin(String sentence) {
      return translateSentence(bookText);
  }

  public String translateSentence(String sentence) {
      String[] wordList = sentence.split(" "); // Split the sentence into words
      String ret = "";
      
      for (String word : wordList) {
        if (word.length() == 0) {
            continue;
        }
        else {
            ret += (translateWord(word)) + " ";
        
          
            }
        }
      return ret.trim(); // Remove trailing space

  }
  public String translateWord(String word) {
    String cleanWord = word;
    String endPunctuation = extractEndPunctuation(cleanWord);
    
    // Check if cleanWord is empty before accessing its first character
    boolean isCapital = cleanWord.length() > 0 && Character.isUpperCase(cleanWord.charAt(0));

    String pigLatinWord = convertToPigLatin(cleanWord);

    if (isCapital) {
        pigLatinWord = capitalizeFirstLetter(pigLatinWord);
    }

    return pigLatinWord + endPunctuation;
}

  private String extractEndPunctuation(String word) {
      String punctuation = "";
      while (word.length() > 0 && PUNCTUATION.indexOf(word.charAt(word.length() - 1)) >= 0) {
        punctuation += word.charAt(word.length() - 1);
        word = word.substring(0, word.length() - 1);
    }
      return punctuation;
  }

  private String convertToPigLatin(String word) {
    if (word.length() == 0) {
        return word; // Handle empty string
    }
    
    // Remove trailing punctuation
    while (word.length() > 0 && PUNCTUATION.indexOf(word.charAt(word.length() - 1)) >= 0) {
        word = word.substring(0, word.length() - 1);
    }

    if (word.length() == 0) {
        return ""; // Return empty if only punctuation was present
    }

    String firstLetter = word.substring(0, 1);
    String lowerFirstLetter = firstLetter.toLowerCase();
    String pigLatin;

    if (vowels.indexOf(lowerFirstLetter) >= 0) {
        pigLatin = word + "yay";
    } else if (word.length() > 1 && vowels.indexOf(word.charAt(1)) >= 0) {
        pigLatin = word.substring(1) + lowerFirstLetter + "ay";
    } else if (word.length() > 2 && vowels.indexOf(word.charAt(2)) >= 0) {
        pigLatin = word.substring(2) + lowerFirstLetter + word.charAt(1) + "ay";
    } else {
        pigLatin = word + "ay"; // Handle cases where the word is too short
    }

    return pigLatin;
}
  

  
  private String capitalizeFirstLetter(String word) {
      return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

  public int endPunctuation(String word) {
      int index = -1;
      for (int i = word.length() - 1; i >= 0; i--) {
          if (PUNCTUATION.indexOf(word.charAt(i)) < 0) {
              // Found a non-punctuation character
              index = i + 1; // Return the index of the first non-punctuation character
              break;
          }
      }
      return -1; // If all punctuation, return 0; if none, return -1
  }
  public int wordCount() {
    return totalWords;
  }
}

