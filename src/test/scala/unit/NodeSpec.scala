package com.contactlab.hub.test.unit

import org.scalatest.AsyncFunSpec
import org.scalatest.Matchers._
import org.scalatest.GivenWhenThen
import org.scalatest.mock.MockitoSugar
import scala.compat.java8.FutureConverters._

import com.contactlab.hub.Node
import com.contactlab.hub.Api

import org.mockito.Mockito.{ verify, times };

class NodeSpec extends AsyncFunSpec with MockitoSugar {

  implicit def toFuture[A](c: java.util.concurrent.CompletionStage[A]) = c.toScala

  val api = mock[Api]
  val node = new Node("nodeId", "workspaceId", api)

  describe("Node") {
    describe("getCustomers") {

      it("invokes the right api method") {
        node.getCustomers.map { _ =>
          verify(api, times(1)).getCustomers(node.workspaceId, node.id)
          succeed
        }
      }

    }
  }

}

