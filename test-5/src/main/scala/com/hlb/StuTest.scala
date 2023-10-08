
package com.hlb
/**
 * User: 麦田里的乌鸦
 * Date: 2022-10-08
 * Time: 10:41
 */
object StuTest {
  def main(args: Array[String]): Unit = {
    // 创建学生对象
    var s1 = new Student
    s1.addCourse("chinese",1)
    s1.addCourse("math",2)
    s1.addCourse("english",3)
    println(s1.getStuName + "\t" + s1.getStuAge)
    println("---------选修课程-----------")
    for(s<-s1.getCourseList) println(s.courseName + "\t\t" + s.credit)
  }
}

