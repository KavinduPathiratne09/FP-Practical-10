import scala.io.StdIn

class MyBankAccount(val accountNumber: String, initialBalance: Double) {
  private var balance: Double = initialBalance

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

  def transfer(amount: Double, toAccount: MyBankAccount): Unit = {
    if (amount > 0 && balance >= amount) {
      withdraw(amount)
      toAccount.deposit(amount)
      println(s"Transferred $amount from account $accountNumber to account ${toAccount.accountNumber}")
    } else {
      println(s"Invalid transfer amount or insufficient funds in account $accountNumber")
    }
  }

  override def toString: String = s"Account Number: $accountNumber, Balance: $balance"
}

object BankAccountApp extends App {
  def readDouble(prompt: String): Double = {
    println(prompt)
    StdIn.readDouble()
  }

  def readString(prompt: String): String = {
    println(prompt)
    StdIn.readLine()
  }

  val account1Number = readString("Enter the account number for the first account:")
  val account1InitialBalance = readDouble("Enter the initial balance for the first account:")
  val account1 = new MyBankAccount(account1Number, account1InitialBalance)

  val account2Number = readString("Enter the account number for the second account:")
  val account2InitialBalance = readDouble("Enter the initial balance for the second account:")
  val account2 = new MyBankAccount(account2Number, account2InitialBalance)

  val depositAmount = readDouble(s"Enter the deposit amount for account ${account1.accountNumber}:")
  account1.deposit(depositAmount)

  val withdrawAmount = readDouble(s"Enter the withdrawal amount for account ${account1.accountNumber}:")
  account1.withdraw(withdrawAmount)

  val transferAmount = readDouble(s"Enter the transfer amount from account ${account1.accountNumber} to account ${account2.accountNumber}:")
  account1.transfer(transferAmount, account2)

  println(account1)
  println(account2)
}
