package com.hlb

import scala.collection.mutable.ArrayBuffer

class Student {
  // 内部类 -->  记录学生课程信息
  class Course(val courseName:String,val credit:Int) {
  }
  // 学生信息
  private var stuName:String = "Tom"
  private var stuAge:Int = 18
  // get/set 方法
  def getStuName:String=stuName
  def setStuName(newStuName:String): Unit ={
    stuName=newStuName
  }

  def getStuAge:Int=stuAge
  def setStuAge(newStuAge:Int): Unit ={
    stuAge=newStuAge
  }
  // 创建ArrayBuffer列表记录学生选修课程
  private var courseList = new ArrayBuffer[Course]()
  // get方法取出课程列表
  def getCourseList:ArrayBuffer[Course] = courseList
  // 定义方法来添加课程
  def addCourse(name:String,credit:Int): Unit ={
    var c = new Course(name,credit)
    courseList+=c
  }
}
