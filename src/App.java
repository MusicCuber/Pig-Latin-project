class App {
  public static void main(String[] args) {
    Book aBook = new Book("https://www.gutenberg.org/cache/epub/74696/pg74696.txt");
    Book sosBook = new Book(" https://www.gutenberg.org/cache/epub/64317/pg64317.txt");
    
  //  aBook.writeToFile(aBook.pigLatin(aBook.returnBook()));
  //  System.out.println("Word count for myBook: " + aBook.wordCount());
   sosBook.writeToFile(sosBook.pigLatin(sosBook.returnBook()));
   System.out.println("Word count for sosBook: " + sosBook.wordCount());
  }
}
