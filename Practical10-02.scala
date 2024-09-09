object RationalOperation {
  def main(args: Array[String]): Unit = {
    val x = (3, 4)
    val y = (5, 8)
    val z = (2, 7)

    val result = sub(x, y, z)
    println(s"The result is: $result") 
  }

  def sub(x: (Int, Int), y: (Int, Int), z: (Int, Int)): (Int, Int) = {
    val commonDenominator = lcm(x._2, y._2, z._2)

    val xNum = x._1 * (commonDenominator / x._2)
    val yNum = y._1 * (commonDenominator / y._2)
    val zNum = z._1 * (commonDenominator / z._2)

    val resultNum = (xNum - yNum) - zNum
    val resultDen = commonDenominator

    val gcdResult = gcd(resultNum.abs, resultDen): Int
    (resultNum / gcdResult, resultDen / gcdResult)
  }

  private def lcm(a: Int, b: Int, c: Int): Int = {
    val ab = a * b / gcd(a, b): Int
    ab * c / gcd(ab, c): Int
  }

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }
}