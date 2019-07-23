package com.atguigu.bean

import java.sql.Date
import java.text.SimpleDateFormat

case class StartupLog(mid: String,
                      uid: String,
                      appId: String,
                      area: String,
                      os: String,
                      channel: String,
                      logType: String,
                      version: String,
                      ts: Long) {

  private val date = new Date(ts)

  val logDate: String = new SimpleDateFormat("yyyy-MM-dd").format(date)

  val logHour: String = new SimpleDateFormat("HH").format(date)

  val logHourMinute: String = new SimpleDateFormat("HH:mm").format(date)
}