import scala.io.StdIn

object RationalOperation {
  def main(args: Array[String]): Unit = {
    println("Enter the first fraction:")
    val x = readFraction()
    
    println("Enter the second fraction:")
    val y = readFraction()

    val result = sub(x, y)
    println(s"The result is: $result")
  }

  def readFraction(): (Int, Int) = {
    val numerator = readInt("Enter numerator:")
    val denominator = readInt("Enter denominator (non-zero):")
    (numerator, denominator)
  }

  def readInt(prompt: String): Int = {
    println(prompt)
    StdIn.readInt()
  }

  def sub(x: (Int, Int), y: (Int, Int)): (Int, Int) = {
    val commonDenominator = lcm(x._2, y._2)

    val xNum = x._1 * (commonDenominator / x._2)
    val yNum = y._1 * (commonDenominator / y._2)

    val resultNum = xNum - yNum
    val resultDen = commonDenominator

    val gcdResult = gcd(resultNum.abs, resultDen)
    (resultNum / gcdResult, resultDen / gcdResult)
  }

  private def lcm(a: Int, b: Int): Int = {
    a * b / gcd(a, b)
  }

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}

