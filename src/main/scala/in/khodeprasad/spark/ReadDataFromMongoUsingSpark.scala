package in.khodeprasad.spark

import com.stratio.datasource.mongodb._
import com.stratio.datasource.mongodb.config.MongodbConfig._
import com.stratio.datasource.mongodb.config._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Prasad Khode on 16-02-2017.
  */
object ReadDataFromMongoUsingSpark extends Serializable {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("ReadDataFromMongoUsingSpark")
    sparkConf.setMaster("local[*]")
    sparkConf.set("spark.sql.warehouse.dir", "file:///")

    val sparkSession = SparkSession.builder.config(sparkConf).getOrCreate

    val builder = MongodbConfigBuilder(Map(Host -> List("localhost:27017"), Database -> "spark_test", Collection ->"zips_collection", SamplingRatio -> 1.0, WriteConcern -> "normal"))

    val mongoRDD = sparkSession.sqlContext.fromMongoDB(builder.build())
    mongoRDD.createTempView("zips_collection")

    val dataFrame = sparkSession.sql("SELECT * FROM zips_collection")

    val count = dataFrame.count
    dataFrame.show
    println(s"Total Records Count : $count \n")

    dataFrame.printSchema()

    mongoRDD.unpersist()
    dataFrame.unpersist()

    sparkSession.stop()
  }
}
