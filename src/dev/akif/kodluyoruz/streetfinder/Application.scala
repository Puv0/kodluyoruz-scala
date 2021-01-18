package dev.akif.kodluyoruz.streetfinder
import dev.akif.kodluyoruz.streetfinder.StreetFinder

import java.io.File
import scala.io.Source
import scala.util.Using
/**
 * See CSV file at: https://github.com/makiftutuncu/kodluyoruz-scala/blob/main/data/streets.csv
 *
 * Original data: https://github.com/life/il-ilce-mahalle-sokak-cadde-sql
 */
object Application extends StreetFinder with CsvLoader {

  def main(args: Array[String]): Unit = {


  println(findStreets(Set(" VEFA SK","BUHARA")))

  }

  override def findStreets(names: Set[String]): List[String] = {
    val file = new File("data\\streets.csv")
    val raw_data = loadCsv(file)

    val all_streets =
      raw_data.foldLeft(List[String]()) {
      case (streetList, street) =>
        val allStreets  = street.split(",").map(_.trim).filter(_.nonEmpty).toList(1)
            allStreets::streetList
    }
    val matchingStreets =
      all_streets.filter { street =>
        names.exists (name => {
          street.contains(name)
        })
      }

    matchingStreets
  }


  override def loadCsv(file: File): List[String] = {

     Using.resource(Source.fromFile(file.getAbsolutePath)) {
       reader => reader.getLines().toList.foldRight(List[String]()) {
         (elem, acc) =>  correctForm(elem)::acc }
       }

}

  def correctForm(street:String): String = {
    street.replaceAll("'","")
  }



}



