package in.olc.scala.scalaPractice

import java.io._
import scala.io.Source

object mergeTextFiles {
  def main(args: Array[String]) {
    val folderPath = "C:/Ganesh/New"
    val outputFile = "C:/Ganesh/output.txt"
    val folder = new File(folderPath)
    val files = folder.listFiles.filter(_.isFile).filter(_.getName.endsWith(".txt"))

    val writer = new PrintWriter(outputFile)
    for (file <- files) {
      writer.println("------ " + file.getName + " ------")
      val source = Source.fromFile(file)
      for (line <- source.getLines()) {
        writer.println(line)
      }
      source.close()
    }
    writer.close()
  }
}