import scala.io.StdIn

object CountLetterOccurrencesApp {
  
  def main(args: Array[String]): Unit = {
    def readWords(prompt: String): List[String] = {
      println(prompt)
      // Read input from the user and split by spaces to get a list of words
      StdIn.readLine().split("\\s+").toList
    }

    def countLetterOccurrences(words: List[String]): Int = {
      // Map each word to its length and then reduce to get the total count
      words.map(_.length).reduce(_ + _)
    }

    // Get user input
    val words = readWords("Enter a list of words separated by spaces:")
    
    // Calculate and print the total count of letter occurrences
    val totalCount = countLetterOccurrences(words)
    println(s"Total count of letter occurrences: $totalCount")
  }
}
