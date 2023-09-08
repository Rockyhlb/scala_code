package com.hlb

class Counter {
  var value = 0;
  def increament(num: Int): Unit = {
    value += num;
  }
  def curent(): Int = (value);
}
