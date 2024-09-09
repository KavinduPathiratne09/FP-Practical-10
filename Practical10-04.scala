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

  // Use the getBalance method instead of accessing the private balance field
  def listNegativeBalanceAccounts(): List[BankAccount] = {
    accounts.filter(_.getBalance < 0)
  }

  // Use the getBalance method for summing the total balance
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
  val account1 = new BankAccount("12345", 1000.0)
  val account2 = new BankAccount("67890", -500.0)
  val account3 = new BankAccount("54321", 750.0)

  Bank.addAccount(account1)
  Bank.addAccount(account2)
  Bank.addAccount(account3)

  println("Accounts with negative balances:")
  Bank.listNegativeBalanceAccounts().foreach(println)

  println(s"\nTotal balance: ${Bank.calculateTotalBalance()}")

  Bank.applyInterestToAllAccounts()

  println(s"\nBank accounts after applying interest:")
  println(Bank)
}