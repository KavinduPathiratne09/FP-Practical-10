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
  val x = new Rational(3, 4)
  println(s"Original: $x")        
  println(s"Negation: ${x.neg}")  

  val y = new Rational(5)
  println(s"Original: $y")        
  println(s"Negation: ${y.neg}")  
}
