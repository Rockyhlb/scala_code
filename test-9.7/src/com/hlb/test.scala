package com.hlb
import collection.Map
import scala.collection.mutable.ArrayBuffer

object test {
  def main(args: Array[String]): Unit = {
    var mystr="2343243";
    println("String" + mystr);

//    val不能更改
    mystr="2343dsadsa";
    println("String" + mystr);

    println("---------------------------------")
    val num1 = 23;
    val num2 = 12;
    val res1 = num1 + num2;
    val res2 = num1 .*(num2);
    println("+  " + res1);
    println(".*()  " + res2)

    println("---------------------------------")
    val map1 = Map("first"->"a","second"->"b");
    println("map:" + map1("first"));

    println("---------------------------------")
    // 申明长度为3的定长数组
    val intValueArr1 = new Array[Int](3);
    intValueArr1(0) = 12;
    intValueArr1(1) = 23;
    intValueArr1(2) = 32;
    for(i <- 0 to 2) println("Array:" + intValueArr1(i));

    // 申明可变长数组
    val intValueArr2 = new ArrayBuffer[Int]();
    intValueArr2(0) = 12;
    intValueArr2(1) = 23;
    intValueArr2(2) = 32;
    for(i <- 0 to 2) println("Array:" + intValueArr2(i));

    // 元组 -->  tuple
    val tuple1 = ("bigdata");
  }
}
