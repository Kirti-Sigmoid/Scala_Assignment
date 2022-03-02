import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.collection.immutable.Vector
import scala.collection.immutable.Map
import scala.collection.immutable.ListMap

object ques2 extends App {
  val rows = ArrayBuffer[Array[String]]()
  val bufferedSource = io.Source.fromFile("/Users/kirti_sigmoid/Downloads/Scala/assignment/data.csv")
  for (line <- bufferedSource.getLines.drop(1)) {
    rows += line.split(",").map(_.trim)

  }
//  for (row <- rows) {
//    println(s"${row(0)}|${row(1)}|${row(2)}|${row(3)}")
//  }


  val score = scala.collection.mutable.Map.empty[String,Int]
  val wickets = scala.collection.mutable.Map.empty[String,Int]
  val rank = scala.collection.mutable.Map.empty[String,Int]
 // println(rows(1)(1))
 // println(rows.length)

 var i = 0
 var max=0
  println( "Player with the best highest run scored\n")
 for (j <- 0 to rows.length-1){

     score+=(rows(j)(1)->rows(j)(4).toInt)
     wickets+=(rows(j)(1)->rows(j)(5).toInt)
     rank+=(rows(j)(1)->(rows(j)(5).toInt*5 + rows(j)(4).toInt*.05).toInt)

   if (rows(j)(4).toInt > max)
   {
     max=rows(j)(4).toInt
     i=j
   }
 }
  println(rows(i)(1)+" scored best highest score "+rows(i)(4))

// Top 5 players by run scored.
  var res = ListMap(score.toSeq.sortWith(_._2 > _._2):_*)// sorting in decending order

  println("Top 5 players by run scored\n")
 res.take(5).foreach
  {
    case (key, value) => println (key + " scored " + value)
  }


  println("Top 5 players by wicket taken\n")
  res=ListMap(wickets.toSeq.sortWith(_._2 > _._2):_*)// sorting in decending order
  res.take(5).foreach
  {
    case (key, value) => println (key + " take " + value+" wickets")
  }
  res=ListMap(rank.toSeq.sortWith(_._2 > _._2):_*)// sorting in decending order

  var c=1
  println("Ranking players with overall performance\n")
  res.foreach
  {
    case (key, value) => System.out.printf("Player : %-7s    Rank: %s%n",key,c )
      c+=1
  }
}
