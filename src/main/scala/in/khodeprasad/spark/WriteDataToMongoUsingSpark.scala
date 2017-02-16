package in.khodeprasad.spark

import com.stratio.datasource.mongodb._
import com.stratio.datasource.mongodb.config.MongodbConfig._
import com.stratio.datasource.mongodb.config._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Prasad Khode on 16-02-2017.
  */
object WriteDataToMongoUsingSpark extends Serializable {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("ReadDataFromMongoUsingSpark")
    sparkConf.setMaster("local[*]")
    sparkConf.set("spark.sql.warehouse.dir", "file:///")

    val sparkSession = SparkSession.builder.config(sparkConf).getOrCreate

    val dataFrame = sparkSession.read.json("src/main/resources/zips.json")
    dataFrame.printSchema()

    val saveConfig = MongodbConfigBuilder(Map(Host -> List("localhost:27017"), Database -> "spark_test", Collection -> "zips_collection", SplitKey -> "_id"))
    dataFrame.saveToMongodb(saveConfig.build)

    dataFrame.unpersist()
    sparkSession.stop()
  }
}
