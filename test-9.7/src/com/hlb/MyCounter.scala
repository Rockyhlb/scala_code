package com.hlb

object MyCounter {
  def main(args: Array[String]): Unit = {
    val myCounter = new Counter;
    println(myCounter.value);
    myCounter.value = 3;
    myCounter.increament(1);
    println(myCounter.value);
  }
}
