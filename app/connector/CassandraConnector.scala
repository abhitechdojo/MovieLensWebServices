package connector

import com.datastax.driver.core.{Session, Cluster}
import com.websudos.phantom.connectors.{KeySpace, SessionProvider}

/**
  * Created by abhishek.srivastava on 3/1/16.
  */
trait CassandraConnector extends SessionProvider {

  implicit val space: KeySpace = Connector.keySpace
  val cluster = Connector.cluster
  override implicit lazy val session = Connector.session
}

object Connector {
  val keySpace = KeySpace("MovieLens")
  val cluster = Cluster.builder().addContactPoint("192.168.99.100").build()
  val session = cluster.connect(keySpace.name)
}
