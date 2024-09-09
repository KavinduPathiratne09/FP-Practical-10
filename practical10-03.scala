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
  val account1 = new MyBankAccount("12345", 1000.0)
  val account2 = new MyBankAccount("67890", 500.0)

  account1.deposit(500.0)
  account1.withdraw(200.0)
  account1.transfer(300.0, account2)

  println(account1)
  println(account2)
}
