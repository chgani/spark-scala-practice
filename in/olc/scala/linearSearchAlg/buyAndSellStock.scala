package in.olc.scala.linearSearchAlg

object buyAndSellStock {
  def main(args:Array[String]):Unit={
    val prices=List(8,2,3,1,6,5,7,10)
    println(dayToSell(prices))
    val max_profit=prices(dayToSell(prices)-1)-prices(daytoBuy(prices)-1)
    println(s"maximum profit= $max_profit")
    println(maxProfit(prices.toArray))
  }
  def daytoBuy(ls:List[Int]):Int={
    var buy=Int.MaxValue
    for(i<- 0 to ls.length-1 ){
      if(ls(i)<buy) {
        buy=ls(i)
      }
    }
    ls.indexOf(buy)+1
  }
  def dayToSell(ls:List[Int]):Int={
    val dayToBuy=daytoBuy(ls)
    var daytoSell=0
    for(i<- dayToBuy until ls.length){
      if(ls(i)>ls(dayToBuy-1)) {
        daytoSell=i
      }
    }
    daytoSell+1
  }

  def maxProfit(prices: Array[Int]): Int = {
var min_price=Int.MaxValue
    var max_profit=0
    for(i<- 0 to prices.length-1){
      if(prices(i)<min_price) min_price=prices(i)
      else if(prices(i)-min_price>max_profit) max_profit=prices(i)-min_price
    }
    max_profit
  }
}
