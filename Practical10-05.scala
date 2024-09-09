import scala.io.StdIn

object CountLetterOccurrencesApp {
  
  def main(args: Array[String]): Unit = {
    def readWords(prompt: String): List[String] = {
      println(prompt)
      StdIn.readLine().split("\\s+").toList
    }

    def countLetterOccurrences(words: List[String]): Int = {
      words.map(_.length).reduce(_ + _)
    }

    val words = readWords("Enter a list of words separated by spaces:")
    
    val totalCount = countLetterOccurrences(words)
    println(s"Total count of letter occurrences: $totalCount")
  }
}
