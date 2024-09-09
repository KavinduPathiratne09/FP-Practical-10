import scala.io.StdIn

class BankAccount(val accountNumber: String, initialBalance: Double) {
  private var balance: Double = initialBalance

  def getBalance: Double = balance

  def deposit(amount: Double): Unit = {
    if (amount > 0) {
      balance += amount
      println(s"Deposited $amount into account $accountNumber. New balance: $balance")
    } else {
      println(s"Invalid deposit amount: $amount")
    }
  }

  def withdraw(amount: Double): Unit = {
    if (amount > 0 && balance >= amount) {
      balance -= amount
      println(s"Withdrew $amount from account $accountNumber. New balance: $balance")
    } else {
      println(s"Invalid withdrawal amount or insufficient funds in account $accountNumber")
    }
  }

  def transfer(amount: Double, toAccount: BankAccount): Unit = {
    if (amount > 0 && balance >= amount) {
      withdraw(amount)
      toAccount.deposit(amount)
      println(s"Transferred $amount from account $accountNumber to account ${toAccount.accountNumber}")
    } else {
      println(s"Invalid transfer amount or insufficient funds in account $accountNumber")
    }
  }

  def applyInterest(): Unit = {
    if (balance > 0) {
      balance += balance * 0.05
      println(s"Applied 5% interest to account $accountNumber. New balance: $balance")
    } else {
      balance -= balance * 0.1  // Corrected: Subtract for negative balance
      println(s"Applied 10% overdraft interest to account $accountNumber. New balance: $balance")
    }
  }

  override def toString: String = s"Account Number: $accountNumber, Balance: $balance"
}

object Bank {
  private var accounts: List[BankAccount] = Nil

  def addAccount(account: BankAccount): Unit = {
    accounts = account :: accounts
  }

  def listNegativeBalanceAccounts(): List[BankAccount] = {
    accounts.filter(_.getBalance < 0)
  }

  def calculateTotalBalance(): Double = {
    accounts.map(_.getBalance).sum
  }

  def applyInterestToAllAccounts(): Unit = {
    accounts.foreach(_.applyInterest())
  }

  override def toString: String = {
    accounts.mkString("\n")
  }
}

object BankApp extends App {
  def readDouble(prompt: String): Double = {
    println(prompt)
    StdIn.readDouble()
  }

  def readString(prompt: String): String = {
    println(prompt)
    StdIn.readLine()
  }

  def createAccount(): BankAccount = {
    val accountNumber = readString("Enter account number:")
    val initialBalance = readDouble("Enter initial balance:")
    new BankAccount(accountNumber, initialBalance)
  }

  // Create accounts
  val account1 = createAccount()
  val account2 = createAccount()
  val account3 = createAccount()

  Bank.addAccount(account1)
  Bank.addAccount(account2)
  Bank.addAccount(account3)

  // Perform operations
  println("Enter deposit amount for account 1:")
  account1.deposit(readDouble(""))

  println("Enter withdrawal amount for account 1:")
  account1.withdraw(readDouble(""))

  println("Enter transfer amount from account 1 to account 2:")
  account1.transfer(readDouble(""), account2)

  println("Enter deposit amount for account 2:")
  account2.deposit(readDouble(""))

  // Print account details
  println("\nAccounts with negative balances:")
  Bank.listNegativeBalanceAccounts().foreach(println)

  println(s"\nTotal balance: ${Bank.calculateTotalBalance()}")

  Bank.applyInterestToAllAccounts()

  println(s"\nBank accounts after applying interest:")
  println(Bank)
}
