package com.hlb

object Demo5 {
  def main(args: Array[String]): Unit = {
    // 一些有用的高阶函数
    // Example1: map
    // 在列表中的每个元素上计算一个函数，并且返回一个包含相同数目元素的列表
    // out: 2 4 6 8 10
    println("----------map------------")
    val nums1 = List(1,2,3,4,5)
    nums1.map((i:Int) => i*2).foreach(println)

    // Example2: foreach
    // 和map相似,只不过没有返回值，主要是为了对参数进行作用
    // out: 1 2 3 4 5
    println("----------foreach------------")
    nums1.foreach(println)

    // Example3: filter
    // 移除任何使得传入的函数返回false的元素
    // out: 2 4 6 8
    println("----------filter-------------")
    val nums2 = List(1,2,3,4,5,6,7,8)
    nums2.filter((i:Int) => i%2 == 0).foreach(println)

    // Example4: zip
    // 把两个列表的元素合成一个由元素对组成的列表,元素对数量由前列表长度决定
    // out: (1,4) (2,5) ) (3,6)
    println("----------zip-------------")
    List(1,2,3).zip(List(4,5,6,7)).foreach(println)

    // Example5: partition
    // 根据断言函数的返回值对列表进行拆分
    // out: List(2, 4)   List(1, 3, 5)
    println("----------partition-------------")
    nums1.partition((i:Int) => i%2==0).productIterator.foreach(println)

    // Example6: find
    // 返回集合里第一个匹配断言函数的元素
    // out: 3
    println("----------find-------------")
    nums1.find(_ % 3 == 0).foreach(println)

    // Example7: flatten
    // 可以把嵌套的结构展开
    // out: 1 2 3 4 5 6
    println("----------flatten-------------")
    List(List(1,2,3),List(4,5,6)).flatten.foreach(println)

    // Example8: flatMap
    // 常用的combinator，它结合了map 和 flatten 的功能
    // out: 2 4 6 8 10 12
    println("----------flatMap-------------")
    val nums3 = List(List(1,2,3),List(4,5,6))
    nums3.flatMap(x => x.map(_ * 2)).foreach(println)
  }
}
