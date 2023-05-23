package in.olc.scala.scalaPractice

import java.io.File

object countFiles {
  def countFiles(dir: String): Option[Int] = {
    val file = new File(dir)
    if (file.exists) {
      val fileList = file.listFiles().toList.size
      Some(fileList)
    } else None

  }
  def main(args:Array[String])={
    val fileCount=countFiles("")
    fileCount match{
      case Some(x)=>println(s"the directory is having $x files")
      case None=>println("directory is not valid")
    }
  }

}
