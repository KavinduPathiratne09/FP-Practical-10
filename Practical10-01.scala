import scala.io.StdIn

class Rational(n: Int, d: Int) {
  require(d != 0, "Denominator cannot be zero")

  private val gcdValue = gcd(n.abs, d.abs)
  val numer: Int = n / gcdValue
  val denom: Int = d / gcdValue

  def this(n: Int) = this(n, 1)

  def neg: Rational = new Rational(-this.numer, this.denom)

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  override def toString: String = {
    if (denom == 1) s"$numer" else s"$numer/$denom"
  }
}

object RationalTest extends App {
  def readInt(prompt: String): Int = {
    println(prompt)
    StdIn.readInt()
  }

  def readRational(): Rational = {
    val numer = readInt("Enter numerator:")
    val denom = readInt("Enter denominator (non-zero):")
    new Rational(numer, denom)
  }

  println("Enter details for the first Rational number")
  val x = readRational()
  println(s"Original: $x")
  println(s"Negation: ${x.neg}")

  println("Enter details for the second Rational number (denominator can be omitted, defaulting to 1):")
  val numer = readInt("Enter numerator:")
  val y = new Rational(numer)
  println(s"Original: $y")
  println(s"Negation: ${y.neg}")
}
