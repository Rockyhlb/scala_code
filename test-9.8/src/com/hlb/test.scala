package com.hlb

object test {
  def main(args: Array[String]): Unit = {

    println("----------------------");
    // 调用伴生对象中的中的apply方法
    val a = ApplyTest();
    a.greeting();
    a();      // 调用伴生类中的中的apply方法

    println("----------------------");
    val car = new TestAbstract()
    car.greeting()
    car.info()

    println("----------------------");
    val bydCarId = new BydCarId()
    bydCarId.greeting("my first car")
    println("my car firstId is:" + bydCarId.id)
    bydCarId.greeting("my second car")
    println("my car currentId is:" + bydCarId.currentId())
  }
}

// 伴生类
class ApplyTest{
  def apply(): Unit = println("apply method in class is called~");
  def greeting(): Unit ={
    println("Greeting method in class is called~");
  }
}

// 伴生对象
object ApplyTest{
  def apply() = {
    println("apply method in object is called");
    new ApplyTest();
  }
}

// 抽象类
abstract class Car {
  val carBrand:String
  def info()
  def greeting() {println("welcome to car~");}
}
// 扩展类
class TestAbstract extends Car {
  override val carBrand: String = "BMW";
  // 重写超类的抽象方法可以不需要使用 override 关键字
  def info() {println("This is:" + carBrand);}
  // 重写非抽方法必须加override
  override def greeting() {println("welcome to:" + carBrand);}
}

// 特质  -->  java 当中的接口
trait CarId {
  var id: Int
  // 抽象方法（无方法体）
  def currentId(): Int
//  def greeting(msg:String) {println("welcome:" + msg)}
}
// 特质中也可以包含非抽
trait CarGreeting{
  def greeting(msg:String) {println("welcome:" + msg)}
}
// 将特质混入类中
class BydCarId extends CarId with CarGreeting {
  override var id = 10000
  // 最后一行默认为返回值
  override def currentId(): Int = {id += 1;id}
}

