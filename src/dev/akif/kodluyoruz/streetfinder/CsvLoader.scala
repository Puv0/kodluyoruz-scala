package dev.akif.kodluyoruz.streetfinder

import java.io.File
import scala.collection.mutable.ListBuffer

trait CsvLoader {
  def loadCsv(file: File): List[String]
}


