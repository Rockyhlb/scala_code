package com.hlb

class Person(val name:String,val age:Int){
  def greet(): String = "name is " + name + ",age is " + age
}

class Employee(override val name:String, override val age: Int,val salary:Int) extends Person(name, age){
  // 重写父类greet方法
  override def greet(): String = "子类的greet方法"
}

object extendsDemo {
  // 测试程序
  def main(args: Array[String]): Unit = {
    // 创建对象
    val jerry:Person = new Employee("Jerry", 19, 20000)
    println(jerry.greet())
    // 使用匿名子类创建Person对象
    // 使用匿名子类可以方便地在需要临时定义一个子类对象的地方使用，而不需要显式地定义一个具名的子类
    val tom:Person = new Person("Tom", 18){
      override def greet(): String = "匿名子类中的greet方法"
    }
    println(tom.greet())
  }
}
